package net.torosamy.torosamyProtect.api

import net.torosamy.torosamyCore.config.Config
import net.torosamy.torosamyCore.config.ConfigFile
import net.torosamy.torosamyProtect.TorosamyProtect
import net.torosamy.torosamyProtect.config.WorldConfig
import net.torosamy.torosamyProtect.utils.ConfigUtil
import org.bukkit.World

class TorosamyProtectAPI {
    companion object {
        val worldConfigs: HashMap<String, WorldConfig> = HashMap()
        
        fun loadWorlds() {
            worldConfigs.clear()

            for (it in ConfigUtil.mainConfig.enabledWorlds) {
                val configFile = ConfigFile(TorosamyProtect.plugin, it + ".yml", listOf("worlds"))

                if (!configFile.exists()) {
                    worldConfigs[it] = ConfigUtil.mainConfig.defaultWorldConfig.clone()
                    continue
                }
                val worldConfig = WorldConfig()
                
                Config(worldConfig, configFile).load(false)
                
                worldConfigs[it] = worldConfig
            }
        }
        
        fun getWorld(worldName: String): WorldConfig? {
            return worldConfigs[worldName]
        }
        
        fun saveWorlds() {
            worldConfigs.forEach{(name, config)->
                Config(config, ConfigFile(TorosamyProtect.plugin, name + ".yml", listOf("worlds"))).save(false)
            }
        }
    }
}