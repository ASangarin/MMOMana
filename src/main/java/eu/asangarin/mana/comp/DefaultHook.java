//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package eu.asangarin.mana.comp;

import eu.asangarin.mana.MMOMana;
import eu.asangarin.mana.api.ResourceData;
import net.Indyuce.mmoitems.api.player.PlayerData;
import net.Indyuce.mmoitems.api.player.RPGPlayer;
import net.Indyuce.mmoitems.comp.rpg.RPGHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLevelChangeEvent;

public class DefaultHook implements RPGHandler, Listener {
	@EventHandler
	public void a(PlayerLevelChangeEvent event) {
		PlayerData.get(event.getPlayer()).scheduleDelayedInventoryUpdate();
	}

	public RPGPlayer getInfo(PlayerData data) {
		return new ManaPlayer(data);
	}

	public void refreshStats(PlayerData data) {
	}

	public static class ManaPlayer extends RPGPlayer {
		private final ResourceData data;

		public ManaPlayer(PlayerData playerData) {
			super(playerData);
			this.data = MMOMana.plugin.dataManager.get(this.getPlayer());
		}

		public int getLevel() {
			return this.getPlayer().getLevel();
		}

		public String getClassName() {
			return "";
		}

		public double getMana() {
			return this.data.getMana();
		}

		public double getStamina() {
			return this.data.getStamina();
		}

		public void setMana(double value) {
			this.data.setMana(value);
		}

		public void setStamina(double value) {
			this.data.setStamina(value);
		}

		public void giveMana(double value) {
			this.data.giveMana(value);
		}

		public void giveStamina(double value) {
			this.data.giveStamina(value);
		}
	}
}
