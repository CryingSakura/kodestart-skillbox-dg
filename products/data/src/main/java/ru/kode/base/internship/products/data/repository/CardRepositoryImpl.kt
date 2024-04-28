package ru.kode.base.internship.products.data.repository

import com.squareup.anvil.annotations.ContributesBinding
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import ru.kode.base.core.di.AppScope
import ru.kode.base.internship.products.data.MockGetData
import ru.kode.base.internship.products.domain.Money
import ru.kode.base.internship.products.domain.entity.AccountDataEntity
import ru.kode.base.internship.products.domain.entity.CardDataEntity
import ru.kode.base.internship.products.domain.repository.CardRepository
import javax.inject.Inject

@ContributesBinding(AppScope::class)
class CardRepositoryImpl @Inject constructor() : CardRepository {
  override val money = MutableStateFlow(
    Money(MockGetData.getAccounts()[1].balance, MockGetData.getAccounts()[1].currency)
  )
  override val card = MutableStateFlow(
    MockGetData.getCard()[1]
  )
  override fun cardDetails(id: CardDataEntity.Id) {
    card.update { MockGetData.getCard().find { it.cardId == id }!! }
  }
  override fun rename(newName: String, id: CardDataEntity.Id) {
    MockGetData.renameCard(id, newName)
  }
  override fun getMoney(id: AccountDataEntity.Id) {
    val account = MockGetData.getAccounts().find { it.accountId == id }
    money.update { Money(account!!.balance, account.currency) }
  }
}
