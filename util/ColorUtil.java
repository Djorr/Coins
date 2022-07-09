package com.rubixstudios.staffcoin.util;

import org.bukkit.ChatColor;

/**
 * @author Djorr
 * @created 08/07/2022 - 14:20
 * @project StaffCoin
 */
public class ColorUtil {

    public static String translate(String line) { return ChatColor.translateAlternateColorCodes('&', line); }

    public static String strip(String line) { return ChatColor.stripColor(line); }
}
