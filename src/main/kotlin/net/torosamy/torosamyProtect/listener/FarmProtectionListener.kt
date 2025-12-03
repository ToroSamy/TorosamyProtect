package net.torosamy.torosamyProtect.listener

import net.torosamy.torosamyProtect.api.TorosamyProtectAPI
import net.torosamy.torosamyProtect.utils.ConfigUtil
import org.bukkit.Material
import org.bukkit.entity.EntityType
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.entity.EntityInteractEvent
import org.bukkit.event.player.PlayerInteractEvent

class FarmProtectionListener :Listener {
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    fun onPlayerInteractEvent(event: PlayerInteractEvent) {
        val clickedBlock = event.clickedBlock ?: return
        
        if(clickedBlock.type != Material.FARMLAND) {
            return
        }

        if(event.action != Action.PHYSICAL) {
            return
        }
        
        val worldConfig = TorosamyProtectAPI.getWorld(event.player.world.name) ?: return

        if(!worldConfig.farmProtection) return
        
        event.isCancelled = true
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    fun onEntityInteract(event: EntityInteractEvent) {
        if (event.entityType == EntityType.PLAYER) {
            return
        }

        if (event.block.type != Material.FARMLAND) {
            return
        }
        
        val worldConfig = TorosamyProtectAPI.getWorld(event.entity.world.name) ?: return
        
        if(!worldConfig.farmProtection) return
        
        event.isCancelled = true
    }

}