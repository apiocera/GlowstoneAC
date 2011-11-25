package net.glowstone.command;

import net.glowstone.GlowServer;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.permissions.PermissionDefault;

/**
 * some day I will put some licence text here, but not now. //apiocera
 */
public class GiveCommand extends GlowCommand {
	public GiveCommand(GlowServer server) {
		super(server, "give", "Give player a resource", "<player> <item> [<amount>]");
	}

	@Override
	public boolean run(CommandSender sender, String commandLabel, String[] args) {
		if (!checkArgs(sender, args, 2, 3)) {
			return false;
		}
		int item = Integer.parseInt(args[1]);
		int amount = (args.length == 3 ? Integer.parseInt(args[2]) : 1);

		Player player = server.getPlayer(args[0]);
		if (player == null) {
			sender.sendMessage(ChatColor.GRAY + "Player not found.");
			return false;
		}

		player.getInventory().addItem(new ItemStack(item, amount));
		return true;
	}

	@Override
	public PermissionDefault getPermissionDefault() {
		return PermissionDefault.OP;
	}
}
