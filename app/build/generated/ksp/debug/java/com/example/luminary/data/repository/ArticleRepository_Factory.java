package com.example.luminary.data.repository;

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
    "deprecation"
})
public final class ArticleRepository_Factory implements Factory<ArticleRepository> {
  @Override
  public ArticleRepository get() {
    return newInstance();
  }

  public static ArticleRepository_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static ArticleRepository newInstance() {
    return new ArticleRepository();
  }

  private static final class InstanceHolder {
    private static final ArticleRepository_Factory INSTANCE = new ArticleRepository_Factory();
  }
}
