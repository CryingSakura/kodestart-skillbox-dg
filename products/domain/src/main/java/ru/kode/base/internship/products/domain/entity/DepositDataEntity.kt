package ru.kode.base.internship.products.domain.entity

import ru.kode.base.internship.products.domain.Currency
import ru.kode.base.internship.products.domain.DepositStatus

data class DepositDataEntity(
  val depositId: DepositDataEntity.Id,
  val balance: String,
  val status: DepositStatus,
  val rate: String,
  val closeDate: String,
  val currency: Currency,
  val name: String,
) {
  @JvmInline
  value class Id(val depositId: String)
}
