package de.ndpcloud.addon.listener;

import de.dytanic.cloudnet.api.CloudServicePlugin;
import de.dytanic.cloudnet.lib.player.CloudPlayer;
import net.md_5.bungee.api.event.ServerSwitchEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ServerSwtichListener implements Listener {

    @EventHandler
    public void onServerSwitch(ServerSwitchEvent e) {
        final CloudPlayer cp = CloudServicePlugin.getInstance().getOnlinePlayer(e.getPlayer().getUniqueId());

        if (cp.getMetaData().contains("joinmebaby")) {
            cp.setMetaData(cp.getMetaData().remove("joinmebaby"));
        }

    }

}