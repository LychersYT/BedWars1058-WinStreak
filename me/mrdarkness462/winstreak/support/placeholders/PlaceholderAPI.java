/*    */
package me.mrdarkness462.winstreak.support.placeholders;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.mrdarkness462.winstreak.streak.PlayerStreak;
import org.bukkit.entity.Player;

public class PlaceholderAPI extends PlaceholderExpansion {

    @Override
    public String getIdentifier() {
        return "bw1058win";
    }

    @Override
    public String getAuthor() {
        return "MrDarkness462, reussy";
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

    public String onPlaceholderRequest(Player p, String identifier) {
        if (identifier.equalsIgnoreCase("streak")) {
            return String.valueOf(PlayerStreak.getStreak(p));
        }
        if (p == null) {
            return "";
        }
        return null;
    }
}