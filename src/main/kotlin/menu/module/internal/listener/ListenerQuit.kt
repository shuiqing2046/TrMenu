package menu.module.internal.listener

import trmenu.module.display.Menu
import trmenu.module.display.MenuSession
import trmenu.module.internal.data.Metadata
import org.bukkit.event.player.PlayerQuitEvent
import taboolib.common.platform.event.SubscribeEvent
import taboolib.module.ui.receptacle.setViewingReceptacle

/**
 * @author Arasple
 * @date 2021/1/27 12:14
 */
object ListenerQuit {

    @SubscribeEvent
    fun onQuit(e: PlayerQuitEvent) {
        val player = e.player
        val session = MenuSession.getSession(player)
        session.shut()
        Metadata.pushData(player)
        Menu.menus.forEach {
            it.icons.forEach { icon -> icon.onReset(session) }
        }
        MenuSession.removeSession(player)
        player.setViewingReceptacle(null)
    }

}