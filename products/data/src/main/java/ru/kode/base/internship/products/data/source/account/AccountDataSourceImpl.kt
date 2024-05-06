package ru.kode.base.internship.products.data.source.account

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.squareup.anvil.annotations.ContributesBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import ru.kode.base.core.di.AppScope
import ru.kode.base.internship.products.data.AccountEntity
import ru.kode.base.internship.products.data.ProdDB
import javax.inject.Inject

@ContributesBinding(AppScope::class)
class AccountDataSourceImpl @Inject constructor(
  db: ProdDB,
) : AccountDataSource {
  private val queries = db.accountEntityQueries
  override fun getAllAccounts(): Flow<List<AccountEntity>> {
    return queries.getAllAccounts().asFlow().mapToList(Dispatchers.IO)
  }
  override suspend fun insertAccountObj(account: AccountEntity) {
    withContext(Dispatchers.IO) {
      queries.insertAccountObj(account)
    }
  }

}
