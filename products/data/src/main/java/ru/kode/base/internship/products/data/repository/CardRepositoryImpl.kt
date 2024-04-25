package ru.kode.base.internship.products.data.repository

import com.squareup.anvil.annotations.ContributesBinding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.kode.base.core.di.AppScope
import ru.kode.base.internship.products.data.MockGetData
import ru.kode.base.internship.products.domain.entity.CardDataEntity
import ru.kode.base.internship.products.domain.repository.CardRepository
import javax.inject.Inject

@ContributesBinding(AppScope::class)
class CardRepositoryImpl @Inject constructor(): CardRepository {
  override fun cardDetails(id: CardDataEntity.Id): Flow<CardDataEntity> {
    return flow {
      MockGetData.getCard().find { it.cardId == id }
    }
  }
}
