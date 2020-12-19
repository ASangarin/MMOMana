package eu.asangarin.mana;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

public class ManaConfig {
	public int refreshRate;

	public double loginManaRatio;
	public double loginStaminaRatio;

	public int manaBarLength;
	public int staminaBarLength;

	public ChatColor manaBarWholeColor;
	public ChatColor manaBarHalfColor;
	public ChatColor manaBarEmptyColor;
	public ChatColor staminaBarWholeColor;
	public ChatColor staminaBarHalfColor;
	public ChatColor staminaBarEmptyColor;

	public char manaBarChar;
	public char staminaBarChar;

	public void loadOptions(FileConfiguration config) {
		this.refreshRate = config.getInt("refresh-rate");

		this.loginManaRatio = config.getDouble("login-ratio.mana");
		this.loginStaminaRatio = config.getDouble("login-ratio.stamina");

		this.manaBarChar = config.getString("resource-bar.mana.char").charAt(0);
		this.manaBarLength = config.getInt("resource-bar.mana.length");

		this.staminaBarChar = config.getString("resource-bar.stamina.char").charAt(0);
		this.staminaBarLength = config.getInt("resource-bar.stamina.length");

		this.manaBarWholeColor = getColorFromString(config.getString(""));
		this.manaBarHalfColor = getColorFromString(config.getString(""));
		this.manaBarEmptyColor = getColorFromString(config.getString(""));
		this.staminaBarWholeColor = getColorFromString(config.getString(""));
		this.staminaBarHalfColor = getColorFromString(config.getString(""));
		this.staminaBarEmptyColor = getColorFromString(config.getString(""));
	}

	private ChatColor getColorFromString(String color) {
		ChatColor cc = ChatColor.getByChar(color.charAt(0));
		if(cc == null)
			cc = ChatColor.of(color);
		if(cc == null) {
			MMOMana.plugin.getLogger().severe("Couldn't load Color {" + color + "}");
			return ChatColor.WHITE;
		}
		return cc;
	}
}
