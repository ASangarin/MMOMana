package eu.asangarin.mana.stat;

import net.Indyuce.mmoitems.stat.type.DoubleStat;
import net.mmogroup.mmolib.version.VersionMaterial;

public class MaxStamina extends DoubleStat {
	public MaxStamina() {
		super("MAX_STAMINA", VersionMaterial.LIGHT_BLUE_DYE.toMaterial(), "Max Stamina", new String[]{"Adds stamina to your max stamina bar."});
	}
}
