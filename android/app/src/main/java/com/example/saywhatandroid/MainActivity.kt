package com.example.saywhatandroid

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.content.Intent
import android.content.pm.PackageManager
import android.media.AudioAttributes
import android.media.AudioDeviceCallback
import android.media.AudioDeviceInfo
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import android.media.AudioAttributes
import android.media.AudioDeviceCallback
import android.util.Log
import android.net.Uri

data class DeviceItem(
    val name: String,
    val address: String,
    val isConnected: Boolean
)

class MainActivity : ComponentActivity() {

    private var bluetoothAdapter: BluetoothAdapter? = null
    private lateinit var audioManager: AudioManager
    private var mediaPlayer: MediaPlayer? = null
    private var audioDeviceCallback: AudioDeviceCallback? = null
    private val STREAM_URL = "http://10.14.143.36:3000/audio"
    private var wasPlayingBeforeDeviceChange = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bluetoothManager = getSystemService(BLUETOOTH_SERVICE) as BluetoothManager
        bluetoothAdapter = bluetoothManager.adapter
        audioManager = getSystemService(AUDIO_SERVICE) as AudioManager

        setContent {
            var devices by remember { mutableStateOf(emptyList<DeviceItem>()) }
            var statusText by remember { mutableStateOf("Not checked yet") }
            var playbackStatus by remember { mutableStateOf("Stopped") }

            val permissionLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.RequestMultiplePermissions()
            ) {
                devices = loadBluetoothDevices()
                statusText = getAudioConnectionStatus()
            }

            LaunchedEffect(Unit) {
                requestNeededPermissions { permissions ->
                    permissionLauncher.launch(permissions)
                }

                statusText = getAudioConnectionStatus()
                logAudioDevices()
            }

            DisposableEffect(Unit) {
                setupAudioDeviceCallback { newStatus ->
                    statusText = newStatus
                }

                onDispose {
                    audioDeviceCallback?.let {
                        audioManager.unregisterAudioDeviceCallback(it)
                    }
                    releasePlayer()
                }
            }

            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Bluetooth Audio Devices",
                            style = MaterialTheme.typography.headlineSmall
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Text("Connection status: $statusText")

                        Spacer(modifier = Modifier.height(16.dp))

                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                Button(onClick = {
                                    devices = loadBluetoothDevices()
                                    statusText = getAudioConnectionStatus()
                                }) {
                                    Text("Refresh")
                                }

