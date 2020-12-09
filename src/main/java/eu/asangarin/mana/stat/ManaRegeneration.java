package eu.asangarin.mana.stat;

import net.Indyuce.mmoitems.stat.type.DoubleStat;
import net.mmogroup.mmolib.version.VersionMaterial;

public class ManaRegeneration extends DoubleStat {
	public ManaRegeneration() {
		super("MANA_REGENERATION", VersionMaterial.LAPIS_LAZULI.toMaterial(), "Mana Regeneration", new String[]{"Increases mana regen."});
	}
}
