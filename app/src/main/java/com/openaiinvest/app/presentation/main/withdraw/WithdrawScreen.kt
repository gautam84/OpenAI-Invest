package com.openaiinvest.app.presentation.main.withdraw

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.openaiinvest.app.presentation.components.OpenAIInvestInfoCard
import com.openaiinvest.app.presentation.components.OpenAIInvestTextField

@Composable
fun WithdrawScreen(
    viewModel: WithdrawViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .background(Color(0xffF2F2F2))
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val context = LocalContext.current

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Enter USDT Address:",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        OpenAIInvestTextField(
            modifier = Modifier.fillMaxWidth(),
            text = viewModel.usdtAddress.value.text,
            onValueChange = {
                viewModel.onEvent(
                    WithdrawEvent.EnteredUSDTAddress(
                        it
                    )
                )


            },
            imageVector = null,
            placeHolderText = ""
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Enter Amount:",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        OpenAIInvestTextField(
            modifier = Modifier.fillMaxWidth(),
            text = viewModel.enterAmount.value.text,
            onValueChange = {
                viewModel.onEvent(
                    WithdrawEvent.EnteredAmount(
                        it
                    )
                )

            },
            imageVector = null,
            placeHolderText = "",
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Decimal
            )
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            modifier = Modifier.fillMaxWidth(), onClick = {
                viewModel.onEvent(WithdrawEvent.Withdraw(context))
            }, colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF106675)
            )
        ) {
            Text(text = "Withdraw", color = Color.White)

        }
        Spacer(modifier = Modifier.height(16.dp))

        OpenAIInvestInfoCard(
            "If Amount is Withdrawn before a period of 3 months of investing, then no profit will be given"
        )

    }


}