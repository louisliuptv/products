package com.example.baseandroid.data.repository;

import com.example.baseandroid.data.remote.UserApi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import kotlinx.coroutines.CoroutineDispatcher;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("com.example.baseandroid.core.di.IoDispatcher")
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
public final class UserRepositoryImpl_Factory implements Factory<UserRepositoryImpl> {
  private final Provider<UserApi> apiProvider;

  private final Provider<CoroutineDispatcher> ioDispatcherProvider;

  private UserRepositoryImpl_Factory(Provider<UserApi> apiProvider,
      Provider<CoroutineDispatcher> ioDispatcherProvider) {
    this.apiProvider = apiProvider;
    this.ioDispatcherProvider = ioDispatcherProvider;
  }

  @Override
  public UserRepositoryImpl get() {
    return newInstance(apiProvider.get(), ioDispatcherProvider.get());
  }

  public static UserRepositoryImpl_Factory create(Provider<UserApi> apiProvider,
      Provider<CoroutineDispatcher> ioDispatcherProvider) {
    return new UserRepositoryImpl_Factory(apiProvider, ioDispatcherProvider);
  }

  public static UserRepositoryImpl newInstance(UserApi api, CoroutineDispatcher ioDispatcher) {
    return new UserRepositoryImpl(api, ioDispatcher);
  }
}
