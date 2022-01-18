package com.mzk.samplee_market.navigations

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mzk.samplee_market.ui.screens.home.HomeScreen
import com.mzk.samplee_market.ui.screens.summary.SummaryScreen
import com.mzk.samplee_market.ui.viewmodels.EMarketViewModel

private const val TAG = "SetUpNavigation"

@Composable
fun SetUpNavigation() {
    val navHostController = rememberNavController()

    val sharedViewModel = hiltViewModel<EMarketViewModel>()


    NavHost(navController = navHostController, startDestination = Screens.HomeScreen.route) {
        composable(route = Screens.HomeScreen.route) {
            HomeScreen(eMarketViewModel = sharedViewModel) {
                sharedViewModel.resetOrderRequest()
                navHostController.navigate(Screens.SummaryScreen.route)
            }
        }

        composable(route = Screens.SummaryScreen.route) {

            SummaryScreen(eMarketViewModel = sharedViewModel) {
                navHostController.navigateUp()
            }
        }


    }


}