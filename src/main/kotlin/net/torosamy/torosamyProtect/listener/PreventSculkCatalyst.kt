package net.torosamy.torosamyProtect.listener

import net.torosamy.torosamyProtect.utils.ConfigUtil
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockSpreadEvent

class PreventSculkCatalyst : Listener {

    @EventHandler
    fun blockFrom(event: BlockSpreadEvent) {
        val worldConfig = ConfigUtil.worldConfigs[event.block.world.name] ?: return
        if (!worldConfig.preventSculkCatalyst) return

        if(event.source.type != Material.SCULK_CATALYST) return
        event.isCancelled = true

    }
}