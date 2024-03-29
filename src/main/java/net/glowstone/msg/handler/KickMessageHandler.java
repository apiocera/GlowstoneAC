package net.glowstone.msg.handler;

import net.glowstone.EventFactory;
import net.glowstone.entity.GlowPlayer;
import net.glowstone.msg.KickMessage;
import net.glowstone.net.Session;

public final class KickMessageHandler extends MessageHandler<KickMessage> {

	@Override
	public void handle(Session session, GlowPlayer player, KickMessage message) {

		String text = EventFactory.onPlayerQuit(player).getQuitMessage();
		if (message != null) {
			session.getServer().broadcastMessage(text);
		}

		session.disconnect("Goodbye!", true);
	}

}
