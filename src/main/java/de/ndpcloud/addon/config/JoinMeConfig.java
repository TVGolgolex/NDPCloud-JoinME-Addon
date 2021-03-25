package de.ndpcloud.addon.config;

import java.io.File;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;

import de.ndpcloud.addon.NDPAddon;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class JoinMeConfig {

	public ArrayList<String> BlockedServers;
	public String Permissions = "Cloud.JoinMe";
	public String CooldownIgnorPermissions = "Cloud.JoinMeNoCooldown";

	public String NoPermissions = "&c&oI`m Sorry but you don`t have the Permissions!";

	public String DisableServerCommandMessage = "&c&oI'm Sorry but you are not allow to create a JoinMe on Your Server!";
	public String CommandSuccessfullySentMessage = "&a&oYou successfully created a JoinMe.";
	public String CommandCooldownMessage = "&c&oYou have to wait to created a JoinMe.";

	public String PlayerClosedTheJoinMe = "&c&oI´m Sorry but the JoinMe is Closed!";

	public Integer CommandCooldownMin = Integer.valueOf(3);

	public Boolean PlayerHead = Boolean.valueOf(true);

	public String line1 = " ";
	public String line2 = "&6&k=========================================";
	public String line3 = " ";
	public String line4 = " &e%DisplayName% &6plays on &e%Server%";
	public String line5 = "  &6Click here to Play with him!";
	public String line6 = " ";
	public String line7 = "&6&k=========================================";
	public String line8 = " ";

	public String HoverText = "&6Click to play!";

	public ArrayList<String> DisableServerCommand = new ArrayList<String>();
	public ArrayList<String> DisableServerReceive = new ArrayList<String>();

	@SuppressWarnings("unchecked")
	public JoinMeConfig(NDPAddon NDPAddon) {
		try {
			if (!NDPAddon.getDataFolder().exists()) {
				NDPAddon.getDataFolder().mkdir();
			}
			File file = new File(NDPAddon.getDataFolder(), "JoinMe-Config.yml");
			if (!file.exists()) {
				Files.copy(NDPAddon.getResourceAsStream("JoinMe-Config.yml"), file.toPath(), new CopyOption[0]);
			}

			Configuration config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
			Permissions = config.getString("Permissions");
			CooldownIgnorPermissions = config.getString("CooldownIgnorPermissions");

			PlayerHead = Boolean.valueOf(config.getBoolean("PlayerHead"));

			NoPermissions = config.getString("NoPermissions");
			line1 = config.getString("line1");
			line2 = config.getString("line2");
			line3 = config.getString("line3");
			line4 = config.getString("line4");
			line5 = config.getString("line5");
			line6 = config.getString("line6");
			line7 = config.getString("line7");
			line8 = config.getString("line8");

			HoverText = config.getString("HoverText");

			DisableServerCommand.addAll((Collection<? extends String>) config.getList("DisableServerCommand"));
			DisableServerReceive.addAll((Collection<? extends String>) config.getList("DisableServerReceive"));

			DisableServerCommandMessage = config.getString("DisableServerCommandMessage");
			CommandSuccessfullySentMessage = config.getString("CommandSuccessfullySentMessage");
			CommandCooldownMessage = config.getString("CommandCoolcdownMessage");
			CommandCooldownMin = Integer.valueOf(config.getInt("CommandCooldownMin"));

		} catch (Exception exception) {
			NDPAddon.getLogger().info("Error: " + exception.getMessage());
		}

	}

}
