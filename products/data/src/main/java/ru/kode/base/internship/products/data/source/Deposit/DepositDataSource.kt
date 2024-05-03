package ru.kode.base.internship.products.data.source.Deposit

import kotlinx.coroutines.flow.Flow
import ru.kode.base.internship.products.data.DepositEntity

interface DepositDataSource {
  suspend fun getDepositById(id: Long): DepositEntity?
  fun getAllDeposits(): Flow<List<DepositEntity>>

  suspend fun insertDepositObj(deposit: DepositEntity)

  suspend fun insertDeposit(
    id: Long,
    name: String,
    status: String,
    currency: String,
    balance: String,
    closeDate: String,
    rate: String,
  )

  suspend fun deleteDeposit(id: Int)
}
