package de.ndpcloud.addon.utils;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import de.ndpcloud.addon.NDPAddon;
import de.ndpcloud.addon.utils.ImageUtil;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class JoinMeUnits {

	public static void sendJoinMeMessage(String Servername, String Name, String uuid) {

		String RunCommand = "/joinme " + uuid;

		ArrayList<TextComponent> texts = new ArrayList<>();
		texts.add(new TextComponent(ChatColor.translateAlternateColorCodes('&',
				NDPAddon.getNdpAddon().getJoinme().line1.replaceAll("%Name%", Name).replaceAll("%Server%", Servername))));
		texts.add(new TextComponent(ChatColor.translateAlternateColorCodes('&',
				NDPAddon.getNdpAddon().getJoinme().line2.replaceAll("%Name%", Name).replaceAll("%Server%", Servername))));
		texts.add(new TextComponent(ChatColor.translateAlternateColorCodes('&',
				NDPAddon.getNdpAddon().getJoinme().line3.replaceAll("%Name%", Name).replaceAll("%Server%", Servername))));
		texts.add(new TextComponent(ChatColor.translateAlternateColorCodes('&',
				NDPAddon.getNdpAddon().getJoinme().line4.replaceAll("%Name%", Name).replaceAll("%Server%", Servername))));
		texts.add(new TextComponent(ChatColor.translateAlternateColorCodes('&',
				NDPAddon.getNdpAddon().getJoinme().line5.replaceAll("%Name%", Name).replaceAll("%Server%", Servername))));
		texts.add(new TextComponent(ChatColor.translateAlternateColorCodes('&',
				NDPAddon.getNdpAddon().getJoinme().line6.replaceAll("%Name%", Name).replaceAll("%Server%", Servername))));
		texts.add(new TextComponent(ChatColor.translateAlternateColorCodes('&',
				NDPAddon.getNdpAddon().getJoinme().line7.replaceAll("%Name%", Name).replaceAll("%Server%", Servername))));
		texts.add(new TextComponent(ChatColor.translateAlternateColorCodes('&',
				NDPAddon.getNdpAddon().getJoinme().line8.replaceAll("%Name%", Name).replaceAll("%Server%", Servername))));

		for (TextComponent component : texts) {
			component.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, RunCommand));
			component.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
					(new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', NDPAddon.getNdpAddon().getJoinme().HoverText)))
							.create()));
		}

		if (NDPAddon.getNdpAddon().getJoinme().PlayerHead.booleanValue()) {

			try {
				BufferedImage imageToSend = ImageIO.read(new URL("https://cravatar.eu/helmavatar/" + uuid + "/8"));
				ImageUtil msh = new ImageUtil(imageToSend, 8, '█');

				ArrayList<TextComponent> heads = new ArrayList<>();

				int i = 0;
				while (i < 8) {
					heads.add(new TextComponent(msh.getLines()[i]));
					i++;
				}

				for (TextComponent component : heads) {
					component.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, RunCommand));
					component.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
							(new ComponentBuilder(
									ChatColor.translateAlternateColorCodes('&', NDPAddon.getNdpAddon().getJoinme().HoverText)))
											.create()));
				}

				i = 0;
				while (i < heads.size()) {
					heads.get(i).addExtra(texts.get(i));
					i++;
				}

				for (ProxiedPlayer all : NDPAddon.getNdpAddon().getProxy().getPlayers()) {
					if (!NDPAddon.getNdpAddon().getJoinme().DisableServerReceive
							.contains(all.getServer().getInfo().getName().split("-")[0])) {

						for (TextComponent component : heads) {
							all.sendMessage(component);
						}
					}
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else {
			for (ProxiedPlayer all : NDPAddon.getNdpAddon().getProxy().getPlayers()) {
				if (!NDPAddon.getNdpAddon().getJoinme().DisableServerReceive.contains(all.getServer().getInfo().getName().split("-")[0])) {
					for (TextComponent component : texts) {
						all.sendMessage(component);
					}
				}
			}
		}
	}
}
