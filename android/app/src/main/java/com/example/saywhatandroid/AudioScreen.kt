package com.example.saywhatandroid

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AudioScreen(
    bluetoothDeviceName: String,
    bluetoothStatus: String,
    playbackStatus: String,
    onHomeClick: () -> Unit,
    onScanClick: () -> Unit,
    onHelpClick: () -> Unit,
    onSettingsClick: () -> Unit
){
    var volume by remember { mutableStateOf(0.75f) }
    val scrollState = rememberScrollState()

    Scaffold(
        bottomBar = {
            AudioBottomBar(
                onHomeClick = onHomeClick,
                onScanClick = onScanClick,
                onAudioClick = {},
                onHelpClick = onHelpClick
            )
        },
        containerColor = Color(0xFFF8F8FF)
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(scrollState)
                .padding(horizontal = 20.dp)
                .padding(bottom = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AudioTopHeader(onSettingsClick = onSettingsClick)

            Spacer(modifier = Modifier.height(24.dp))

            AudioSessionCard(playbackStatus = playbackStatus)

            Spacer(modifier = Modifier.height(18.dp))

            BluetoothStatusCard(
                bluetoothDeviceName = bluetoothDeviceName,
                bluetoothStatus = bluetoothStatus
            )

            Spacer(modifier = Modifier.height(24.dp))

            VolumeCard(
                volume = volume,
                onVolumeChange = {
                    volume = it
                }
            )

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun AudioTopHeader(onSettingsClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_hearing),
            contentDescription = "Say What Logo",
            tint = Color(0xFF3047E8),
            modifier = Modifier.size(24.dp)
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
                .size(22.dp)
                .clickable {
                    onSettingsClick()
                }
        )
    }
}

@Composable
fun AudioSessionCard(playbackStatus: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(215.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(18.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                PlaybackStatusBadge(playbackStatus = playbackStatus)

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = when (playbackStatus) {
                        "Connecting" -> "Connecting to audio..."
                        "Live" -> "Session Active"
                        "Paused" -> "Session Paused"
                        else -> "Session Stopped"
                    },
                    color = Color(0xFF17172A),
                    fontSize = 13.sp
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Grand Concert Hall",
                color = Color(0xFF17172A),
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "◷  00:42:17",
                color = Color(0xFF333333),
                fontSize = 13.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            WaveformBox()
        }
    }
}
@Composable
fun PlaybackStatusBadge(playbackStatus: String) {
    val badgeColor = when (playbackStatus) {
        "Connecting" -> Color(0xFFFFA000)
        "Live" -> Color(0xFF0B8F3A)
        "Paused" -> Color(0xFF777777)
        else -> Color(0xFFB00020)
    }

    val badgeText = when (playbackStatus) {
        "Connecting" -> "● CONNECTING"
        "Live" -> "● LIVE"
        "Paused" -> "● PAUSED"
        else -> "● STOPPED"
    }

    Box(
        modifier = Modifier
            .height(24.dp)
            .width(92.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(badgeColor),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = badgeText,
            color = Color.White,
            fontSize = 9.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
@Composable
fun BluetoothStatusCard(
    bluetoothDeviceName: String,
    bluetoothStatus: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(92.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFE9EBFF)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "BT",
                    color = Color(0xFF3047E8),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Bluetooth Output",
                    color = Color(0xFF17172A),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = bluetoothDeviceName,
                    color = Color(0xFF3047E8),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = bluetoothStatus,
                    color = Color(0xFF555555),
                    fontSize = 11.sp
                )
            }
        }
    }
}

