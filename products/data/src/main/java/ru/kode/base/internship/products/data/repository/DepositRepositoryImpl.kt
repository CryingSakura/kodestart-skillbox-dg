package ru.kode.base.internship.products.data.repository

import com.squareup.anvil.annotations.ContributesBinding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.kode.base.core.di.AppScope
import ru.kode.base.internship.products.data.mappers.toDepositDm
import ru.kode.base.internship.products.data.mappers.toDepositEntity
import ru.kode.base.internship.products.data.network.ProdApi
import ru.kode.base.internship.products.data.network.entity.deposit.Deposit
import ru.kode.base.internship.products.data.network.entity.deposit.DepositResponseById
import ru.kode.base.internship.products.data.source.deposit.DepositDataSource
import ru.kode.base.internship.products.domain.Currency
import ru.kode.base.internship.products.domain.DepositStatus
import ru.kode.base.internship.products.domain.entity.DepositDomainEntity
import ru.kode.base.internship.products.domain.repository.DepositRepository
import javax.inject.Inject

@ContributesBinding(AppScope::class)
class DepositRepositoryImpl @Inject constructor(
  private val api: ProdApi,
  private val depositDataSource: DepositDataSource,
) : DepositRepository {
  override suspend fun fetchDeposits() {
    val depositsResponse = api.requestDeposits()
    val depositsDomain = mutableListOf<DepositDomainEntity>()
    depositsResponse.deposits.forEach { deposit ->
      val id = deposit.depositId
      val depById = api.getDeposit(depositId = id)
      depositsDomain.add(mapToDomainDeposit(depResponse = deposit, depById = depById))
    }
    val depositsSM = depositsDomain.map { it.toDepositEntity() }
    depositsSM.forEach { deposit ->
      depositDataSource.insertDepositObj(deposit)
    }
  }
  override fun getAllDeposits(): Flow<List<DepositDomainEntity>> {
    return depositDataSource.getAllDeposits().map { depositsSM -> depositsSM.map { it.toDepositDm() } }
  }
}
private fun mapToDomainDeposit(
  depResponse: Deposit,
  depById: DepositResponseById,
): DepositDomainEntity {
  return DepositDomainEntity(
    depositId = DepositDomainEntity.Id(depResponse.depositId.toString()),
    balance = depResponse.balance.toString(),
    status = DepositStatus.valueOf(depResponse.status),
    rate = depById.rate.toString(),
    closeDate = depById.closeDate,
    currency = Currency.valueOf(depResponse.currency),
    name = depResponse.name
  )
}
