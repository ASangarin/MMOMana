package eu.asangarin.mana.api.event;

import eu.asangarin.mana.api.ResourceData;
import eu.asangarin.mana.api.ResourceRegainReason;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ResourceRegainEvent extends Event implements Cancellable {
	private static final HandlerList handlers = new HandlerList();
	private final ResourceType type;
	private final ResourceRegainReason reason;
	private final ResourceData player;
	private double amount;
	private boolean cancelled;

	public ResourceRegainEvent(ResourceData player, double amount, ResourceRegainReason reason, ResourceType type) {
		this.player = player;
		this.reason = reason;
		this.amount = amount;
		this.type = type;
	}

	public ResourceData getPlayer() {
		return this.player;
	}

	public double getAmount() {
		return this.amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public ResourceRegainReason getReason() {
		return this.reason;
	}

	public boolean isCancelled() {
		return this.cancelled;
	}

	public void setCancelled(boolean value) {
		this.cancelled = value;
	}

	public ResourceType getType() {
		return type;
	}

	public HandlerList getHandlers() {
		return handlers;
	}
	public static HandlerList getHandlerList() {
		return handlers;
	}

	public enum ResourceType {
		MANA,
		STAMINA
	}
}
