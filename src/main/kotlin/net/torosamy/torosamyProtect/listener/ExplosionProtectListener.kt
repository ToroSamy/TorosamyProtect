package net.torosamy.torosamyProtect.listener


import com.bekvon.bukkit.residence.api.ResidenceApi
import net.torosamy.torosamyProtect.TorosamyProtect
import net.torosamy.torosamyProtect.utils.ConfigUtil
import org.bukkit.Location
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockExplodeEvent
import org.bukkit.event.entity.EntityExplodeEvent
import org.bukkit.event.hanging.HangingBreakByEntityEvent
import org.bukkit.event.vehicle.VehicleDamageEvent
import kotlin.math.E


class ExplosionProtectListener : Listener {
    companion object {
        private fun hasResidence(location: Location): Boolean {
            return ResidenceApi.getResidenceManager().getByLoc(location) != null
        }

        private fun isExplosive(type: EntityType): Boolean {
            if (ConfigUtil.mainConfig.debug) println("isExplosive: ${type}")

            return when (type) {
                EntityType.PLAYER,
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
        val worldConfig = ConfigUtil.worldConfigs[event.location.world.name] ?: return

        if (!worldConfig.explosionProtect) return
        if (TorosamyProtect.isUseRes && hasResidence(event.location)) return
        event.blockList().clear()
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    fun onBlockExplode(event: BlockExplodeEvent) {
        val worldConfig = ConfigUtil.worldConfigs[event.block.world.name] ?: return

        if (!worldConfig.explosionProtect) return
        if (TorosamyProtect.isUseRes && hasResidence(event.block.location)) return
        event.blockList().clear()
    }


    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    fun onHangingBreakByEntity(event: HangingBreakByEntityEvent) {
        val worldConfig = ConfigUtil.worldConfigs[event.entity.world.name] ?: return

        if (!worldConfig.explosionProtect) return


        if (!isExplosive(event.remover.type)) return

        if (TorosamyProtect.isUseRes && hasResidence(event.entity.location)) return

        event.isCancelled = true

    }

    //载具受到伤害的事件
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    fun onVehicleDamage(event: VehicleDamageEvent) {
        val worldConfig = ConfigUtil.worldConfigs[event.vehicle.world.name] ?: return

        if (!worldConfig.explosionProtect) return
        //如有领地存在 则交给领地监管
        if (TorosamyProtect.isUseRes && hasResidence(event.vehicle.location)) return

        val attacker: Entity? = event.attacker

        if (ConfigUtil.mainConfig.debug) println("vehicleDamage: ${attacker}")

        if (attacker == null) return
        if(!isExplosive(attacker.type)) return


        if (event.attacker is Player) return

        event.isCancelled = true

    }
}