package ru.kode.base.internship.products.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.kode.base.internship.products.domain.entity.CardDomainEntity
interface CardRepository {
  fun rename(newName: String, id: CardDomainEntity.Id)
  suspend fun getCardById(id: CardDomainEntity.Id): CardDomainEntity?
  fun getAllCards(): Flow<List<CardDomainEntity>>
}
