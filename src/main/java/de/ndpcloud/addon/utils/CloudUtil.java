package de.ndpcloud.addon.utils;

import java.util.UUID;

import de.dytanic.cloudnet.api.CloudServicePlugin;
import de.dytanic.cloudnet.lib.player.CloudPlayer;
import de.dytanic.cloudnet.lib.utility.document.Document;
import de.ndpcloud.addon.API.API;
import de.ndpcloud.addon.listener.CloudListener;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import de.ndpcloud.addon.NDPAddon;

public class CloudUtil implements API {

	public CloudUtil() {
		NDPAddon.getNdpAddon().getProxy().getPluginManager().registerListener(NDPAddon.getNdpAddon(), new CloudListener());
	}

	public void createJoinMe(ProxiedPlayer p) {

		Document document = new Document();
		document.append("servername", p.getServer().getInfo().getName());
		document.append("name", p.getName());
		document.append("uuid", p.getUniqueId().toString());
		CloudServicePlugin.getInstance().sendCustomSubProxyMessage("joinme", "system_joinme", document);
		CloudPlayer cp = CloudServicePlugin.getInstance().getOnlinePlayer(p.getUniqueId());
		cp.setMetaData(cp.getMetaData().append("joinmebaby", true));
		CloudServicePlugin.getInstance().updatePlayer(cp);
	}

	@Override
	public void joinJoinMe(ProxiedPlayer p, UUID uuid) {
		CloudPlayer tcp = CloudServicePlugin.getInstance().getOnlinePlayer(uuid);

		if (tcp.getMetaData().contains("joinmebaby")) {
			p.connect(NDPAddon.getNdpAddon().getProxy().getServerInfo(tcp.getServer()));
		} else {
			p.sendMessage(new TextComponent(
					ChatColor.translateAlternateColorCodes('&', NDPAddon.getNdpAddon().getJoinme().PlayerClosedTheJoinMe)));
		}
	}

}
