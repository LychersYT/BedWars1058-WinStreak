package me.mrdarkness462.winstreak.files;


import me.mrdarkness462.winstreak.utils.FileManager;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Messages {

    private static final FileManager messagesFile = new FileManager("messages", "plugins/BedWars1058/Addons/WinStreak");

    public Messages() {
        YamlConfiguration msg = messagesFile.getYml();
        msg.addDefault("addon.only-player", "&cCommands can't be run from the console!");
        msg.addDefault("addon.unknown-player", "%target% &7is not online!");
        msg.addDefault("addon.not-enough", "%target% &7doesn't have enough streaks!");
        msg.addDefault("addon.not-number", "%value% &7is not a number!");
        msg.addDefault("addon.reloaded", "&7Win Streak addon was reloaded successfully!");
        msg.addDefault("commands.available", Arrays.asList("§6 ▪ &7/bw streak add <player> <amount>", "§6 ▪ &7/bw streak remove <player> <amount>", "§6 ▪ &7/bw streak set <player> <amount>", "§6 ▪ &7/bw streak reset <player>", "§6 ▪ &7/bw streak reload"));
        msg.addDefault("commands.add-streak.usage", "&7Usage: &f/bw streak add <player> <amount>");
        msg.addDefault("commands.add-streak.success", "&7You added %streaks% Streaks to %target%. Now %target% has %pstreaks% Win Streaks");
        msg.addDefault("commands.remove-streak.usage", "&7Usage: &f/bw streak remove <player> <amount>");
        msg.addDefault("commands.remove-streak.success", "&7You removed %streaks% Streaks from %target%. Now %target% has %pstreaks% Win Streaks");
        msg.addDefault("commands.set-streak.usage", "&7Usage: &f/bw Streak Set <player> <amount>");
        msg.addDefault("commands.set-streak.success", "&7Now %target% has %streaks% Win Streaks");
        msg.addDefault("commands.reset-streak.usage", "&7Usage: &f/bw streak reset <player>");
        msg.addDefault("commands.reset-streak.success", "&7You've reseted %target% win streaks");
        msg.addDefault("commands.player-streak", "&7Your streaks: &6%streaks%");
        msg.options().copyDefaults(true);
        messagesFile.save();

    }


    public void reload() {
        messagesFile.reload();

    }


    public String getString(String str) {
        return ChatColor.translateAlternateColorCodes('&', messagesFile.getYml().getString(str));

    }


    public List<String> getStringList(String str) {
        List<String> colored = new ArrayList<>();
        for (String str2 : messagesFile.getYml().getStringList(str)) {
            colored.add(ChatColor.translateAlternateColorCodes('&', str2));

        }
        return colored;

    }

}