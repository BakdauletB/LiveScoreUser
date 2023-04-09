package ffinbank.myfreedom.uilibrary.values

import androidx.compose.runtime.Composable
import java.util.*

@Composable
fun ChangeLocale() {
    val locale = Locale("RU")
    Locale.setDefault(locale)
//    val config: Configuration = context.resources.getConfiguration()
//    config.setLocale(locale)
//    context.resources.updateConfiguration(config, context.resources.getDisplayMetrics())
}