package com.openaiinvest.app.presentation.util

sealed class Screen(val route: String) {

    object OnboardingScreen : Screen(route = "onboarding_screen")
    object ResetPasswordScreen : Screen(route = "reset_password_screen")
    object SignInScreen : Screen(route = "sign_in_screen")
    object SignUpScreen : Screen(route = "sign_up_screen")

    object HomeScreen : Screen(route = "home_screen")
    object InvestScreen1 : Screen(route = "invest_screen_1")
    object InvestScreen2 : Screen(route = "invest_screen_2")
    object InviteScreen : Screen(route = " invite_screen")
    object WithdrawScreen : Screen(route = "withdraw_screen")
    object AboutUsScreen : Screen(route = "about_us_screen")


}
