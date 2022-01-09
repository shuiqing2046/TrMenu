package menu.api.action.impl.hook

import trmenu.api.action.base.AbstractAction
import trmenu.api.action.base.ActionOption
import trmenu.module.internal.hook.HookPlugin
import org.bukkit.entity.Player
import taboolib.common.platform.function.adaptPlayer
import trmenu.api.action.InternalAction
import trmenu.api.action.base.ActionDesc

/**
 * @author Arasple
 * @date 2021/1/31 16:21
 */
class ActionMoneyAdd(content: String, option: ActionOption) : InternalAction(content, option) {

    override fun onExecute(player: Player, placeholderPlayer: Player) {
        val amount = parseContent(adaptPlayer(placeholderPlayer)).toDoubleOrNull() ?: -1.0
        if (amount > 0) {
            HookPlugin.getVault().addMoney(player, amount)
        }
    }

    companion object : ActionDesc {

        override val name = "(give|add|deposit)-?(money|eco|coin)s?".toRegex()

        override val parser: (Any, ActionOption) -> AbstractAction = { value, option ->
            ActionMoneyAdd(value.toString(), option)
        }

    }

}