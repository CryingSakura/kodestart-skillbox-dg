package ru.kode.base.internship.ui.carddetails

import androidx.compose.runtime.Immutable
import ru.kode.base.internship.products.domain.CardStatus
import ru.kode.base.internship.products.domain.CardType
import ru.kode.base.internship.products.domain.Currency
import ru.kode.base.internship.products.domain.Money
import ru.kode.base.internship.products.domain.PaymentSystem
import ru.kode.base.internship.products.domain.entity.AccountDomainEntity
import ru.kode.base.internship.products.domain.entity.CardDomainEntity
import ru.kode.base.internship.ui.accountId1UI
import ru.kode.base.internship.ui.cardId1UI
import ru.kode.base.internship.ui.carddetails.states.ListState

@Immutable
data class CardDetailsViewState(
  val listState: ListState = ListState.Actions,
  val cardDetails: CardDomainEntity = CardDomainEntity(
    accountId = AccountDomainEntity.Id(accountId1UI),
    number = "1111222233334444",
    expiredAt = "2022-04-21T00:00:00Z",
    status = CardStatus.valueOf("ACTIVE"),
    name = "test1",
    cardType = CardType.valueOf("physical"),
    paymentSystem = PaymentSystem.valueOf("Visa"),
    cardId = CardDomainEntity.Id(cardId1UI)
  ),
  val money: Money = Money("0", Currency.RUB),
  val enterName: String = "",
  val showDialog: Boolean = false,
)
