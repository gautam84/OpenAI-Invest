package com.openaiinvest.app.presentation.util

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Button
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Error
import androidx.compose.material.icons.outlined.Groups
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Payments
import androidx.compose.material.icons.outlined.Redeem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.openaiinvest.app.presentation.main.about.AboutScreen
import com.openaiinvest.app.presentation.main.home.HomeScreen
import com.openaiinvest.app.presentation.main.invest.InvestScreen1
import com.openaiinvest.app.presentation.main.invest.InvestScreen2
import com.openaiinvest.app.presentation.main.invite.InviteScreen
import com.openaiinvest.app.presentation.main.withdraw.WithdrawScreen
import com.openaiinvest.app.presentation.start.onboarding.OnboardingScreen
import com.openaiinvest.app.presentation.start.resetpassword.ResetPasswordScreen
import com.openaiinvest.app.presentation.start.signin.SignInScreen
import com.openaiinvest.app.presentation.start.signup.SignUpScreen

@Composable
fun NavigationGraph(
    navController: NavHostController, innerPaddings: PaddingValues, startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = Modifier.padding(innerPaddings)
    ) {
        composable(
            route = Screen.OnboardingScreen.route,
        ) {
            OnboardingScreen(navController = navController)
        }

        composable(
            route = Screen.SignUpScreen.route,
        ) {
            SignUpScreen(
                navController = navController
            )
        }

        composable(
            route = Screen.SignInScreen.route,
        ) {
            SignInScreen(
                navController = navController
            )
        }

        composable(
            route = Screen.ResetPasswordScreen.route
        ) {
            ResetPasswordScreen(
                navController = navController
            )
        }

        composable(route = Screen.HomeScreen.route) {
            HomeScreen()
        }

        composable(route = Screen.InvestScreen1.route) {
            InvestScreen1(navController = navController)
        }

        composable(route = Screen.InvestScreen2.route) {
            InvestScreen2()
        }

        composable(route = Screen.InviteScreen.route) {
            InviteScreen()
        }
        composable(route = Screen.WithdrawScreen.route) {
            WithdrawScreen()
        }
        composable(route = Screen.AboutUsScreen.route) {
            AboutScreen()
        }
    }
}

@Composable
fun SetupNavigation(
    startDestination: String,
    isEmailVerified: Boolean = true,
    onResendClick: () -> Unit


) {
    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val currentRoute = navBackStackEntry?.destination?.route


    Scaffold(
        topBar = {
          if (!isEmailVerified)  {
                Row(
                    modifier = Modifier
                        .background(Color(0xFFF97830))
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Error,
                            contentDescription = null,
                            tint = Color.White
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Please verify your email address.",
                            color = Color.White,
                        )
                    }
                    Text(text = "Send Link", modifier = Modifier
                        .clickable {
                            onResendClick()
                        }
                        .padding(4.dp)
                        .clip(RoundedCornerShape(8.dp)), color = Color.Blue)

                }
            }
        },
        bottomBar = {
            if (currentRoute != Screen.OnboardingScreen.route && currentRoute != Screen.ResetPasswordScreen.route && currentRoute != Screen.SignInScreen.route && currentRoute != Screen.SignUpScreen.route) {
                BottomAppBar(
                    modifier = Modifier
                        .height(65.dp)
                        .clip(RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp)),
                    cutoutShape = CircleShape,
                    backgroundColor = Color(0xff106675),
                    elevation = 22.dp
                ) {

                    BottomNavigationItem(icon = {
                        Icon(
                            Icons.Outlined.Home,
                            contentDescription = null
                        )
                    },
                        label = { Text("Home") },
                        selected = currentDestination?.hierarchy?.any { it.route == Screen.HomeScreen.route } == true,
                        onClick = {
                            navController.navigate(Screen.HomeScreen.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        },
                        selectedContentColor = Color.White,
                        unselectedContentColor = Color.White.copy(0.5f)
                    )

                    BottomNavigationItem(icon = {
                        Icon(
                            Icons.Outlined.Redeem,
                            contentDescription = null
                        )
                    },
                        label = { Text("Invite") },
                        selected = currentDestination?.hierarchy?.any { it.route == Screen.InviteScreen.route } == true,
                        onClick = {
                            navController.navigate(Screen.InviteScreen.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        },
                        selectedContentColor = Color.White,
                        unselectedContentColor = Color.White.copy(0.5f)
                    )
                    Column() {
                        Spacer(modifier = Modifier.height(24.dp))
                        Text(text = "Invest",
                            color = if (currentDestination?.hierarchy?.any { it.route == Screen.InvestScreen1.route || it.route == Screen.InvestScreen2.route } == true) {
                                Color.White
                            } else {
                                Color.White.copy(0.5f)
                            })
                    }

                    BottomNavigationItem(icon = {
                        Icon(
                            Icons.Outlined.Payments,
                            contentDescription = null
                        )
                    },
                        label = { Text("Withdraw") },
                        selected = currentDestination?.hierarchy?.any { it.route == Screen.WithdrawScreen.route } == true,
                        onClick = {
                            navController.navigate(Screen.WithdrawScreen.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        },
                        selectedContentColor = Color.White,
                        unselectedContentColor = Color.White.copy(0.5f)
                    )
                    BottomNavigationItem(icon = {
                        Icon(
                            Icons.Outlined.Groups,
                            contentDescription = null
                        )
                    },
                        label = { Text("About Us") },
                        selected = currentDestination?.hierarchy?.any { it.route == Screen.AboutUsScreen.route } == true,
                        onClick = {
                            navController.navigate(Screen.AboutUsScreen.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        },
                        selectedContentColor = Color.White,
                        unselectedContentColor = Color.White.copy(0.5f)
                    )


                }
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        floatingActionButton = {
            if (currentRoute != Screen.OnboardingScreen.route && currentRoute != Screen.ResetPasswordScreen.route && currentRoute != Screen.SignInScreen.route && currentRoute != Screen.SignUpScreen.route) {
                FloatingActionButton(
                    shape = CircleShape, backgroundColor = Color(0xff106675), onClick = {
                        Screen.InvestScreen1.route.let {
                            navController.navigate(it) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                        Screen.InvestScreen1.route.let { navController.navigate(it) }
                    }, contentColor = Color.White
                ) {
                    Icon(imageVector = Icons.Outlined.Add, contentDescription = "Add icon")
                }
            }
        }
    ) {
        NavigationGraph(
            navController = navController, innerPaddings = it, startDestination = startDestination
        )
    }

}