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
        if(event.getPlayer().isOp()) return
        val worldConfig = ConfigUtil.getWorldConfig(event.player.world.name)
        if (worldConfig == null) return
        if (!worldConfig.preventBreak) return

        event.setCancelled(true)
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    fun onPlayerBucketFill(event: PlayerBucketFillEvent) {
        if(event.getPlayer().isOp()) return
        val worldConfig = ConfigUtil.getWorldConfig(event.player.world.name)
        if (worldConfig == null) return
        if (!worldConfig.preventBreak) return

        event.setCancelled(true)
    }

}