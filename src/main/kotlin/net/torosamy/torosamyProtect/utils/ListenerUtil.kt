package net.torosamy.torosamyProtect.utils

import net.torosamy.torosamyProtect.TorosamyProtect
import net.torosamy.torosamyProtect.listener.*

class ListenerUtil {
    companion object{
        fun registerListener() {
            TorosamyProtect.plugin.server.pluginManager.registerEvents(InteractContainerListener(),TorosamyProtect.plugin)
            TorosamyProtect.plugin.server.pluginManager.registerEvents(FarmProtectionListener(),TorosamyProtect.plugin)
            TorosamyProtect.plugin.server.pluginManager.registerEvents(ChangeSpawnerListener(),TorosamyProtect.plugin)
            TorosamyProtect.plugin.server.pluginManager.registerEvents(BreakBlockListener(),TorosamyProtect.plugin)
            TorosamyProtect.plugin.server.pluginManager.registerEvents(PlaceBlockListener(),TorosamyProtect.plugin)
            TorosamyProtect.plugin.server.pluginManager.registerEvents(FireSpreadListener(),TorosamyProtect.plugin)
            TorosamyProtect.plugin.server.pluginManager.registerEvents(ExplosionProtectListener(),TorosamyProtect.plugin)
        }
    }
}