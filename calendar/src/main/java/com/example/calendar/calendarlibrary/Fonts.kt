package ffinbank.myfreedom.uilibrary.values

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.example.calendar.R
val fonts = FontFamily(
    Font(R  .font.inter_black, weight = FontWeight.Black),
    Font(R.font.inter_bold, weight = FontWeight.Bold),
    Font(R.font.inter_light, weight = FontWeight.Light),
    Font(R.font.inter_medium, weight = FontWeight.Medium),
    Font(R.font.inter_regular, weight = FontWeight.Normal),
    Font(R.font.inter_semi_bold, weight = FontWeight.SemiBold),
    Font(R.font.inter_thin, weight = FontWeight.Thin),
    Font(R.font.inter_extra_bold, weight = FontWeight.ExtraLight),
    Font(R.font.inter_extra_light, weight = FontWeight.ExtraLight, style = FontStyle.Italic),
)

val black = TextStyle(
    fontFamily = fonts,
    fontWeight = FontWeight.Black,
    fontSize = fontSize16
)
val bold = TextStyle(
    fontFamily = fonts,
    fontWeight = FontWeight.Bold,
    fontSize = fontSize16
)
val heavy = TextStyle(
    fontFamily = fonts,
    fontWeight = FontWeight.ExtraBold,
    fontSize = fontSize16
)
val light = TextStyle(
    fontFamily = fonts,
    fontWeight = FontWeight.Light,
    fontSize = fontSize16
)
val medium = TextStyle(
    fontFamily = fonts,
    fontWeight = FontWeight.Medium,
    fontSize = fontSize16
)
val regular = TextStyle(
    fontFamily = fonts,
    fontWeight = FontWeight.Normal,
    fontSize = fontSize16
)
val thin = TextStyle(
    fontFamily = fonts,
    fontWeight = FontWeight.Thin,
    fontSize = fontSize16
)
val ultraLight = TextStyle(
    fontFamily = fonts,
    fontWeight = FontWeight.ExtraLight,
    fontSize = fontSize16
)
val semiBold = TextStyle(
    fontFamily = fonts,
    fontWeight = FontWeight.SemiBold,
    fontSize = fontSize16
)