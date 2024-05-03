package ru.kode.base.internship.products.data.mappers

import ru.kode.base.internship.products.data.DepositEntity
import ru.kode.base.internship.products.domain.Currency
import ru.kode.base.internship.products.domain.DepositStatus
import ru.kode.base.internship.products.domain.entity.DepositDomainEntity

internal fun DepositEntity.toDepositDm(): DepositDomainEntity {
  return DepositDomainEntity(
    depositId = DepositDomainEntity.Id(depositId = id.toString()),
    balance = balance,
    status = DepositStatus.valueOf(status),
    rate = rate,
    closeDate = closeDate,
    currency = Currency.valueOf(currency),
    name = name
  )
}

internal fun DepositDomainEntity.toDepositEntity(): DepositEntity {
  return DepositEntity(
    depositId.depositId.toLong(),
    name,
    status.name,
    currency.name,
    balance,
    closeDate,
    rate
  )
}
