package node

import be.encelade.chimp.material.UnshadedMaterial
import be.encelade.chimp.utils.ColorHelperUtils.ColorRGBA
import be.encelade.chimp.utils.NodeHelperUtils.attachChildren
import com.jme3.math.ColorRGBA.Blue
import com.jme3.math.FastMath.HALF_PI
import com.jme3.math.Vector2f
import com.jme3.renderer.queue.RenderQueue
import com.jme3.scene.Geometry
import com.jme3.scene.Node
import com.jme3.scene.debug.Grid
import com.jme3.scene.shape.Box
import org.apache.commons.lang3.RandomUtils.nextBoolean
import org.apache.commons.lang3.RandomUtils.nextInt

class SceneNode(private val sizeX: Int,
                private val sizeY: Int,
                private val nbrOfStacks: Int) : Node("MY_SCENE") {

    init {
        if (nbrOfStacks > sizeX * sizeY) throw IllegalArgumentException()

        attachChildren(makeFloor(), makeGrid())
        attachChildren(makeStackNodes())
    }

    private fun makeStackNodes(): List<Node> {
        val used = mutableSetOf<Vector2f>()

        fun randomPosition(): Vector2f {
            val x = (nextInt(0, sizeX / 2)).toFloat()
            val y = (nextInt(0, sizeY / 2)).toFloat()

            val signX = if (nextBoolean()) 1 else -1
            val signY = if (nextBoolean()) 1 else -1

            return Vector2f(x * signX, y * signY)
        }

        fun randomUnusedPosition(): Vector2f {
            while (true) {
                val position = randomPosition()
                if (!used.contains(position)) {
                    return position
                }
            }
        }

        val nodes = mutableListOf<Node>()

        repeat(nbrOfStacks) {
            val position = randomUnusedPosition()
            val stackHeight = nextInt(1, 4)
            repeat(stackHeight) { height ->
                nodes += BoxNode(position, height)
            }
        }

        println("added ${nodes.size} boxes")

        return nodes
    }

    private fun makeFloor(): Geometry {
        val floorMat = UnshadedMaterial()
        floorMat.setColor(ColorRGBA(155, 164, 193))

        val floor = Geometry("FLOOR", Box(sizeX / 2f, sizeY / 2f, 0f))
        floor.material = floorMat
        floor.shadowMode = RenderQueue.ShadowMode.Receive

        return floor
    }

    private fun makeGrid(): Geometry {
        val gridMat = UnshadedMaterial()
        gridMat.setColor(Blue)

        val grid = Geometry("GRID", Grid(sizeX + 1, sizeY + 1, 1f))
        grid.material = gridMat
        grid.rotate(HALF_PI, 0f, HALF_PI)
        grid.move(-(sizeX / 2f), -(sizeY / 2f), 0.01f)

        return grid
    }
}