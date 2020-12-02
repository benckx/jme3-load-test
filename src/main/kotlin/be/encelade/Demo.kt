package be.encelade

import be.encelade.chimp.material.MaterialDefinitions
import be.encelade.chimp.utils.ColorHelperUtils.ColorRGBA
import be.encelade.ouistiti.CameraManager
import com.jme3.app.SimpleApplication
import com.jme3.system.AppSettings

fun main() {
    val settings = AppSettings(true)
    settings.isFullscreen = false
    settings.setResolution(1280, 720)
    settings.samples = 16
    settings.title = "Load Test"

    val simpleApp = DemoSimpleApp()
    simpleApp.setSettings(settings)
    simpleApp.isShowSettings = false
    simpleApp.start()
}

class DemoSimpleApp : SimpleApplication() {

    lateinit var cameraManager: CameraManager

    override fun simpleInitApp() {
        // init chimp-utils lib for materials
        MaterialDefinitions.load(assetManager)

        // init ouistiti lib CameraManager
        cameraManager = CameraManager(this)
        cameraManager.addDefaultKeyMappings()

        // build scene
        viewPort.backgroundColor = ColorRGBA("#1c3064")
        rootNode.attachChild(SceneNode(sizeX = 24, sizeY = 16))
    }

    override fun simpleUpdate(tpf: Float) {
        cameraManager.simpleUpdate(tpf)
    }

}
