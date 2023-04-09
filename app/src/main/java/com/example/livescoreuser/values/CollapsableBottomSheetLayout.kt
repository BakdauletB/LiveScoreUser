package com.example.livescoresdu.uilibrary.values

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import ffinbank.myfreedom.uilibrary.values.cornerRadius16

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CollapsableBottomSheetLayout(
    state: BottomSheetScaffoldState,
    sheetHeightAnimated: Dp,
    sheetBackgroundColor: Color = MaterialTheme.colors.surface,
    content: @Composable () -> Unit,
    toolbar: @Composable () -> Unit
) {
    BottomSheetScaffold(
        sheetShape = RoundedCornerShape(
            topStart = cornerRadius16,
            topEnd = cornerRadius16
        ),
        scaffoldState = state,
        sheetPeekHeight = sheetHeightAnimated,
        sheetBackgroundColor = sheetBackgroundColor,
        sheetContent = {
            content()
        }) {
        toolbar()
    }
}
