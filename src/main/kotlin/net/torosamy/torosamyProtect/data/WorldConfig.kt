package net.torosamy.torosamyProtect.data

import org.bukkit.Bukkit
import org.bukkit.GameRule
import org.bukkit.configuration.ConfigurationSection

class WorldConfig {
    val gameRuleBool: HashMap<String, Boolean> = HashMap()

    val gameRuleNumber: HashMap<String, Int> = HashMap()

    val farmProtection: Boolean
    val preventSculkCatalyst: Boolean
    
    val preventChangeSpawner: Option
    val preventInteractBlock: Option
    val preventInteractItem: Option
    val preventPlace: Option
    val preventBreak: Option
    val explosionProtect: Option
    val preventCraft: Option
    

    public constructor(config: ConfigurationSection) {
        this.farmProtection = config.getBoolean("farmProtection", true)
        this.preventSculkCatalyst = config.getBoolean("preventSculkCatalyst", true)
        
        
        this.preventChangeSpawner = Option(config.getConfigurationSection("preventChangeSpawner"))
        this.preventInteractBlock = Option(config.getConfigurationSection("preventInteractBlock"))
        this.preventInteractItem = Option(config.getConfigurationSection("preventInteractItem"))
        this.preventPlace = Option(config.getConfigurationSection("preventPlace"))
        this.preventBreak = Option(config.getConfigurationSection("preventBreak"))
        this.explosionProtect = Option(config.getConfigurationSection("explosionProtect"))
        this.preventCraft = Option(config.getConfigurationSection("preventCraft"))
        
        val boolSection = config.getConfigurationSection("gameRuleBool")
        
        if (boolSection != null) {
            for (key in boolSection.getKeys(false)) {
                this.gameRuleBool[key] = boolSection.getBoolean(key)
            }
        }

        val numberSection = config.getConfigurationSection("gameRuleNumber")
        
        if (numberSection != null) {
            for (key in numberSection.getKeys(false)) {
                this.gameRuleNumber[key] = numberSection.getInt(key)
            }
        }
    }
    
    public fun setGameRule(worldName: String) {
        val world = Bukkit.getWorld(worldName) ?: return
        
        for (it in this.gameRuleBool) {
            val rule = GameRule.getByName(it.key) ?: continue


            val isBoxedBoolean = rule.type == java.lang.Boolean::class.java

            val isPrimitiveBoolean = rule.type == java.lang.Boolean.TYPE

            if (!isBoxedBoolean && !isPrimitiveBoolean) {
                continue
            }


            world.setGameRule(rule as GameRule<Boolean>, it.value)
        }

        for (it in this.gameRuleNumber) {
            val rule = GameRule.getByName(it.key) ?: continue

            val isBoxedInteger = rule.type == java.lang.Integer::class.java

            val isPrimitiveInteger = rule.type == Integer.TYPE

            if (!isBoxedInteger && !isPrimitiveInteger) {
                continue
            }
            
            world.setGameRule(rule as GameRule<Int>, it.value)
        }
    }

    class Option {
        val enable: Boolean
        val isPreventList: Boolean
        val list: ArrayList<String> = arrayListOf()

        public constructor(config: ConfigurationSection?) {
            if (config == null) {
                this.enable = true
                this.isPreventList = false
                return
            }

            this.enable = config.getBoolean("enable", true)
            this.isPreventList = config.getBoolean("is-prevent-list", false)
            this.list.addAll(config.getStringList("list"))
        }
        
        public fun prevent(worldName: String): Boolean {
            if (!this.enable) {
                return false
            }

            val result = this.list.contains(worldName)
            
            if (this.isPreventList) {
                return result
            }
            
            return !result
        }
    }
}