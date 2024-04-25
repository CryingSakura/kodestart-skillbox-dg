package ru.kode.base.internship.products.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.kode.base.internship.products.domain.entity.DepositDataEntity

interface DepositRepository {
  val depositsFlow: Flow<List<DepositDataEntity>>
  fun term(id: DepositDataEntity.Id): Flow<DepositDataEntity>
}
