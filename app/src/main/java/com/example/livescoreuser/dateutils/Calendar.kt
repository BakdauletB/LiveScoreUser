package com.example.livescoresdu.dateutils

import androidx.compose.foundation.layout.*
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.chargemap.compose.numberpicker.NumberPicker
import com.example.livescoresdu.presentation.screens.bundle.PostBundle
import com.example.livescoresdu.presentation.viewmodels.MatchesViewModel
import com.example.livescoresdu.uilibrary.values.CustomButtonCalendar
import com.fvalela.calendarjetpackcompose.model.CalendarDate
import com.fvalela.calendarjetpackcompose.sample.ui.CalendarJetpackComposeSampleTheme
import com.fvalela.calendarjetpackcompose.sample.ui.blue200
import com.fvalela.calendarjetpackcompose.sample.viewmodel.CalendarViewModel
import ffinbank.myfreedom.uilibrary.values.*
import org.koin.androidx.compose.getViewModel

@Composable
fun Calendar(
    navController: NavController,
    onBackClick: () -> Unit,
    viewModel: CalendarViewModel = getViewModel(),
    matchesViewModel: MatchesViewModel = getViewModel()
){
//    val viewModel = ViewModelProvider(L).get(CalendarViewModel::class.java)

    var pickerValueHours = remember { mutableStateOf(0) }
    var pickerValueMinutes = remember { mutableStateOf(0) }

    CalendarJetpackComposeSampleTheme(darkTheme = false) {
                Surface(
                    color = MaterialTheme.colors.background,
                    modifier = Modifier.fillMaxHeight()
                ) {
                    Column(Modifier.fillMaxSize()) {
                        SelectDateOneColourCalendarHelper(viewModel = viewModel)
//                        DatesList(viewModel)
                        Row(modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center) {
                            NumberPicker(value = pickerValueHours.value,
                                dividersColor = Color.Transparent,
                                modifier = Modifier.height((50.dp + spacing12) * 3),
                                textStyle = TextStyle(fontSize = 30.sp),
                                range = matchesViewModel.hours,
                                onValueChange = {
                                    pickerValueHours.value = it
                                    matchesViewModel.selectHour.value = if(it < 10) "0${it}" else pickerValueHours.value.toString()
                                })
                            Spacer(modifier = Modifier.width(spacing4))
                            Box(modifier = Modifier.height(50.dp), contentAlignment = Alignment.BottomCenter) {
                                Text(
                                    text = "ч",
                                    fontSize = fontSize11,
                                    fontWeight = FontWeight.Normal,
                                    style = regular,
                                    color = Base400
                                )
                            }
                            Spacer(modifier = Modifier.width(spacing12))
                            Text(
                                text = ":",
                                fontSize = fontSize30,
                                fontWeight = FontWeight.Normal,
                                style = regular,
                                color = Base900
                            )
                            Spacer(modifier = Modifier.width(spacing24))
                            NumberPicker(
                                value = pickerValueMinutes.value,
                                range = matchesViewModel.minutes,
                                dividersColor = Color.Transparent,
                                modifier = Modifier.height((50.dp + spacing12) * 3),
                                textStyle = TextStyle(fontSize = 30.sp),
                                onValueChange = {
                                    pickerValueMinutes.value = it
                                    matchesViewModel.selectMinutes.value = if(it < 10) "0${it}" else pickerValueMinutes.value.toString()
                                }
                            )
                            Spacer(modifier = Modifier.width(spacing4))
                            Box(modifier = Modifier.height(50.dp), contentAlignment = Alignment.BottomCenter) {
                                Text(
                                    text = "мин",
                                    fontSize = fontSize11,
                                    fontWeight = FontWeight.Normal,
                                    style = regular,
                                    color = Base400
                                )
                            }


                        }
                        CustomButtonCalendar(
                            buttonColors = ButtonDefaults.buttonColors(Orange500),
                            modifier = Modifier
                                .padding(spacing16)
                                .fillMaxWidth()
                                .then(Modifier),
                            content = {
                                Text(
                                    text = "Create",
                                    fontSize = fontSize18,
                                    color = Base50,
                                    fontWeight = FontWeight.SemiBold,
                                    style = semiBold
                                )
                            }
                        ) {
                             val date = "${matchesViewModel.selectedDate.value} ${matchesViewModel.selectHour.value}:${matchesViewModel.selectMinutes.value}"
//                            matchesViewModel.postGame(
//                                dateTime = date,
//                                groupId = PostBundle.selectedGroup,
//                                team2Id = PostBundle.selectedAway,
//                                team1Id = PostBundle.selectedHome
//                            )
                            navController.popBackStack()


                        }
                        DatesList(viewModel = viewModel)
                    }
                }
            }




}

@Composable
fun SelectDateOneColourCalendarHelper(viewModel: CalendarViewModel) {
    val month: Int by viewModel.selectedMonth.observeAsState(initial = 0)
    val year: Int by viewModel.selectedYear.observeAsState(initial = 0)
    val selectedDates: Set<SelectedDate> by viewModel.selectedDates.observeAsState(initial = mutableSetOf())

    CalendarJetpackCompose(
        year = year,
        month = month,
        selectedDates = convertSelectedDatesToCalendarDates(dates = selectedDates),
        onDayPressed = viewModel::updateSelectedDate,
        onNavigateMonthPressed = viewModel::updateSelectedMonth,
        canNavigateMonths = true,
    )
}

private fun convertSelectedDatesToCalendarDates(dates: Set<SelectedDate>): Set<CalendarDate> {
    val convertedDates: MutableSet<CalendarDate> = mutableSetOf()

    dates.forEach {
        convertedDates.add(CalendarDate(it.dateInMilli, blue200, TextStyle.Default))
    }

    return convertedDates
}