package ru.kode.base.internship.products.data.mappers

import ru.kode.base.internship.products.data.AccountEntity
import ru.kode.base.internship.products.data.network.entity.account.Account
import ru.kode.base.internship.products.domain.AccountStatus
import ru.kode.base.internship.products.domain.Currency
import ru.kode.base.internship.products.domain.entity.AccountDomainEntity
import ru.kode.base.internship.products.domain.entity.CardDomainEntity

internal fun AccountDomainEntity.toAccountEntity(): AccountEntity {
  return AccountEntity(
    id = accountId.accountId.toLong(),
    balance = balance,
    currency = currency.name,
    number = number,
    status = status.name
  )
}

internal fun mapFromDBToDomainAccount(
  account: AccountEntity,
): AccountDomainEntity {
  return AccountDomainEntity(
    cards = emptyList(),
    number = account.number,
    status = AccountStatus.valueOf(account.status),
    balance = account.balance,
    currency = Currency.valueOf(account.currency),
    accountId = AccountDomainEntity.Id(account.id.toString())
  )
}
internal fun mapToDomainAccount(
  responseAcc: Account,
  domainCards: List<CardDomainEntity>,
): AccountDomainEntity {
  return AccountDomainEntity(
    cards = domainCards,
    number = responseAcc.number,
    status = AccountStatus.valueOf(responseAcc.status),
    balance = responseAcc.balance.toString(),
    currency = Currency.valueOf(responseAcc.currency),
    accountId = AccountDomainEntity.Id(responseAcc.accountId.toString())
  )
}
