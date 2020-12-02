package node

import be.encelade.chimp.material.LightingMaterial
import be.encelade.chimp.tpf.TpfReceiver
import com.jme3.math.ColorRGBA.randomColor
import com.jme3.math.Vector2f
import com.jme3.renderer.queue.RenderQueue
import com.jme3.scene.Geometry
import com.jme3.scene.Node
import com.jme3.scene.shape.Box
import org.apache.commons.lang3.RandomUtils.nextBoolean

class BoxNode(position: Vector2f, level: Int) : Node("BOX_${counter++}"), TpfReceiver {

    private val rotationDirection = if (nextBoolean()) +1 else -1
    private val geometry = Geometry(this.name, Box(RADIUS, RADIUS, HEIGHT / 2))

    init {
        geometry.material = material
        geometry.shadowMode = RenderQueue.ShadowMode.CastAndReceive

        val z = .5f * HEIGHT + (level * HEIGHT * 1.10f)
        geometry.move(position.x - .5f, position.y - .5f, z)

        attachChild(geometry)
    }

    override fun simpleUpdate(tpf: Float) {
        geometry.rotate(0f, 0f, tpf * rotationDirection)
    }

    private companion object {

        const val SIZE = .6f
        const val RADIUS = SIZE / 2
        const val HEIGHT = .35f

        var counter = 0

        val material = LightingMaterial()

        init {
            material.setColor(randomColor())
        }

    }

}
