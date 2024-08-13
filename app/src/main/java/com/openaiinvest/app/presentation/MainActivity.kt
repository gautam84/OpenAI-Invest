package com.openaiinvest.app.presentation

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.openaiinvest.app.presentation.util.Screen
import com.openaiinvest.app.presentation.util.SetupNavigation
import com.openaiinvest.app.ui.theme.OpenAIInvestTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        if (auth.currentUser != null)  auth.currentUser!!.reload()


        setContent {
            OpenAIInvestTheme {
                val context = LocalContext.current
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {


                    SetupNavigation(startDestination = if (auth.currentUser != null) {
                        Screen.HomeScreen.route
                    } else {
                        Screen.OnboardingScreen.route
                    }, isEmailVerified =

                    if (auth.currentUser != null) {
                        auth.currentUser!!.isEmailVerified
                    } else {
                        true
                    },
                        onResendClick = {
                        if (auth.currentUser != null) {

                            auth.currentUser!!.sendEmailVerification()
                            Toast.makeText(context, "Verification Email Sent!", Toast.LENGTH_SHORT).show()
                        }
                    })
                }
            }
        }
    }
}

