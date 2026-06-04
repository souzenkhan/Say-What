package com.example.saywhatandroid

import androidx.compose.foundation.Image
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
import androidx.compose.material3.Icon
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.IntOffset
import androidx.compose.foundation.layout.offset

@Composable
fun SayWhatHomeScreen(
    onScanClick: () -> Unit,
    onAudioClick: () -> Unit,
    onHelpClick: () -> Unit,
    onBluetoothSettingsClick: () -> Unit,
    onAboutClick: () -> Unit
) {
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopHeader(onSettingsClick = onBluetoothSettingsClick)
        },
        bottomBar = {
            BottomNavigationBar(
                onHomeClick = {},
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
                .verticalScroll(scrollState)
                .padding(horizontal = 20.dp)
                .padding(bottom = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(24.dp))

            IntroSection()

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                NextButton(
                    onClick = {
                        coroutineScope.launch {
                            val nextPosition = scrollState.value + 650
                            scrollState.animateScrollTo(
                                if (nextPosition > scrollState.maxValue) scrollState.maxValue else nextPosition
                            )
                        }
                    }
                )
            }

            Spacer(modifier = Modifier.height(22.dp))

            IllustrationCard()

            Spacer(modifier = Modifier.height(18.dp))

            ReadySection()

            Spacer(modifier = Modifier.height(18.dp))

            ScanButton(onScanClick = onScanClick)

            Spacer(modifier = Modifier.height(22.dp))

            RecentVenuesSection()

            Spacer(modifier = Modifier.height(18.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                WireframeNextButton(
                    onClick = {
                        coroutineScope.launch {
                            val nextPosition = scrollState.value + 450
                            scrollState.animateScrollTo(
                                if (nextPosition > scrollState.maxValue) scrollState.maxValue else nextPosition
                            )
                        }
                    }
                )
            }

            Spacer(modifier = Modifier.height(22.dp))

            NearbyVenuesSection()

            Spacer(modifier = Modifier.height(24.dp))

            AboutLink(onAboutClick = onAboutClick)

            Spacer(modifier = Modifier.height(80.dp))
        }
    }
}

@Composable
fun TopHeader(onSettingsClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(Color.White)
            .padding(horizontal = 20.dp),
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
fun IntroSection() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Hearing clearly\nshouldn't be a luxury.",
            color = Color(0xFF2835D8),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            lineHeight = 28.sp
        )

        Spacer(modifier = Modifier.height(18.dp))

        Text(
            text = "Say What? was born from a simple\nobservation: public spaces are often\ndesigned for aesthetics, not\nacoustics. Our mission is to bridge\nthe communication gap for those\nwith hearing challenges using\nadvanced real-time audio\nprocessing.",
            color = Color(0xFF111111),
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            lineHeight = 21.sp
        )
    }
}

@Composable
fun IllustrationCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(210.dp),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clipToBounds(),
            contentAlignment = Alignment.CenterStart
        ) {
            Image(
                painter = painterResource(id = R.drawable.listening_illustration),
                contentDescription = "Listening illustration",
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .height(205.dp)
                    .offset {
                        IntOffset(x = -170, y = 0)
                    },
                contentScale = ContentScale.Fit
            )
        }
    }
}

@Composable
fun ReadySection() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Ready to Listen?",
            color = Color(0xFF17172A),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(14.dp))

        Text(
            text = "Connect to the venue audio by\nclicking on the button below",
            color = Color(0xFF151515),
            fontSize = 13.sp,
            textAlign = TextAlign.Center,
            lineHeight = 18.sp
        )
    }
}

@Composable
fun ScanButton(onScanClick: () -> Unit) {
    Button(
        onClick = onScanClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF3047E8)
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_qr_code_scanner_24),
                contentDescription = "Scan QR Code",
                tint = Color.White,
                modifier = Modifier.size(18.dp)
            )

            Spacer(modifier = Modifier.width(10.dp))

            Text(
                text = "SCAN QR CODE",
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )
        }
    }
}

@Composable
fun RecentVenuesSection() {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Recent Venues",
                color = Color(0xFF111111),
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "View All",
                color = Color(0xFF3047E8),
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        VenueCard(
            emoji = "🏛",
            title = "City Museum",
            subtitle = "Visited\nYesterday"
        )

        Spacer(modifier = Modifier.height(12.dp))

        VenueCard(
            emoji = "🎬",
            title = "Starlight\nCinema",
            subtitle = "Visited 3\ndays ago"
        )
    }
}

@Composable
fun VenueCard(
    emoji: String,
    title: String,
    subtitle: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(86.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFFE7E9FF)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = emoji,
                    fontSize = 26.sp
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    color = Color(0xFF111111),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 15.sp
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = subtitle,
                    color = Color(0xFF555555),
                    fontSize = 11.sp,
                    lineHeight = 13.sp
                )
            }

            Text(
                text = ">",
                color = Color(0xFF3047E8),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun NearbyVenuesSection() {
    Column {
        Text(
            text = "Nearby Venues",
            color = Color(0xFF111111),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        NearbyVenueItem(
            title = "St. Jude's Cathedral",
            distance = "0.2 miles away"
        )

        Spacer(modifier = Modifier.height(10.dp))

        NearbyVenueItem(
            title = "Olympic Stadium",
            distance = "0.8 miles away"
        )
    }
}

@Composable
fun NearbyVenueItem(
    title: String,
    distance: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(58.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
            .padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(34.dp)
                .clip(CircleShape)
                .background(Color(0xFF5F6DFF)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "📍",
                fontSize = 15.sp
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                color = Color(0xFF111111),
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = distance,
                color = Color(0xFF3047E8),
                fontSize = 11.sp
            )
        }

        Box(
            modifier = Modifier
                .size(34.dp)
                .clip(CircleShape)
                .background(Color(0xFFE9EBFF)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "▶",
                color = Color(0xFF3047E8),
                fontSize = 13.sp
            )
        }
    }
}

@Composable
fun AboutLink(onAboutClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onAboutClick()
            },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "ⓘ About Say What?",
            color = Color(0xFF3047E8),
            fontSize = 11.sp
        )
    }
}

@Composable
fun NextButton(onClick: () -> Unit) {
    WireframeNextButton(onClick = onClick)
}

@Composable
fun WireframeNextButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .width(72.dp)
            .height(34.dp),
        shape = RoundedCornerShape(5.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF3047E8)
        ),
        contentPadding = androidx.compose.foundation.layout.PaddingValues(0.dp)
    ) {
        Text(
            text = "NEXT",
            color = Color.White,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.5.sp
        )
    }
}

@Composable
fun BottomNavigationBar(
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
            .background(Color.White),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BottomNavItem(
            iconRes = R.drawable.baseline_home_24,
            label = "Home",
            selected = true,
            onClick = onHomeClick
        )

        BottomNavItem(
            iconRes = R.drawable.baseline_qr_code_scanner_24,
            label = "Scan",
            selected = false,
            onClick = onScanClick
        )

        BottomNavItem(
            iconRes = R.drawable.ic_volume,
            label = "Audio",
            selected = false,
            onClick = onAudioClick
        )

        BottomNavTextItem(
            icon = "?",
            label = "Help",
            selected = false,
            onClick = onHelpClick
        )
    }
}

@Composable
fun BottomNavItem(
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
fun BottomNavTextItem(
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
