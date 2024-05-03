package ru.kode.base.internship.products.data.repository

import com.squareup.anvil.annotations.ContributesBinding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import ru.kode.base.core.di.AppScope
import ru.kode.base.internship.products.data.mappers.mapFromDBToDomainAccount
import ru.kode.base.internship.products.data.mappers.mapToDomainAccount
import ru.kode.base.internship.products.data.mappers.mapToDomainCard
import ru.kode.base.internship.products.data.mappers.toAccountEntity
import ru.kode.base.internship.products.data.mappers.toCardDm
import ru.kode.base.internship.products.data.mappers.toCardEntity
import ru.kode.base.internship.products.data.network.ProdApi
import ru.kode.base.internship.products.data.source.Account.AccountDataSource
import ru.kode.base.internship.products.data.source.Card.CardDataSource
import ru.kode.base.internship.products.domain.entity.AccountDomainEntity
import ru.kode.base.internship.products.domain.entity.CardDomainEntity
import ru.kode.base.internship.products.domain.repository.AccountsRepository
import javax.inject.Inject

@ContributesBinding(AppScope::class)
class AccountsRepositoryImpl @Inject constructor(
  private val api: ProdApi,
  private val accountsDataSource: AccountDataSource,
  private val cardsDataSource: CardDataSource,
) : AccountsRepository {
  override suspend fun fetchAccounts() {
    val responseAcc = api.requestAccounts()
    val accountsForDomain = mutableListOf<AccountDomainEntity>()
    val allCardsDomain = mutableListOf<CardDomainEntity>()
    responseAcc.accounts.forEach { account ->
      val cardsForDomain = mutableListOf<CardDomainEntity>()
      val cards = account.cards
      cards.forEach { card ->
        val cardById = api.requestCard(cardId = card.card_id.toInt())
        allCardsDomain.add(mapToDomainCard(card, cardById))
        cardsForDomain.add(mapToDomainCard(card, cardById))
      }
      accountsForDomain.add(mapToDomainAccount(account, cardsForDomain))
    }
    val accountsSM = accountsForDomain.map { it.toAccountEntity() }
    val cardsSM = allCardsDomain.map { it.toCardEntity() }
    accountsSM.forEach { account ->
      accountsDataSource.insertAccountObj(account)
    }
    cardsSM.forEach { card ->
      cardsDataSource.insertCardObj(card)
    }
  }

  override fun getAllAccounts(): Flow<List<AccountDomainEntity>> {
    val accounts = accountsDataSource.getAllAccounts().map {
        accountsEntity ->
      accountsEntity.map { mapFromDBToDomainAccount(it) }
    }
    val cards = cardsDataSource.getAllCards().map { cardsEntity ->
      cardsEntity.map { it.toCardDm() }
    }
    val combineAcc = accounts.combine(flow = cards) { accounts, cards ->
      accounts.map { account ->
        account.copy(cards = cards.filter { it.accountId == account.accountId })
      }
    }
    return combineAcc
  }
}
