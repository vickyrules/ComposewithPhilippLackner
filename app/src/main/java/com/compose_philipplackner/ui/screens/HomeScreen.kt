package com.compose_philipplackner.ui.screens


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.node.modifierElementOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose_philipplackner.R
import com.compose_philipplackner.data.Feature
import com.compose_philipplackner.data.bottomItemList
import com.compose_philipplackner.data.features
import com.compose_philipplackner.fontFamily
import com.compose_philipplackner.utils.standardQuadFromTo
import com.compose_philipplackner.ui.theme.*

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

    LazyRow(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(chipList) {
            ChipGroup(chipData = it)
        }
    }
}


@Composable
fun ChipGroup(
    modifier: Modifier = Modifier,
    chipData: String
) {
    var selectedChip = remember { mutableStateOf(false) }
    ElevatedChip(
        selected = selectedChip.value,
        setEnabled = { selectedChip.value = !selectedChip.value },
        chipData = chipData
    )

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ElevatedChip(
    modifier: Modifier = Modifier,
    selected: Boolean,
    setEnabled: () -> Unit,
    chipData: String
) {
    FilterChip(
        selected = selected,
        modifier = Modifier
            .padding(
                start = 5.dp,
                top = 5.dp,
                end = 5.dp
            ),
        onClick = setEnabled,
        label = {
            Text(
                text = chipData,
                fontWeight = FontWeight.Bold,
                color = TextWhite,
            )
        },
        enabled = true,
        colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = ButtonBlue,
            containerColor = GrayViolet,

            ),
        elevation = FilterChipDefaults.filterChipElevation(10.dp)
    )
}


@Composable
fun AudioBanner(modifier: Modifier = Modifier) {
    val cardList: List<Pair<String, Color>> = listOf(
        Pair("Daily Though", LightRed),
        Pair("Birds Sounds", Beige1),
        Pair("Nature Melody", LightGreen2),
    )

    CardGroup(
        cardList = cardList
    )
}

@Composable
fun CardGroup(
    modifier: Modifier = Modifier,
    cardList: List<Pair<String, Color>>,
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(cardList.size) {
            val (title, colorBg) = cardList[it]
            var isPlaying by remember { mutableStateOf(false) }
            CardBanner(
                isPlaying = isPlaying,
                onClick = { isPlaying = !isPlaying },
                title = title,
                colorBg = colorBg
            )

        }
    }
}

@Composable
fun CardBanner(
    modifier: Modifier = Modifier,
    isPlaying: Boolean,
    onClick: () -> Unit,
    title: String,
    colorBg: Color
) {
    Card(
        Modifier
            .fillMaxWidth()
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
                    text = title,
                    style = MaterialTheme.typography.h2

                )
                Text(
                    text = title + " • 8-10 min",
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

@Composable
fun FeaturedSection(padding: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 90.dp)
    ) {
        Text(
            text = "Features",
            modifier = Modifier.padding(start = 10.dp),
            style = MaterialTheme.typography.h1
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(
                all = 8.dp
            ),
        ) {
            items(features) {
                FeatureCard(featureObj = it)
            }
        }
    }
}

@Composable
fun FeatureCard(
    modifier: Modifier = Modifier,
    featureObj: Feature
) {

    BoxWithConstraints(
        modifier = Modifier
            .padding(5.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(10.dp))
            .background(featureObj.darkColor)
    ) {
        val width = constraints.maxWidth
        val height = constraints.maxHeight

        // Medium colored cureve path
        val mediumColoredPoint1 = Offset(0f, height * 0.3f)
        val mediumColoredPoint2 = Offset(width * 0.1f, height * 0.35f)
        val mediumColoredPoint3 = Offset(width * 0.4f, height * 0.05f)
        val mediumColoredPoint4 = Offset(width * 0.75f, height * 0.7f)
        val mediumColoredPoint5 = Offset(width * 1.4f, -height.toFloat())

        val mediumColoredPath = Path().apply {
            moveTo(mediumColoredPoint1.x, mediumColoredPoint1.y)
            standardQuadFromTo(mediumColoredPoint1, mediumColoredPoint2)
            standardQuadFromTo(mediumColoredPoint2, mediumColoredPoint3)
            standardQuadFromTo(mediumColoredPoint3, mediumColoredPoint4)
            standardQuadFromTo(mediumColoredPoint4, mediumColoredPoint5)
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }

        // Light colored curve path
        val lightPoint1 = Offset(0f, height * 0.35f)
        val lightPoint2 = Offset(width * 0.1f, height * 0.4f)
        val lightPoint3 = Offset(width * 0.3f, height * 0.35f)
        val lightPoint4 = Offset(width * 0.65f, height.toFloat())
        val lightPoint5 = Offset(width * 1.4f, -height.toFloat() / 3f)

        val lightColoredPath = Path().apply {
            moveTo(lightPoint1.x, lightPoint1.y)
            standardQuadFromTo(lightPoint1, lightPoint2)
            standardQuadFromTo(lightPoint2, lightPoint3)
            standardQuadFromTo(lightPoint3, lightPoint4)
            standardQuadFromTo(lightPoint4, lightPoint5)
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }

        Canvas(
            modifier = Modifier
                .fillMaxSize()
        ) {
            drawPath(
                path = mediumColoredPath,
                color = featureObj.lightColor
            )
            drawPath(
                path = lightColoredPath,
                color = featureObj.mediumColor
            )
        }

        Text(
            text = featureObj.title,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 10.dp, top = 10.dp),
            style = MaterialTheme.typography.h1,
            color = TextWhite,
            lineHeight = 30.sp
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 10.dp)
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(featureObj.iconId),
                contentDescription = null,
            )

            TextButton(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = ButtonBlue,
                ),
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp)),
                elevation = ButtonDefaults.buttonElevation(5.dp)
            ) {
                Text(text = "Start", color = TextWhite)
            }
        }

    }
    //}
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationBar() {
    var selectedItem by remember { mutableStateOf(0) }
    val items = bottomItemList

    NavigationBar(
        modifier = Modifier
            .fillMaxWidth(),
        containerColor = Color.Transparent,
        tonalElevation = 10.dp
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                modifier = Modifier.clip(MaterialTheme.shapes.small),
                icon = {
                    BadgedBox(
                        badge = {
                            if(index == 0) Badge { Text("8") }
                        }
                    ) {
                        Icon(
                            painter = painterResource(item.icon),
                            modifier = Modifier.size(24.dp),
                            contentDescription = item.label
                        )
                    }
                },
                label = {
                    Text(
                        text = item.label,
                        modifier = Modifier
                            .padding(top = 2.dp),
                        color = if (selectedItem == index) TextWhite else AquaBlue,
                        fontFamily = FontFamily(Font(R.font.genos_regular))
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = ButtonBlue,
                ),
                selected = selectedItem == index,
                onClick = { selectedItem = index }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
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
            Scaffold(
                modifier = Modifier,
                containerColor = Color.Transparent,
                bottomBar = { BottomNavigationBar() }
            ) { paddingValues -> FeaturedSection(paddingValues) }

        }
    }
}
