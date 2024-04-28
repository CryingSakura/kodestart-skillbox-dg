package ru.kode.base.internship.products.domain.repository

import kotlinx.coroutines.flow.MutableStateFlow
import ru.kode.base.internship.products.domain.entity.CardDataEntity
interface CardRepository {
  val cardDetails: MutableStateFlow<CardDataEntity>
  fun cardDetails(id: CardDataEntity.Id)
}
