package com.example.baseandroid.di;

import com.example.baseandroid.data.mock.MockScenarioController;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import okhttp3.OkHttpClient;

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
public final class NetworkModule_ProvideOkHttpClientFactory implements Factory<OkHttpClient> {
  private final Provider<MockScenarioController> mockScenarioControllerProvider;

  private NetworkModule_ProvideOkHttpClientFactory(
      Provider<MockScenarioController> mockScenarioControllerProvider) {
    this.mockScenarioControllerProvider = mockScenarioControllerProvider;
  }

  @Override
  public OkHttpClient get() {
    return provideOkHttpClient(mockScenarioControllerProvider.get());
  }

  public static NetworkModule_ProvideOkHttpClientFactory create(
      Provider<MockScenarioController> mockScenarioControllerProvider) {
    return new NetworkModule_ProvideOkHttpClientFactory(mockScenarioControllerProvider);
  }

  public static OkHttpClient provideOkHttpClient(MockScenarioController mockScenarioController) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideOkHttpClient(mockScenarioController));
  }
}
