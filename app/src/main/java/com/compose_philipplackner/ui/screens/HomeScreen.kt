package com.compose_philipplackner.ui.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.compose_philipplackner.R
import com.compose_philipplackner.ui.theme.*
import kotlin.random.Random

@Composable
fun GreetingTop(modifier: Modifier = Modifier) {
    val userName: String = "Vikas"
    val quote: String = "We wish you have a good day"
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Good Morning, $userName",
                style = MaterialTheme.typography.h2
            )
            Text(
                text = quote,
                style = MaterialTheme.typography.body1
            )
        }
        IconButton(onClick = {}) {
            Icon(
                modifier = Modifier.size(36.dp),
                imageVector = Icons.Outlined.Search,
                //painter = painterResource(R.drawable.ic_search),
                tint = Color.LightGray,
                contentDescription = "search",
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeaderChips(modifier: Modifier = Modifier) {
    var chipList: List<String> = listOf("Sweet Sleep", "Insomania", "Depression", "Dizziness")

    ChipGroup(
        modifier = modifier,
        chipsList = chipList
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChipGroup(
    modifier: Modifier = Modifier,
    chipsList: List<String>,
) {
    var selectedChip by remember { mutableStateOf(0) }
    LazyRow(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(chipsList.size) {

            ElevatedAssistChip(
                modifier = Modifier
                    .padding(
                        start = 5.dp,
                        top = 5.dp,
                        end = 5.dp
                    ),
                onClick = {},
                label = {
                    Text(
                        text = chipsList[it],
                        fontWeight = FontWeight.Bold,
                        color = TextWhite,
                    )
                },
                enabled = if (selectedChip == it) true else false,
                colors = AssistChipDefaults.assistChipColors(
                    containerColor = ButtonBlue,
                    disabledContainerColor = GrayViolet
                ),
                elevation = AssistChipDefaults.assistChipElevation(10.dp)
            )
        }
    }
}


@Composable
fun AudioBanner(modifier: Modifier = Modifier) {
    val cardList: List<Pair<String, Color>> = listOf(
        Pair("Daily Though", LightRed),
        Pair("Birds Sounds", Beige1),
        Pair("Nature Melody", LightGreen2),
    )
    var isPlaying by remember { mutableStateOf(false) }
    CardGroup(
        modifier = modifier,
        cardList = cardList,
        isPlaying = isPlaying,
        onClick = { isPlaying = !isPlaying }
    )
}

@Composable
fun CardGroup(
    modifier: Modifier = Modifier,
    cardList: List<Pair<String, Color>>,
    isPlaying: Boolean,
    onClick: () -> Unit
) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(cardList.size) {
            val (title, colorBg) = cardList[it]
            Card(
                Modifier
                    .fillParentMaxWidth()
                    .padding(8.dp),
                shape = MaterialTheme.shapes.large,
                colors = CardDefaults.cardColors(containerColor = colorBg),
                elevation = CardDefaults.cardElevation(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Column(
                        modifier = Modifier
                            .padding(15.dp),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = title.toString(),
                            style = MaterialTheme.typography.h2

                        )
                        Text(
                            text = title + " • ${Random.nextInt(10)} min",
                            style = MaterialTheme.typography.h3
                        )

                    }

                    IconButton(
                        onClick = onClick,
                        modifier = Modifier
                            .padding(end = 15.dp)
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(RoundedCornerShape(40.dp))
                                .background(ButtonBlue),
                            painter = if (isPlaying) painterResource(R.drawable.baseline_stop_24) else painterResource(
                                R.drawable.baseline_play_arrow_24
                            ),
                            tint = Color.LightGray,
                            contentDescription = "search",
                        )
                    }

                }


            }
        }

    }
}

@Composable
fun FeaturedSection() {
    Text(
        text = "Featured",

        style = MaterialTheme.typography.h1
    )

}

@Preview
@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier
            .background(color = DeepBlue)
            .fillMaxSize()
    ) {
        Column {
            GreetingTop()
            HeaderChips()
            AudioBanner()
            FeaturedSection()
        }
    }
}
