package com.example.livescoresdu.uilibrary.values

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.example.livescore.presentation.screens.standings.components.StandingScreen
import com.example.livescoresdu.presentation.screens.detail.MatchesDetailScreen
import com.example.livescoresdu.dateutils.Calendar
import com.example.livescoresdu.presentation.screens.MatchesScreen
import com.google.accompanist.navigation.animation.composable


@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.homeGraph(navController: NavController,bottomNavHide: (Boolean) -> Unit) {

//    composable(route = HomeDestinations.MATCH){
//        MatchesScreen(
//            navController = navController,
//            bottomNavHide = bottomNavHide
//        )
//    }
    composable(route = HomeDestinations.MATCH_DETAIL + "/{id}") {
        MatchesDetailScreen(
            navController = navController,
            onBackClick = {
                navController.popBackStack()
            }
        )
    }
    composable(route = HomeDestinations.CALENDAR){
        Calendar(
            navController = navController,
            onBackClick = {
            navController.popBackStack()
        })
    }
    composable(route = HomeDestinations.STANDING){
        StandingScreen(onBackClick = {
            navController.popBackStack()
        })
    }

//    composable(route = CardDestinations.CARD_REQUISITES) {
//        //val card = navController.previousBackStackEntry?.savedStateHandle?.get<CardModel>("card")
////        val card =
////            navController.getBackStackParcelableArguments<domain.card.models.CardModel>("card")
//        val card = remember {
//            navController.previousBackStackEntry?.arguments?.getParcelable<CardModel>("card")
//        }
//        if (card != null) {
//            CardRequisitesScreen(navController = navController, card,)
//        }
//    }
//    composable(route = CardDestinations.NEW_CARD_PIN_CODE_ARGS) {
//        NewPinCode(navController = navController, cardId = it.arguments?.getString("cardId"))
//    }
//    composable(route = CardDestinations.NEW_CARD_PIN_CODE_REPEAT_WITH_ARGS) {
//        NewPinCodeRepeatScreen(
//            navController = navController,
//            firstCode = it.arguments?.getString("code").orEmpty(),
//            cardId = it.arguments?.getString("cardId").orEmpty()
//        )
//    }
//    composable(route = CardDestinations.CHOOSE_CITY) {
//        val city = navController.getBackStackParcelableArguments<CityModel>("default_city")
//        val cityList = navController.getBackStackParcelableArguments<CityModel>("city_list")
//        if (!city.isNullOrEmpty() && !cityList.isNullOrEmpty()) {
//            ChooseCityScreen(
//                navController = navController,
//                defaultCity = city[0],
//                cityList = cityList
//            )
//        }
//    }
//    composable(route = CardDestinations.LIMITS_WITH_ARGS) {
//        LimitsScreen(
//            navController = navController,
//            cardId = it.arguments?.getString("card_id").orEmpty()
//        )
//    }
//    composable(route = CardDestinations.CHANGE_LIMITS_WITH_ARGS) {
//        ChangeLimitScreen(
//            navController = navController,
//            cardId = it.arguments?.getString("card_id").orEmpty(),
//            limitId = it.arguments?.getString("limit_id").orEmpty(),
//            limitTypeCode = it.arguments?.getString("limitTypeCode").orEmpty(),
//            limitTitle = it.arguments?.getString("title").orEmpty()
//        )
//    }
////    composable(route = CardDestinations.REFERENCES) {
////        val backStackEntry =
////            remember { navController.getBackStackEntry(CardDestinations.REFERENCES) }
////        ReferenceScreen(navController = navController, viewModel = getViewModel(owner = backStackEntry))
////    }
////    composable(route = CardDestinations.REFERENCES_LANG) {
////        ReferenceLangScreen(navController = navController)
////    }
////    composable(route = CardDestinations.REFERENCES_PERIOD) {
////        ReferencePeriodScreen(navController = navController)
////    }
////    composable(route = CardDestinations.REFERENCES_ACCOUNT) {
////        val backStackEntry =
////            remember { navController.getBackStackEntry(CardDestinations.REFERENCES) }
////        ReferenceAccountScreen(navController = navController, viewModel = getViewModel(owner = backStackEntry))
////    }
////    composable(route = CardDestinations.REFERENCES_INFO) {
////        ReferenceInfoScreen(navController = navController)
////    }
//    composable(route = CardDestinations.CARD_TERMS) {
//        CardTermsScreen(navController = navController)
//    }
//    composable(route = CardDestinations.ORDER_CARD_WITH_ARGS) {
//        OrderCardScreen(navController = navController, bundle = it.arguments)
//    }
//    composable(route = CardDestinations.ORDER_CARD_POP_BACK) {
//        OrderCardScreen(navController = navController, bundle = it.arguments)
//    }
//    composable(route = CardDestinations.CARD_INCOMES) {
//        CardIncomeScreen(navController = navController)
//    }
//    composable(route = CardDestinations.OPEN_NEW_CARD) {
//        OpenNewCardScreen(
//            navController = navController,
//            onOpenCardClick = {
//                navController.navigate(CardDestinations.OPEN_POWER_CARD)
//            },
//            onUserTermClick = { url ->
//                navController.currentBackStackEntry?.arguments?.putString("document", url)
//                navController.navigate(CardDestinations.CARD_PDF_VIEW_SCREEN)
//            }
//        )
//    }
//
//    composable(route = CardDestinations.OPEN_POWER_CARD) {
//        val powerCardBackStackEntry =
//            remember { navController.getBackStackEntry(CardDestinations.OPEN_POWER_CARD) }
//        OpenPowerCardScreen(
//            navController = navController,
//            onNextButtonClick = {
//                navController.navigate(CardDestinations.POWER_CARD_PHONE)
//            },
//            onTypeIinClick = { navController.navigate(CardDestinations.POWER_CARD_CHILD_INFO) },
//            powerCardViewModel = getViewModel(owner = powerCardBackStackEntry)
//        )
//    }
//    composable(route = CardDestinations.POWER_CARD_CHILD_INFO) {
//        val powerCardBackStackEntry =
//            remember { navController.getBackStackEntry(CardDestinations.OPEN_POWER_CARD) }
//        PowerCardAddChildScreen(
//            navController = navController,
//            viewModel = getViewModel(owner = powerCardBackStackEntry),
//            onNextButtonClick = {
//                navController.popBackStack()
//            }
//        )
//    }
//    composable(route = CardDestinations.POWER_CARD_PHONE) {
//        val powerCardBackStackEntry =
//            remember { navController.getBackStackEntry(CardDestinations.OPEN_POWER_CARD) }
//        EnterChildPhoneScreen(
//            navController = navController,
//            onContactsClick = {
//                navController.navigate(CardDestinations.POWER_CARD_CONTACTS)
//            },
//            onContinueClick = { processInstanceId ->
//                navController.currentBackStackEntry?.arguments?.putString(
//                    "processInstanceId",
//                    processInstanceId
//                )
//                navController.currentBackStackEntry?.arguments?.putString(
//                    "nextNavigation",
//                    CardDestinations.POWER_CARD_SIGN_DOCUMENTS
//                )
//                navController.navigate(CardDestinations.POWER_CARD_CONFIRM_PHONE)
//            },
//            viewModel = getViewModel(owner = powerCardBackStackEntry)
//        )
//    }
//    composable(route = CardDestinations.POWER_CARD_CONTACTS) {
//        val powerCardBackStackEntry =
//            remember { navController.getBackStackEntry(CardDestinations.OPEN_POWER_CARD) }
//        PowerCardContactsScreen(
//            navController = navController,
//            viewModel = getViewModel(owner = powerCardBackStackEntry)
//        )
//    }
//    composable(route = CardDestinations.POWER_CARD_CONFIRM_PHONE) {
//        val context = LocalContext.current
//        val processInstanceId = remember {
//            navController.previousBackStackEntry?.arguments?.getString("processInstanceId")
//                .orEmpty()
//        }
//        val nextNavigation = remember {
//            navController.previousBackStackEntry?.arguments?.getString("nextNavigation")
//        }
//        PowerCardConfirmPhoneScreen(
//            otpType = VerifyOtpEnum.CONFIRM_CHILD_PHONE,
//            bundle = null,
//            processInstanceId = processInstanceId,
//            onBackClick = {
//                navController.popBackStack()
//            },
//            onSuccess = {
//                try {
//                    context.unregisterReceiver(smsReceiver)
//                    navController.currentBackStackEntry?.arguments?.putString(
//                        "processInstanceId",
//                        processInstanceId
//                    )
//                    navController.navigate(nextNavigation.orEmpty())
//                } catch (e: Exception) {
//                }
////                Log.d("PowerCardConfirmPhoneScreen", "onSuccess")
////                navController.navigate(CardDestinations.POWER_CARD_SIGN_DOCUMENTS)
//            },
//        )
//    }
//    composable(route = CardDestinations.POWER_CARD_SIGN_DOCUMENTS) {
//        val processInstanceId = remember {
//            navController.previousBackStackEntry?.arguments?.getString("processInstanceId")
//                .orEmpty()
//        }
//        PowerCardSignDocumentsScreen(
//            onBackClick = { navController.popBackStack() },
//            onSignClick = {
//                navController.currentBackStackEntry?.arguments?.putString(
//                    "processInstanceId",
//                    processInstanceId
//                )
//                navController.currentBackStackEntry?.arguments?.putString(
//                    "nextNavigation",
//                    CardDestinations.POWER_CARD_SUCCESS
//                )
//                navController.navigate(CardDestinations.POWER_CARD_CONFIRM_PHONE)
//            }
//        )
//    }
//    composable(route = CardDestinations.CARD_INFO) {
//        CardInfoScreen(
//            navController = navController,
//            onOpenCardClick = { },
//            onUserTermsClick = { url ->
//                navController.currentBackStackEntry?.arguments?.putString("document", url)
//                navController.navigate(CardDestinations.CARD_PDF_VIEW_SCREEN)
//            },
//            onCloseClick = { navController.popBackStack() },
//            onKnowMoreClick = { url ->
//                navController.currentBackStackEntry?.arguments?.putString("document", url)
//                navController.navigate(CardDestinations.CARD_PDF_VIEW_SCREEN)
//            }
//        )
//    }
//    composable(route = CardDestinations.POWER_CARD_SUCCESS) {
//        PowerCardSuccessScreen(
//            onGoBackToMainClick = {
//                navController.popBackStack(
//                    route = MainDestinations.MAIN.destination,
//                    inclusive = false
//                )
//            }
//        )
//    }
//    composable(route = CardDestinations.POWER_CARD_ERROR) {
//        PowerCardErrorScreen(
//            onBackClick = { navController.popBackStack() }
//        )
//    }
//    composable(route = CardDestinations.CARD_PDF_VIEW_SCREEN) {
//        PdfViewScreen(
//            onBackClick = { navController.popBackStack() },
//            document = navController.previousBackStackEntry?.arguments?.getString("document"),
//            title = navController.previousBackStackEntry?.arguments?.getString("title"),
//        )
//    }
////    composable(route = CardDestinations.NEW_CHILD_CARD_TERMS) {
////        NewChildCard(navController = navController)
////    }
////    composable(route = CardDestinations.OPEN_CHILD_CARD) {
////        val openChildCardBackStackEntry =
////            remember { navController.getBackStackEntry(CardDestinations.OPEN_CHILD_CARD) }
////        OpenNewChildCard(
////            navController = navController,
////            onContinueClick = { processInstanceId ->
////                Log.e("TAG", "cardGraph: OPEN CHILD CARD PROCESS = $processInstanceId", )
////                navController.currentBackStackEntry?.arguments?.putString(
////                    "processInstanceId",
////                    processInstanceId
////                )
////                navController.navigate(CardDestinations.CONFIRM_PHONE)
////            },
////            viewModel = getViewModel(owner = openChildCardBackStackEntry)
////        )
////    }
////    composable(route = CardDestinations.OPEN_CHILD_CARD_DATA) {
////
//////        val list: MutableList<DropDownListData> = ArrayList()
//////        list.add(DropDownListData(0, "Маркорий Андрей Владимирович", true))
//////        val incomes = navController.getBackStackArguments<MutableList<DropDownListData>>("incomes", list) as MutableList<DropDownListData>
////        val openChildCardBackStackEntry =
////            remember { navController.getBackStackEntry(CardDestinations.OPEN_CHILD_CARD) }
////        OpenChildCardDataPagerScreen(
////            navController = navController,
////            viewModel = getViewModel(owner = openChildCardBackStackEntry)
////        )
////    }
//    composable(route = CardDestinations.CARD_OPEN_SUCCESS) {
//        CardOpenSuccessScreen(navController = navController)
//    }
//    composable(route = CardDestinations.OPEN_INVEST_WEB_VIEW) {
//        val processInstanceId = remember {
//            navController.previousBackStackEntry?.arguments?.getString("processInstanceId")
//        }
//        val url = remember {
//            navController.previousBackStackEntry?.arguments?.getString("url")
//        }
//        val sid = remember {
//            navController.previousBackStackEntry?.arguments?.getString("sid")
//        }
//        OpenInvestCardWebView(
//            navController = navController,
//            processInstanceId = processInstanceId.orEmpty(),
//            url = url.orEmpty(),
//            sid = sid.orEmpty()
//        )
//    }
//    composable(route = CardDestinations.OPEN_INVEST_NEED_BROKER) {
//        val processInstanceId = remember {
//            navController.previousBackStackEntry?.arguments?.getString("processInstanceId")
//        }
//        OpenInvestCardNeedBrokerScreen(
//            processInstanceId = processInstanceId.orEmpty(),
//            submitted = { url: String, sid: String ->
//                navController.currentBackStackEntry?.arguments?.putString("url", url)
//                navController.currentBackStackEntry?.arguments?.putString("sid", sid)
//                navController.navigate(CardDestinations.OPEN_INVEST_WEB_VIEW)
//            },
//            onBack = {
//                navController.popBackStack(route = CardDestinations.OPEN_NEW_CARD, inclusive = false)
//            }
//        )
//    }
//    composable(route = CardDestinations.CONFIRM_PHONE) {
//        val context = LocalContext.current
//        val processInstanceId = remember {
//            navController.previousBackStackEntry?.arguments?.getString("processInstanceId")
//        }
//        val cardType = remember {
//            navController.previousBackStackEntry?.arguments?.getString("cardType")
//        }
//        OpenCardConfirmPhoneScreen(
//            otpType = VerifyOtpEnum.OPEN_CARD,
//            bundle = it.arguments,
//            isInvest = try {
//                CardTypeEnum.valueOf(cardType.orEmpty()) == CardTypeEnum.INVEST_CARD
//            } catch (e: Exception) { false },
//            processInstanceId = processInstanceId.orEmpty(),
//            success = {
//                try {
//                    context.unregisterReceiver(smsReceiver)
//                    navController.navigate(CardDestinations.CARD_OPEN_SUCCESS)
//                } catch (e: Exception) {
//                }
//            },
//            error = {
//                context.unregisterReceiver(smsReceiver)
//                navController.navigate(CardDestinations.ERROR_ALERT)
//            },
//            needBrokerAccount = {
//                navController.currentBackStackEntry?.arguments?.putString(
//                    "processInstanceId",
//                    processInstanceId
//                )
//                navController.navigate(CardDestinations.OPEN_INVEST_NEED_BROKER)
//            },
//            onBackClick = {
//                navController.popBackStack()
//            }
//        )
//    }
//
//    composable(route = CardDestinations.ERROR_ALERT) {
//        ErrorAlertScreen(
//            navController = navController,
//            title = "Что-то пошло не так",
//            description = "Пожалуйста, обратитесь в Отделение банка",
//            buttonText = "Вернуться в начало"
//        )
//    }
//
//    composable(route = CardDestinations.ORDER_CARD_INPUTS_ARGS) {
//        val inputs = navController.getBackStackParcelableArguments<StepInputParcelable>("inputs")
//        val startPosition = navController.getBackStackArguments<Int>("start_position", 0) as Int
//
//        Log.e(
//            "TAG",
//            "cardGraph: ${
//                navController.currentBackStackEntry?.arguments?.getInt(
//                    "start_position",
//                    2
//                )
//            }"
//        )
//        if (inputs != null) {
//            StepInputsScreen(
//                navController = navController,
//                startPosition = startPosition,
//                inputs = inputs
//            )
//        }
////        StepInputsScreen(navController = navController,
////            startPosition = it.arguments?.getInt("start_position") ?: 0,
////            inputs = it.arguments?.getParcelableArrayList<StepInputParcelable>("inputs").orEmpty()
////        )
//    }
}