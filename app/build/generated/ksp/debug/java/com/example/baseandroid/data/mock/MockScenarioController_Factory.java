package com.example.baseandroid.data.mock;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
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
public final class MockScenarioController_Factory implements Factory<MockScenarioController> {
  @Override
  public MockScenarioController get() {
    return newInstance();
  }

  public static MockScenarioController_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static MockScenarioController newInstance() {
    return new MockScenarioController();
  }

  private static final class InstanceHolder {
    static final MockScenarioController_Factory INSTANCE = new MockScenarioController_Factory();
  }
}
