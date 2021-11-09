/*    */
package me.mrdarkness462.winstreak.streak;


import me.mrdarkness462.winstreak.Main;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class PlayerStreak {

    private final Main plugin;
    private static final HashMap<Player, PlayerStreak> playerStreak = new HashMap<>();
    private int streak;

    public PlayerStreak(Player p, Main plugin, int streak) {
        this.plugin = plugin;
        this.streak = streak;
        playerStreak.put(p, this);
    }

    public static void setStreak(Player p, int val) {
        if (playerStreak.containsKey(p)) {
            PlayerStreak pStreak = playerStreak.get(p);
            pStreak.streak = val;
            pStreak.plugin.getProfile().setInt(p, pStreak.streak);
        }
    }

    public static int getStreak(Player p) {
        if (playerStreak.containsKey(p)) {
            return playerStreak.get(p).streak;
        }
        return 0;
    }
}