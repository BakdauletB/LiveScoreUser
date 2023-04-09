package com.example.livescoresdu.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha.medium
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ffinbank.myfreedom.uilibrary.values.*

@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@Composable
fun SelectBottomSheet(
    title: String,
    items: List<String>,
    selectedItem: String,
    bottomSheetState: ModalBottomSheetState,
    onBottomSheetHide: () -> Unit,
    onItemClick: (String) -> Unit,
    withSearch: Boolean = false,
    searchHint: String = ""
){

    val searchText = remember {
        mutableStateOf("")
    }
    val keyboardController = LocalSoftwareKeyboardController.current

    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        scrimColor = Base900.copy(alpha = 0.6f),
        sheetContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(screenHeight - 48.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .background(Base100)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                            .padding(top = spacing16, start = spacing16, end = spacing16)
                    ) {
                        Text(
                            text = title,
                            color = Base900,
                            fontSize = fontSize16,
                            modifier = Modifier
                                .weight(1f),
                            fontWeight = FontWeight.Medium,
                        )
                        Image(
                            modifier = Modifier
                                .clip(CircleShape)
                                .clickable {
                                    onBottomSheetHide()
                                },
                            painter = painterResource(id = com.example.livescoresdu.R.drawable.ic_circle_close_transparent),
                            contentDescription = "",
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = spacing16)
                ) {

                    GroupedByAlphabetList(
                        list = items,
                        selected = selectedItem,
                        onClick = onItemClick,
                        filterText = searchText.value
                    )
                }
            }
        }, sheetShape = RoundedCornerShape(topStart = cornerRadius16, topEnd = cornerRadius16),
        sheetBackgroundColor = Base100
    ) {

    }

}