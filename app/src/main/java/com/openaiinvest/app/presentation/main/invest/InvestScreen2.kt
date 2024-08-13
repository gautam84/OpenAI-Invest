package com.openaiinvest.app.presentation.main.invest

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FileCopy
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.getSystemService
import com.openaiinvest.app.R
import com.openaiinvest.app.presentation.components.OpenAIInvestInfoCard


@Composable
fun InvestScreen2() {

    val walletAddress = "0x741Bd0600a1853Fb6cf37b378541f3b87cECd3d4"
    val context = LocalContext.current
    val clipboardManager = LocalClipboardManager.current



    Column(
        modifier = Modifier
            .background(Color(0xffF2F2F2))
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .clip(
                    RoundedCornerShape(16.dp)
                )
                .background(Color(0xFFC2C0FF).copy(alpha = 0.2f))
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.openaiinvest_qr),
                contentDescription = null
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .clickable {
                    clipboardManager.setText(AnnotatedString(walletAddress))
                    // Show a toast message or perform any other action to indicate the text is copied
                    Toast.makeText(context, "Text copied!", Toast.LENGTH_SHORT).show()
                }

                .background(Color.White)
                .padding(8.dp)
        ) {


            Text(
                text = walletAddress,
                style = MaterialTheme.typography.labelLarge
            )
            Spacer(modifier = Modifier.width(8.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    Icon(imageVector = Icons.Outlined.FileCopy, contentDescription = null)

            }

        }
        Spacer(modifier = Modifier.height(12.dp))

        OpenAIInvestInfoCard("Send only USDT to this address.")
        Spacer(modifier = Modifier.height(8.dp))

        OpenAIInvestInfoCard("After payment send screenshot to our telegram id @openaiinvest or mail to openaiinvest@gmail.com")
        Spacer(modifier = Modifier.height(8.dp))

        OpenAIInvestInfoCard("Refer atleast 5 people to get free A.I. Tokens and pre-IPO shares")


    }
}