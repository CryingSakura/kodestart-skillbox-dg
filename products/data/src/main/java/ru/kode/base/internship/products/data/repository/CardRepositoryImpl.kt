package ru.kode.base.internship.products.data.repository

import com.squareup.anvil.annotations.ContributesBinding
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import ru.kode.base.core.di.AppScope
import ru.kode.base.internship.products.data.MockGetData
import ru.kode.base.internship.products.domain.entity.CardDataEntity
import ru.kode.base.internship.products.domain.repository.CardRepository
import javax.inject.Inject

@ContributesBinding(AppScope::class)
class CardRepositoryImpl @Inject constructor() : CardRepository {
  override val cardDetails = MutableStateFlow(
    MockGetData.getCard()[1]
  )

  override fun cardDetails(id: CardDataEntity.Id) {
    cardDetails.update { MockGetData.getCard().find { it.cardId == id }!! }
  }
}
