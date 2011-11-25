package net.glowstone.msg;

public final class EntityActionMessage extends Message {

	public static final int ACTION_SNEAKING = 1;
	public static final int ACTION_STOP_SNEAKING = 2;
	public static final int ACTION_LEAVE_BED = 3;
	public static final int ACTION_SPRINTING = 4;
	public static final int ACTION_STOP_SPRINTING = 5;

	private final int id, action;

	public EntityActionMessage(int id, int action) {
		this.id = id;
		this.action = action;
	}

	public int getId() {
		return id;
	}

	public int getAction() {
		return action;
	}

	@Override
	public String toString() {
		return "EntityActionMessage{id=" + id + ",action=" + action + "}";
	}
}
