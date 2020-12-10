//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package eu.asangarin.mana.comp;

import eu.asangarin.mana.MMOMana;
import eu.asangarin.mana.api.ResourceData;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.mmogroup.mmolib.MMOLib;
import org.bukkit.OfflinePlayer;

public class PAPIPlaceholders extends PlaceholderExpansion {
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
		ResourceData resourceData = MMOMana.plugin.dataManager.get(player);
		switch (identifier) {
			case "mana":
				return MMOLib.plugin.getMMOConfig().decimal.format(resourceData.getMana());
			case "stamina":
				return MMOLib.plugin.getMMOConfig().decimal.format(resourceData.getStamina());
			case "mana_bar":
				return resourceData.getManaBar();
			case "stamina_bar":
				return resourceData.getStaminaBar();
			case "max_mana":
				return MMOLib.plugin.getMMOConfig().decimal.format(resourceData.getStat(ResourceData.StatType.MAX_MANA));
			case "max_stamina":
				return MMOLib.plugin.getMMOConfig().decimal.format(resourceData.getStat(ResourceData.StatType.MAX_STAMINA));
			case "mana_regen":
				return MMOLib.plugin.getMMOConfig().decimal.format(resourceData.getStat(ResourceData.StatType.MANA_REGENERATION));
			case "stamina_regen":
				return MMOLib.plugin.getMMOConfig().decimal.format(resourceData.getStat(ResourceData.StatType.STAMINA_REGENERATION));
			default:
				return "PHE";
		}
	}
}
