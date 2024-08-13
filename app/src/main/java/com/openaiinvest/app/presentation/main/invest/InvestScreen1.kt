package com.openaiinvest.app.presentation.main.invest

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AttachMoney
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.openaiinvest.app.presentation.components.OpenAIInvestCardSection
import com.openaiinvest.app.presentation.components.OpenAIInvestTextField
import com.openaiinvest.app.presentation.main.invest.util.roundToTwoDecimalPlaces
import com.openaiinvest.app.presentation.util.Screen
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun InvestScreen1(
    navController: NavHostController
) {

    Column(
        modifier = Modifier
            .background(Color(0xffF2F2F2))
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        val context = LocalContext.current
        Column {
            OpenAIInvestCardSection()
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Invest:",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        var state by remember {
            mutableStateOf(false)
        }
        var selectedOption by remember {
            mutableStateOf(0)
        }
        val list = listOf(5, 10, 15, 20, 25, 30, 35, 40)


        var state2 by remember {
            mutableStateOf(false)
        }
        var selectedOption2 by remember {
            mutableStateOf(0)
        }
        val list2 = listOf(3, 6, 9, 12, 15, 18, 21, 24)

        Column(
            horizontalAlignment = Alignment.Start,

            ) {
            Spacer(modifier = Modifier.height(16.dp))

            ExposedDropdownMenuBox(
                expanded = state,
                onExpandedChange = {
                    state = !state
                },
            ) {


                OutlinedTextField(
                    value = if (selectedOption != 0) {
                        selectedOption.toString()
                    } else {
                        ""
                    },
                    readOnly = true,
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = {},
                    shape = RoundedCornerShape(32.dp),
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = state
                        )
                    },
                    placeholder = {
                        Text(
                            text = "Number of Shares", color = Color.Black.copy(0.5f)
                        )
                    },
                    colors = androidx.compose.material3.TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Black.copy(0.5f),
                        unfocusedBorderColor = Color.Black.copy(0.5f)
                    ),

                    )

                ExposedDropdownMenu(expanded = state, onDismissRequest = {
                    state = !state
                }) {
                    list.forEach { number ->
                        DropdownMenuItem(onClick = {
                            selectedOption = number
                            state = false
                        }) {
                            Text(text = number.toString())
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            OpenAIInvestTextField(
                text = if (selectedOption == 0) {
                    ""
                } else {
                    (selectedOption * 20).toString()
                },
                modifier = Modifier.fillMaxWidth(),

                readOnly = true,
                onValueChange = {},
                imageVector = Icons.Outlined.AttachMoney,
                placeHolderText = "Amount"
            )
        }
        Spacer(modifier = Modifier.height(8.dp))





        ExposedDropdownMenuBox(
            expanded = state2,
            onExpandedChange = {
                state2 = !state2
            },
        ) {


            OutlinedTextField(
                value = if (selectedOption2 != 0) {
                    selectedOption2.toString() + "m"
                } else {
                    ""
                },
                readOnly = true,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {},
                shape = RoundedCornerShape(32.dp),
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = state2
                    )
                },
                placeholder = {
                    Text(
                        text = "Invest For", color = Color.Black.copy(0.5f)
                    )
                },
                colors = androidx.compose.material3.TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Black.copy(0.5f),
                    unfocusedBorderColor = Color.Black.copy(0.5f)
                ),

                )

            ExposedDropdownMenu(expanded = state2, onDismissRequest = {
                state2 = !state2
            }) {
                list2.forEach { number ->
                    DropdownMenuItem(onClick = {
                        selectedOption2 = number
                        state2 = false
                    }) {
                        Text(text = number.toString() + "m")
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        OpenAIInvestTextField(
            modifier = Modifier.fillMaxWidth(),

            text = if (selectedOption != 0 && selectedOption2 != 0) {
                roundToTwoDecimalPlaces((selectedOption * 20) + (selectedOption * 20) * selectedOption2 * 30 * 0.0175).toString()
            } else "",
            readOnly = true,
            onValueChange = {},
            imageVector = Icons.Outlined.AttachMoney,
            placeHolderText = "Estimated Returns"
        )

        Spacer(modifier = Modifier.height(16.dp))



        Button(
            modifier = Modifier.fillMaxWidth(), onClick = {
                if (selectedOption != 0 && selectedOption2 != 0) {

                    navController.navigate(Screen.InvestScreen2.route)
                } else {
                    Toast.makeText(context, "Please fill in the necessary details.", Toast.LENGTH_SHORT).show()
                }
            }, colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF106675)
            )
        ) {
            Text(text = "Make Payment", color = Color.White)

        }
    }

}


