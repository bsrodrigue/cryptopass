package bf.rodo.birthdayapp

import android.app.Activity
import android.os.Bundle
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import androidx.room.Room
import bf.rodo.birthdayapp.ui.theme.BirthdayAppTheme
import kotlinx.serialization.Serializable

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

@Serializable
object Onboarding

@Serializable
object Dashboard

@Serializable
object CreatePassword

@Serializable
data class CreateAccount(val password: String)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        setContent {
            val navController = rememberNavController()
            val viewModel: MyViewModel = MyViewModel()

            BirthdayAppTheme(darkTheme = false, dynamicColor = false) {
                val localContext = LocalContext.current
                val activity = localContext as Activity
                val window: Window = activity.window
                window.statusBarColor = primary.toInt()

                Scaffold(
                    content = { paddingValues ->
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(paddingValues)
                                .background(color = Color.White)
                        ) {

                            NavHost(navController, Onboarding) {
                                composable<Onboarding> {
                                    OnboardingScreen(
                                        onSubmit = {
                                            navController.navigate(route = Dashboard)
                                        }
                                    )
                                }
                                composable<Dashboard> {
                                    DashboardScreen(
                                        viewModel = viewModel,
                                        onClickCreatePassword = {
                                            navController.navigate(route = CreatePassword)
                                        }
                                    )
                                }
                                composable<CreatePassword> {
                                    CreatePasswordScreen(
                                        onClickNext = {
                                            navController.navigate(route = CreateAccount(it))
                                        }
                                    )
                                }
                                composable<CreateAccount> { backStackEntry ->
                                    val createAccount: CreateAccount = backStackEntry.toRoute()
                                    CreateAccountScreen(
                                        password = createAccount.password,
                                        onSubmit = { passwordItem ->
                                            navController.navigate(route = Dashboard)
                                            viewModel.addItem(passwordItem)
                                        })
                                }
                            }
                        }
                    }
                )
            }
        }
    }
}