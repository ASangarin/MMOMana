package eu.asangarin.mana;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class EventListener implements Listener {
	@EventHandler(priority = EventPriority.LOW)
	public void playerJoin(PlayerJoinEvent event) {
		MMOMana.plugin.dataManager.setup(event.getPlayer());
	}
}
