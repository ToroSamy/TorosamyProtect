package net.torosamy.torosamyProtect.utils

import net.torosamy.torosamyCore.commands.CommandManager
import net.torosamy.torosamyProtect.TorosamyProtect
import net.torosamy.torosamyProtect.commands.AdminCommands

class CommandUtil {
    companion object {
        private val commanderManager: CommandManager = CommandManager(TorosamyProtect.plugin)

        public val ADMIN_COMMANDS: AdminCommands = AdminCommands()

        fun registerCommand() {
            commanderManager.annotationParser.parse(ADMIN_COMMANDS)
        }
    }
}