package com.example.livescoresdu.uilibrary.values

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kizitonwose.calendar.compose.VerticalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.*
import ffinbank.myfreedom.uilibrary.values.*
import java.time.*
import java.time.format.TextStyle
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SingleSelectCalendar(
    bottomPadding: Dp,
    monthsBefore: Long = 100,
    monthsAfter: Long = 100,
    isCalendarUntilToday: Boolean = false,
    isCalendarAfterToday: Boolean = false,
    selectedDate: LocalDate?,
    onClickSelectedStartDate: (selectedDate: LocalDate) -> Unit,
) {
    val currentMonth = remember { YearMonth.now() }
    val startMonth = remember { currentMonth.minusMonths(monthsBefore) }
    val endMonth = remember { currentMonth.plusMonths(monthsAfter) }
    val daysOfWeek = remember { daysOfWeek(firstDayOfWeek = DayOfWeek.MONDAY) }

//    var selectedDate by remember { mutableStateOf<LocalDate?>(null) }


    val state = rememberCalendarState(
        startMonth = startMonth,
        endMonth = endMonth,
        firstVisibleMonth = currentMonth,
        firstDayOfWeek = daysOfWeek.first()
    )
    VerticalCalendar(
        contentPadding = PaddingValues(
            start = spacing16,
            end = spacing16,
            top = spacing16,
            bottom = bottomPadding
        ),
        state = state,
        dayContent = {
            if (it.position == DayPosition.MonthDate) {

                Day(
                    day = it,
                    isCalendarUntilToday = isCalendarUntilToday,
                    isCalendarAfterToday = isCalendarAfterToday,
                    isSelected = selectedDate == it.date,
                    onClick = { day ->
                        onClickSelectedStartDate(day.date)

                    }
                )
            }
        },
        monthHeader = {
            val monthAsString = it.yearMonth.month.getDisplayName(
                TextStyle.FULL_STANDALONE,
                Locale.getDefault()
            ).replaceFirstChar { char ->
                char.titlecase(Locale.getDefault())
            }

            val monthPrefix = it.yearMonth.year.toString()

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Base50,
                        shape = RoundedCornerShape(
                            topStart = spacing12,
                            topEnd = spacing12
                        )
                    )
                    .padding(spacing16)
            ) {
                Text(
                    text = "$monthAsString $monthPrefix",
                    fontSize = fontSize16,
                    fontWeight = FontWeight.SemiBold,
                    style = semiBold,
                    color = Base700
                )
            }
        },
        monthBody = { month, container ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = spacing8)
                    .background(
                        color = Base50,
                        shape = RoundedCornerShape(
                            bottomStart = cornerRadius12,
                            bottomEnd = cornerRadius12
                        )
                    )
                    .padding(
                        start = spacing16,
                        end = spacing16,
                        bottom = spacing8
                    )
            ) {
                container()
            }
        }
    )
