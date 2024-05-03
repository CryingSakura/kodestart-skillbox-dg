package ru.kode.base.internship.products.data.network

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import ru.kode.base.internship.products.data.network.entity.account.AccountsResponse
import ru.kode.base.internship.products.data.network.entity.card.ResponseCardParamsById
import ru.kode.base.internship.products.data.network.entity.deposit.DepositResponseById
import ru.kode.base.internship.products.data.network.entity.deposit.DepositsResponse

interface ProdApi {
  @GET("api/core/account/list")
  suspend fun requestAccounts(
    @Header("Prefer") code: String = "code=200, example=android",
  ): AccountsResponse
  @GET("api/core/card/{cardId}")
  suspend fun requestCard(
    @Path("cardId") cardId: Int,
    @Header("Prefer") code: String = "code=200, example=android-$cardId",
  ): ResponseCardParamsById
  @GET("api/core/deposit/list")
  suspend fun requestDeposits(
    @Header("Prefer") code: String = "code=200, example=android",
  ): DepositsResponse
  @GET("api/core/deposit/{depositId}")
  suspend fun getDeposit(
    @Path("depositId") depositId: Int,
    @Header("Prefer") code: String = "code=200, example=android-$depositId",
  ): DepositResponseById
}
