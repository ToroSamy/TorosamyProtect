package net.torosamy.torosamyProtect.listener

import net.torosamy.torosamyProtect.api.TorosamyProtectAPI
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.PrepareItemCraftEvent


class PreventCraftListener : Listener {
    @EventHandler
    fun onCraft(event: PrepareItemCraftEvent) {
        if (event.view.player.isOp) return
        
        val worldConfig = TorosamyProtectAPI.getWorld(event.view.player.world.name) ?: return

        val result = event.inventory.result ?: return

        if (!worldConfig.preventCraft.prevent(result.type.name)) {
            return
        }
        
        event.inventory.result = null
    }
}