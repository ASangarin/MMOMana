//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package eu.asangarin.mana.comp;

import java.text.DecimalFormat;

import eu.asangarin.mana.MMOMana;
import eu.asangarin.mana.api.ResourceData;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;

public class PAPIPlaceholders extends PlaceholderExpansion {
	private static final DecimalFormat digit = new DecimalFormat("0.#");

	public String getAuthor() {
		return "Indyuce";
	}

	public String getIdentifier() {
		return "mana";
	}

	public String getVersion() {
		return MMOMana.plugin.getDescription().getVersion();
	}

	public String onRequest(OfflinePlayer player, String identifier) {
		switch (identifier) {
			case "mana":
				return digit.format(MMOMana.plugin.dataManager.get(player).getMana());
			case "stamina":
				return digit.format(MMOMana.plugin.dataManager.get(player).getStamina());
			case "mana_bar":
				return MMOMana.plugin.dataManager.get(player).getManaBar(MMOMana.plugin.manaBarLength, MMOMana.plugin.manaBarChar, MMOMana.plugin.manaBarColor);
			case "stamina_bar":
				return MMOMana.plugin.dataManager.get(player).getStaminaBar(MMOMana.plugin.staminaBarLength, MMOMana.plugin.staminaBarChar, MMOMana.plugin.staminaBarColor);
			case "max_mana":
				return digit.format(MMOMana.plugin.dataManager.get(player).getStat(ResourceData.StatType.MAX_MANA));
			case "max_stamina":
				return digit.format(MMOMana.plugin.dataManager.get(player).getStat(ResourceData.StatType.MAX_STAMINA));
			case "mana_regen":
				return digit.format(MMOMana.plugin.dataManager.get(player).getStat(ResourceData.StatType.MANA_REGENERATION));
			case "stamina_regen":
				return identifier.equals("stamina_regen") ? digit.format(MMOMana.plugin.dataManager.get(player).getStat(ResourceData.StatType.STAMINA_REGENERATION)) : null;
			default:
				return "PHE";
		}
	}
}
