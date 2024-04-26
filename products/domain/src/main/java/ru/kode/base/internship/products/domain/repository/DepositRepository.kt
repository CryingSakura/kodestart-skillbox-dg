package ru.kode.base.internship.products.domain.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import ru.kode.base.internship.products.domain.entity.DepositDataEntity

interface DepositRepository {
  val depositsFlow: MutableStateFlow<List<DepositDataEntity>>
  fun term(id: DepositDataEntity.Id): Flow<DepositDataEntity>
  fun fetchDeposits() {
  }
}
