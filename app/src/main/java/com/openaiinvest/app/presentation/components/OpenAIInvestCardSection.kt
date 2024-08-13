package com.openaiinvest.app.presentation.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.openaiinvest.app.R

import com.openaiinvest.app.presentation.main.home.util.HomeScreenItem


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OpenAIInvestCardSection() {
    val pagerState = rememberPagerState()
    val itemsList = listOf(
        HomeScreenItem(
            title = "Invest in the Future",
            caption = "Invest in the power of A.I., Big Data and Blockchain",
            drawableRes = R.drawable.ai_logo

        ), HomeScreenItem(
            title = "Invest in A.I.",
            caption = "Be a part of the A.I. and Big Data revolution",
            drawableRes = R.drawable.ai_logo2

        ), HomeScreenItem(
            title = "Invest in Web3",
            caption = "Invest in the power of A.I., Big Data and Blockchain",
            drawableRes = R.drawable.network_logo
        )
    )

    Box(
        contentAlignment = Alignment.BottomCenter
    ) {
        HorizontalPager(
            pageCount = 3, modifier = Modifier, state = pagerState, pageSpacing = 8.dp
        ) { page ->
            OpenAIInvestCard(
                itemsList[page]
            )
        }
        Box(modifier = Modifier.padding(16.dp)) {
            Indicators(
                size = itemsList.size, index = pagerState.currentPage
            )
        }


    }


}



@Composable
fun OpenAIInvestCard(
    item: HomeScreenItem
) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(24.dp))
            .background(
                Brush.linearGradient(
                    listOf(
                        Color(0xFF042F8E), Color(0xFF124C95)
                    )
                )
            )
            .padding(24.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(0.5f)
            ) {
                Text(
                    text = item.title,
                    color = Color.White,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = item.caption,
                    color = Color.White,
                    style = MaterialTheme.typography.labelMedium
                )

            }
            Image(
                modifier = Modifier.weight(0.5f),
                contentScale = ContentScale.Crop,
                painter = painterResource(id = item.drawableRes),
                contentDescription = null
            )
        }
    }
}


@Composable
fun Indicator(isSelected: Boolean) {

    val width = animateDpAsState(
        targetValue = if (isSelected) 21.dp else 8.dp,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
        label = ""
    )


    Box(
        modifier = Modifier
            .height(8.dp)
            .width(width.value)
            .clip(CircleShape)
            .background(if (isSelected) MaterialTheme.colorScheme.secondary else Color.White)
    ) {

    }

}

@Composable
fun BoxScope.Indicators(size: Int, index: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.align(Alignment.CenterStart)
    ) {
        repeat(size) {
            Indicator(isSelected = it == index)
        }

    }

}


