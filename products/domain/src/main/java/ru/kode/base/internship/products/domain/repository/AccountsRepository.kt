package ru.kode.base.internship.products.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.kode.base.internship.products.domain.entity.AccountDomainEntity

interface AccountsRepository {
  suspend fun fetchAccounts()

  fun getAllAccounts(): Flow<List<AccountDomainEntity>>
}
