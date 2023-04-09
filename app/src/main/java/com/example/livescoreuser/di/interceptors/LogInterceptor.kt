package domain.retrofit.interceptors

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okio.Buffer
import okio.IOException

class LogInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        Log.e("TAG", "intercept: ${bodyToString(chain.request())}")
        return chain.proceed(chain.request())
    }


    private fun bodyToString(request: Request): String? {
        return try {
            val copy: Request = request.newBuilder().build()
            val buffer = Buffer()
            copy.body?.writeTo(buffer)
            buffer.readUtf8()
        } catch (e: IOException) {
            "did not work"
        }
    }
}