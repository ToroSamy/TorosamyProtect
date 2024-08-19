package net.torosamy.torosamyProtect.listener

import net.torosamy.torosamyProtect.utils.ConfigUtil
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent

class ChangeSpawnerListener:Listener {
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    fun onPlayerInteractEvent(event: PlayerInteractEvent) {
        //op可以更改刷怪蛋
        if (event.player.isOp) return

        val worldConfig = ConfigUtil.getWorldConfig(event.player.world.name)
        if (worldConfig == null) return


        if (!worldConfig.preventChangeSpawner) return
        if(event.getAction() != Action.RIGHT_CLICK_BLOCK) return
        //如果玩家没有右键一个方块
        val clickedBlock = event.getClickedBlock()
        if (clickedBlock == null) return
        if (clickedBlock.getType() != Material.SPAWNER) return

        //如果玩家手上不存在物品
        val item = event.getItem()
        if (item == null) return
        //如果玩家手上不是刷怪蛋
        if(!item.getType().name.endsWith("_SPAWN_EGG" )) return

        event.isCancelled = true
    }
}