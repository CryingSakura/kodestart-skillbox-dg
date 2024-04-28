package ru.kode.base.internship.products.data.repository

import com.squareup.anvil.annotations.ContributesBinding
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import ru.kode.base.core.di.AppScope
import ru.kode.base.internship.products.data.MockGetData
import ru.kode.base.internship.products.domain.repository.AccountsRepository
import javax.inject.Inject

@ContributesBinding(AppScope::class)
class AccountsRepositoryImpl @Inject constructor() : AccountsRepository {
  override val accountsFlow = MutableStateFlow(MockGetData.getAccounts())

  override fun fetchAccounts() {
    accountsFlow.update { MockGetData.getAccounts() }
  }
}
