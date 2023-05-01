package com.example.livescoresdu.presentation.screens

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavController
import com.example.livescoresdu.presentation.Documents
import com.example.livescoresdu.presentation.screens.bundle.IdBundle
import com.example.livescoresdu.presentation.screens.match.RequestStoragePermission
import com.example.livescoresdu.presentation.viewmodels.FavorutiesViewModel
import com.example.livescoresdu.presentation.viewmodels.swiftFileMimeTypes
import com.example.livescoresdu.uilibrary.values.CustomButton
import com.example.livescoresdu.uilibrary.values.CustomButtonText
import com.example.livescoresdu.uilibrary.values.fromUri
import com.example.livescoreuser.presentation.viewmodels.WelcomViewModel
import com.example.livescoreuser.values.ItemList
import com.example.livescoreuser.values.SearchTextField
import ffinbank.myfreedom.uilibrary.values.Base50
import ffinbank.myfreedom.uilibrary.values.Base900
import ffinbank.myfreedom.uilibrary.values.Orange500
import ffinbank.myfreedom.uilibrary.values.fontSize16
import ffinbank.myfreedom.uilibrary.values.fontSize28
import ffinbank.myfreedom.uilibrary.values.semiBold
import ffinbank.myfreedom.uilibrary.values.spacing16
import ffinbank.myfreedom.uilibrary.values.spacing32
import org.koin.androidx.compose.getViewModel
import java.io.File

@Composable
fun FavorutiesScreen(
    navController: NavController,
    viewModel: WelcomViewModel = getViewModel()
){
    val lazyPages = viewModel.getFavs().collectAsState(emptyList())
    val searchText = remember { mutableStateOf(TextFieldValue("")) }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Base900
    ) {
        Column(modifier = Modifier.padding(start = spacing16, end = spacing16)) {
            Text(text = "Favorites",
                color = Base50,
                fontSize = fontSize28,
                fontWeight = FontWeight.SemiBold,
                style = semiBold
            )
            Spacer(modifier = Modifier.height(spacing32))
            SearchTextField(state = searchText)
            Spacer(modifier = Modifier.height(spacing16))
            LazyColumn {
                items(lazyPages.value.filter { image ->
                    val text = searchText.value.text
                    (text.isEmpty() || image.tournamentName.contains(text, ignoreCase = true))

                }) { image ->
                    val isFavorite = viewModel.isFavorite(image)
                    ItemList(image, isFavorite,
                        isFavCallback = { value ->
                            if (value) {
                                viewModel.addFavorite(image)
                            } else {
                                viewModel.removeFavorite(image)
                            }
                        }) {
//                    navController.navigate("detail/${image.tournamentId}")
                    }
                    Spacer(modifier = Modifier.height(spacing16))
                }
            }
        }
    }

}