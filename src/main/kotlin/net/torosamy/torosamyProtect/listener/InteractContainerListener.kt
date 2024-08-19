package net.torosamy.torosamyProtect.listener

import net.torosamy.torosamyProtect.utils.ConfigUtil
import org.bukkit.block.Beacon
import org.bukkit.block.Container
import org.bukkit.block.Dispenser
import org.bukkit.block.Dropper
import org.bukkit.block.EnderChest
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent

class InteractContainerListener :Listener {
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    fun onPlayerInteractEvent(event: PlayerInteractEvent) {
        //如果是op则取消监听
        if (event.player.isOp) return
        //如果该世界未被监听
        val worldConfig = ConfigUtil.getWorldConfig(event.player.world.name)
        if (worldConfig == null) return


        //如果开启了阻止打开容器
        if (!worldConfig.preventInteractContainer) return
        //如果没有右键方块
        val clickedBlock = event.clickedBlock
        if (clickedBlock == null) return
        //如果右键的方块不是容器
        if (clickedBlock.state !is Container) return
        if (clickedBlock.state !is Dispenser) return
        if (clickedBlock.state !is Dropper) return
        if (clickedBlock.state !is Beacon) return
        if (clickedBlock.state !is EnderChest) return
        event.isCancelled = true
    }
}