package net.torosamy.torosamyProtect.listener

import net.torosamy.torosamyProtect.api.TorosamyProtectAPI
import net.torosamy.torosamyProtect.utils.ConfigUtil
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.player.PlayerBucketEmptyEvent

class PlaceBlockListener :Listener {
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    fun onBlockPlace(event: BlockPlaceEvent) {
        if(event.player.isOp) return

        val worldConfig = TorosamyProtectAPI.getWorld(event.player.world.name) ?: return

        if (!worldConfig.preventPlace.prevent(event.blockPlaced.type.name)) {
            return
        }

        event.isCancelled = true
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    fun onPlayerBucketEmpty(event: PlayerBucketEmptyEvent) {
        if(event.player.isOp) return
        
        val worldConfig = TorosamyProtectAPI.getWorld(event.player.world.name) ?: return

        if (!worldConfig.preventPlace.prevent(event.block.type.name)) {
            return
        }
        
        event.isCancelled = true
    }
}