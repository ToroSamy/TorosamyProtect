package net.torosamy.torosamyProtect.listener

import net.torosamy.torosamyProtect.utils.ConfigUtil
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockFormEvent

class PreventSculkCatalyst : Listener {

    @EventHandler
    fun blockFrom(event: BlockFormEvent) {
        val worldConfig = ConfigUtil.worldConfigs[event.block.world.name] ?: return
        if (!worldConfig.preventSculkCatalyst) return

        when (event.newState.block.type) {
            Material.SCULK -> event.isCancelled = true
            Material.SCULK_VEIN -> event.isCancelled = true
            else -> return
        }
    }
}