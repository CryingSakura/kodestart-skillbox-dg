package ru.kode.base.internship.products.domain.repository

import kotlinx.coroutines.flow.MutableStateFlow
import ru.kode.base.internship.products.domain.entity.AccountDataEntity

interface AccountsRepository {
  val accountsFlow: MutableStateFlow<List<AccountDataEntity>>
  fun fetchAccounts()
}
