package com.openaiinvest.app.presentation.start.onboarding

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.openaiinvest.app.R
import com.openaiinvest.app.presentation.util.Screen

@Composable
fun OnboardingScreen(
    navController: NavHostController

) {

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(100.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier.size(150.dp),
                    painter = painterResource(id = R.drawable.openaiinvest_logo),
                    contentDescription = null
                )
                Image(
                    modifier = Modifier
                        .height(50.dp)
                        .width(300.dp),
                    painter = painterResource(id = R.drawable.opeaiinvest_writing_logo),
                    contentDescription = null,
                )

            }
            Box(
                contentAlignment = Alignment.BottomCenter

            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.3f)
                        .graphicsLayer(
                            scaleX = 1.5f,
                            scaleY = 1.5f
                        )
                        .clip(
                            RoundedCornerShape(topStart = 180.dp, topEnd = 180.dp)
                        )
                        .shadow(100.dp)
                        .background(Color(0xff106675))
                )

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                        onClick = {
                            navController.navigate(Screen.SignUpScreen.route)

                        }, colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White
                        )
                    ) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "SIGN UP", color = Color(0xFF106675))
                        Spacer(modifier = Modifier.width(8.dp))

                    }
                    Button(
                        onClick = {
                            navController.navigate(Screen.SignInScreen.route)

                        },
                        border = BorderStroke(1.dp, Color.White),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF106675)
                        )

                    ) {
                        Spacer(modifier = Modifier.width(8.dp))

                        Text(text = "SIGN IN", color = Color.White)
                        Spacer(modifier = Modifier.width(8.dp))

                    }
                    Spacer(modifier = Modifier.height(80.dp))
                }
            }
        }

    }

}



