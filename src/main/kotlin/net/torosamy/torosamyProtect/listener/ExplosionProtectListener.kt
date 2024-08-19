package net.torosamy.torosamyProtect.listener


import com.bekvon.bukkit.residence.api.ResidenceApi
import net.torosamy.torosamyProtect.utils.ConfigUtil
import org.bukkit.Location
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.entity.TNTPrimed
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockExplodeEvent
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityExplodeEvent
import org.bukkit.event.hanging.HangingBreakByEntityEvent
import org.bukkit.event.vehicle.VehicleDamageEvent


class ExplosionProtectListener : Listener {
    companion object {
        private fun hasResidence(location: Location): Boolean {
            return ResidenceApi.getResidenceManager().getByLoc(location) != null
        }

        //TODO
        private fun isExplosive(type: EntityType): Boolean {
            return when (type) {
                EntityType.TNT,
                EntityType.TNT_MINECART,
                EntityType.CREEPER,
                EntityType.DRAGON_FIREBALL,
                EntityType.FIREBALL -> true
                else -> false
            }
        }
    }


    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    fun onEntityExplode(event: EntityExplodeEvent) {
        val worldConfig = ConfigUtil.getWorldConfig(event.location.world.name)
        if (worldConfig == null) return

        if (!worldConfig.explosionProtect) return
        if (hasResidence(event.location)) return
        event.blockList().clear()
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    fun onBlockExplode(event: BlockExplodeEvent) {
        val worldConfig = ConfigUtil.getWorldConfig(event.block.world.name)
        if (worldConfig == null) return

        if (!worldConfig.explosionProtect) return
        if (hasResidence(event.block.location)) return
        event.blockList().clear()
    }


    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    fun onHangingBreakByEntity(event: HangingBreakByEntityEvent) {
        val worldConfig = ConfigUtil.getWorldConfig(event.entity.world.name)
        if (worldConfig == null) return

        if (!worldConfig.explosionProtect) return
        //TODO
        if (!isExplosive(event.remover.type)) return

        if (hasResidence(event.entity.location)) return

        event.isCancelled = true

    }

    //载具受到伤害的事件
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    fun onVehicleDamage(event: VehicleDamageEvent) {
        val worldConfig = ConfigUtil.getWorldConfig(event.vehicle.world.name)
        if (worldConfig == null) return

        if (!worldConfig.explosionProtect) return
        //TODO
//        if (event.attacker == null) return
//        if(!isExplosive(event.attacker.type)) return

        if (hasResidence(event.vehicle.location)) return

        event.isCancelled = true

    }

    //当一个实体受到另外一个实体伤害时触发该事件
//    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
//    fun onEntityDamageByEntity(event: EntityDamageByEntityEvent) {
//        val player: Entity = event.damager
//        val beDamager: Entity = event.entity
//
//        if (player !is Player) return
//        if (player.isOp) return
//
//
//        if (hasResidence(beDamager.location)) return
//
//        val worldConfig = ConfigUtil.getWorldConfig(beDamager.world.name)
//        if (worldConfig == null) return
//
//        if (!worldConfig.explosionProtect) return
//        //TODO
//        if (!isExplosive(event.damager.type)) return
//
//        event.isCancelled = true
//    }

}