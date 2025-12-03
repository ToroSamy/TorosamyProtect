package net.torosamy.torosamyProtect.listener

import net.torosamy.torosamyProtect.api.TorosamyProtectAPI
import net.torosamy.torosamyProtect.utils.ConfigUtil
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockSpreadEvent

class PreventSculkCatalyst : Listener {

    @EventHandler
    fun blockFrom(event: BlockSpreadEvent) {
        if(event.source.type != Material.SCULK_CATALYST) {
            return
        }
        
        val worldConfig = TorosamyProtectAPI.getWorld(event.block.world.name) ?: return
        
        if (!worldConfig.preventSculkCatalyst) return
        
        event.isCancelled = true
    }
}