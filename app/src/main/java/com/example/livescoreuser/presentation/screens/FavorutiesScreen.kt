package com.example.livescoresdu.presentation.screens

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.livescoresdu.presentation.Documents
import com.example.livescoresdu.presentation.screens.bundle.IdBundle
import com.example.livescoresdu.presentation.screens.match.RequestStoragePermission
import com.example.livescoresdu.presentation.viewmodels.FavorutiesViewModel
import com.example.livescoresdu.presentation.viewmodels.swiftFileMimeTypes
import com.example.livescoresdu.uilibrary.values.CustomButton
import com.example.livescoresdu.uilibrary.values.CustomButtonText
import com.example.livescoresdu.uilibrary.values.fromUri
import ffinbank.myfreedom.uilibrary.values.Base900
import ffinbank.myfreedom.uilibrary.values.Orange500
import ffinbank.myfreedom.uilibrary.values.spacing16
import ffinbank.myfreedom.uilibrary.values.spacing32
import org.koin.androidx.compose.getViewModel
import java.io.File

@Composable
fun FavorutiesScreen(
    navController: NavController,
    viewModel: FavorutiesViewModel = getViewModel()
){
    val onDocumentClick = remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current
    val documentPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetMultipleContents(),
        onResult = { result ->
            try {
                val files: MutableList<File> = mutableListOf()
                result.forEach { uri ->
                    val file = File("").fromUri(context = context, uri = uri)
                    if (file.readBytes().size <= 10_000_000 && files.size < viewModel.documentsMaxSize) {
                        files.add(file)
                    }
                }
                files.forEach {
                    if (viewModel.documents.size < viewModel.documentsMaxSize) {
                        viewModel.documents.add(it)
                    } else return@forEach
                }
            } catch (e: Exception) {
                Log.e("TAG", "SwiftTransferScreen: $e")
            }
            onDocumentClick.value = false
        }
    )


    Column(modifier = Modifier.background(color = Base900)) {
        Spacer(modifier = Modifier.height(spacing32))
                Documents(
            documents = viewModel.documents,
            onOpenBottomSheetClick = {
//                currentInfo.value =
//                    viewModel.documentInfo.parseToInfoModel(context = context)
//                openInfoBottomSheet.value = true
            },
            onAddDocumentClick = {
                //TODO Open documents
                onDocumentClick.value = true

            },
            maxSize = viewModel.documentsMaxSize,
            onDeleteDocumentClick = {
                viewModel.documents.remove(it)
                it.delete()
            }
        )
        Spacer(modifier = Modifier.height(spacing16))
        CustomButton(buttonColors = ButtonDefaults.buttonColors(backgroundColor = Orange500),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = spacing16, end = spacing16),
            content = {
                CustomButtonText(
                    text = "Upload",
                    color = Base900
                )
            }) {
            viewModel.uploadDocument()
            navController.popBackStack()
        }
    }
    if (onDocumentClick.value) {
        Log.e("TAG", "FavorutiesScreen: Baha", )
        RequestStoragePermission(
            onPermissionDenied = {},
            onPermissionGranted = {
                documentPickerLauncher.launch(swiftFileMimeTypes)
            }
        )
    }

}