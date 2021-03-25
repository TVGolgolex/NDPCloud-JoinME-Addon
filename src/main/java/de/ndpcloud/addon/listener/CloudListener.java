package de.ndpcloud.addon.listener;

import de.dytanic.cloudnet.api.CloudServicePlugin;
import de.dytanic.cloudnet.bridge.event.proxied.ProxiedSubChannelMessageEvent;
import de.dytanic.cloudnet.lib.utility.document.Document;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import de.ndpcloud.addon.NDPAddon;
import de.ndpcloud.addon.utils.JoinMeUnits;

public class CloudListener implements Listener {

    @EventHandler
    public void on(ProxiedSubChannelMessageEvent event) {
        try {
            if (event.getMessage().equals("system_joinme")) {
                Document document = event.getDocument();
                final String servername = document.getString("servername");
                final String name = document.getString("name");
                NDPAddon.getNdpAddon().getProxy().getScheduler().runAsync(NDPAddon.getNdpAddon(), () -> JoinMeUnits.sendJoinMeMessage(servername, name, CloudServicePlugin.getInstance().getPlayerUniqueId(name).toString()));
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }
}
