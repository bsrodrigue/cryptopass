package bf.rodo.birthdayapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun DashboardScreen(
    viewModel: MyViewModel = viewModel(),
    onClickCreatePassword: ()-> Unit
) {
    val items by remember {
        mutableStateOf(viewModel.items)
    }

    Box(
        Modifier
            .fillMaxSize()
    ){
        FloatingActionButton(onClick = {
            onClickCreatePassword()
        },
            modifier = Modifier
                .align(Alignment.BottomEnd)
        ) {
            Icon(Icons.Filled.AddCircle, "Create Password")
        }

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            DashboardHeader()
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                "Overview",
                modifier = Modifier.padding(start = 10.dp),
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(10.dp))
            Overview(items)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    "LATEST ACCESSED PASSWORDS",
                    fontSize = TextUnit(
                        2.5f,
                        type = TextUnitType.Em
                    ),
                    fontWeight = FontWeight.Bold
                )
                Text("SEE ALL")
            }
            LazyColumn(
                contentPadding = PaddingValues(10.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {

                items(items.size) { index ->
                    PasswordListItem(items[index])
                }
            }
        }


    }
}

@Composable
fun PasswordListItem(passwordItem: PasswordItem) {
    Row {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ) {
            Box(
                modifier = Modifier
                    .height(50.dp)
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(100.dp))
                    .background(Color(primary))
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Icon(Icons.Filled.Lock, contentDescription = "Lock", tint = Color.White)
                }
            }

            Spacer(Modifier.width(10.dp))

            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    passwordItem.account,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    passwordItem.email,
                    color = Color(primary)
                )
            }
        }

        // Arrow
    }
}

@Composable
fun Overview(items: List<PasswordItem>) {
    val localConfig = LocalConfiguration.current
    val width = localConfig.screenWidthDp

    Box(
        modifier = Modifier
            .width((width * 0.8).dp)
            .height(150.dp)
            .clip(RoundedCornerShape(topEnd = 100.dp, bottomEnd = 100.dp))
            .background(Color(secondary))
            .padding(10.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .aspectRatio(1f)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(100.dp))
                    .background(Color.Black)
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Text(
                        "${items.size}", color = Color.White,
                        fontSize = TextUnit(8f, TextUnitType.Em),
                        fontWeight = FontWeight.Bold
                    )
                    Text("Passwords", color = Color.White)
                }

            }
        }
    }
}

@Composable
fun DashboardHeader() {
    val localConfig = LocalConfiguration.current
    val width = localConfig.screenWidthDp

    Column(
        horizontalAlignment = Alignment.End
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 70.dp)
                .clip(RoundedCornerShape(bottomStart = 25.dp))
                .background(Color(primary)),
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .padding(start = 20.dp)
                    .fillMaxSize()
            ) {
                Text(
                    "Dashboard",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = TextUnit(5f, TextUnitType.Em),
                )
            }
        }

        Box(
            modifier = Modifier
                .width(width.dp / 2)
                .height(35.dp)
                .clip(RoundedCornerShape(bottomStart = 25.dp))
                .background(Color(secondary)),
        )

    }
}
