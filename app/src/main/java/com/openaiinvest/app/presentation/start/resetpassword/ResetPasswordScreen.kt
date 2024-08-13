package com.openaiinvest.app.presentation.start.resetpassword

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.openaiinvest.app.presentation.components.OpenAIInvestTextField
import com.openaiinvest.app.presentation.start.signin.SignInEvent
import com.openaiinvest.app.presentation.start.signin.SignInViewModel
import com.openaiinvest.app.presentation.util.Screen

@Composable
fun ResetPasswordScreen(
    viewModel: ResetPasswordViewModel = hiltViewModel(),
    navController: NavHostController,
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2F2F2)),
    ) {
        val context = LocalContext.current

        Column(
            modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween
        ) {

            Box(
                modifier = Modifier.graphicsLayer(
                    scaleX = 1.5f, scaleY = 1.5f
                )
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(
                        modifier = Modifier
                            .size(190.dp)
                            .graphicsLayer(
                                scaleX = 1.5f, scaleY = 1.5f
                            )
                            .clip(
                                CircleShape
                            )
                            .shadow(100.dp)
                            .background(Color(0xffffffff))
                    )

                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .graphicsLayer(
                                scaleX = 2.5f, scaleY = 2.5f
                            )
                            .clip(
                                CircleShape
                            )
                            .shadow(100.dp)
                            .background(Color(0xff3292A3))
                    )
                }
            }
            Box(
                contentAlignment = Alignment.BottomCenter
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.3f)
                        .graphicsLayer(
                            scaleX = 1.5f, scaleY = 1.5f
                        )
                        .clip(
                            RoundedCornerShape(topStart = 200.dp, topEnd = 200.dp)
                        )
                        .shadow(100.dp)
                        .background(Color(0xff106675))
                )

                Column(
                    modifier = Modifier.padding(bottom = 16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                        onClick = {
                            viewModel.onEvent(ResetPasswordEvent.SendRecoveryMail(navController, context))
                        }, colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White
                        )
                    ) {
                        Spacer(modifier = Modifier.width(64.dp))
                        Text(text = "RESET", color = Color(0xFF106675))
                        Spacer(modifier = Modifier.width(64.dp))

                    }
                    Spacer(modifier = Modifier.height(16.dp))

                    Row {
                        Text(text = "Don't have an account? ", color = Color.White)
                        Text(
                            text = "Sign Up",
                            color = Color.White,
                            style = MaterialTheme.typography.labelLarge,
                            modifier = Modifier.clickable {
                                navController.navigate(Screen.SignUpScreen.route)
                            })
                    }

                    Spacer(modifier = Modifier.height(32.dp))


                }
            }


        }

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(start = 40.dp, top = 72.dp, bottom = 80.dp)) {
                Text(text = "Reset", style = MaterialTheme.typography.headlineLarge)
                Text(text = "Password", style = MaterialTheme.typography.headlineLarge)

                Text(text = "Entered registered Email", color = Color(0xffA8A8A8))
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OpenAIInvestTextField(
                    text = viewModel.email.value.text,
                    onValueChange = { viewModel.onEvent(ResetPasswordEvent.EnteredEmail(it)) },
                    imageVector = Icons.Filled.MailOutline,
                    placeHolderText = viewModel.email.value.hint
                )

            }
        }
    }
}