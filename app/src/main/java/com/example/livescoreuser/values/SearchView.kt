package com.example.livescoreuser.values

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.livescoresdu.uilibrary.values.click
import com.example.livescoreuser.R
import ffinbank.myfreedom.uilibrary.values.Base200
import ffinbank.myfreedom.uilibrary.values.Base500
import ffinbank.myfreedom.uilibrary.values.Base900
import ffinbank.myfreedom.uilibrary.values.Green500
import ffinbank.myfreedom.uilibrary.values.Orange500
import ffinbank.myfreedom.uilibrary.values.cornerRadius12
import ffinbank.myfreedom.uilibrary.values.fontSize16
import ffinbank.myfreedom.uilibrary.values.regular

@Composable
fun SearchView(state: MutableState<TextFieldValue>) {
    TextField(
        value = state.value,
        onValueChange = { value ->
            state.value = value
        },
        modifier = Modifier
            .fillMaxWidth(),
        textStyle = TextStyle(color = Color.White, fontSize = 18.sp),
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = "",
                modifier = Modifier
                    .padding(15.dp)
                    .size(24.dp)
            )
        },
        trailingIcon = {
            if (state.value != TextFieldValue("")) {
                IconButton(
                    onClick = {
                        state.value =
                            TextFieldValue("") // Remove text from TextField when you press the 'X' icon
                    }
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(15.dp)
                            .size(24.dp)
                    )
                }
            }
        },
        singleLine = true,
        shape = RectangleShape,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.White,
            cursorColor = Color.White,
            leadingIconColor = Color.White,
            trailingIconColor = Color.White,
            backgroundColor = Orange500,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}
@Composable
fun SearchTextField(
//    text: String,
    state: MutableState<TextFieldValue>,
//    onSearchTextChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Base200,
    hint: String = stringResource(id = R.string.search),
    withClearIcon: Boolean = false,
    onDoneClick: () -> Unit = {}
) {
    TextField(
        value = state.value,
        onValueChange = {  value ->
            state.value = value },
        placeholder = {
            Text(
                text = hint,
                fontSize = fontSize16,
                fontWeight = FontWeight.Normal,
                style = regular,
                color = Base500,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        modifier = modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(cornerRadius12))
            .background(color = Base200),
        textStyle = TextStyle(Base900, fontSize = fontSize16),
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = null,
                tint = Base500,
                modifier = Modifier
                    .size(24.dp)
                    .click { onDoneClick() }
                    .padding(2.dp)
            )
        },
        singleLine = true,
        shape = RoundedCornerShape(cornerRadius12),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Base900,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            backgroundColor = backgroundColor,
            cursorColor = Green500
        ),
        trailingIcon = {
            if (state.value != TextFieldValue("")) {
                Image(
                    painter = painterResource(id = R.drawable.ic_close_base400),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(24.dp)
                        .clickable {
                            state.value =
                                TextFieldValue("")
//                            onSearchTextChanged("")
                        }
                )
            }
//            if (text.isNotEmpty() && withClearIcon) {
//
//            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun SearchViewPreview() {
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    SearchView(textState)
}