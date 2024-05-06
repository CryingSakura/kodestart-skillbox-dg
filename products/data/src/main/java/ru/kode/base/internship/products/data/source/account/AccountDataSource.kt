package ru.kode.base.internship.products.data.source.account

import kotlinx.coroutines.flow.Flow
import ru.kode.base.internship.products.data.AccountEntity

interface AccountDataSource {
  fun getAllAccounts(): Flow<List<AccountEntity>>
  suspend fun insertAccountObj(account: AccountEntity)
}
