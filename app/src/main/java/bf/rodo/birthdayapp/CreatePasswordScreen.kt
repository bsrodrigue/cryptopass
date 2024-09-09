package bf.rodo.birthdayapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import kotlinx.serialization.Serializable

val largeTextUnit = TextUnit(3f, TextUnitType.Em)
val smallTextUnit = TextUnit(1.5f, TextUnitType.Em)

@Serializable
data class PasswordItem(val account: String, val email: String, val password: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatePasswordScreen(onClickNext: (password: String)-> Unit) {
    var passwordLength by remember { mutableIntStateOf(8) }

    var password by remember { mutableStateOf("") }

    var hasUppercase by remember { mutableStateOf(false) }
    var hasLowerCase by remember { mutableStateOf(false) }
    var hasNumeric by remember { mutableStateOf(false) }
    var hasSpecial by remember { mutableStateOf(false) }


    val interactionSource = remember {
        MutableInteractionSource()
    }

    fun submit() {
        val charsetList: MutableList<String> = mutableListOf()

        if (hasUppercase) charsetList.add("uppercase")
        if (hasLowerCase) charsetList.add("lowercase")
        if (hasNumeric) charsetList.add("digits")
        if (hasSpecial) charsetList.add("special")

        if (charsetList.isEmpty()) return

        val charset = getCharset(charsetList)

        password = genPassword(passwordLength, charset)
    }

    Box(
        Modifier
            .fillMaxSize()
    ) {
        Column(
            Modifier
                .fillMaxSize()
        ) {
            Header("GENERATE PASSWORD")
            Spacer(Modifier.height(10.dp))
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                Column {
                    if (password.isNotEmpty())
                        Text(
                            password, modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            fontSize = largeTextUnit
                        )
                    else
                        Text(
                            "No Password", modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            fontSize = largeTextUnit
                        )

                    Text("Press here to generate",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .graphicsLayer(alpha = 0.5f)
                            .padding(top = 10.dp)
                            .clickable {
                                submit()
                            })

                    Column(
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 15.dp)
                    ) {
                        Slider(
                            value = passwordLength.toFloat(),
                            valueRange = 8f..24f,
                            interactionSource = interactionSource,
                            steps = 24 - 8,
                            onValueChange = {
                                passwordLength = it.toInt()
                            },
                            track = {
                                SliderDefaults.Track(
                                    sliderState = it,
                                    modifier = Modifier.scale(
                                        scaleX = 1f,
                                        scaleY = 1f
                                    )
                                )
                            },

                            thumb = {
                                Box {
                                    SliderDefaults.Thumb(
                                        interactionSource = interactionSource
                                    )

                                    Text(
                                        "$passwordLength",
                                        fontWeight = FontWeight.Bold,
                                        color = Color(primary),
                                        modifier = Modifier
                                            .offset(y = 20.dp)
                                    )
                                }
                            }
                        )

                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("8")
                            Text("24")
                        }


                        Spacer(Modifier.height(20.dp))
                        Text("PASSWORD CHARACTERS", fontWeight = FontWeight.Bold)

                        CustomCheckbox("UPPERCASE", hasUppercase) { hasUppercase = it }
                        CustomCheckbox("LOWERCASE", hasLowerCase) { hasLowerCase = it }
                        CustomCheckbox("NUMERIC", hasNumeric) { hasNumeric = it }
                        CustomCheckbox("SPECIAL CHARACTERS", hasSpecial) { hasSpecial = it }
                    }

                }

                Button(
                    enabled = password.isNotEmpty(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        ,
                    onClick = {
                        if(password.isEmpty()) return@Button

                        onClickNext(password)
                    }
                ) {
                    Text("NEXT")
                }
            }

        }
    }
}

@Composable
fun CustomCheckbox(label: String, checked: Boolean = false, onClick: (checked: Boolean) -> Unit) {
    var _checked by remember { mutableStateOf(checked) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(label,
            fontSize = smallTextUnit,
            fontWeight = FontWeight.Bold
        )

        Checkbox(
            checked = _checked,
            onCheckedChange = {
                _checked = it
                onClick(it)
            },
        )
    }

}

@Composable
fun Header(title: String = "Header") {
    Box(
        Modifier
            .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
            .background(Color(primary))
            .padding(20.dp)
            .fillMaxWidth()
    ) {
        Text(
            title,
            textAlign = TextAlign.Center,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = TextUnit(
                value = 4.5f,
                type = TextUnitType.Em,
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}