@Composable
fun WaveformBox() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(96.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(Color(0xFFEFEFFF)),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.height(78.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AnimatedWaveBar(startHeight = 22f, endHeight = 58f, duration = 650)
            AnimatedWaveBar(startHeight = 48f, endHeight = 20f, duration = 720)
            AnimatedWaveBar(startHeight = 30f, endHeight = 66f, duration = 580)
            AnimatedWaveBar(startHeight = 62f, endHeight = 26f, duration = 800)
            AnimatedWaveBar(startHeight = 18f, endHeight = 52f, duration = 690)
            AnimatedWaveBar(startHeight = 44f, endHeight = 72f, duration = 760)
            AnimatedWaveBar(startHeight = 70f, endHeight = 34f, duration = 610)
            AnimatedWaveBar(startHeight = 28f, endHeight = 60f, duration = 850)
            AnimatedWaveBar(startHeight = 56f, endHeight = 24f, duration = 700)
            AnimatedWaveBar(startHeight = 20f, endHeight = 46f, duration = 640)
            AnimatedWaveBar(startHeight = 40f, endHeight = 68f, duration = 790)
        }
    }
}

@Composable
fun AnimatedWaveBar(
    startHeight: Float,
    endHeight: Float,
    duration: Int
) {
    val infiniteTransition = rememberInfiniteTransition(label = "waveAnimation")

    val animatedHeight by infiniteTransition.animateFloat(
        initialValue = startHeight,
        targetValue = endHeight,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = duration),
            repeatMode = RepeatMode.Reverse
        ),
        label = "waveHeight"
    )

    Box(
        modifier = Modifier
            .width(6.dp)
            .height(animatedHeight.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(Color(0xFF4B55E7))
    )
}

@Composable
fun VolumeCard(
    volume: Float,
    onVolumeChange: (Float) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 22.dp, vertical = 20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_volume),
                    contentDescription = "Volume",
                    tint = Color(0xFF3047E8),
                    modifier = Modifier.size(24.dp)
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = "VOLUME",
                    color = Color(0xFF17172A),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )

                Text(
                    text = "${(volume * 100).toInt()}%",
                    color = Color(0xFF3047E8),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Slider(
                value = volume,
                onValueChange = onVolumeChange,
                colors = SliderDefaults.colors(
                    thumbColor = Color(0xFF4B55E7),
                    activeTrackColor = Color(0xFF4B55E7),
                    inactiveTrackColor = Color(0xFFDDE1FF)
                )
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Min",
                    color = Color(0xFF17172A),
                    fontSize = 12.sp
                )

                Text(
                    text = "Max",
                    color = Color(0xFF17172A),
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Composable
fun AudioBottomBar(
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
        AudioBottomNavIcon(
            iconRes = R.drawable.baseline_home_24,
            label = "Home",
            selected = false,
            onClick = onHomeClick
        )

        AudioBottomNavIcon(
            iconRes = R.drawable.baseline_qr_code_scanner_24,
            label = "Scan",
            selected = false,
            onClick = onScanClick
        )

        AudioBottomNavIcon(
            iconRes = R.drawable.ic_volume,
            label = "Audio",
            selected = true,
            onClick = onAudioClick
        )

        AudioBottomNavText(
            icon = "?",
            label = "Help",
            selected = false,
            onClick = onHelpClick
        )
    }
}

@Composable
fun AudioBottomNavIcon(
    iconRes: Int,
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    val color = if (selected) Color.White else Color(0xFF777777)
    val backgroundColor = if (selected) Color(0xFF4B55E7) else Color.Transparent

    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(28.dp))
            .background(backgroundColor)
            .clickable {
                onClick()
            }
            .padding(
                horizontal = if (selected) 18.dp else 0.dp,
                vertical = if (selected) 8.dp else 0.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = label,
            tint = color,
            modifier = Modifier.size(18.dp)
        )

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = label,
            color = color,
            fontSize = 10.sp,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
        )
    }
}

@Composable
fun AudioBottomNavText(
    icon: String,
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    val color = if (selected) Color.White else Color(0xFF777777)
    val backgroundColor = if (selected) Color(0xFF4B55E7) else Color.Transparent

    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(28.dp))
            .background(backgroundColor)
            .clickable {
                onClick()
            }
            .padding(
                horizontal = if (selected) 18.dp else 0.dp,
                vertical = if (selected) 8.dp else 0.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = icon,
            color = color,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = label,
            color = color,
            fontSize = 10.sp,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
        )
    }
}