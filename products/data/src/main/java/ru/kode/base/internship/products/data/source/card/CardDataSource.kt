package ru.kode.base.internship.products.data.source.card

import kotlinx.coroutines.flow.Flow
import ru.kode.base.internship.products.data.CardEntity

interface CardDataSource {
  suspend fun insertCardObj(card: CardEntity)
  suspend fun getCardById(id: Int): Flow<CardEntity?>
  fun getAllCards(): Flow<List<CardEntity>>

  fun getCardsById(id: Long): Flow<List<CardEntity>>
}
