package net.torosamy.torosamyProtect.utils

import net.torosamy.torosamyCore.config.Config
import net.torosamy.torosamyCore.config.ConfigFile
import net.torosamy.torosamyProtect.TorosamyProtect
import net.torosamy.torosamyProtect.config.LangConfig
import net.torosamy.torosamyProtect.config.MainConfig

class ConfigUtil {
    companion object {
        private val configs: ArrayList<Config> = ArrayList()

        public var mainConfig: MainConfig = MainConfig()
        public var langConfig: LangConfig = LangConfig()
        
        fun initConfig() {
            configs.clear()
            configs.add(Config(mainConfig, ConfigFile(TorosamyProtect.plugin,"config.yml")))
            configs.add(Config(langConfig, ConfigFile(TorosamyProtect.plugin,"lang.yml")))
        }

        fun reloadConfig() {
            for (config in configs) {
                config.load()
            }
        }

        fun saveConfig() {
            for (config in configs) {
                config.save()
            }
        }
    }
}