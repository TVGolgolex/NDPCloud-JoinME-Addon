package de.ndpcloud.addon.API;

import java.util.UUID;

import net.md_5.bungee.api.connection.ProxiedPlayer;

public interface API {

	public void createJoinMe(ProxiedPlayer p);

	public void joinJoinMe(ProxiedPlayer p, UUID uuid);

}
