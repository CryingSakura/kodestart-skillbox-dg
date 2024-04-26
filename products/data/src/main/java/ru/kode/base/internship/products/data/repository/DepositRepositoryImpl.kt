package ru.kode.base.internship.products.data.repository

import com.squareup.anvil.annotations.ContributesBinding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import ru.kode.base.core.di.AppScope
import ru.kode.base.internship.products.data.MockGetData
import ru.kode.base.internship.products.domain.entity.DepositDataEntity
import ru.kode.base.internship.products.domain.repository.DepositRepository
import javax.inject.Inject

@ContributesBinding(AppScope::class)
class DepositRepositoryImpl @Inject constructor() : DepositRepository {
  override val depositsFlow = MutableStateFlow(MockGetData.getDeposits())

  override fun fetchDeposits() {
    depositsFlow.update { MockGetData.getDeposits() }
  }

  override fun term(id: DepositDataEntity.Id): Flow<DepositDataEntity> {
    return flow {
      MockGetData.getDeposits().find { it.depositId == id }
    }
  }
}
