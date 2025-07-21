/* Copyright 2024 Conny Duck
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */

package at.connyduck.sparkbutton.sample

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import at.connyduck.sparkbutton.compose.SparkButton
import at.connyduck.sparkbutton.compose.rememberSparkButtonState
import kotlinx.coroutines.delay

@Composable
fun ComposeDemo() {
    Column(
        Modifier.fillMaxWidth()
    ) {
        DemoRow(R.string.heart_standard_description) {
            val sparkButtonState = rememberSparkButtonState()
            var checked by remember { mutableStateOf(false) }

            SparkButton(
                animateOnClick = !checked,
                onClick = {
                    checked = !checked
                },
                state = sparkButtonState,
                modifier = Modifier
                    .padding(16.dp)
                    .size(32.dp)
            ) {
                if (checked) {
                    Image(painterResource(R.drawable.ic_heart_on), stringResource(R.string.unlike))
                } else {
                    Image(painterResource(R.drawable.ic_heart_off), stringResource(R.string.like))
                }
            }

            LaunchedEffect(Unit) {
                delay(3000)
                checked = true
                sparkButtonState.animate()
            }
        }

        DemoRow(R.string.heart_disabled_description) {
            SparkButton(
                onClick = {},
                enabled = false,
                modifier = Modifier
                    .padding(16.dp)
                    .size(32.dp)
            ) {
                Image(painterResource(R.drawable.ic_heart_on), null)
            }
        }

        DemoRow(R.string.heart_slow_description) {
            SparkButton(
                onClick = {},
                modifier = Modifier
                    .padding(16.dp)
                    .size(32.dp),
                animationSpeed = 0.1f
            ) {
                Image(painterResource(R.drawable.ic_heart_on), null)
            }
        }

        DemoRow(R.string.thumbs_description) {
            SparkButton(
                onClick = { },
                modifier = Modifier
                    .padding(32.dp)
                    .size(64.dp)
                    .align(Alignment.CenterHorizontally),
                primaryColor = colorResource(id = R.color.thumb_primary_color),
                secondaryColor = colorResource(id = R.color.thumb_secondary_color),
                animationSpeed = 0.5f
            ) {
                Image(painter = painterResource(R.drawable.ic_thumb), contentDescription = stringResource(R.string.thumbs_up))
            }
        }

        DemoRow(R.string.text_description) {
            SparkButton(
                onClick = { },
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally),
                primaryColor = colorResource(id = R.color.thumb_primary_color),
                secondaryColor = colorResource(id = R.color.thumb_secondary_color),
                animationSpeed = 0.5f
            ) {
                Text("Click me!", fontSize = 24.sp)
            }
        }
    }
}

@Composable
fun DemoRow(
    @StringRes text: Int,
    content: @Composable () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp, vertical = 16.dp)
    ) {
        Text(
            text = stringResource(id = text),
            fontSize = 16.sp,
            modifier = Modifier.weight(1f)
        )
        content()
    }
}

@Preview
@Composable
fun ComposeDemoPreview() {
    ComposeDemo()
}
