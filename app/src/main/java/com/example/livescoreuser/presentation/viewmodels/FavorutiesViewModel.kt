package com.example.livescoresdu.presentation.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.livescoresdu.data.MatchRepository
import com.example.livescoresdu.uilibrary.values.Status
import ffinbank.utils.state.UiStatus
import kotlinx.coroutines.launch
import java.io.File

const val swiftFileMimeTypes = "*/*"
class FavorutiesViewModel(private val repository: MatchRepository): ViewModel() {
    val documents: SnapshotStateList<File> = mutableStateListOf()
    private val documentUrls: MutableList<String> = mutableListOf()
    var document: String  = ""
    val documentsMaxSize = 1

    val transferStatus: MutableState<UiStatus> = mutableStateOf(UiStatus.WAITING)

    fun uploadDocument() {
        viewModelScope.launch {
            documents.forEach { file ->
                repository.uploadFile(file = file).collect {
                    when (it.status) {
                        Status.SUCCESS -> {
                            val result = it.data
                            document = result.toString()
//                            val result = it.data as FileResponseModel
//                            documentUrls.add(result.url)
//                            if (documents.size == documentUrls.size) {
//                                transfer()
//                            }
                        }
                        Status.LOADING -> {
                            transferStatus.value = UiStatus.LOADING
                        }
                        Status.ERROR -> {
                            transferStatus.value = UiStatus.ERROR
                        }
                    }
                }
            }
        }
    }
}