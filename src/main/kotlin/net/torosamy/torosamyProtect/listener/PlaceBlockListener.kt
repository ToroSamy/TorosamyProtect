package net.torosamy.torosamyProtect.listener

import net.torosamy.torosamyProtect.listener.InteractContainerListener.Companion
import net.torosamy.torosamyProtect.utils.ConfigUtil
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.player.PlayerBucketEmptyEvent

class PlaceBlockListener :Listener {
    companion object {
        private fun isContainer(material: Material): Boolean {
            if (ConfigUtil.mainConfig.debug) println("placeBlock: ${material}")

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
    fun onBlockPlace(event: BlockPlaceEvent) {
        if(event.player.isOp) return

        val worldConfig = ConfigUtil.worldConfigs[event.player.world.name] ?: return

        if (!worldConfig.preventPlace) return
        if (worldConfig.ignoreChest && isContainer(event.blockPlaced.type)) return
        event.isCancelled = true
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    fun onPlayerBucketEmpty(event: PlayerBucketEmptyEvent) {
        if(event.player.isOp) return


        val worldConfig = ConfigUtil.worldConfigs[event.player.world.name] ?: return

        if (!worldConfig.preventPlace) return


        event.isCancelled = true
    }
}