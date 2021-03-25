package de.ndpcloud.addon.commands;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import de.ndpcloud.addon.NDPAddon;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class JoinMeCommand extends Command {
    public JoinMeCommand() {
        super(
                "JoinMe",
                null,
                "inviteme"
                );
    }

    ArrayList<String> waiting = new ArrayList<>();

    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            final ProxiedPlayer p = (ProxiedPlayer) sender;
            if (args.length == 0) {
                if (p.hasPermission(NDPAddon.getNdpAddon().getJoinme().Permissions)) {
                    if (NDPAddon.getNdpAddon().getJoinme().DisableServerCommand
                            .contains(p.getServer().getInfo().getName().split("-")[0])) {
                        p.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&',
                                NDPAddon.getNdpAddon().getJoinme().DisableServerCommandMessage)));
                    } else if (waiting.contains(p.getName())) {
                        p.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&',
                                NDPAddon.getNdpAddon().getJoinme().CommandCooldownMessage)));
                    } else {
                        p.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&',
                                NDPAddon.getNdpAddon().getJoinme().CommandSuccessfullySentMessage)));
                        NDPAddon.getNdpAddon().getApi().createJoinMe(p);

                        if (!p.hasPermission(NDPAddon.getNdpAddon().getJoinme().CooldownIgnorPermissions)) {
                            waiting.add(p.getName());
                            NDPAddon.getNdpAddon().getProxy().getScheduler().schedule(NDPAddon.getNdpAddon(),
                                    () -> {
                                        if (waiting.contains(p.getName()))
                                            waiting.remove(p.getName());

                                    }, NDPAddon.getNdpAddon().getJoinme().CommandCooldownMin.intValue(), TimeUnit.MINUTES);
                        }
                    }
                } else {

                    p.sendMessage(new TextComponent(
                            ChatColor.translateAlternateColorCodes('&', NDPAddon.getNdpAddon().getJoinme().NoPermissions)));
                }
            } else if (args.length == 1) {
                try {
                    UUID uuid = UUID.fromString(args[0]);
                    if (uuid != null) {
                        NDPAddon.getNdpAddon().getApi().joinJoinMe(p, uuid);
                    }
                } catch (IllegalArgumentException e) {
                    NDPAddon.getNdpAddon().getLogger().info(MessageFormat.format("The Player: {0} was trying to buged the System.", sender.getName()));
                }

            }
        }
    }
}
