package ru.kode.base.internship.products.domain.repository

import kotlinx.coroutines.flow.MutableStateFlow
import ru.kode.base.internship.products.domain.Money
import ru.kode.base.internship.products.domain.entity.AccountDataEntity
import ru.kode.base.internship.products.domain.entity.CardDataEntity
interface CardRepository {
  val money: MutableStateFlow<Money>
  val card: MutableStateFlow<CardDataEntity>
  fun cardDetails(id: CardDataEntity.Id)

  fun rename(newName: String, id: CardDataEntity.Id)
  fun getMoney(id: AccountDataEntity.Id)
}
