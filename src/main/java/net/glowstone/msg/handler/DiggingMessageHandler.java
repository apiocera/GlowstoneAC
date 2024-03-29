package net.glowstone.msg.handler;

import net.glowstone.EventFactory;
import net.glowstone.GlowWorld;
import net.glowstone.block.BlockID;
import net.glowstone.block.BlockProperties;
import net.glowstone.entity.GlowPlayer;
import net.glowstone.msg.DiggingMessage;
import net.glowstone.net.Session;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.block.Block;
import org.bukkit.event.Event;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * A {@link MessageHandler} which processes digging messages.
 */
public final class DiggingMessageHandler extends MessageHandler<DiggingMessage> {

	@Override
	public void handle(Session session, GlowPlayer player, DiggingMessage message) {
		if (player == null)
			return;

		boolean blockBroken = false;

		GlowWorld world = player.getWorld();

		int x = message.getX();
		int y = message.getY();
		int z = message.getZ();

		Block block = world.getBlockAt(x, y, z);

		// Need to have some sort of verification to deal with malicious clients.
		if (message.getState() == DiggingMessage.STATE_START_DIGGING) {
			Action act = Action.LEFT_CLICK_BLOCK;
			if (player.getLocation().distanceSquared(block.getLocation()) > 36 || block.getTypeId() == BlockID.AIR) {
				act = Action.LEFT_CLICK_AIR;
			}
			PlayerInteractEvent interactEvent = EventFactory.onPlayerInteract(player, act, block, MessageHandlerUtils.messageToBlockFace(message.getFace()));
			if (interactEvent.isCancelled()) return;
			if (interactEvent.useItemInHand() == Event.Result.DENY) return;
			// TODO: Item interactions
			BlockDamageEvent event = EventFactory.onBlockDamage(player, block);
			if (!event.isCancelled()) {
				blockBroken = BlockProperties.get(block.getType()).isInstantlyBroken() || player.getGameMode() == GameMode.CREATIVE;
			}
		} else if (message.getState() == DiggingMessage.STATE_DONE_DIGGING) {
			BlockBreakEvent event = EventFactory.onBlockBreak(block, player);
			if (!event.isCancelled()) {
				blockBroken = true;
			}
		}

		if (blockBroken) {
			// TODO: Instead of dumb adding the broken block, spawn a drop entity
			if (!block.isEmpty() && !block.isLiquid()) {
				if ((!player.getInventory().contains(block.getType()) || player.getGameMode() != GameMode.CREATIVE)) {
					player.getInventory().addItem(BlockProperties.get(block.getTypeId()).getDrops(block.getData()));
				}
			}
			world.playEffectExceptTo(block.getLocation(), Effect.STEP_SOUND, block.getTypeId(), 64, player);
			block.setTypeId(BlockID.AIR);
		}
	}

}
