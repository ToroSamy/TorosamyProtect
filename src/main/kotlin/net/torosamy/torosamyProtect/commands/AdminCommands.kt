package net.torosamy.torosamyProtect.commands

import net.torosamy.torosamyCore.utils.MessageUtil
import net.torosamy.torosamyProtect.TorosamyProtect
import net.torosamy.torosamyProtect.api.TorosamyProtectAPI
import net.torosamy.torosamyProtect.utils.ConfigUtil
import org.bukkit.command.CommandSender
import org.incendo.cloud.annotations.Argument
import org.incendo.cloud.annotations.Command
import org.incendo.cloud.annotations.CommandDescription
import org.incendo.cloud.annotations.Permission

class AdminCommands {
    @Command(value = "tsp reload")
    @Permission("torosamyProtect.reload")
    @CommandDescription("重载TorosamyProtect配置文件")
    fun reloadConfig(sender: CommandSender) {
        ConfigUtil.reloadConfig()
        TorosamyProtectAPI.loadWorlds()
        sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.reloadMessage))
    }

    @Command(value = "tsp gamerule lookup <world> <rule>")
    @Permission("torosamyProtect.gamerule")
    @CommandDescription("查看指定世界的指定规则")
    fun lookupGamerule(sender: CommandSender, @Argument("world") worldName: String, @Argument("rule") ruleName: String) {
        TorosamyProtect.plugin.server.worlds.forEach{
            if (it.name == worldName) {
                it.gameRules.forEach { rule->
                    if (rule == ruleName) {
                        sender.sendMessage(rule + ": " + it.getGameRuleValue(rule))
                        return
                    }
                }
                sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.notFoundRule))
                return
            }
        }
        
        sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.notFoundWorld))
    }
}