package menu.api.action.impl.func

import trmenu.api.action.base.AbstractAction
import trmenu.api.action.base.ActionOption
import trmenu.util.bukkit.ItemMatcher
import org.bukkit.entity.Player
import taboolib.common.platform.function.adaptPlayer
import trmenu.api.action.InternalAction
import trmenu.api.action.base.ActionDesc
import trmenu.module.display.session

/**
 * @author Arasple
 * @date 2021/1/31 16:25
 */
class ActionTakeItem(content: String, option: ActionOption) : InternalAction(content, option) {

    override fun onExecute(player: Player, placeholderPlayer: Player) {
        ItemMatcher.eval(parseContent(adaptPlayer(placeholderPlayer)), !parsable).takeItem(player)
        player.session().playerItemSlots()
    }

    companion object : ActionDesc {

        override val name = "(take|remove)(-)?items?".toRegex()

        override val parser: (Any, ActionOption) -> AbstractAction = { value, option ->
            ActionTakeItem(value.toString(), option)
        }

    }

}