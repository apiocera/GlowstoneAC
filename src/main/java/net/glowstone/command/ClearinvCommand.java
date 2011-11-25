package net.glowstone.command;

import net.glowstone.GlowServer;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionDefault;

/**
 * some day I will put some licence text here, but not now. //apiocera
 */
public class ClearinvCommand extends GlowCommand {
	public ClearinvCommand(GlowServer server) {
		super(server, "clearinv", "Clear inventory for player", "[player]");
	}

	@Override
	public boolean run(CommandSender sender, String commandLabel, String[] args) {
		if (!checkArgs(sender, args, 0, 1)) {
			return false;
		}
		String name;
		Player targetPlayer;

		if (args.length == 0) {
			if (!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "You must be a player to use this without a player name");
				return false;
			}
			targetPlayer = (Player) sender;

		} else {
			name = args[0];
			targetPlayer = server.getPlayerExact(name);
			if (targetPlayer == null) {
				sender.sendMessage(ChatColor.RED + "Unknown player given: " + name);
				return false;
			}
		}

		targetPlayer.getInventory().clear();
		return true;
	}

	@Override
	public PermissionDefault getPermissionDefault() {
		return null;
	}
}
