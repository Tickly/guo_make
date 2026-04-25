# 锅造（GuoZao）

最小可用 Android 示例：包含桌面小组件：**日期** 与 **可配置文本（纯数字也可）**。

## 应用信息
- **应用名**：锅造
- **applicationId**：`com.taoguo.guo_make`
- **小组件**：桌面 App Widget（RemoteViews）
  - 日期小组件：显示当前年月日（`yyyy-MM-dd`）
  - 文本小组件：添加时弹出配置页，输入后单行居中显示

## HyperOS 安装后找不到小组件？（重要）
如果你是 **HyperOS 3**（或类似小米系统），通过三方渠道安装后可能默认禁用部分“桌面能力”。请到：

`设置 -> 应用管理 -> 锅造 -> 权限管理/其他权限 -> 打开「桌面快捷方式」`

否则可能出现：
- 小组件列表找不到“锅造”
- 点击“添加小部件”提示已发起，但没有系统弹窗

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

