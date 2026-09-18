# 蓝牙聊天 Android App

第一版使用 Android Bluetooth Classic RFCOMM，支持两台 Android 手机在系统蓝牙配对后进行文字聊天。

## 使用
1. 两台手机打开系统蓝牙并互相配对。
2. 安装 APK。
3. 一台点“连接”→“等待连接”。
4. 另一台点“连接”→选择已配对设备。
5. 连接成功后即可发送文字。

## 构建
项目包含 GitHub Actions。把整个项目上传到 GitHub 后，Actions 会自动构建 `app-debug.apk`，在运行记录的 Artifacts 中下载。
