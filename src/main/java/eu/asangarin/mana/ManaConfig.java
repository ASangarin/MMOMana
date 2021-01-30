package eu.asangarin.mana;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

public class ManaConfig {
	public int refreshRate;

	public double loginManaRatio;
	public double loginStaminaRatio;
	public double regenScale;

	public int manaBarLength;
	public int staminaBarLength;

	public ChatColor manaBarWholeColor = ChatColor.GRAY;
	public ChatColor manaBarHalfColor = ChatColor.GRAY;
	public ChatColor manaBarEmptyColor = ChatColor.GRAY;
	public ChatColor staminaBarWholeColor = ChatColor.GRAY;
	public ChatColor staminaBarHalfColor = ChatColor.GRAY;
	public ChatColor staminaBarEmptyColor = ChatColor.GRAY;

	public char manaBarChar;
	public char staminaBarChar;

	public void loadOptions(FileConfiguration config) {
		this.refreshRate = config.getInt("refresh-rate");
		this.regenScale = config.getDouble("regen-scale");

		this.loginManaRatio = config.getDouble("login-ratio.mana");
		this.loginStaminaRatio = config.getDouble("login-ratio.stamina");

		this.manaBarChar = config.getString("resource-bar.mana.char").charAt(0);
		this.manaBarLength = config.getInt("resource-bar.mana.length");

		this.staminaBarChar = config.getString("resource-bar.stamina.char").charAt(0);
		this.staminaBarLength = config.getInt("resource-bar.stamina.length");

		this.manaBarWholeColor = getColorFromString(config.getString("resource-bar.mana.colors.whole"));
		this.manaBarHalfColor = getColorFromString(config.getString("resource-bar.mana.colors.half"));
		this.manaBarEmptyColor = getColorFromString(config.getString("resource-bar.mana.colors.empty"));
		this.staminaBarWholeColor = getColorFromString(config.getString("resource-bar.stamina.colors.whole"));
		this.staminaBarHalfColor = getColorFromString(config.getString("resource-bar.stamina.colors.half"));
		this.staminaBarEmptyColor = getColorFromString(config.getString("resource-bar.stamina.colors.empty"));
	}

	@SuppressWarnings("deprecation")
	private ChatColor getColorFromString(String color) {
		ChatColor cc = ChatColor.getByChar(color.charAt(0));
		try {
			return ChatColor.valueOf(color);
		} catch (IllegalArgumentException ignore) {}
		if(cc == null) {
			try {
				cc = ChatColor.of(color);
			} catch (NoSuchMethodError exception) {
				MMOMana.plugin.getLogger().severe("Tried to use the ChatColor.of() method, but it couldn't be found!");
				MMOMana.plugin.getLogger().severe("Is '" + color + "' a hex color? If so, make sure you're on 1.16 or above!");
				return ChatColor.WHITE;
			}
		}
		if(cc != null)
			return cc;

		MMOMana.plugin.getLogger().severe("Couldn't load Color {" + color + "}");
		return ChatColor.WHITE;
	}
}
