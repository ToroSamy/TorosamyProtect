package net.torosamy.torosamyProtect.utils

import net.torosamy.torosamyCore.TorosamyCore
import net.torosamy.torosamyProtect.commands.AdminCommands

class CommandUtil {
    companion object {
        fun registerCommand() {
            TorosamyCore.commanderManager.annotationParser.parse(AdminCommands())
        }
    }
}