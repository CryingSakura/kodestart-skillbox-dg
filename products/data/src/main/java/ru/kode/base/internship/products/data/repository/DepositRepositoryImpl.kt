package ru.kode.base.internship.products.data.repository

import com.squareup.anvil.annotations.ContributesBinding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.kode.base.core.di.AppScope
import ru.kode.base.internship.products.data.MockGetData
import ru.kode.base.internship.products.domain.entity.DepositDataEntity
import ru.kode.base.internship.products.domain.repository.DepositRepository
import javax.inject.Inject

@ContributesBinding(AppScope::class)
class DepositRepositoryImpl @Inject constructor(
  private val getData: MockGetData,
) : DepositRepository {
  override val depositsFlow: Flow<List<DepositDataEntity>>
    get() = flow { getData.getDeposits() }

  override fun term(id: DepositDataEntity.Id): Flow<DepositDataEntity> {
    return flow {
      getData.getDeposits().find { it.depositId == id }
    }
  }

}
