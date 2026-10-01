package com.example.baseandroid.ui

import com.example.baseandroid.data.Base
import com.example.baseandroid.data.BaseRepository
import com.example.baseandroid.here.UiState1
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@OptIn(ExperimentalCoroutinesApi::class)
class BaseViewModelTest {

    private val dispatcher = StandardTestDispatcher()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule(dispatcher)

    @Test
    fun `starts in Loading, then shows the data`() = runTest(dispatcher.scheduler) {
        val viewModel = BaseViewModel()

        runCurrent()
        assertEquals(UiState1.Loading, viewModel.uiState.value)

        advanceUntilIdle()
        val state = viewModel.uiState.value
        assertTrue(state is UiState1.Success)
        assertEquals(4, (state as UiState1.Success).data.size)
    }

    @Test
    fun `a throwing repository becomes Error`() = runTest(dispatcher.scheduler) {
        val viewModel = BaseViewModel(FlakyRepository(fail = true))
        advanceUntilIdle()

        assertTrue(viewModel.uiState.value is UiState1.Error)
    }

    @Test
    fun `retry after a failure recovers`() = runTest(dispatcher.scheduler) {
        val repository = FlakyRepository(fail = true)
        val viewModel = BaseViewModel(repository)

        advanceUntilIdle()
        assertTrue(viewModel.uiState.value is UiState1.Error)

        repository.fail = false
        viewModel.load()
        advanceUntilIdle()

        assertTrue(viewModel.uiState.value is UiState1.Success)
    }
}

private class FlakyRepository(var fail: Boolean) : BaseRepository() {
    override suspend fun getBases(): List<Base> =
        if (fail) throw RuntimeException("boom") else super.getBases()
}

@OptIn(ExperimentalCoroutinesApi::class)
class MainDispatcherRule(
    private val dispatcher: TestDispatcher = StandardTestDispatcher(),
) : TestWatcher() {

    override fun starting(description: Description) = Dispatchers.setMain(dispatcher)

    override fun finished(description: Description) = Dispatchers.resetMain()
}
