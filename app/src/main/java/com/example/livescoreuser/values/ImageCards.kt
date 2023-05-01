package com.example.livescoreuser.values

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.transform.CircleCropTransformation
import com.example.livescoresdu.data.response.TournamentUserResponse
import com.example.livescoreuser.R
import ffinbank.myfreedom.uilibrary.values.Base50
import ffinbank.myfreedom.uilibrary.values.Base800
import ffinbank.myfreedom.uilibrary.values.cornerRadius12
import ffinbank.myfreedom.uilibrary.values.cornerRadius16
import ffinbank.myfreedom.uilibrary.values.fontSize13
import ffinbank.myfreedom.uilibrary.values.fontSize16
import ffinbank.myfreedom.uilibrary.values.medium
import ffinbank.myfreedom.uilibrary.values.semiBold
import ffinbank.myfreedom.uilibrary.values.spacing10
import ffinbank.myfreedom.uilibrary.values.spacing16
import ffinbank.myfreedom.uilibrary.values.spacing24
import ffinbank.myfreedom.uilibrary.values.spacing32
import ffinbank.myfreedom.uilibrary.values.spacing6


@Composable
fun ItemList(
    image: TournamentUserResponse,
    isFavorite: Boolean,
    isFavCallback: (value: Boolean) -> Unit,
    onClick: () -> Unit
) {

        CardImage(
            image,
            isFavorite,
            isFavCallback,
            onClick
        )
}

@Composable
fun CardImage(
    tournaments: TournamentUserResponse,
    isFav: Boolean,
    isFavCallback: (value: Boolean) -> Unit,
    click: () -> Unit
){


    Row(modifier = Modifier
        .background(shape = RoundedCornerShape(cornerRadius12), color = Base800)
        .clip(RoundedCornerShape(cornerRadius16))
        .padding(spacing16)
        .clickable {
//            navController.navigate(Screen.StandingScreen.route)
    }, verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
        Column(modifier = Modifier
            .padding(start = spacing10)
            .background(color = Base50, RoundedCornerShape(cornerRadius12))
            .padding(spacing6),) {
            Image(painter = painterResource(id = R.drawable.sdu_logo),
                contentDescription = null,
                modifier = Modifier
                    .width(spacing32)
                    .height(spacing24))
        }
        Spacer(modifier = androidx.compose.ui.Modifier.width(spacing16))
        Column() {
            Text(text = tournaments.tournamentName,
                color = Base50,
                fontSize = fontSize16,
                fontWeight = FontWeight.SemiBold,
                style = semiBold
            )
            Spacer(modifier = androidx.compose.ui.Modifier.height(spacing6))
            Text(text = "Almaty",
                color = Base50,
                fontSize = fontSize13,
                fontWeight = FontWeight.Medium,
                style = medium
            )

        }
        Spacer(modifier = Modifier.weight(1f))
        FavoriteButton(
            isChecked = isFav,
            onClick = isFavCallback
        )
    }
//    Card(
//        elevation = 8.dp,
//        modifier = Modifier
//            .fillMaxWidth()
//            .wrapContentHeight(align = Alignment.Top)
//            .padding(8.dp)
//            .clickable(onClick = click)
//    ) {
//        Column(
//            verticalArrangement = Arrangement.Center
//        ) {
////            CoilImage(
////                imageModel = imageRoot.urls.thumbImage,
////                contentDescription = "Image showed",
////                shimmerParams = ShimmerParams(
////                    baseColor = MaterialTheme.colors.surface,
////                    highlightColor = shimmerHighLight,
////                ),
////                modifier = Modifier
////                    .fillMaxWidth()
////                    .height(200.dp)
////                    .clip(RoundedCornerShape(4.dp)),
////                contentScale = ContentScale.Crop,
////            )
//            Row(
//                modifier = Modifier
//                    .wrapContentHeight()
//                    .fillMaxWidth()
//                    .padding(8.dp),
//                horizontalArrangement = Arrangement.SpaceBetween,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Column (
//                    modifier = Modifier.fillMaxWidth(0.86f)
//                ) {
////                    Text(
////                        text = imageRoot.description ?: (imageRoot.altDescription ?: ""),
////                        style = MaterialTheme.typography.subtitle2
////                    )
//                    Row(
//                        modifier = Modifier
//                            .wrapContentHeight()
//                            .padding(top = 8.dp),
//                        verticalAlignment = Alignment.CenterVertically,
//                    ) {
////                        Image(
////                            painter = rememberImagePainter(
////                                data = imageRoot.user.profileImage.small,
////                                builder = {
////                                    transformations(CircleCropTransformation())
////                                }
////                            ),
////                            contentDescription = "profile picture",
////                            modifier = Modifier
////                                .size(30.dp)
////                                .padding(end = 8.dp)
////                        )
////                        Text(
////                            text = imageRoot.user.name,
////                            style = MaterialTheme.typography.caption,
////                            color = Color.Gray
////                        )
//                    }
//                }
//                Column(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    FavoriteButton(
//                        isChecked = isFav,
//                        onClick = isFavCallback
//                    )
//
//                }
//            }
//        }
//    }
}
