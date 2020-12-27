package eu.asangarin.mana;

import java.util.logging.Level;

import eu.asangarin.mana.api.ResourceData;
import eu.asangarin.mana.api.ResourceRegainReason;
import eu.asangarin.mana.comp.DefaultHook;
import eu.asangarin.mana.comp.PAPIPlaceholders;
import eu.asangarin.mana.manager.PlayerDataManager;
import net.Indyuce.mmoitems.MMOItems;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class MMOMana extends JavaPlugin {
	public static MMOMana plugin;
	public final PlayerDataManager dataManager = new PlayerDataManager();
	public final ManaConfig config = new ManaConfig();

	public void onLoad() {
		plugin = this;
		this.saveDefaultConfig();
		config.loadOptions(getConfig());
		for(ResourceData.StatType type : ResourceData.StatType.values())
			type.registerStat();
	}

	public void onEnable() {
		final int configVersion = getConfig().contains("config-version", true) ? getConfig().getInt("config-version") : -1;
		final int defConfigVersion = getConfig().getDefaults().getInt("config-version");
		if(configVersion != defConfigVersion) {
			getLogger().warning("You may be using an outdated config.yml!");
			getLogger().warning("(Your config version: '" + configVersion + "' | Expected config version: '" + defConfigVersion + "')");
		}

		if (Bukkit.getPluginManager().isPluginEnabled("MMOCore")) {
			getLogger().severe("DO NOT USE THIS ADDON ALONGSIDE MMOCORE!");
			getLogger().severe("DO NOT USE THIS ADDON ALONGSIDE MMOCORE!");
			getLogger().severe("DO NOT USE THIS ADDON ALONGSIDE MMOCORE!");
			getLogger().severe("DO NOT USE THIS ADDON ALONGSIDE MMOCORE!");
			getLogger().severe("DO NOT USE THIS ADDON ALONGSIDE MMOCORE!");
			Bukkit.getPluginManager().disablePlugin(this);
			return;
		}
		MMOItems.plugin.setRPG(new DefaultHook());

		Bukkit.getOnlinePlayers().forEach(this.dataManager::setup);
		if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
			(new PAPIPlaceholders()).register();
			this.getLogger().log(Level.INFO, "Hooked onto PlaceholderAPI");
		}

		Bukkit.getPluginManager().registerEvents(new EventListener(), this);
		Bukkit.getScheduler().runTaskTimer(this, () -> {
			for(ResourceData data : dataManager.getLoaded()) {
				if (data.toMMOLib().isOnline()) {
					data.giveMana(data.getStat(ResourceData.StatType.MANA_REGENERATION), ResourceRegainReason.REGENERATION);
					data.giveStamina(data.getStat(ResourceData.StatType.STAMINA_REGENERATION), ResourceRegainReason.REGENERATION);
				}
			}
		}, 100L, config.refreshRate);
		Bukkit.getScheduler().runTaskTimerAsynchronously(this, () -> dataManager.getLoaded().removeIf(data -> !data.toMMOLib().isOnline()), 600000L, 600000L);
	}
}
