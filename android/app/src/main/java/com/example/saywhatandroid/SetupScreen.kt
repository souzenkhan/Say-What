package com.example.saywhatandroid

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SetupScreen(
    onScanVenueClick: () -> Unit,
    onHelpClick: () -> Unit,
    onHomeClick: () -> Unit,
    onScanClick: () -> Unit,
    onAudioClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    Scaffold(
        bottomBar = {
            SetupBottomBar(
                onHomeClick = onHomeClick,
                onScanClick = onScanClick,
                onAudioClick = onAudioClick,
                onHelpClick = onHelpClick
            )
        },
        containerColor = Color(0xFFF8F8FF)
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SetupTopHeader(onSettingsClick = onSettingsClick)

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Connect to Venue\nAudio",
                color = Color(0xFF17172A),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                lineHeight = 30.sp
            )

            Spacer(modifier = Modifier.height(18.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(92.dp)
                            .background(Color(0xFFE9EBFF), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_qr_code_scanner_24),
                            contentDescription = "QR Code",
                            tint = Color(0xFF3047E8),
                            modifier = Modifier.size(44.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(28.dp))

                    Button(
                        onClick = onScanVenueClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(58.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF3047E8)
                        )
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_qr_code_scanner_24),
                            contentDescription = "Scan Venue QR Code",
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )

                        Spacer(modifier = Modifier.size(10.dp))

                        Text(
                            text = "Scan Venue QR Code",
                            color = Color.White,
                            fontSize = 16.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(18.dp))

                    Text(
                        text = "Look for a \"Say What?\" poster at the\ncounter or on your table.",
                        color = Color(0xFF17172A),
                        fontSize = 13.sp,
                        textAlign = TextAlign.Center,
                        lineHeight = 19.sp
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(Color(0xFFE0E0E8))
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    Text(
                        text = "ⓘ Need help connecting?",
                        color = Color(0xFF3047E8),
                        fontSize = 13.sp,
                        modifier = Modifier.clickable {
                            onHelpClick()
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun SetupBottomBar(
    onHomeClick: () -> Unit,
    onScanClick: () -> Unit,
    onAudioClick: () -> Unit,
    onHelpClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp)
            .background(Color.White)
            .padding(horizontal = 12.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SetupBottomNavItem(
            iconRes = R.drawable.baseline_home_24,
            label = "Home",
            selected = false,
            onClick = onHomeClick
        )

        SetupBottomNavItem(
            iconRes = R.drawable.baseline_qr_code_scanner_24,
            label = "Scan",
            selected = true,
            onClick = onScanClick
        )

        SetupBottomNavItem(
            iconRes = R.drawable.ic_volume,
            label = "Audio",
            selected = false,
            onClick = onAudioClick
        )

        SetupBottomNavTextItem(
            icon = "?",
            label = "Help",
            selected = false,
            onClick = onHelpClick
        )
    }
}

@Composable
fun SetupBottomNavItem(
    iconRes: Int,
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    val color = if (selected) Color(0xFF3047E8) else Color(0xFF777777)

    Column(
        modifier = Modifier.clickable {
            onClick()
        },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = label,
            tint = color,
            modifier = Modifier.size(20.dp)
        )

        Spacer(modifier = Modifier.height(3.dp))

        Text(
            text = label,
            color = color,
            fontSize = 10.sp,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
        )
    }
}
@Composable
fun SetupTopHeader(onSettingsClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(horizontal = 0.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_hearing),
            contentDescription = "Say What Logo",
            tint = Color(0xFF3047E8),
            modifier = Modifier.size(22.dp)
        )

        Text(
            text = "Say What?",
            color = Color(0xFF3047E8),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Icon(
            painter = painterResource(id = R.drawable.ic_settings),
            contentDescription = "Settings",
            tint = Color(0xFF3047E8),
            modifier = Modifier
                .size(20.dp)
                .clickable {
                    onSettingsClick()
                }
        )
    }
}
@Composable
fun SetupBottomNavTextItem(
    icon: String,
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    val color = if (selected) Color(0xFF3047E8) else Color(0xFF777777)

    Column(
        modifier = Modifier.clickable {
            onClick()
        },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = icon,
            color = color,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(3.dp))

        Text(
            text = label,
            color = color,
            fontSize = 10.sp,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
        )
    }
}

