package net.torosamy.torosamyProtect.config;

import net.torosamy.torosamyCore.config.TorosamyConfig;

import java.util.List;

public class MainConfig extends TorosamyConfig {
    public List<String> enabledWorlds;
    public WorldConfig defaultWorldConfig = new WorldConfig();
    public Boolean debug;
}
