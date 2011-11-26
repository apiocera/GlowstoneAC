package net.glowstone.command;

import net.glowstone.GlowServer;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionDefault;

/**
 * some day I will put some licence text here, but not now. //apiocera
 */
public class SpawnCommand extends GlowCommand {
	public SpawnCommand(GlowServer server) {
		super(server, "spawn", "Go to spawn", "");
	}

	@Override
	public boolean run(CommandSender sender, String commandLabel, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "You must be a player to use this.");
			return false;
		}

		((Player) sender).teleport(((Player) sender).getWorld().getSpawnLocation());
		return true;
	}

	@Override
	public PermissionDefault getPermissionDefault() {
		return PermissionDefault.TRUE;
	}
}
