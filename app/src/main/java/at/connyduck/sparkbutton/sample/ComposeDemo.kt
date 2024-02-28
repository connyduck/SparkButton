package at.connyduck.sparkbutton.sample

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
        var selected1 by remember { mutableStateOf(false) }

        SparkButton(
            icon =
            if (selected1) {
                painterResource(R.drawable.ic_heart_on)
            } else {
                painterResource(R.drawable.ic_heart_off)
            },
            active = selected1,
            onClick = {
                selected1 = !selected1
            },
            modifier =
            Modifier
                .padding(32.dp)
                .size(32.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        SparkButton(
            icon = painterResource(R.drawable.ic_thumb),
            active = true,
            onClick = { },
            modifier =
            Modifier
                .padding(32.dp)
                .size(80.dp)
                .align(Alignment.CenterHorizontally),
            primaryColor = colorResource(id = R.color.facebook_primary_color),
            secondaryColor = colorResource(id = R.color.facebook_secondary_color)
        )

        Spacer(modifier = Modifier.height(16.dp))

        var selected2 by remember { mutableStateOf(false) }

        SparkButton(
            icon = painterResource(id = R.drawable.empty),
            active = selected2,
            onClick = {
                selected2 = !selected2
            },
            modifier =
            Modifier
                .padding(32.dp)
                .size(64.dp),
            animationSpeed = 0.1f
        )
    }
}
