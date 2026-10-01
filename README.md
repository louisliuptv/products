# Base Android — 20-minute live-coding scaffold

Matches the interview format: **no network, no Retrofit, no DI, no endpoint.** A single `:app`
module with Compose already configured, so the twenty minutes go on the ViewModel, the state and
the Compose UI.

```bash
./gradlew :app:installDebug        # run it
./gradlew :app:testDebugUnitTest   # 3 unit tests, no device needed
```

Needs JDK 17 and an Android SDK with API 36.

---

## The files

```
app/src/main/java/com/example/baseandroid/
├── MainActivity.kt          Activity + Material 3 theme. ~30 lines, touch once.
├── data/
│   └── BaseRepository.kt    the fake backend: one suspend fun, hardcoded list, one delay
└── ui/
    ├── BaseViewModel.kt     UiState (Loading | Success | Error) + ViewModel
    └── BaseScreen.kt        Route + stateless screen + one @Preview per state
app/src/test/java/com/example/baseandroid/ui/BaseViewModelTest.kt
```

Five files. There is no `Network`, no API interface, no DTO, no DI module — the email rules all
of that out, and none of it is needed to show the parts being evaluated.

`Base` is a placeholder shape (`id`, `title`, `subtitle`). Nothing else in the project depends on
what is inside it, so swapping in the shape they hand you is a one-class edit.

---

## The two-minute part

`data/BaseRepository.kt` is the whole backend:

```kotlin
open class BaseRepository {
    open suspend fun getBases(): List<Base> {
        delay(1500)                                  // makes Loading visible
        // throw RuntimeException("boom")            // -> Error state
        // return emptyList()                        // -> Empty state
        return listOf(Base(1, "Ada Lovelace", "ada@analytical.io"), /* ... */)
    }
}
```

`open` only so the test can override it. Uncomment a line to demo the other two states — no
rebuild-time config, no flags, no scenario switcher.

---

## The state contract

```kotlin
sealed interface UiState<out T> {
    data object Loading : UiState<Nothing>
    data class Success<T>(val data: T) : UiState<T>
    data class Error(val message: String) : UiState<Nothing>
}
```

Sealed, so the `when` in the UI is exhaustive and the compiler catches a forgotten branch. Two
choices worth saying out loud when you write it:

- **No `Empty` state.** Empty is `Success(emptyList())`. One fewer state to keep consistent.
- **`Error` carries a String, not a Throwable.** The exception → copy translation happens once,
  in the ViewModel. The screen never sees an exception type.

`load()` sets `Loading` first on every call, so Retry visibly re-enters the loading state.

---

## Order of work (suggested)

| Minutes | Do | Where |
|---|---|---|
| 0–2 | Write the model + fake repository | `data/BaseRepository.kt` |
| 2–4 | `sealed interface UiState` | `ui/BaseViewModel.kt` |
| 4–9 | ViewModel: `StateFlow`, `init { load() }`, `load()` with try/catch | `ui/BaseViewModel.kt` |
| 9–11 | Wire the Activity: `setContent { MaterialTheme { BaseRoute() } }` | `MainActivity.kt` |
| 11–18 | The screen: `when (uiState)` → spinner / error+retry / empty / `LazyColumn` | `ui/BaseScreen.kt` |
| 18–20 | Run it, show all four states, walk the follow-ups | — |

Do the Compose part last so there is always something running. Keep `BaseRoute` stateful and
everything below it stateless — that is what makes the four `@Preview`s work, and previews are a
much faster feedback loop than a Gradle install while someone is watching.

---

## No DI, on purpose

`BaseViewModel(repository: BaseRepository = BaseRepository())` — because every constructor
parameter has a default, Kotlin emits a no-arg constructor, and Compose's `viewModel()` creates
the class with no factory. That is the entire wiring.

The seam is still there: the ViewModel takes the repository as a constructor parameter, so a test
(or a real repository later) can substitute one. Say that if they ask about testability.

---

## Tests

`BaseViewModelTest` — 3 tests, no mocking library:

- starts in `Loading`, ends in `Success`
- a throwing repository becomes `Error`
- retry after a failure recovers

`runTest` skips the repository's `delay(1500)` instantly, so the real fake is used rather than a
stub. `FlakyRepository` is a 3-line subclass for the failure paths. `MainDispatcherRule` swaps
`Dispatchers.Main` for a `StandardTestDispatcher` so `viewModelScope` runs on the test scheduler.

---

## Two things worth knowing

- **Cancellation is not an error.** The `catch` rethrows `CancellationException` before the
  generic `catch (error: Exception)`. Swallowing it makes a cancelled screen show an error.
- **`"${code()}"`** — inside a Kotlin string template `"$code()"` parses as `${code}()` and will
  not compile. Braces are required. (Only relevant if a `code` method appears later.)

## Toolchain

Gradle 8.14.4 (wrapper included) · AGP 8.13.2 · Kotlin 2.2.10 · Compose BOM 2024.12.01 ·
compileSdk 36 / minSdk 24. Dependencies are Compose + lifecycle + coroutines, nothing else.

## Likely follow-ups

Pull-to-refresh (`PullToRefreshBox` in `BaseScreen`) · keep the list while refreshing (add
`isRefreshing` to `Success`) · search (`combine` a query `StateFlow`) · pull-to-refresh + retry with
backoff · navigation to a detail screen · `SavedStateHandle` for process death.
