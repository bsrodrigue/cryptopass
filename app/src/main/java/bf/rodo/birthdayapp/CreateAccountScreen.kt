package bf.rodo.birthdayapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CreateAccountScreen(password: String, onSubmit: (PasswordItem) -> Unit) {
    var accountName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    fun submit() {
        if (accountName.isEmpty() || email.isEmpty()) return;

        val passwordItem: PasswordItem  = PasswordItem(
            account = accountName,
            email = email,
            password = password
        )

        onSubmit(passwordItem)
    }

    Box(
        Modifier
            .fillMaxSize()
    ) {
        Column(
            Modifier
                .fillMaxSize()
        ) {
            Header("CREATE ACCOUNT")
            Spacer(Modifier.height(10.dp))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.SpaceBetween,

                ) {
                Column {
                    TextField(accountName,
                        label = {
                            Text("ACCOUNT TITLE")
                        },
                        modifier = Modifier
                            .fillMaxWidth(),
                        onValueChange = {
                            accountName = it
                        })

                    Spacer(Modifier.height(20.dp))

                    TextField(email,
                        label = {
                            Text("EMAIL ADDRESS")
                        },
                        modifier = Modifier
                            .fillMaxWidth(),
                        onValueChange = {
                            email = it
                        })

                }


                Button(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = {
                        submit()
                    }) {
                    Text("CONFIRM")
                }
            }

        }
    }
}
