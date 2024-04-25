package ru.kode.base.internship.products.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.kode.base.internship.products.domain.entity.CardDataEntity
interface CardRepository {
  fun cardDetails(id: CardDataEntity.Id): Flow<CardDataEntity>
}
