package ru.kode.base.internship.products.data.DI

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import app.cash.sqldelight.logs.LogSqliteDriver
import com.squareup.anvil.annotations.ContributesTo
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import ru.kode.base.core.annotations.ApplicationContext
import ru.kode.base.core.di.AppScope
import ru.kode.base.core.di.SingleIn
import ru.kode.base.internship.products.data.ProdDB
import ru.kode.base.internship.products.data.network.ProdApi
import timber.log.Timber
import javax.inject.Named

@Module
@ContributesTo(AppScope::class)
object ProdDataModule {
  @Provides
  @SingleIn(AppScope::class)
  fun provideProdApi(
    @Named("products") retrofit: Retrofit,
  ): ProdApi {
    return retrofit.create(ProdApi::class.java)
  }

  @Provides
  @SingleIn(AppScope::class)
  @Named("products")
  fun provideProdSqlDriver(
    @ApplicationContext
    context: Context,
  ): SqlDriver {
    return AndroidSqliteDriver(
      ProdDB.Schema,
      context,
      name = "products.db"
    ).let { driver ->
      if (ENABLE_LOGGING) {
        LogSqliteDriver(driver) { log ->
          Timber.e(log)
        }
      } else {
        driver
      }
    }
  }

  @Provides
  @SingleIn(AppScope::class)
  fun provideProdDB(
    @Named("products")driver: SqlDriver
  ): ProdDB {
    return ProdDB(driver)
  }
}

private const val ENABLE_LOGGING = false
