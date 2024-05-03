package ru.kode.base.internship.products.data.repository

import com.squareup.anvil.annotations.ContributesBinding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.kode.base.core.di.AppScope
import ru.kode.base.internship.products.data.mappers.toCardDm
import ru.kode.base.internship.products.data.network.ProdApi
import ru.kode.base.internship.products.data.source.Card.CardDataSource
import ru.kode.base.internship.products.domain.entity.CardDomainEntity
import ru.kode.base.internship.products.domain.repository.CardRepository
import javax.inject.Inject

@ContributesBinding(AppScope::class)
class CardRepositoryImpl @Inject constructor(
  private val cardsDataSource: CardDataSource,
  private val api: ProdApi,
) : CardRepository {
  override fun rename(newName: String, id: CardDomainEntity.Id) {
    TODO()
  }

  override suspend fun getCardById(id: CardDomainEntity.Id): CardDomainEntity? {
    return cardsDataSource.getCardById(id.cardId.toInt())?.toCardDm()
  }

  override fun getAllCards(): Flow<List<CardDomainEntity>> {
    return cardsDataSource.getAllCards().map { cardsEntity -> cardsEntity.map { it.toCardDm() } }
  }
}
