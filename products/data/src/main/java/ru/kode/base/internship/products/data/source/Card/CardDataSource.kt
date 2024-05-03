package ru.kode.base.internship.products.data.source.Card

import kotlinx.coroutines.flow.Flow
import ru.kode.base.internship.products.data.CardEntity

interface CardDataSource {
  suspend fun insertCard(
    id: Int,
    accountId: Int,
    name: String,
    number: String,
    paymentSystem: String,
    status: String,
    cardType: String,
    expiredAt: String,
  )
  suspend fun insertCardObj(card: CardEntity)
  suspend fun getCardById(id: Int): CardEntity?
  fun getAllCards(): Flow<List<CardEntity>>

  fun getCardsById(id: Long): Flow<List<CardEntity>>
  suspend fun deleteCard(id: Int)
}
