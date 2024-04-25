package ru.kode.base.internship.products.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.kode.base.internship.products.domain.entity.AccountDataEntity

interface AccountsRepository {
  val accountsFlow: Flow<List<AccountDataEntity>>
}
