package net.torosamy.torosamyProtect.listener

import net.torosamy.torosamyProtect.api.TorosamyProtectAPI
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent

class ChangeSpawnerListener:Listener {
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    fun onPlayerInteractEvent(event: PlayerInteractEvent) {
        if(event.player.isOp) {
            return
        }

        if(event.action != Action.RIGHT_CLICK_BLOCK) {
            return
        }

        val clickedBlock = event.clickedBlock ?: return

        if (clickedBlock.type != Material.SPAWNER) {
            return
        }

        val item = event.item ?: return

        if(!item.type.name.endsWith("_SPAWN_EGG" )) {
            return
        }

        val worldConfig = TorosamyProtectAPI.getWorld(event.player.world.name) ?: return
        
        if (!worldConfig.preventChangeSpawner.prevent(item.type.name)) {
            return
        }
        
        event.isCancelled = true
    }
}