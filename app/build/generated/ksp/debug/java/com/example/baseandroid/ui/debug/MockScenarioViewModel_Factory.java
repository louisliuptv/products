package com.example.baseandroid.ui.debug;

import com.example.baseandroid.data.mock.MockScenarioController;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class MockScenarioViewModel_Factory implements Factory<MockScenarioViewModel> {
  private final Provider<MockScenarioController> controllerProvider;

  private MockScenarioViewModel_Factory(Provider<MockScenarioController> controllerProvider) {
    this.controllerProvider = controllerProvider;
  }

  @Override
  public MockScenarioViewModel get() {
    return newInstance(controllerProvider.get());
  }

  public static MockScenarioViewModel_Factory create(
      Provider<MockScenarioController> controllerProvider) {
    return new MockScenarioViewModel_Factory(controllerProvider);
  }

  public static MockScenarioViewModel newInstance(MockScenarioController controller) {
    return new MockScenarioViewModel(controller);
  }
}
