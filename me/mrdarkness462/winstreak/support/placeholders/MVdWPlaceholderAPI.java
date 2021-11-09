/*    */
package me.mrdarkness462.winstreak.support.placeholders;

import be.maximvdw.placeholderapi.PlaceholderAPI;
import me.mrdarkness462.winstreak.Main;
import me.mrdarkness462.winstreak.streak.PlayerStreak;
import org.bukkit.plugin.java.JavaPlugin;

public class MVdWPlaceholderAPI {

    public void hook() {

        PlaceholderAPI.registerPlaceholder(JavaPlugin.getPlugin(Main.class), "bw1058win_streak", placeholderReplaceEvent -> String.valueOf(PlayerStreak.getStreak(placeholderReplaceEvent.getPlayer())));
    }
}