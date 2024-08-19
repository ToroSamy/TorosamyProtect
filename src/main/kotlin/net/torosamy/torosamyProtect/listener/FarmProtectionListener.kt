package net.torosamy.torosamyProtect.listener

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
        val worldConfig = ConfigUtil.getWorldConfig(event.player.world.name)
        if (worldConfig == null) return
        //如果开启了耕地保护
        if(!worldConfig.farmProtection) return
        //如果没有右键方块
        val clickedBlock = event.clickedBlock
        if (clickedBlock == null) return
        //如果不是耕地方块
        if(clickedBlock.type != Material.FARMLAND) return
        //如果动作不合法
        if(event.action != Action.PHYSICAL) return

        event.isCancelled = true
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    fun onEntityInteract(event: EntityInteractEvent) {
        val worldConfig = ConfigUtil.getWorldConfig(event.entity.world.name)
        if (worldConfig == null) return

        if (event.entityType == EntityType.PLAYER) return
        if (event.block.type != Material.FARMLAND) return

        event.isCancelled = true
    }

}