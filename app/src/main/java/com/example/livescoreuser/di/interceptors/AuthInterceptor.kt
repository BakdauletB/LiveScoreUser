package com.example.livescoreuser.di.interceptors


import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import okhttp3.*
import java.net.HttpURLConnection.HTTP_UNAUTHORIZED

class AuthInterceptor : Interceptor {

    private val mutext = Mutex()

    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
        val token =
            runBlocking {
//                if (!SharedPreferencesHelper.getAccessToken()
//                        .isNullOrEmpty() && chain.request().url.encodedPath != "/user/auth/token"
//                ) {
////                    SharedPreferencesHelper.getAccessToken()
//                } else {
//                    null
//                }
            }
        val res = chain.proceedWithToken(request, token)


        if (res.code != HTTP_UNAUTHORIZED || token == null) {
            return res
        }


        val newToken = runBlocking {
            mutext.withLock {
//                val maybeUpdatedToken = SharedPreferencesHelper.getAccessToken().orEmpty()
//                if (token == maybeUpdatedToken) {
//                    val r = Request.Builder()
//                        .url("${BuildConfig.BASE_URL}/user/auth/token")
//                        .header("Content-Type", "application/x-www-form-urlencoded")
//                        .post(
//                            FormBody.Builder()
//                                .addEncoded("refreshToken", SharedPreferencesHelper.getRefreshToken().orEmpty())
//                                .build()
//                        )
//                        .build()
//                    val result = chain.proceed(r)
//
//
//                    if (result.isSuccessful) {
//                        val jsonObject = JSONObject(result.body?.string().orEmpty())
//                        val accessToken = jsonObject["access_token"]
//                        val refreshToken = jsonObject["refresh_token"]
//                        SharedPreferencesHelper.saveAccessToken(accessToken = accessToken.toString())
//                        SharedPreferencesHelper.saveRefreshToken(token = refreshToken.toString())
//                        accessToken.toString()
//                    } else {
//                        UiStatusListener.status.value = UiStatus.LOGIN_ERROR
//                        ""
//                    }
//                } else {
//                    maybeUpdatedToken
//                }
            }
        }


        return chain.proceedWithToken(request, newToken)
    }


    private fun Interceptor.Chain.proceedWithToken(req: Request, token: Unit): Response =
        req.newBuilder()
            .apply {
                removeHeader("Authorization")
            }
            .apply {
//                if (token !== null) {
//                    addHeader("Authorization", "${SharedPreferencesHelper.getTokenType()} $token")
//                }
            }
            .build()
            .let(::proceed)
}


