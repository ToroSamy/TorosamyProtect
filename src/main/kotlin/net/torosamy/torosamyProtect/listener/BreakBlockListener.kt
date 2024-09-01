package net.torosamy.torosamyProtect.listener

import net.torosamy.torosamyProtect.utils.ConfigUtil
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.player.PlayerBucketFillEvent

class BreakBlockListener: Listener {
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    fun onBlockBreak(event: BlockBreakEvent) {
        if(event.player.isOp) return
        val worldConfig = ConfigUtil.worldConfigs[event.player.world.name] ?: return
        if (!worldConfig.preventBreak) return

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