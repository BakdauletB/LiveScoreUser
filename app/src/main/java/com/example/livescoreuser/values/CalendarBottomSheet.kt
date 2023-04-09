package com.example.livescoresdu.uilibrary.values

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.livescoresdu.data.model.convertDate
import com.example.livescoresdu.dateutils.SelectDateOneColourCalendarHelper
import com.fvalela.calendarjetpackcompose.sample.ui.CalendarJetpackComposeSampleTheme
import com.fvalela.calendarjetpackcompose.sample.viewmodel.CalendarViewModel
import com.kizitonwose.calendar.core.daysOfWeek
import ffinbank.myfreedom.uilibrary.values.*
import org.koin.androidx.compose.getViewModel
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CalendarBottomSheet(
    openCalendarBottomSheet: Boolean,
    monthsBefore: Long = 100,
    monthsAfter: Long = 100,
    isCalendarUntilToday: Boolean = false,
    onOpenCalendarBottomSheet: (Boolean) -> Unit,
    onBottomSheetHide: (Date?,) -> Unit,
    viewModel: CalendarViewModel = getViewModel()

) {
    val scope = rememberCoroutineScope()
    var convertedStartDate: LocalDate? by rememberSaveable { mutableStateOf(null) }

    val calculatedHeight = LocalConfiguration.current.screenHeightDp.dp -
            WindowInsets.statusBars.asPaddingValues().calculateBottomPadding()

    val calendarBottomSheetState =
        rememberModalBottomSheetState(
            initialValue = ModalBottomSheetValue.Hidden,
            skipHalfExpanded = true,
            confirmStateChange = {
                onOpenCalendarBottomSheet(it != ModalBottomSheetValue.Hidden)
                return@rememberModalBottomSheetState true
            }
        )

    ModalBottomSheetLayout(
        sheetState = calendarBottomSheetState,
        scrimColor = Base900.copy(alpha = 0.6f),
        sheetContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = calculatedHeight)
            ) {
                CalendarJetpackComposeSampleTheme(darkTheme = false) {
                    Surface(
                        color = MaterialTheme.colors.background,
                        modifier = Modifier.fillMaxHeight()
                    ) {
                        Column(Modifier.fillMaxSize()) {
                            SelectDateOneColourCalendarHelper(viewModel = viewModel)
//                            DatesList(viewModel)
                        }
                    }
                }




//                Scaffold(
//                    backgroundColor = Base100,
//                    topBar = {
//                        CalendarTopAppBar(
//                            selectedStartDate = convertedStartDate,
//                        ) {
//                            convertedStartDate = null
//                        }
//                    },
//                    bottomBar = {
//                        BottomAppBar(
//                            selectedStartDate = convertedStartDate,
//                            onBottomSheetHide = { chosenSelectedDate,  ->
//                                convertedStartDate = chosenSelectedDate
//
//                                if (chosenSelectedDate == null) {
//                                    onBottomSheetHide(null, )
//                                } else {
//                                    if (chosenSelectedDate != null) {
//                                        onBottomSheetHide(
//                                            Date.from(
//                                                chosenSelectedDate.atStartOfDay(ZoneId.systemDefault())
//                                                    ?.toInstant()
//                                            ),
////                                            if (convertedEndDate == null) {
////                                                Date.from(
////                                                    chosenSelectedDate.atStartOfDay(ZoneId.systemDefault())
////                                                        ?.toInstant()
////                                                )
////                                            } else {
////                                                Date.from(
////                                                    chosenSelectedEndDate?.atStartOfDay(ZoneId.systemDefault())
////                                                        ?.toInstant()
////                                                )
////                                            }
//                                        )
//                                    }
//                                }
//                            }
//                        )
//                    }
//                ) {
//                    Box(
//                        contentAlignment = Alignment.Center
//                    ) {
//                        MultiSelectCalendar(
//                            monthsBefore = monthsBefore,
//                            monthsAfter = monthsAfter,
//                            isCalendarUntilToday = isCalendarUntilToday,
//                            bottomPadding = it.calculateBottomPadding(),
//                            selectedStartDate = convertedStartDate,
////                            selectedEndDate = convertedEndDate,
//                            onClickSelectedStartDate = { startDate ->
//                                convertedStartDate = startDate
//                            },
////                            onClickSelectedEndDate = { endDate ->
////                                convertedEndDate = endDate
////                            }
//                        )
//                    }
//                }
            }
        },
        sheetShape = RoundedCornerShape(topStart = cornerRadius16, topEnd = cornerRadius16),
        sheetBackgroundColor = Base100
    ) {

    }

    if (openCalendarBottomSheet) {
        LaunchedEffect(key1 = scope) {
            calendarBottomSheetState.show()
        }
    } else {
        LaunchedEffect(key1 = scope) {
            calendarBottomSheetState.hide()
        }
    }
}
//@RequiresApi(Build.VERSION_CODES.O)
//@OptIn(ExperimentalMaterialApi::class)
//@Composable
//fun CalendarSingleSelectBottomSheet(
//    openCalendarBottomSheet: Boolean,
//    monthsBefore: Long = 100,
//    monthsAfter: Long = 100,
//    isCalendarUntilToday: Boolean = false,
//    isCalendarAfterToday: Boolean = false,
//    selectedStartDate: LocalDate?,
//    onOpenCalendarBottomSheet: (Boolean) -> Unit,
//    onBottomSheetHide: (Date?, LocalDate?) -> Unit
//) {
//    val scope = rememberCoroutineScope()
//    var convertedStartDate: LocalDate? by rememberSaveable { mutableStateOf(null) }
//    LaunchedEffect(key1 = selectedStartDate ){
//        convertedStartDate = selectedStartDate
//    }
////    var convertedEndDate: LocalDate? by rememberSaveable { mutableStateOf(null) }
//
//    val calculatedHeight = LocalConfiguration.current.screenHeightDp.dp -
//            WindowInsets.statusBars.asPaddingValues().calculateBottomPadding()
//
//    val calendarBottomSheetState =
//        rememberModalBottomSheetState(
//            initialValue = ModalBottomSheetValue.Hidden,
//            skipHalfExpanded = true,
//            confirmStateChange = {
//                onOpenCalendarBottomSheet(it != ModalBottomSheetValue.Hidden)
//                return@rememberModalBottomSheetState true
//            }
//        )
//
//    ModalBottomSheetLayout(
//        sheetState = calendarBottomSheetState,
//        scrimColor = Base900.copy(alpha = 0.6f),
//        sheetContent = {
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .heightIn(max = calculatedHeight)
//            ) {
//                Scaffold(
//                    backgroundColor = Base100,
//                    topBar = {
//                        CalendarTopAppBar(
//                            selectedStartDate = convertedStartDate,
//                            selectedEndDate = null
//                        ) {
//                            convertedStartDate = null
//                        }
//                    },
//                    bottomBar = {
//                        BottomAppBar(
//                            selectedStartDate = convertedStartDate,
//                            selectedEndDate = null,
//                            onBottomSheetHide = { chosenSelectedDate, chosenSelectedEndDate ->
//                                convertedStartDate = chosenSelectedDate
//
//                                if (chosenSelectedDate == null && chosenSelectedEndDate == null) {
//                                    onBottomSheetHide(null, null)
//                                } else {
//                                    if (chosenSelectedDate != null) {
//                                        onBottomSheetHide(
//                                            Date.from(
//                                                chosenSelectedDate.atStartOfDay(ZoneId.systemDefault())
//                                                    ?.toInstant()
//                                            ),
//                                            chosenSelectedDate
//                                        )
//
//                                    }
//                                }
//                            }
//                        )
//                    }
//                ) {
//                    Box(
//                        contentAlignment = Alignment.Center
//                    ) {
//                        SingleSelectCalendar(
//                            monthsBefore = monthsBefore,
//                            monthsAfter = monthsAfter,
//                            isCalendarUntilToday = isCalendarUntilToday,
//                            isCalendarAfterToday = isCalendarAfterToday,
//                            bottomPadding = it.calculateBottomPadding(),
//                            selectedDate = convertedStartDate,
//                            onClickSelectedStartDate = { startDate ->
//                                convertedStartDate = startDate
//                            }
//                        )
//                    }
//                }
//            }
//        },
//        sheetShape = RoundedCornerShape(topStart = cornerRadius16, topEnd = cornerRadius16),
//        sheetBackgroundColor = Base100
//    ) {
//
//    }
//
//    if (openCalendarBottomSheet) {
//        LaunchedEffect(key1 = scope) {
//            calendarBottomSheetState.show()
//        }
//    } else {
//        LaunchedEffect(key1 = scope) {
//            calendarBottomSheetState.hide()
//        }
//    }
//}
@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun DaysOfWeek(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(
                horizontal = spacing32
            )
            .fillMaxWidth()
            .background(
                color = Base50
            ),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        for (day in daysOfWeek()) {
            Day(
                name = day.getDisplayName(
                    TextStyle.SHORT,
                    Locale.getDefault()
                ).uppercase()
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BottomAppBar(
    modifier: Modifier = Modifier,
    selectedStartDate: LocalDate?,
    onBottomSheetHide: (
        selectedDate: LocalDate?,
//        selectedEndDate: LocalDate?
    ) -> Unit
) {
    val convertedStartDate =
        convertDate("$selectedStartDate", fromFormat = "yyyy-MM-dd", toFormat = "dd MMM ’yy")

    AnimatedVisibility(
        visible = selectedStartDate != null,
        enter = fadeIn() + slideInVertically(
            animationSpec = tween(200),
            initialOffsetY = { fullHeight -> fullHeight }),
        exit = slideOutVertically(
            animationSpec = tween(200),
            targetOffsetY = { fullHeight -> fullHeight })
    ) {
        CustomButtonCalendar(
            buttonColors = ButtonDefaults.buttonColors(Green500),
            modifier = Modifier
                .padding(spacing16)
                .fillMaxWidth()
                .then(modifier),
            content = {
                Text(
                    text = if (convertedStartDate.isEmpty() ) {
                        ""
                    } else {
                        "${"Select"} " + convertedStartDate
                    },
                    fontSize = fontSize18,
                    color = Base50,
                    fontWeight = FontWeight.SemiBold,
                    style = semiBold
                )
            }
        ) {
            onBottomSheetHide(
                selectedStartDate,
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarTopAppBar(
    selectedStartDate: LocalDate?,
    onResetClicked: () -> Unit
) {
    Column(
        modifier = Modifier
//            .advancedShadow()
            .background(color = Base50),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .padding(
                    horizontal = spacing16,
                    vertical = spacing16
                )
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Выберите период",
                fontSize = fontSize16,
                fontWeight = FontWeight.SemiBold,
                lineHeight = lineHeight22,
                style = semiBold,
                color = Base900
            )


            Text(
                text = "Отмена",
                color = Base800,
                fontSize = fontSize13,
                fontWeight = FontWeight.Medium,
                style = medium,
                modifier = Modifier
                    .clip(RoundedCornerShape(cornerRadius16))
                    .background(color = Base200)
                    .clickable {
                        if (selectedStartDate != null) {
                            onResetClicked()
                        }
                    }
                    .padding(
                        horizontal = spacing16,
                        vertical = spacing8
                    )
            )
        }

        DaysOfWeek()
    }

    Spacer(modifier = Modifier.height(spacing8))
}

@Composable
private fun Day(name: String) {
    DayContainer {
        Text(
            modifier = Modifier.wrapContentSize(Alignment.Center),
            text = name,
            fontSize = fontSize13,
            fontWeight = FontWeight.Medium,
            lineHeight = lineHeight18,
            style = medium,
            color = Base400,
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun DayContainer(
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    onClick: () -> Unit = { },
    onClickEnabled: Boolean = true,
    backgroundColor: Color = Color.Transparent,
    content: @Composable () -> Unit
) {
    val stateDescriptionLabel = if(selected) "Selected" else "Not selected"

    Surface(
        onClick = onClick,
        modifier = modifier
            .size(
                width = CELL_SIZE,
                height = CELL_SIZE
            )
            .then(
                if (onClickEnabled) {
                    modifier.semantics {
                        stateDescription = stateDescriptionLabel
                    }
                } else {
                    modifier.clearAndSetSemantics { }
                }
            ),
        enabled = onClickEnabled,
        color = backgroundColor
    ) {
        content()
    }
}
@Composable
fun CustomButtonCalendar(
    modifier: Modifier = Modifier,
    buttonColors: ButtonColors = ButtonDefaults.buttonColors(
        backgroundColor = Green500,
        contentColor = Base50,
        disabledBackgroundColor = Green300,
        disabledContentColor = Base50
    ),
    enabled: Boolean = true,
    minClickSec: Long = 500,
    height: Dp = 52.dp,
    maxHeight: Dp = 52.dp,
    cornerRadius: Dp = cornerRadius16,
    elevation: ButtonElevation? = ButtonDefaults.elevation(),
    content: @Composable RowScope.() -> Unit,
    onButtonClicked: () -> Unit
) {
    var lastClickSec = 0L
    Button(
        onClick = {
            if (System.currentTimeMillis() - lastClickSec > minClickSec) {
                lastClickSec = System.currentTimeMillis()
                onButtonClicked()
            }
        },
        modifier = modifier
            .clip(RoundedCornerShape(cornerRadius))
            .heightIn(min = height, max = maxHeight),
        colors = buttonColors,
        enabled = enabled,
        content = content,
        elevation = elevation
    )
}

private val CELL_SIZE = 40.dp
