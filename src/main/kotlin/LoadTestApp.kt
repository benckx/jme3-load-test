import be.encelade.chimp.material.MaterialDefinitions
import be.encelade.chimp.utils.ColorHelperUtils.ColorRGBA
import be.encelade.ouistiti.CameraManager
import com.jme3.app.SimpleApplication
import com.jme3.light.AmbientLight
import com.jme3.light.DirectionalLight
import com.jme3.math.Vector3f
import com.jme3.shadow.DirectionalLightShadowRenderer
import node.SceneNode

class LoadTestApp : SimpleApplication() {

    private lateinit var cameraManager: CameraManager
    private lateinit var sceneNode: SceneNode

    override fun simpleInitApp() {
        // init chimp-utils lib for materials
        MaterialDefinitions.load(assetManager)

        // init ouistiti lib CameraManager
        cameraManager = CameraManager(this)
        cameraManager.addDefaultKeyMappings()

        // build scene
        addLighting()

        sceneNode = SceneNode(sizeX = 40, sizeY = 30, nbrOfStacks = 700)
        viewPort.backgroundColor = ColorRGBA("#1c3064")
        rootNode.attachChild(sceneNode)
    }

    override fun simpleUpdate(tpf: Float) {
        cameraManager.simpleUpdate(tpf)
        sceneNode.simpleUpdate(tpf)
    }

    private fun addLighting() {
        val light = DirectionalLight()
        light.direction = Vector3f(-1f, 1f, -2f).normalizeLocal()
        light.color = com.jme3.math.ColorRGBA.White

        rootNode.addLight(light)

        val shadowMapSize = 1024 * 5
        val shadowRenderer = DirectionalLightShadowRenderer(assetManager, shadowMapSize, 4)
        shadowRenderer.light = light
        viewPort.addProcessor(shadowRenderer)

        val al = AmbientLight()
        al.color = com.jme3.math.ColorRGBA.White.mult(0.2f)
        rootNode.addLight(al)
    }
}