//    VerticalCalendar(
//        state = state,
//        dayContent = {
//            Day(
//                day = it,
//                isSelected = selectedDate == it.date,
//                onClick = { day ->
//                    selectedDate = if (selectedDate == day.date) null else day.date
//                }
//            )
//        },
//        monthHeader = {
//            Text(
//                text = it.yearMonth.month.getDisplayName(
//                    TextStyle.FULL_STANDALONE,
//                    Locale(SharedPreferencesHelper.getLocale().orEmpty())
//                )
//            )
//        }
//    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MultiSelectCalendar(
    bottomPadding: Dp,
    monthsBefore: Long = 100,
    monthsAfter: Long = 100,
    isCalendarUntilToday: Boolean = false,
    selectedStartDate: LocalDate?,
    onClickSelectedStartDate: (selectedDate: LocalDate) -> Unit,
) {
    val currentMonth = remember { YearMonth.now() }
    val startMonth = remember { currentMonth.minusMonths(monthsBefore) }
    val endMonth = remember { currentMonth.plusMonths(monthsAfter) }
    val daysOfWeek = remember { daysOfWeek(firstDayOfWeek = DayOfWeek.MONDAY) }

    val state = rememberCalendarState(
        startMonth = startMonth,
        endMonth = endMonth,
        firstVisibleMonth = currentMonth,
        firstDayOfWeek = daysOfWeek.first()
    )

    VerticalCalendar(
        contentPadding = PaddingValues(
            start = spacing16,
            end = spacing16,
            top = spacing16,
            bottom = bottomPadding
        ),
        state = state,
        dayContent = {
            if (it.position == DayPosition.MonthDate) {
                val isMiddle =
                    (selectedStartDate != null )
//                            && selectedEndDate != null) &&
//                            ((it.date > selectedStartDate && it.date < selectedEndDate) ||
//                                    (it.date < selectedStartDate && it.date > selectedEndDate))

//                val lastSelected = selectedEndDate != null

                Day(
                    day = it,
                    isCalendarUntilToday = isCalendarUntilToday,
                    isSelected = selectedStartDate == it.date, //|| selectedEndDate == it.date,
                    isMiddle = isMiddle,
//                    lastSelected = lastSelected,
                    lastBefore = (selectedStartDate != null),
//                            &&
//                            (selectedEndDate != null) &&
//                            (selectedEndDate < selectedStartDate),
                    isFirst = (selectedStartDate != null) &&
//                            (selectedEndDate != null) &&
                            (selectedStartDate == it.date),
                    onClick = { day ->
                        if (
//                            ((selectedEndDate == null) &&
                                    (selectedStartDate == null)
                            //||
//                            (selectedEndDate != null)
                        ) {
                            onClickSelectedStartDate(day.date)
                        }

                        if (
                            selectedStartDate != null &&
//                            selectedEndDate == null &&
                            selectedStartDate != day.date
                        ) {
                            if (selectedStartDate > day.date) {
                                onClickSelectedStartDate(day.date)
//                                onClickSelectedEndDate(selectedStartDate)
                            } else {
//                                onClickSelectedEndDate(day.date)
                            }
                        }
                    }
                )
            }
        },
        monthHeader = {
            val monthAsString = it.yearMonth.month.getDisplayName(
                TextStyle.FULL_STANDALONE,
                Locale.getDefault()
            ).replaceFirstChar { char ->
                char.titlecase(Locale.getDefault())
            }

            val monthPrefix = it.yearMonth.year.toString()

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Base50,
                        shape = RoundedCornerShape(
                            topStart = spacing12,
                            topEnd = spacing12
                        )
                    )
                    .padding(spacing16)
            ) {
                Text(
                    text = "$monthAsString $monthPrefix",
                    fontSize = fontSize16,
                    fontWeight = FontWeight.SemiBold,
                    style = semiBold,
                    color = Base700
                )
            }
        },
        monthBody = { month, container ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = spacing8)
                    .background(
                        color = Base50,
                        shape = RoundedCornerShape(
                            bottomStart = cornerRadius12,
                            bottomEnd = cornerRadius12
                        )
                    )
                    .padding(
                        start = spacing16,
                        end = spacing16,
                        bottom = spacing8
                    )
            ) {
                container()
            }
        }
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Day(
    day: CalendarDay,
    isCalendarUntilToday: Boolean = false,
    isCalendarAfterToday: Boolean = false,
    isSelected: Boolean,
    isFirst: Boolean = false,
    lastSelected: Boolean = false,
    isMiddle: Boolean = false,
    lastBefore: Boolean = false,
    onClick: (CalendarDay) -> Unit
) {
    val innerPadding =
        animateDpAsState(targetValue = 3.dp)
    Box(
        modifier = Modifier
            .padding(bottom = spacing8)
            .aspectRatio(1f)
            .clip(
                if (isMiddle) {
                    RoundedCornerShape(0.dp)
                } else if (isSelected && lastSelected) RoundedCornerShape(
                    topStartPercent = if (lastBefore) {
                        if (isFirst) 0 else 50
                    } else if (isFirst) 50 else 0,
                    bottomStartPercent = if (lastBefore) {
                        if (isFirst) 0 else 50
                    } else if (isFirst) 50 else 0,
                    topEndPercent = if (lastBefore) {
                        if (isFirst) 50 else 0
                    } else if (isFirst) 0 else 50,
                    bottomEndPercent = if (lastBefore) {
                        if (isFirst) 50 else 0
                    } else if (isFirst) 0 else 50
                ) else CircleShape
            )
            .background(
                color = if (isMiddle || (isSelected && lastSelected)) {
                    Base200
                } else if (isSelected && !lastSelected) {
                    Green500
                } else Color.Transparent
            )
            .padding(innerPadding.value)
            .background(
                color = if (isSelected && lastSelected) Green500 else Color.Transparent,
                shape = CircleShape
            )
            .click(
                enabled = if (isCalendarUntilToday) {
                    day.date <= LocalDate.now()
                } else if (isCalendarAfterToday){
                    day.date > LocalDate.now()
                } else {
                    day.position == DayPosition.MonthDate
                },
                onClick = { onClick(day) }
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = day.date.dayOfMonth.toString(),
            style = semiBold,
            fontWeight = FontWeight.SemiBold,
            lineHeight = lineHeight24,
            fontSize = fontSize18,
            color = if (isSelected) {
                Base50
            } else if (isCalendarUntilToday && day.date > LocalDate.now()) {
                Base500
            }else if (isCalendarAfterToday && day.date <= LocalDate.now()) {
                Base500
            } else if (day.date.dayOfWeek.value < 6 || day.position == DayPosition.MonthDate) {
                Base800
            } else Base500
        )
    }
}
