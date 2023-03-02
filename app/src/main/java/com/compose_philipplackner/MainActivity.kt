package com.compose_philipplackner

import android.graphics.Paint.Align
import android.graphics.Paint.FontMetrics
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalOf
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

@Preview(showBackground = true)
@Composable
fun ComposePreview() {
    //RowColmun()
    TextStyling()
}
