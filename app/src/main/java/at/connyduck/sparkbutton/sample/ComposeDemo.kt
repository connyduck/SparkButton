package at.connyduck.sparkbutton.sample

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import at.connyduck.sparkbutton.compose.SparkButton

@Composable
fun ComposeDemo() {
    Column(Modifier.fillMaxWidth()) {
        var checked1 by remember { mutableStateOf(false) }

        SparkButton(
            checked = checked1,
            onCheckedChange = {
                checked1 = it
                println("callback: $checked1")
            },
            modifier =
            Modifier
                .padding(32.dp)
                .size(32.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            println("content: $checked1")
            if (checked1) {
                Image(painterResource(R.drawable.ic_heart_on), null)
            } else {
                Image(painterResource(R.drawable.ic_heart_off), null)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        SparkButton(
            checked = true,
            onCheckedChange = {},
            enabled = false,
            modifier =
            Modifier
                .padding(32.dp)
                .size(32.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Image(painterResource(R.drawable.ic_heart_on), null)
        }

        Spacer(modifier = Modifier.height(16.dp))

        var checked2 by remember { mutableStateOf(true) }

        SparkButton(
            checked = checked2,
            onCheckedChange = { checked2 = it },
            modifier =
            Modifier
                .padding(32.dp)
                .size(80.dp)
                .align(Alignment.CenterHorizontally),
            primaryColor = colorResource(id = R.color.facebook_primary_color),
            secondaryColor = colorResource(id = R.color.facebook_secondary_color),
        ) {
            Image(painter = painterResource(R.drawable.ic_thumb), contentDescription = "")
        }
    }
}
