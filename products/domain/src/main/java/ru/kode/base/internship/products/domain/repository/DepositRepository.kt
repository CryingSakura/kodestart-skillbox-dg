package ru.kode.base.internship.products.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.kode.base.internship.products.domain.entity.DepositDomainEntity

interface DepositRepository {
  suspend fun fetchDeposits()

  fun getAllDeposits(): Flow<List<DepositDomainEntity>>
}
