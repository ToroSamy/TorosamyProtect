package net.torosamy.torosamyProtect.listener

import net.torosamy.torosamyProtect.api.TorosamyProtectAPI
import org.bukkit.event.Event
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent

class PreventInteractListener :Listener {
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    fun onPlayerInteractEvent(event: PlayerInteractEvent) {
        if (event.player.isOp) return

        val block = event.clickedBlock ?: return

        event.player.equipment.itemInMainHand
        
        if (event.action != Action.RIGHT_CLICK_BLOCK) {
            return
        }
        
        val worldConfig = TorosamyProtectAPI.getWorld(event.player.world.name) ?: return
        
        if (worldConfig.preventInteractBlock.prevent(block.type.name)) {
            event.setUseInteractedBlock(Event.Result.DENY)
        }

        val item = event.item ?: return

        if (worldConfig.preventInteractItem.prevent(item.type.name)) {
            event.setUseItemInHand(Event.Result.DENY)
        }
    }
}