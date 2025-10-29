package net.torosamy.torosamyProtect.config;

import net.torosamy.torosamyCore.config.IConfigManage;
import net.torosamy.torosamyProtect.utils.ConfigUtil;
import org.bukkit.configuration.ConfigurationSection;

public class WorldConfig implements IConfigManage {
    public Boolean farmProtection;
    public Boolean preventChangeSpawner;
    public Boolean preventInteractOther;
    public Boolean preventInteractContainer;
    public Boolean ignoreChest;
    public Boolean preventPlace;
    public Boolean preventBreak;
    public Boolean PreventFireSpread;
    public Boolean KeepInventory;
    public Boolean explosionProtect;
    public Boolean preventSculkCatalyst;

    public WorldConfig clone() {
        WorldConfig newConfig = new WorldConfig();
        newConfig.farmProtection = this.farmProtection;
        newConfig.preventChangeSpawner = this.preventChangeSpawner;
        newConfig.preventInteractOther = this.preventInteractOther;
        newConfig.preventInteractContainer = this.preventInteractContainer;
        newConfig.ignoreChest = this.ignoreChest;
        newConfig.preventPlace = this.preventPlace;
        newConfig.preventBreak = this.preventBreak;
        newConfig.PreventFireSpread = this.PreventFireSpread;
        newConfig.KeepInventory = this.KeepInventory;
        newConfig.explosionProtect = this.explosionProtect;
        newConfig.preventSculkCatalyst = this.preventSculkCatalyst;
        return newConfig;
    }
//    public WorldConfig(ConfigurationSection config) {
//        farmProtection = config.getBoolean("farm-protection", ConfigUtil.mainConfig.defaultWorldConfig.farmProtection);
//        preventChangeSpawner = config.getBoolean("prevent-change-spawner", ConfigUtil.mainConfig.defaultWorldConfig.farmProtection);
//        explosionProtect = config.getBoolean("explosion-protect", ConfigUtil.mainConfig.defaultWorldConfig.farmProtection);
//        preventInteractOther = config.getBoolean("prevent-interact-other", ConfigUtil.mainConfig.defaultWorldConfig.farmProtection);
//        preventInteractContainer = config.getBoolean("prevent-interact-container", ConfigUtil.mainConfig.defaultWorldConfig.farmProtection);
//        ignoreChest = config.getBoolean("ignore-chest", ConfigUtil.mainConfig.defaultWorldConfig.farmProtection);
//        preventPlace = config.getBoolean("prevent-place", ConfigUtil.mainConfig.defaultWorldConfig.farmProtection);
//        preventBreak = config.getBoolean("prevent-break", ConfigUtil.mainConfig.defaultWorldConfig.farmProtection);
//        PreventFireSpread = config.getBoolean("prevent-fire-spread", ConfigUtil.mainConfig.defaultWorldConfig.farmProtection);
//        KeepInventory = config.getBoolean("keep-inventory", ConfigUtil.mainConfig.defaultWorldConfig.farmProtection);
//        preventSculkCatalyst = config.getBoolean("prevent-sculk-catalyst", ConfigUtil.mainConfig.defaultWorldConfig.farmProtection);
//    }
//
//    public Boolean getPreventSculkCatalyst() {
//        return preventSculkCatalyst;
//    }
//
//    public Boolean getExplosionProtect() {
//        return explosionProtect;
//    }
//
//    public Boolean getKeepInventory() {
//        return KeepInventory;
//    }
//
//    public Boolean getPreventFireSpread() {
//        return PreventFireSpread;
//    }
//
//    public Boolean getPreventBreak() {
//        return preventBreak;
//    }
//
//    public Boolean getPreventPlace() {
//        return preventPlace;
//    }
//
//    public Boolean getIgnoreChest() {
//        return ignoreChest;
//    }
//
//    public Boolean getPreventInteractContainer() {
//        return preventInteractContainer;
//    }
//
//    public Boolean getPreventInteractOther() {
//        return preventInteractOther;
//    }
//
//    public Boolean getPreventChangeSpawner() {
//        return preventChangeSpawner;
//    }
//
//    public Boolean getFarmProtection() {
//        return farmProtection;
//    }
}
