package ru.kode.base.internship.products.data.source.card

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOne
import com.squareup.anvil.annotations.ContributesBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.withContext
import ru.kode.base.core.di.AppScope
import ru.kode.base.internship.products.data.CardEntity
import ru.kode.base.internship.products.data.ProdDB
import javax.inject.Inject

@ContributesBinding(AppScope::class)
class CardDataSourceImpl @Inject constructor(
  db: ProdDB,
) : CardDataSource {
  private val queries = db.cardEntityQueries
  override suspend fun insertCardObj(card: CardEntity) {
    withContext(Dispatchers.IO) {
      queries.insertCardObj(card)
    }
  }
  override suspend fun getCardById(id: Int): Flow<CardEntity?> {
    return withContext(Dispatchers.IO) {
      queries.getCardById(id.toLong()).asFlow().mapToOne(Dispatchers.IO)
    }
  }
  override fun getAllCards(): Flow<List<CardEntity>> {
    return queries.getAllCards().asFlow().mapToList(Dispatchers.IO)
  }
  override fun getCardsById(id: Long): Flow<List<CardEntity>> {
    return queries.getCardsById(id).asFlow().mapToList(Dispatchers.IO)
  }
}
