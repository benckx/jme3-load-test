import com.jme3.system.AppSettings

fun main() {
    val settings = AppSettings(true)
    settings.isFullscreen = false
    settings.setResolution(1920, 1080)
    settings.samples = 16
    settings.title = "Load Test"

    val simpleApp = LoadTestApp()
    simpleApp.setSettings(settings)
    simpleApp.isShowSettings = false
    simpleApp.start()
}
