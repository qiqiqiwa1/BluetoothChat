package com.example.bluetoothchat.data


import android.os.Build


data class DeviceInfo(

    val manufacturer: String,

    val model: String,

    val androidVersion: String

)



fun getDeviceInfo(): DeviceInfo {


    return DeviceInfo(

        manufacturer = Build.MANUFACTURER,

        model = Build.MODEL,

        androidVersion = Build.VERSION.RELEASE

    )


}
