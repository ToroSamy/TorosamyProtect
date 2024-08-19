package net.torosamy.torosamyProtect.listener

import net.torosamy.torosamyProtect.utils.ConfigUtil
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockIgniteEvent

class FireSpreadListener : Listener {
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    fun onBlockIgnite(event: BlockIgniteEvent) {
        val player = event.player
        if (player == null) return
        if (player.isOp) return

        val worldConfig = ConfigUtil.getWorldConfig(player.world.name)
        if (worldConfig == null) return

        if(!worldConfig.PreventFireSpread) return

        event.isCancelled = true
    }
}