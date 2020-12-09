package eu.asangarin.mana.stat;

import net.Indyuce.mmoitems.stat.type.DoubleStat;
import net.mmogroup.mmolib.version.VersionMaterial;

public class StaminaRegeneration extends DoubleStat {
	public StaminaRegeneration() {
		super("STAMINA_REGENERATION", VersionMaterial.LIGHT_BLUE_DYE.toMaterial(), "Stamina Regeneration", new String[]{"Increases stamina regen."});
	}
}
