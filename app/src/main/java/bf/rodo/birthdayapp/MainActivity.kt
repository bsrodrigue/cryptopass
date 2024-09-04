package bf.rodo.birthdayapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import bf.rodo.birthdayapp.ui.theme.BirthdayAppTheme

val primary = 0xFF6C48C5
val secondary = 0xFFC68FE6
val tertiary = 0xFFFFF7F7
val black = 0xFF000000

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val fontName = GoogleFont("Kanit")

val fontFamily = FontFamily(
    Font(
        googleFont = fontName,
        fontProvider = provider
    )
)

val charsetMap: Map<String, List<Char>> = mapOf(
    "uppercase" to ('A'..'Z').toList(),
    "lowercase" to ('a'..'z').toList(),
    "digits" to ('0'..'9').toList(),
    "special" to listOf('!', '@', '#', '$', '%', '^', '&', '*')
)

fun getCharset(charsetNames: List<String>): List<Char> {
    val charList = mutableListOf<Char>()

    for (name in charsetNames) {
        charsetMap[name]?.let {
            charList.addAll(it)
        }
    }

    return charList
}

fun genPassword(length: Int, charset: List<Char>): String {
    return (1..length)
        .map { charset.random() }
        .joinToString("")
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            BirthdayAppTheme(darkTheme = false, dynamicColor = false) {
                Scaffold(
                    content = { paddingValues ->
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(paddingValues)
                                .background(color = Color.White)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(horizontal = 30.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "CryptoPass",
                                    fontFamily = fontFamily,
                                    color = Color(primary),
                                    fontSize = TextUnit(value = 5f, type = TextUnitType.Em),
                                )

                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Image(
                                        painter = painterResource(id = R.drawable.lock),
                                        contentDescription = "Lock",
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(50.dp),
                                    )
                                    Text(
                                        "Easy Password Management",
                                        color = Color(secondary),
                                        fontSize = TextUnit(value = 3f, type = TextUnitType.Em),
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        "Create Strong & Secure Passwords",
                                        color = Color(primary),
                                        fontWeight = FontWeight.Bold
                                    )
                                }

                                Button(
                                    modifier = Modifier.fillMaxWidth(),
                                    onClick = {}
                                ) {
                                    Text("Get Started")
                                }
                            }
                        }
                    }
                )
            }
        }
    }

}

@Composable
fun CustomCheckbox(label: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = {
                onCheckedChange(it)
            }
        )
        Text(label)
    }

}

@Composable
fun PasswordList(items: List<String>, onClickDelete: (String) -> Unit) {
    LazyColumn {
        items(items) { item ->
            ListItem(text = item, onClickDelete)
        }
    }
}

@Composable
fun ListItem(text: String, onClickDelete: (String) -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = "Delete", style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.clickable {
                        onClickDelete(text)
                    }
                )
            }


        }
    }
}