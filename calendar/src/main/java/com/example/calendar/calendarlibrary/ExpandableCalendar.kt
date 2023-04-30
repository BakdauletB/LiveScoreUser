package com.mabn.calendarlibrary

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import java.time.YearMonth
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mabn.calendarlibrary.core.CalendarIntent
import com.mabn.calendarlibrary.core.Period
import com.mabn.calendarlibrary.utils.getWeekStartDate
import com.mabn.calendarlibrary.component.InlineCalendar
import com.mabn.calendarlibrary.component.MonthText
import com.mabn.calendarlibrary.component.MonthViewCalendar
import com.mabn.calendarlibrary.component.ToggleExpandCalendarButton
import com.mabn.calendarlibrary.core.CalendarTheme
import com.mabn.calendarlibrary.core.calendarDefaultTheme
import com.mabn.calendarlibrary.utils.yearMonth
import ffinbank.myfreedom.uilibrary.values.Base200
import ffinbank.myfreedom.uilibrary.values.cornerRadius12
import ffinbank.myfreedom.uilibrary.values.cornerRadius16
import ffinbank.myfreedom.uilibrary.values.fontSize10
import ffinbank.myfreedom.uilibrary.values.semiBold
import ffinbank.myfreedom.uilibrary.values.spacing6
import java.time.LocalDate

@Composable
fun ExpandableCalendar(
    onDayClick: (LocalDate) -> Unit,
    onLiveClick: () -> Unit,
    theme: CalendarTheme = calendarDefaultTheme
) {
    val viewModel: CalendarViewModel = viewModel()
    val loadedDates = viewModel.visibleDates.collectAsState()
    val selectedDate = viewModel.selectedDate.collectAsState()
    val calendarExpanded = viewModel.calendarExpanded.collectAsState()
    val currentMonth = viewModel.currentMonth.collectAsState()
    ExpandableCalendar(
        loadedDates = loadedDates.value,
        selectedDate = selectedDate.value,
        currentMonth = currentMonth.value,
        onIntent = viewModel::onIntent,
        calendarExpanded = calendarExpanded.value,
        theme = theme,
        onDayClick = onDayClick,
        onLiveClick = onLiveClick
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun ExpandableCalendar(
    loadedDates: Array<List<LocalDate>>,
    selectedDate: LocalDate,
    currentMonth: YearMonth,
    onIntent: (CalendarIntent) -> Unit,
    calendarExpanded: Boolean,
    theme: CalendarTheme,
    onDayClick: (LocalDate) -> Unit,
    onLiveClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .animateContentSize()
            .background(theme.backgroundColor)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(bottom = 10.dp)
                .fillMaxWidth()
                .background(theme.headerBackgroundColor)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .background(
                        color = Base200,
                        shape = RoundedCornerShape(cornerRadius16)
                    )
                    .clip(RoundedCornerShape(cornerRadius12))
                    .padding(spacing6),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "LIVE",
                    style = semiBold,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = fontSize10,
                    modifier = Modifier.clickable {
                        onLiveClick()
                    }
                )
            }
            Spacer(Modifier.weight(1f))
            MonthText(selectedMonth = currentMonth, theme = theme)
            Spacer(Modifier.weight(1f))
            ToggleExpandCalendarButton(
                isExpanded = calendarExpanded,
                expand = { onIntent(CalendarIntent.ExpandCalendar) },
                collapse = { onIntent(CalendarIntent.CollapseCalendar) },
                color = theme.headerTextColor
            )
        }
        if (calendarExpanded) {
            MonthViewCalendar(
                loadedDates,
                selectedDate,
                theme = theme,
                currentMonth = currentMonth,
                loadDatesForMonth = { yearMonth ->
                    onIntent(
                        CalendarIntent.LoadNextDates(
                            yearMonth.atDay(
                                1
                            ), period = Period.MONTH
                        )
                    )
                },
                onDayClick = {
                    onIntent(CalendarIntent.SelectDate(it))
                    onDayClick(it)
                }
            )
        } else {
            InlineCalendar(
                loadedDates,
                selectedDate,
                theme = theme,
                loadNextWeek = { nextWeekDate -> onIntent(CalendarIntent.LoadNextDates(nextWeekDate)) },
                loadPrevWeek = { endWeekDate ->
                    onIntent(
                        CalendarIntent.LoadNextDates(
                            endWeekDate.minusDays(1).getWeekStartDate()
                        )
                    )
                },
                onDayClick = {
                    onIntent(CalendarIntent.SelectDate(it))
                    onDayClick(it)
                }
            )
        }
    }
}








