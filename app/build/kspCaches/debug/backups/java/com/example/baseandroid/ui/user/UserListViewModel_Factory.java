package com.example.baseandroid.ui.user;

import com.example.baseandroid.data.repository.UserRepository;
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
public final class UserListViewModel_Factory implements Factory<UserListViewModel> {
  private final Provider<UserRepository> repositoryProvider;

  private UserListViewModel_Factory(Provider<UserRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public UserListViewModel get() {
    return newInstance(repositoryProvider.get());
  }

  public static UserListViewModel_Factory create(Provider<UserRepository> repositoryProvider) {
    return new UserListViewModel_Factory(repositoryProvider);
  }

  public static UserListViewModel newInstance(UserRepository repository) {
    return new UserListViewModel(repository);
  }
}
