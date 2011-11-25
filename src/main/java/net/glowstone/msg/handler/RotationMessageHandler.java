package net.glowstone.msg.handler;

import net.glowstone.entity.GlowPlayer;
import net.glowstone.msg.RotationMessage;
import net.glowstone.net.Session;
import org.bukkit.Location;

public final class RotationMessageHandler extends MessageHandler<RotationMessage> {

	@Override
	public void handle(Session session, GlowPlayer player, RotationMessage message) {
		if (player == null)
			return;

		Location loc = player.getLocation();
		loc.setYaw(message.getRotation());
		loc.setPitch(message.getPitch());
		player.setRawLocation(loc);
	}

}
