package de.ndpcloud.addon;

import java.util.logging.Logger;

import de.ndpcloud.addon.API.API;
import de.ndpcloud.addon.commands.JoinMeCommand;
import de.ndpcloud.addon.config.JoinMeConfig;
import de.ndpcloud.addon.utils.CloudUtil;
import lombok.AccessLevel;
import lombok.Getter;
import net.md_5.bungee.api.plugin.Plugin;

@Getter
public class NDPAddon extends Plugin {

	@Getter(AccessLevel.PUBLIC) private static volatile NDPAddon ndpAddon;
	@Getter(AccessLevel.PUBLIC) private Logger logger;
	@Getter(AccessLevel.PUBLIC) private JoinMeConfig joinme;
	@Getter(AccessLevel.PUBLIC) private API api;

	@Override
	public void onEnable() {
		ndpAddon = this;
		this.api = new CloudUtil();
		this.logger = this.getLogger();
		this.joinme = new JoinMeConfig(this);
		getProxy().getPluginManager().registerCommand(this, new JoinMeCommand());
	}

}
