package ru.kode.base.internship.products.data.source.Account

import kotlinx.coroutines.flow.Flow
import ru.kode.base.internship.products.data.AccountEntity

interface AccountDataSource {
  suspend fun getAccountById(accountId: Int): AccountEntity?
  fun getAllAccounts(): Flow<List<AccountEntity>>

  suspend fun insertAccount(
    id: Int,
    balance: String,
    currency: String,
    number: String,
    status: String,
  )
  suspend fun insertAccountObj(account: AccountEntity)
  suspend fun deleteAccount(id: Int)
}
