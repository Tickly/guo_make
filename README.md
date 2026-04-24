# 锅造（GuoZao）

最小可用 Android 示例：包含一个桌面小组件，展示 **当前年月日（yyyy-MM-dd）**。

## 应用信息
- **应用名**：锅造
- **applicationId**：`com.taoguo.guo_make`
- **小组件**：桌面 App Widget（RemoteViews）

## GitHub Actions 云端构建（release 签名 APK）
本仓库提供 workflow：`.github/workflows/android-release-apk.yml`（手动触发）。

### 需要配置的 GitHub Secrets
在仓库 `Settings -> Secrets and variables -> Actions -> Repository secrets` 中添加：

- `ANDROID_KEYSTORE_BASE64`：你的 `*.jks` 文件 base64 编码后的内容
- `KEYSTORE_PASSWORD`：keystore 密码
- `KEY_ALIAS`：key alias
- `KEY_PASSWORD`：key password

### 获取 APK
运行 workflow 后，在 GitHub Actions 的运行记录页面下载 artifact：`app-release-apk`。

