package ru.kode.base.internship.products.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.kode.base.internship.products.domain.entity.CardDomainEntity
interface CardRepository {
  suspend fun getCardById(id: CardDomainEntity.Id): Flow<CardDomainEntity?>
  fun getAllCards(): Flow<List<CardDomainEntity>>
}
