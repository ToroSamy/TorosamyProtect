package net.torosamy.torosamyProtect.listener

import net.torosamy.torosamyProtect.api.TorosamyProtectAPI
import net.torosamy.torosamyProtect.utils.ConfigUtil
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.block.Container
class InteractContainerListener :Listener {
    companion object {
        private fun isContainer(material: Material): Boolean {
            if (ConfigUtil.mainConfig.debug) println("openContainer: ${material}")

            return when (material) {
                Material.CHEST,
                Material.ENDER_CHEST,
                Material.TRAPPED_CHEST,
                Material.SHULKER_BOX -> true
                else -> false
            }
        }
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    fun onPlayerInteractEvent(event: PlayerInteractEvent) {

        //如果是op则取消监听
        if (event.player.isOp) return
        //如果该世界未被监听
        val worldConfig = TorosamyProtectAPI.getWorld(event.player.world.name) ?: return

        //如果没有右键方块
        val block = event.clickedBlock?:return


        if (block.state is Container) {
            if(!worldConfig.preventInteractContainer) return
            if(worldConfig.ignoreChest && isContainer(block.type)) return
        }else {
            if(!worldConfig.preventInteractOther) return
        }

        event.isCancelled = true
    }
}