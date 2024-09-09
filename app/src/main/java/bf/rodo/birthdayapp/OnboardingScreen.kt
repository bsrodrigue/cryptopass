package bf.rodo.birthdayapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp

@Composable
fun OnboardingScreen(onSubmit: () -> Unit) {
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

        Button (
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                onSubmit()
            }
        ) {
            Text("Get Started")
        }
    }
}
