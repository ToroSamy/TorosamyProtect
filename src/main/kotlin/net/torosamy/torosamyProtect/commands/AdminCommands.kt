package net.torosamy.torosamyProtect.commands

import net.torosamy.torosamyCore.utils.MessageUtil
import net.torosamy.torosamyProtect.utils.ConfigUtil
import org.bukkit.command.CommandSender
import org.incendo.cloud.annotations.Command
import org.incendo.cloud.annotations.CommandDescription
import org.incendo.cloud.annotations.Permission

class AdminCommands {
    @Command(value = "tsp reload")
    @Permission("torosamyProtect.reload")
    @CommandDescription("重载TorosamyProtect配置文件")
    fun reloadConfig(sender: CommandSender) {
        ConfigUtil.reloadConfig()
        sender.sendMessage(MessageUtil.text(ConfigUtil.getLangConfig().reloadMessage))
    }
}