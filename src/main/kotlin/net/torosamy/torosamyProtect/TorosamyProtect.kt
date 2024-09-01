package net.torosamy.torosamyProtect

import net.torosamy.torosamyCore.utils.MessageUtil
import net.torosamy.torosamyProtect.utils.CommandUtil
import net.torosamy.torosamyProtect.utils.ConfigUtil
import net.torosamy.torosamyProtect.utils.ListenerUtil
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class TorosamyProtect : JavaPlugin() {

    companion object {
        lateinit var plugin: TorosamyProtect
        var isUseRes: Boolean = false
    }

    override fun onEnable() {
        plugin = this
        isUseRes = server.pluginManager.isPluginEnabled("Residence")
        if(isUseRes) Bukkit.getConsoleSender().sendMessage(MessageUtil.text("&a[服务器娘]&a插件 &eTorosamyProtect &a检测到 &eResidence &a开启"))
        else Bukkit.getConsoleSender().sendMessage(MessageUtil.text("&a[服务器娘]&c插件 &eTorosamyProtect &c检测到 &eResidence &c未开启"))
        ConfigUtil.reloadConfig()
        CommandUtil.registerCommand()
        ListenerUtil.registerListener()

        Bukkit.getConsoleSender().sendMessage(MessageUtil.text("&a[服务器娘]&a插件 &eTorosamyProtect &a成功开启喵~"))
        Bukkit.getConsoleSender().sendMessage(MessageUtil.text("&a[服务器娘]&a作者 &eTorosamy|yweiyang"))
    }

    override fun onDisable() {
        ConfigUtil.saveConfig()
        Bukkit.getConsoleSender().sendMessage(MessageUtil.text("&a[服务器娘]&c插件 &eTorosamyProtect &c成功关闭喵~"))
        Bukkit.getConsoleSender().sendMessage(MessageUtil.text("&a[服务器娘]&c作者 &eTorosamy|yweiyang"))
    }
}
