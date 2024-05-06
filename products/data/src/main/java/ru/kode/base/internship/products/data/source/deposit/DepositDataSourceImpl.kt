package ru.kode.base.internship.products.data.source.deposit

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.squareup.anvil.annotations.ContributesBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import ru.kode.base.core.di.AppScope
import ru.kode.base.internship.products.data.DepositEntity
import ru.kode.base.internship.products.data.ProdDB
import javax.inject.Inject

@ContributesBinding(AppScope::class)
class DepositDataSourceImpl @Inject constructor(
  db: ProdDB,
) : DepositDataSource {
  private val queries = db.depositEntityQueries
  override fun getAllDeposits(): Flow<List<DepositEntity>> {
    return queries.getAllDeposits().asFlow().mapToList(Dispatchers.IO)
  }

  override suspend fun insertDepositObj(deposit: DepositEntity) {
    withContext(Dispatchers.IO) {
      queries.insertDepositObj(deposit)
    }
  }

  override suspend fun insertDeposit(
    id: Long,
    name: String,
    status: String,
    currency: String,
    balance: String,
    closeDate: String,
    rate: String,
  ) {
    withContext(Dispatchers.IO) {
      queries.insertDeposit(id, name, status, currency, balance, closeDate, rate)
    }
  }
}
