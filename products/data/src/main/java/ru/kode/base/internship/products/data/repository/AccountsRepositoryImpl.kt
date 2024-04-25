package ru.kode.base.internship.products.data.repository

import com.squareup.anvil.annotations.ContributesBinding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.kode.base.core.di.AppScope
import ru.kode.base.internship.products.data.MockGetData
import ru.kode.base.internship.products.domain.entity.AccountDataEntity
import ru.kode.base.internship.products.domain.repository.AccountsRepository
import javax.inject.Inject

@ContributesBinding(AppScope::class)
class AccountsRepositoryImpl @Inject constructor(
  private val getData: MockGetData,
) : AccountsRepository {
  override val accountsFlow: Flow<List<AccountDataEntity>>
    get() = flow { getData.getAccounts() }


}
