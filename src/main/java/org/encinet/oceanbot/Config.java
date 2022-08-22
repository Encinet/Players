package org.encinet.oceanbot;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Config {
    static final Plugin plugin = JavaPlugin.getProvidingPlugin(OceanBot.class);

    public static FileConfiguration getConfig() {
        return plugin.getConfig();
    }

    public static int ver;
    public static List<String> prefix;
    public static Long BotID;
    public static List<Long> GroupID;
    public static Long MainGroup;
    public static Boolean gnc;
    public static Map<Integer, String> numMessage;
    public static String noWhiteKick;
    public static List<String> chatPrefix;
    public static String qqToServer;
    public static String serverToQQ;
    public static String join;

    public static List<Long> admin;
    public static void load() {
        plugin.reloadConfig();

        ver = getConfig().getInt("ver", 3);
        prefix = getConfig().getStringList("prefix");
        BotID = getConfig().getLong("BotID");
        GroupID = getConfig().getLongList("GroupID");
        MainGroup = getConfig().getLong("MainGroup");
        gnc = getConfig().getBoolean("gnc", true);

        numMessage = new HashMap<>();
        for (int num : getConfig().getIntegerList("NumMessage")) {
            numMessage.put(num, getConfig().getString("NumMessage." + num, ""));
        }

        noWhiteKick = getConfig().getString("noWhiteKick");
        join = getConfig().getString("join");
        chatPrefix = getConfig().getStringList("chat.prefix");
        qqToServer = getConfig().getString("chat.format.qq-to-server");
        serverToQQ = getConfig().getString("chat.format.server-to-qq");

        admin = getConfig().getLongList("admin");
    }
}
