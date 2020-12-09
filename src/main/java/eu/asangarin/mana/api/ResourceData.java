//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package eu.asangarin.mana.api;

import java.util.UUID;

import eu.asangarin.mana.MMOMana;
import eu.asangarin.mana.api.event.ResourceRegainEvent;
import eu.asangarin.mana.stat.ManaRegeneration;
import eu.asangarin.mana.stat.MaxStamina;
import eu.asangarin.mana.stat.StaminaRegeneration;
import net.Indyuce.mmoitems.MMOItems;
import net.Indyuce.mmoitems.stat.type.ItemStat;
import net.mmogroup.mmolib.api.player.MMOPlayerData;
import net.mmogroup.mmolib.api.stat.modifier.StatModifier;
import org.bukkit.Bukkit;

public class ResourceData {
	private final MMOPlayerData data;
	private double mana;
	private double stamina;

	public ResourceData(MMOPlayerData data) {
		this.data = data;
		data.getStatMap().getInstance("MAX_MANA").addModifier("manaAndStamina", new StatModifier(ResourceData.StatType.MAX_MANA.getBase()));
		data.getStatMap().getInstance("MAX_STAMINA").addModifier("manaAndStamina", new StatModifier(ResourceData.StatType.MAX_STAMINA.getBase()));
		data.getStatMap().getInstance("MANA_REGENERATION").addModifier("manaAndStamina", new StatModifier(ResourceData.StatType.MANA_REGENERATION.getBase()));
		data.getStatMap().getInstance("STAMINA_REGENERATION").addModifier("manaAndStamina", new StatModifier(ResourceData.StatType.STAMINA_REGENERATION.getBase()));
		this.mana = MMOMana.plugin.loginManaRatio / 100.0D * this.getStat(ResourceData.StatType.MAX_MANA);
		this.stamina = MMOMana.plugin.loginStaminaRatio / 100.0D * this.getStat(ResourceData.StatType.MAX_STAMINA);
	}

	public MMOPlayerData toMMOLib() {
		return this.data;
	}

	/** @deprecated */
	@Deprecated
	public UUID getUniqueId() {
		return this.data.getPlayer().getUniqueId();
	}

	public double getMana() {
		return this.mana;
	}

	public double getStamina() {
		return this.stamina;
	}

	public double getStat(ResourceData.StatType type) {
		return this.data.getStatMap().getStat(type.name());
	}

	public void giveMana(double value) {
		this.giveMana(value, ResourceRegainReason.OTHER);
	}

	public void giveMana(double value, ResourceRegainReason reason) {
		ResourceRegainEvent called = new ResourceRegainEvent(this, value, reason, ResourceRegainEvent.ResourceType.MANA);
		Bukkit.getPluginManager().callEvent(called);
		if (!called.isCancelled()) {
			this.setMana(this.mana + called.getAmount());
		}
	}

	public void giveStamina(double value) {
		this.giveStamina(value, ResourceRegainReason.OTHER);
	}

	public void giveStamina(double value, ResourceRegainReason reason) {
		ResourceRegainEvent called = new ResourceRegainEvent(this, value, reason, ResourceRegainEvent.ResourceType.STAMINA);
		Bukkit.getPluginManager().callEvent(called);
		if (!called.isCancelled()) {
			this.setStamina(this.stamina + called.getAmount());
		}
	}

	public void setMana(double value) {
		this.mana = Math.max(0.0D, Math.min(this.getStat(ResourceData.StatType.MAX_MANA), value));
	}

	public void setStamina(double value) {
		this.stamina = Math.max(0.0D, Math.min(this.getStat(ResourceData.StatType.MAX_STAMINA), value));
	}

	public String getManaBar(int chars, char character, String empty) {
		StringBuilder bar = new StringBuilder();
		int ratio = (int)((double)chars * this.getMana() / this.getStat(ResourceData.StatType.MAX_MANA));

		for(int j = 0; j < chars; ++j) {
			bar.append(j == ratio ? empty + character : character);
		}

		return bar.toString();
	}

	public String getStaminaBar(int chars, char character, String empty) {
		StringBuilder bar = new StringBuilder();
		int ratio = (int)((double)chars * this.getStamina() / this.getStat(ResourceData.StatType.MAX_STAMINA));

		for(int j = 0; j < chars; ++j) {
			bar.append(j == ratio ? empty + character : character);
		}

		return bar.toString();
	}

	public enum StatType {
		MAX_MANA(null),
		MAX_STAMINA(new MaxStamina()),
		MANA_REGENERATION(new ManaRegeneration()),
		STAMINA_REGENERATION(new StaminaRegeneration());

		private final ItemStat stat;
		private final double base;

		StatType(ItemStat stat) {
			this.stat = stat;
			this.base = MMOMana.plugin.getConfig().getDouble("default." + this.name().toLowerCase().replace("_", "-"));
		}

		public double getBase() {
			return this.base;
		}

		public void registerStat() {
			if (this.stat != null)
				MMOItems.plugin.getStats().register(this.stat);
		}
	}
}