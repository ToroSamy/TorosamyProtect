package net.torosamy.torosamyProtect.utils

import net.torosamy.torosamyCore.TorosamyCore
import net.torosamy.torosamyProtect.TorosamyProtect
import net.torosamy.torosamyProtect.commands.AdminCommands

class CommandUtil {
    companion object {
        private var torosamyCorePlugin: TorosamyCore = TorosamyProtect.plugin.server.pluginManager.getPlugin("TorosamyCore") as TorosamyCore
        fun registerCommand() {
            torosamyCorePlugin.getCommandManager().annotationParser.parse(AdminCommands())
        }
    }
}