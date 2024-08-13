package com.openaiinvest.app.presentation.main.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.openaiinvest.app.R
import com.openaiinvest.app.presentation.components.OpenAIInvestCardSection

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val shares = viewModel.homeState.value.shares
    val tokens = viewModel.homeState.value.tokens

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xffF2F2F2)),
        contentPadding = PaddingValues(16.dp),
        horizontalAlignment = Alignment.Start
    ) {


        item {
            OpenAIInvestCardSection()
        }
        item {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(id = R.drawable.dummy_graph),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "$${((0.016 * shares * 100) + (shares * 100)) + (tokens * 10)}")
                    Text(
                        text = "Available Balance", style = MaterialTheme.typography.labelSmall
                    )

                }
            }
        }
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .shadow(8.dp)
                    .background(Color.White)
                    .padding(24.dp)
            ) {
                Text(
                    text = "My Assets",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color(0xff106675)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Total Investments", style = MaterialTheme.typography.labelSmall
                        )
                        Text(text = "$${shares * 100}")
                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Shares Owned", style = MaterialTheme.typography.labelSmall)
                        Text(text = "$shares")

                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Current Value", style = MaterialTheme.typography.labelSmall)
                        Text(
                            text = "$${(0.016 * shares * 100) + (shares * 100)}",
                            color = Color(0xff47BBC4)
                        )
                    }
                }


            }
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .shadow(8.dp)
                    .background(Color.White)
                    .padding(24.dp)
            ) {
                Text(
                    text = "My Tokens",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color(0xff106675)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Tokens Owned", style = MaterialTheme.typography.labelSmall
                        )
                        Text(text = "$tokens")
                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Estimated Value", style = MaterialTheme.typography.labelSmall)
                        Text(text = "$${tokens * 10}", color = Color(0xff47BBC4))

                    }

                }


            }
        }
        item {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "50000", style = MaterialTheme.typography.titleLarge)
                        Text(text = "Total Shares", style = MaterialTheme.typography.bodyMedium)


                    }
                    Spacer(modifier = Modifier.height(8.dp))

                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "10K+", style = MaterialTheme.typography.titleLarge)
                        Text(text = "Investors", style = MaterialTheme.typography.bodyMedium)


                    }
                    Spacer(modifier = Modifier.height(8.dp))

                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "25K+", style = MaterialTheme.typography.titleLarge)
                        Text(text = "Shares Left", style = MaterialTheme.typography.bodyMedium)


                    }

                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "90K+", style = MaterialTheme.typography.titleLarge)
                        Text(text = "Participants", style = MaterialTheme.typography.bodyMedium)

                    }
                    Spacer(modifier = Modifier.height(8.dp))

                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "10+", style = MaterialTheme.typography.titleLarge)
                        Text(text = "Projects", style = MaterialTheme.typography.bodyMedium)


                    }
                    Spacer(modifier = Modifier.height(8.dp))

                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "200K", style = MaterialTheme.typography.titleLarge)
                        Text(text = "A.I. Tokens", style = MaterialTheme.typography.bodyMedium)


                    }

                }


            }

        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Company Valuation",
                style = MaterialTheme.typography.titleMedium,
                color = Color(0xff106675)
            )
            Spacer(modifier = Modifier.height(8.dp))

            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.bargraph),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))

        }

    }

}


