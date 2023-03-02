package com.compose_philipplackner

import android.graphics.Paint.Align
import android.graphics.Paint.FontMetrics
import android.os.Bundle
import android.view.MotionEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.boundsInParent
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose_philipplackner.ui.theme.ComposePhilippLacknerTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import kotlin.math.PI
import kotlin.math.atan
import kotlin.math.atan2
import kotlin.math.roundToInt
import kotlin.random.Random

val fontFamily = FontFamily(
    Font(R.font.lexend, FontWeight.Thin),
    Font(R.font.lexend_bold, FontWeight.Bold),
    Font(R.font.lexend_extrabold, FontWeight.ExtraBold),
    Font(R.font.lexend_medium, FontWeight.Medium),
    Font(R.font.lexend_semibold, FontWeight.SemiBold),
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposePhilippLacknerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ComposePreview()
                }
            }
        }
    }
}

@Composable
fun ComposeColumn() {
    Column(
        modifier = Modifier.fillMaxWidth()
            .background(Color.Green),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        for (i in 1..3) {
            Text(
                modifier = Modifier
                    .background(MaterialTheme.colors.error),
                text = "Column-${i}"
            )
        }

    }

}

@Composable
fun ComposeRow() {
    Row(
        modifier = Modifier.fillMaxWidth()
            .background(Color.Green)
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 1..3) {
            Text(
                modifier = Modifier
                    .background(MaterialTheme.colors.error),
                text = "Row-${i}"
            )
        }
    }
}

@Composable
fun RowColmun() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Columns")
        ComposeColumn()
        Text("Rows")
        ComposeRow()
    }
}

@Composable
fun TextStyling() {
    Text(
        text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = Color.Green,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            ) {
                append("J")
            }
            append("etPack")
            withStyle(
                style = SpanStyle(
                    color = Color.Red,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            ) {
                append("C")
            }
            append("ompose")
        },
        fontFamily = fontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        color = Color.Blue,
        fontStyle = FontStyle.Italic,
        textAlign = TextAlign.Center,
        textDecoration = TextDecoration.Underline
    )

}

@Composable
fun ComposeState() {
    val color = remember { mutableStateOf(Color.Yellow) }

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        BoxState(
            Modifier
                .weight(1f)
                .fillMaxSize()
        ) {
            color.value = it
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .background(color = color.value)
        ) {

        }
    }
}

@Composable
fun BoxState(
    modifier: Modifier = Modifier,
    updateColor: (Color) -> Unit
) {

    Box(
        modifier = modifier
            .background(color = Color.Red)
            .clickable {
                updateColor(
                    Color(
                        red = Random.nextFloat(),
                        blue = Random.nextFloat(),
                        green = Random.nextFloat(),
                        alpha = 1f
                    )
                )
            }
    ) {

    }
}

@Composable
fun VolumeBar(
    modifier: Modifier = Modifier,
    activeBAr: Int = 0,
    barCount: Int = 10
) {
    BoxWithConstraints(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        val barWidth = remember {
            constraints.maxWidth / (2F * barCount)
        }

        Canvas(modifier = modifier) {
            for (i in 0 until barCount) {
                drawRoundRect(
                    color = if (i in i..activeBAr) Color.Green else Color.DarkGray,
                    topLeft = Offset(i * barWidth * 2f + barWidth / 2f, 0f),
                    size = Size(barWidth, constraints.maxHeight.toFloat()),
                    cornerRadius = CornerRadius(1f)
                )
            }
        }


    }


}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MusicKnob(
              modifier: Modifier = Modifier,
              limitingAngle: Float = 25f,
              onValueChange: (Float) -> Unit
) {
    var rotation by remember {
        mutableStateOf(limitingAngle)
    }
    var touchX by remember {
        mutableStateOf(0f)
    }
    var touchY by remember {
        mutableStateOf(0f)
    }
    var centerX by remember {
        mutableStateOf(0f)
    }
    var centerY by remember {
        mutableStateOf(0f)
    }

    Image(
        painter = painterResource(id = R.drawable.knob),
        contentDescription = "Music knob",
        modifier = modifier
            .fillMaxSize()
            .onGloballyPositioned {
                val windowBounds = it.boundsInWindow()
                centerX = windowBounds.size.width / 2f
                centerY = windowBounds.size.height / 2f
            }
            .pointerInteropFilter { event ->
                touchX = event.x
                touchY = event.y
                val angle = -atan2(centerX - touchX, centerY - touchY) * (180f / PI).toFloat()

                when (event.action) {
                    MotionEvent.ACTION_DOWN,
                    MotionEvent.ACTION_MOVE -> {
                        if (angle !in -limitingAngle..limitingAngle) {
                            val fixedAngle = if (angle in -180f..-limitingAngle) {
                                360f + angle
                            } else {
                                angle
                            }
                            rotation = fixedAngle

                            val percent = (fixedAngle - limitingAngle) / (360f - 2 * limitingAngle)
                            onValueChange(percent)
                            true
                        } else false
                    }
                    else -> false
                }
            }
            .rotate(rotation)
    )
}
@Composable
fun MusicKnobWithVolumenBar() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
            .background(Color.Black),
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .border(1.dp, Color.Green, RoundedCornerShape(10.dp))
                .padding(30.dp)
        ) {
            var volume by remember {
                mutableStateOf(0f)
            }
            var barCount  = 20

            MusicKnob(
                modifier = Modifier.size(100.dp),
            ){
                volume = it
            }
            Spacer(modifier = Modifier.width(20.dp))

            VolumeBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp),
                activeBAr = (barCount * volume).roundToInt(),
                barCount = barCount
            )

        }
    }

}

@Preview(showBackground = true)
@Composable
fun ComposePreview() {
    //RowColmun()
    //TextStyling()
    //ComposeState()
    MusicKnobWithVolumenBar()
}
