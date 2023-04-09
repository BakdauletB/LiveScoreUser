package ffinbank.utils.state

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

object UiStatusListener {

    val status: MutableState<UiStatus> = mutableStateOf(UiStatus.WAITING)

}