package net.glowstone.inventory;

/**
 * Represents an entity which can view an inventory.
 */
public interface InventoryViewer {

	/**
	 * Inform the viewer that an item has changed.
	 *
	 * @param inventory The GlowInventory in which a slot has changed.
	 * @param slot      The slot number which has changed.
	 * @param item      The ItemStack which the slot has changed to.
	 */
	public void onSlotSet(GlowInventory inventory, int slot, GlowItemStack item);

}
