package ru.kode.base.internship.products.data.repository

import com.squareup.anvil.annotations.ContributesBinding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import ru.kode.base.core.di.AppScope
import ru.kode.base.internship.products.data.CardEntity
import ru.kode.base.internship.products.data.mappers.mapFromDBToDomainAccount
import ru.kode.base.internship.products.data.mappers.mapToDomainCard
import ru.kode.base.internship.products.data.mappers.toAccountEntity
import ru.kode.base.internship.products.data.mappers.toCardDm
import ru.kode.base.internship.products.data.mappers.toCardEntity
import ru.kode.base.internship.products.data.network.ProdApi
import ru.kode.base.internship.products.data.source.card.CardDataSource
import ru.kode.base.internship.products.domain.entity.CardDomainEntity
import ru.kode.base.internship.products.domain.repository.CardRepository
import javax.inject.Inject

@ContributesBinding(AppScope::class)
class CardRepositoryImpl @Inject constructor(
  private val cardsDataSource: CardDataSource,
  private val api: ProdApi,
) : CardRepository {
  override suspend fun getCardById(id: CardDomainEntity.Id): Flow<CardDomainEntity?> {
    return cardsDataSource.getCardById(id.cardId.toInt()).map { it?.toCardDm() }
  }
  override fun getAllCards(): Flow<List<CardDomainEntity>> {
    return cardsDataSource.getAllCards().map { cardsEntity -> cardsEntity.map { it.toCardDm() } }
  }
}
