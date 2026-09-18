package com.example.bluetoothchat

import android.Manifest
import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothServerSocket
import android.bluetooth.BluetoothSocket
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.io.PrintWriter
import java.util.UUID
import java.util.concurrent.Executors

private val APP_UUID: UUID = UUID.fromString("8f1b7c20-3f0e-4f2f-ae5b-1a6b1b3c8d21")

data class ChatMessage(val text: String, val mine: Boolean)

class BluetoothChatManager(private val context: Context) {
    private val adapter: BluetoothAdapter? = (context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager).adapter
    private val executor = Executors.newCachedThreadPool()
    private var socket: BluetoothSocket? = null
    private var writer: PrintWriter? = null
    private var server: BluetoothServerSocket? = null
    var onMessage: ((String) -> Unit)? = null
    var onStatus: ((String) -> Unit)? = null

    @SuppressLint("MissingPermission")
    fun startServer() {
        executor.execute {
            try {
                server = adapter?.listenUsingRfcommWithServiceRecord("BluetoothChat", APP_UUID)
                onStatus?.invoke("等待另一台手机连接…")
                val s = server?.accept()
                socket = s
                server?.close()
                setupStreams(s)
                onStatus?.invoke("已连接")
            } catch (e: Exception) { onStatus?.invoke("等待连接失败：${e.message}") }
        }
    }

    @SuppressLint("MissingPermission")
    fun connect(device: BluetoothDevice) {
        executor.execute {
            try {
                onStatus?.invoke("正在连接 ${device.name ?: device.address}…")
                val s = device.createRfcommSocketToServiceRecord(APP_UUID)
                adapter?.cancelDiscovery()
                s.connect()
                socket = s
                setupStreams(s)
                onStatus?.invoke("已连接")
            } catch (e: Exception) { onStatus?.invoke("连接失败：${e.message}") }
        }
    }

    private fun setupStreams(s: BluetoothSocket?) {
        if (s == null) return
        writer = PrintWriter(OutputStreamWriter(s.outputStream), true)
        executor.execute {
            try {
                val reader = BufferedReader(InputStreamReader(s.inputStream))
                while (true) {
                    val line = reader.readLine() ?: break
                    onMessage?.invoke(line)
                }
            } catch (_: Exception) { onStatus?.invoke("连接已断开") }
        }
    }

    fun send(text: String) { writer?.println(text) }

    @SuppressLint("MissingPermission")
    fun pairedDevices(): List<BluetoothDevice> = adapter?.bondedDevices?.toList() ?: emptyList()

    @SuppressLint("MissingPermission")
    fun enableBluetooth() {
        if (adapter?.isEnabled == false) context.startActivity(Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE))
    }

    fun close() { try { socket?.close() } catch (_: Exception) {}; try { server?.close() } catch (_: Exception) {} }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { BluetoothChatApp() }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BluetoothChatApp() {
    val context = androidx.compose.ui.platform.LocalContext.current
    val manager = remember { BluetoothChatManager(context) }
    var messages by remember { mutableStateOf(listOf<ChatMessage>()) }
    var input by remember { mutableStateOf("") }
    var status by remember { mutableStateOf("未连接") }
    var devices by remember { mutableStateOf(emptyList<BluetoothDevice>()) }
    var showDevices by remember { mutableStateOf(false) }

    val permissions = if (android.os.Build.VERSION.SDK_INT >= 31) arrayOf(Manifest.permission.BLUETOOTH_CONNECT, Manifest.permission.BLUETOOTH_SCAN) else emptyArray()
    val permissionLauncher = rememberLauncherForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
        devices = manager.pairedDevices()
    }

    DisposableEffect(Unit) {
        manager.onStatus = { status = it }
        manager.onMessage = { text -> messages = messages + ChatMessage(text, false) }
        onDispose { manager.close() }
    }

    LaunchedEffect(Unit) {
        if (permissions.any { ContextCompat.checkSelfPermission(context, it) != PackageManager.PERMISSION_GRANTED }) permissionLauncher.launch(permissions)
        manager.enableBluetooth()
    }

    Scaffold(topBar = { TopAppBar(title = { Text("蓝牙聊天") }, actions = {
        TextButton(onClick = { devices = manager.pairedDevices(); showDevices = true }) { Text("连接") }
    }) }) { padding ->
        Column(Modifier.fillMaxSize().padding(padding)) {
            Text(status, modifier = Modifier.padding(16.dp))
            HorizontalDivider()
            LazyColumn(Modifier.weight(1f).fillMaxWidth().padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(messages) { msg ->
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = if (msg.mine) Arrangement.End else Arrangement.Start) {
                        Surface(tonalElevation = 2.dp, shape = MaterialTheme.shapes.medium) { Text(msg.text, Modifier.padding(12.dp)) }
                    }
                }
            }
            Row(Modifier.fillMaxWidth().padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                OutlinedTextField(input, { input = it }, Modifier.weight(1f), placeholder = { Text("输入消息…") }, singleLine = true)
                Spacer(Modifier.width(8.dp))
                Button(onClick = { val t = input.trim(); if (t.isNotEmpty()) { manager.send(t); messages = messages + ChatMessage(t, true); input = "" } }) { Text("发送") }
            }
        }
    }

    if (showDevices) {
        AlertDialog(onDismissRequest = { showDevices = false }, title = { Text("已配对设备") }, text = {
            if (devices.isEmpty()) Text("没有已配对的蓝牙设备。请先在 Android 系统蓝牙设置中与另一台手机配对。")
            else Column { devices.forEach { d ->
                TextButton(onClick = { manager.connect(d); showDevices = false }) { Text(d.name ?: d.address) }
            } }
        }, confirmButton = { TextButton(onClick = { showDevices = false }) { Text("关闭") } }, dismissButton = { TextButton(onClick = { manager.startServer(); showDevices = false }) { Text("等待连接") } })
    }
}
