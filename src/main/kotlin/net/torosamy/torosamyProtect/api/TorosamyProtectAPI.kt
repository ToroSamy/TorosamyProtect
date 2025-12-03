package net.torosamy.torosamyProtect.api

import net.torosamy.torosamyCore.api.TorosamyCoreAPI
import net.torosamy.torosamyProtect.TorosamyProtect
import net.torosamy.torosamyProtect.data.WorldConfig

class TorosamyProtectAPI {
    companion object {
        val worldConfigs: HashMap<String, WorldConfig> = HashMap()
        
        fun loadWorlds() {
            worldConfigs.clear()

            for (it in TorosamyCoreAPI.getConfigs(TorosamyProtect.plugin, listOf("worlds"))) {
                val worldName = it.key
                
                val worldConfig = WorldConfig(it.value)
                
                worldConfig.setGameRule(worldName)

                worldConfigs[worldName] = worldConfig
            }
        }
        
        fun getWorld(worldName: String): WorldConfig? {
            return worldConfigs[worldName]
        }
    }
}