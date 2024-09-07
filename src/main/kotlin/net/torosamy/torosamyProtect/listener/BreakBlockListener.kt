package net.torosamy.torosamyProtect.listener

import net.torosamy.torosamyProtect.listener.PlaceBlockListener.Companion
import net.torosamy.torosamyProtect.utils.ConfigUtil
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.player.PlayerBucketFillEvent

class BreakBlockListener: Listener {
    companion object {
        private fun isContainer(material: Material): Boolean {
            if (ConfigUtil.mainConfig.debug) println("breakBlock: ${material}")

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
    fun onBlockBreak(event: BlockBreakEvent) {
        if(event.player.isOp) return
        val worldConfig = ConfigUtil.worldConfigs[event.player.world.name] ?: return
        if (!worldConfig.preventBreak) return
        if (worldConfig.ignoreChest && isContainer(event.block.type)) return
        event.isCancelled = true
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    fun onPlayerBucketFill(event: PlayerBucketFillEvent) {
        if(event.player.isOp) return
        val worldConfig = ConfigUtil.worldConfigs[event.player.world.name] ?: return
        if (!worldConfig.preventBreak) return
        event.isCancelled = true
    }

}