                                Button(onClick = {
                                    startActivity(Intent(Settings.ACTION_BLUETOOTH_SETTINGS))
                                }) {
                                    Text("Bluetooth Settings")
                                }
                            }

                            Button(onClick = {
                                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                                    data = Uri.parse("package:$packageName")
                                }
                                startActivity(intent)
                            }) {
                                Text("App Settings")
                            }
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        Text(
                            text = "Live Stream Playback",
                            style = MaterialTheme.typography.headlineSmall
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Text("Playback status: $playbackStatus")

                        Spacer(modifier = Modifier.height(12.dp))

                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Button(onClick = {
                                logAudioDevices()
                                playAudio(STREAM_URL)
                                wasPlayingBeforeDeviceChange = true
                                playbackStatus = "Playing"
                            }) {
                                Text("Play")
                            }

                            Button(onClick = {
                                pausePlayback()
                                wasPlayingBeforeDeviceChange = false
                                playbackStatus = "Paused"
                            }) {
                                Text("Pause")
                            }

                            Button(onClick = {
                                stopPlayback()
                                wasPlayingBeforeDeviceChange = false
                                playbackStatus = "Stopped"
                            }) {
                                Text("Stop")
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        if (devices.isEmpty()) {
                            Text("No paired Bluetooth devices found.")
                        } else {
                            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                items(devices) { device ->
                                    Card(modifier = Modifier.fillMaxWidth()) {
                                        Column(modifier = Modifier.padding(12.dp)) {
                                            Text(device.name)
                                            Text(device.address)
                                            Text(
                                                if (device.isConnected) {
                                                    "Connected"
                                                } else {
                                                    "Not connected"
                                                }
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun playAudio(source: String) {
        try {
            if (mediaPlayer != null) {
                mediaPlayer?.start()
                return
            }

            if (source.startsWith("http://") || source.startsWith("https://")) {
                mediaPlayer = MediaPlayer().apply {
                    setAudioAttributes(
                        AudioAttributes.Builder()
                            .setUsage(AudioAttributes.USAGE_MEDIA)
                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                            .build()
                    )

                    setDataSource(source)

                    setOnPreparedListener { player ->
                        player.start()
                    }

                    setOnErrorListener { _, what, extra ->
                        Log.e("AUDIO", "Playback error: what=$what extra=$extra")
                        releasePlayer()
                        true
                    }

                    prepareAsync()
                }
            } else {
                mediaPlayer = MediaPlayer.create(this, R.raw.sample_audio)
                mediaPlayer?.start()
            }

            mediaPlayer?.setOnCompletionListener {
                stopPlayback()
            }

        } catch (e: Exception) {
            e.printStackTrace()
            releasePlayer()
        }
    }

    private fun pausePlayback() {
        try {
            if (mediaPlayer?.isPlaying == true) {
                mediaPlayer?.pause()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun stopPlayback() {
        try {
            mediaPlayer?.stop()
            mediaPlayer?.release()
            mediaPlayer = null
        } catch (e: Exception) {
            e.printStackTrace()
            mediaPlayer = null
        }
    }

    private fun releasePlayer() {
        try {
            mediaPlayer?.release()
            mediaPlayer = null
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun setupAudioDeviceCallback(onStatusChange: (String) -> Unit) {
        audioDeviceCallback = object : AudioDeviceCallback() {
            override fun onAudioDevicesAdded(addedDevices: Array<out AudioDeviceInfo>) {
                logAudioDevices()
                onStatusChange(getAudioConnectionStatus())
            }

            override fun onAudioDevicesRemoved(removedDevices: Array<out AudioDeviceInfo>) {
                logAudioDevices()
                onStatusChange(getAudioConnectionStatus())

                val removedBluetoothDevice = removedDevices.any { isBluetoothAudioDevice(it) }

                if (removedBluetoothDevice && wasPlayingBeforeDeviceChange) {
                    try {
                        mediaPlayer?.let { player ->
                            if (!player.isPlaying) {
                                player.start()
                            }
                        }
                    } catch (e: Exception) {
                        Log.e("AUDIO", "Restarting playback after Bluetooth disconnect", e)
                        releasePlayer()
                        playAudio(STREAM_URL)
                    }
                }
            }
        }

        audioManager.registerAudioDeviceCallback(audioDeviceCallback, null)
    }
    private fun logAudioDevices() {
        Log.d("AUDIO", getAudioConnectionStatus())

        audioManager.getDevices(AudioManager.GET_DEVICES_OUTPUTS).forEach {
            Log.d("AUDIO_DEVICE", "type=${it.type}, name=${it.productName}")
        }
    }

    private fun requestNeededPermissions(permissionLauncher: (Array<String>) -> Unit) {
        val permissions = mutableListOf<String>()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.BLUETOOTH_SCAN
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                permissions.add(Manifest.permission.BLUETOOTH_SCAN)
            }

            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.BLUETOOTH_CONNECT
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                permissions.add(Manifest.permission.BLUETOOTH_CONNECT)
            }
        } else {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                permissions.add(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }

        if (permissions.isNotEmpty()) {
            permissionLauncher(permissions.toTypedArray())
        }
    }

    private fun loadBluetoothDevices(): List<DeviceItem> {
        val connectedAudioNames = getConnectedAudioDeviceNames()
        val result = mutableListOf<DeviceItem>()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S &&
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.BLUETOOTH_CONNECT
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return emptyList()
        }

        val bondedDevices: Set<BluetoothDevice> = bluetoothAdapter?.bondedDevices ?: emptySet()
        for (device in bondedDevices) {
            val name = device.name ?: "Unknown Device"
            result.add(
                DeviceItem(
                    name = name,
                    address = device.address ?: "No address",
                    isConnected = connectedAudioNames.contains(name)
                )
            )
        }

        return result.sortedBy { it.name }
    }

    private fun getConnectedAudioDeviceNames(): Set<String> {
        val outputs = audioManager.getDevices(AudioManager.GET_DEVICES_OUTPUTS)
        val connectedNames = mutableSetOf<String>()

        for (device in outputs) {
            if (isBluetoothAudioDevice(device)) {
                connectedNames.add(device.productName?.toString() ?: "Bluetooth Audio")
            }
        }

        return connectedNames
    }

    private fun isBluetoothAudioDevice(device: AudioDeviceInfo): Boolean {
        return device.type == AudioDeviceInfo.TYPE_BLUETOOTH_A2DP ||
                device.type == AudioDeviceInfo.TYPE_BLUETOOTH_SCO ||
                (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P &&
                        device.type == AudioDeviceInfo.TYPE_HEARING_AID) ||
                (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S &&
                        device.type == AudioDeviceInfo.TYPE_BLE_HEADSET)
    }

    private fun getAudioConnectionStatus(): String {
        val outputs = audioManager.getDevices(AudioManager.GET_DEVICES_OUTPUTS)
        val bluetoothConnected = outputs.any { isBluetoothAudioDevice(it) }

        return if (bluetoothConnected) {
            "Bluetooth audio device connected"
        } else {
            "No Bluetooth audio device connected"
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        releasePlayer()
    }
}