//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

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
	public double loginManaRatio;
	public double loginStaminaRatio;
	public int manaBarLength;
	public int staminaBarLength;
	public String manaBarColor;
	public String staminaBarColor;
	public char manaBarChar;
	public char staminaBarChar;

	public void onLoad() {
		plugin = this;
		this.saveDefaultConfig();
		for(ResourceData.StatType type : ResourceData.StatType.values())
			type.registerStat();
	}

	public void onEnable() {
		if (Bukkit.getPluginManager().isPluginEnabled("MMOCore")) {
			getLogger().severe("DO NOT USE THIS ADDON ALONGSIDE MMOCORE!");
			getLogger().severe("DO NOT USE THIS ADDON ALONGSIDE MMOCORE!");
			getLogger().severe("DO NOT USE THIS ADDON ALONGSIDE MMOCORE!");
			getLogger().severe("DO NOT USE THIS ADDON ALONGSIDE MMOCORE!");
			getLogger().severe("DO NOT USE THIS ADDON ALONGSIDE MMOCORE!");
			Bukkit.getPluginManager().disablePlugin(this);
			return;
		}
		this.loadOptions();
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
		}, 100L, 20L);
		Bukkit.getScheduler().runTaskTimerAsynchronously(this, () -> dataManager.getLoaded().removeIf(data -> !data.toMMOLib().getPlayer().isOnline() && (System.currentTimeMillis() - data.toMMOLib().getPlayer().getLastPlayed() > 3600000L)), 100L, 72000L);
	}

	public void loadOptions() {
		this.loginManaRatio = this.getConfig().getDouble("login-ratio.mana");
		this.loginStaminaRatio = this.getConfig().getDouble("login-ratio.stamina");
		this.manaBarChar = this.getConfig().getString("resource-bar.mana.char").toCharArray()[0];
		this.manaBarColor = this.getConfig().getString("resource-bar.mana.color");
		this.manaBarLength = this.getConfig().getInt("resource-bar.mana.length");
		this.staminaBarChar = this.getConfig().getString("resource-bar.stamina.char").toCharArray()[0];
		this.staminaBarColor = this.getConfig().getString("resource-bar.stamina.color");
		this.staminaBarLength = this.getConfig().getInt("resource-bar.stamina.length");
	}
}
