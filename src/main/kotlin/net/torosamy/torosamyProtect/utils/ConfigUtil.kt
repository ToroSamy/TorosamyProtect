package net.torosamy.torosamyProtect.utils

import net.torosamy.torosamyCore.manager.ConfigManager
import net.torosamy.torosamyProtect.TorosamyProtect
import net.torosamy.torosamyProtect.TorosamyProtect.Companion.plugin
import net.torosamy.torosamyProtect.config.LangConfig
import net.torosamy.torosamyProtect.config.MainConfig
import net.torosamy.torosamyProtect.config.WorldConfig
import org.bukkit.GameRule
import org.bukkit.configuration.ConfigurationSection

class ConfigUtil {
    companion object {
        private var mainConfig: MainConfig = MainConfig()
        private var mainConfigManager: ConfigManager = ConfigManager(mainConfig)

        private var langConfig: LangConfig = LangConfig()
        private var langConfigManager: ConfigManager = ConfigManager(langConfig)


        private var worldConfigs = HashMap<String, WorldConfig>()
        private var worldConfigManagers = HashMap<String, ConfigManager>()
        private lateinit var worldTemplate: ConfigurationSection

        fun getMainConfig(): MainConfig {return mainConfig}
        fun getLangConfig(): LangConfig {return langConfig}
        fun getWorldConfig(worldName:String): WorldConfig? {return worldConfigs[worldName] }

        fun initConfig() {
            mainConfigManager.load(plugin, "config.yml")
            langConfigManager.load(plugin, "lang.yml")
            worldTemplate = mainConfigManager.yamlConfiguration.getConfigurationSection("default-world-config")!!
            loadWorldConfigs()
        }

        fun reloadConfig() {
            mainConfigManager.load(plugin, "config.yml")
            langConfigManager.load(plugin, "lang.yml")
            loadWorldConfigs()

        }
        fun saveConfig() {
            mainConfigManager.saveFile()
            langConfigManager.saveFile()
            for (value in worldConfigManagers.values) { value.saveFile() }
        }
        fun loadWorldConfigs() {
            worldConfigs.clear()
            worldConfigManagers.clear()
            for (world in mainConfig.enabledWorlds) {
                val worldConfig = WorldConfig()
                val worldManager = ConfigManager(worldConfig)

                worldManager.loadTemplateFile(plugin,"worlds",world+".yml",worldTemplate)
                worldManager.initConfig()


                for (serverWorld in plugin.server.worlds) {
                    if (serverWorld.name == world) {
                        serverWorld.setGameRule(GameRule.KEEP_INVENTORY,worldConfig.KeepInventory)
                    }
                }
                worldConfigs.put(world,worldConfig)
                worldConfigManagers.put(world, worldManager)
            }
        }
    }
}