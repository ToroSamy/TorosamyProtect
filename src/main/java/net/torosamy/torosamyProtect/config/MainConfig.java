package net.torosamy.torosamyProtect.config;

import net.torosamy.torosamyCore.config.IConfigManage;

import java.util.List;

public class MainConfig implements IConfigManage {
    public List<String> enabledWorlds;
    public WorldConfig defaultWorldConfig = new WorldConfig();
    public Boolean debug;
}
