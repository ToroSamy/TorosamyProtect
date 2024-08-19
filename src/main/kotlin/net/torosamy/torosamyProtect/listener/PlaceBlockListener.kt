package net.torosamy.torosamyProtect.listener

import net.torosamy.torosamyProtect.utils.ConfigUtil
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.player.PlayerBucketEmptyEvent

class PlaceBlockListener :Listener {
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    fun onBlockPlace(event: BlockPlaceEvent) {
        if(event.getPlayer().isOp()) return

        val worldConfig = ConfigUtil.getWorldConfig(event.player.world.name)
        if (worldConfig == null) return

        if (!worldConfig.preventPlace) return

        event.setCancelled(true)
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    fun onPlayerBucketEmpty(event: PlayerBucketEmptyEvent) {
        if(event.player.isOp) return


        val worldConfig = ConfigUtil.getWorldConfig(event.player.world.name)
        if (worldConfig == null) return

        if (!worldConfig.preventPlace) return

        event.setCancelled(true)
    }
}