package ffinbank.myfreedom.uilibrary.values

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

var fontSize10 = 10.sp
    private set
var fontSize11 = 11.sp
    private set
var fontSize13 = 13.sp
    private set
var fontSize14 = 14.sp
    private set
var fontSize16 = 16.sp
    private set
var fontSize18 = 18.sp
    private set
var fontSize22 = 22.sp
    private set
var fontSize28 = 28.sp
    private set
var fontSize30 = 30.sp
    private set
var fontSize32 = 32.sp
    private set
var fontSize40 = 40.sp
    private set


@Composable
fun ConfigureFontSize() {
    val scale = with(LocalDensity.current) {
        fontScale
    }

    fontSize10 = (fontSize10.value / scale).sp
    fontSize11 = (fontSize11.value / scale).sp
    fontSize13 = (fontSize13.value / scale).sp
    fontSize14 = (fontSize14.value / scale).sp
    fontSize16 = (fontSize16.value / scale).sp
    fontSize18 = (fontSize18.value / scale).sp
    fontSize22 = (fontSize22.value / scale).sp
    fontSize28 = (fontSize28.value / scale).sp
    fontSize32 = (fontSize32.value / scale).sp
    fontSize40 = (fontSize40.value / scale).sp
}

@Composable
fun dpToSp(dp: Dp): TextUnit {
    val scale = with(LocalDensity.current) {
        fontScale
    }

    return (dp.value / scale).sp
}