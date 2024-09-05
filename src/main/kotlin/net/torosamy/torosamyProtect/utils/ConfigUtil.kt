package net.torosamy.torosamyProtect.utils

import net.torosamy.torosamyCore.manager.ConfigManager
import net.torosamy.torosamyCore.utils.MessageUtil
import net.torosamy.torosamyProtect.TorosamyProtect
import net.torosamy.torosamyProtect.config.LangConfig
import net.torosamy.torosamyProtect.config.MainConfig
import net.torosamy.torosamyProtect.config.WorldConfig
import org.bukkit.Bukkit
import org.bukkit.GameRule

class ConfigUtil {
    companion object {
        var mainConfig: MainConfig = MainConfig()
        var langConfig: LangConfig = LangConfig()
        var worldConfigs = HashMap<String, WorldConfig>()

        private var mainConfigManager: ConfigManager = ConfigManager(mainConfig, TorosamyProtect.plugin,"","config.yml")
        private var langConfigManager: ConfigManager = ConfigManager(langConfig, TorosamyProtect.plugin,"","lang.yml")
        private var worldConfigManagers = HashMap<String, ConfigManager>()


        fun reloadConfig() {
            mainConfigManager.load()
            langConfigManager.load()
            loadWorldConfigs()
        }

        fun saveConfig() {
            mainConfigManager.save()
            langConfigManager.save()
            worldConfigManagers.values.forEach { it.save()}
        }


        private fun loadWorldConfigs() {
            val section = mainConfigManager.yaml.getConfigurationSection("default-world-config")!!
            worldConfigs.clear()
            worldConfigManagers.clear()
            for (world in mainConfig.enabledWorlds) {
                val worldConfig = WorldConfig()
                val worldManager = ConfigManager(worldConfig,TorosamyProtect.plugin,"worlds", "$world.yml",section)
                worldManager.load()
                worldConfigs[world] = worldConfig
                worldConfigManagers[world] = worldManager


                TorosamyProtect.plugin.server.getWorld(world)?.setGameRule(GameRule.KEEP_INVENTORY,worldConfig.KeepInventory)
                TorosamyProtect.plugin.server.getWorld(world)?.setGameRule(GameRule.DO_FIRE_TICK,!worldConfig.PreventFireSpread)

            }

            Bukkit.getConsoleSender().sendMessage(MessageUtil.text("&a[服务器娘]&a插件 &eTorosamyProtect &a成功加载 &e${worldConfigs.size} &a个世界喵~"))
        }
    }
}