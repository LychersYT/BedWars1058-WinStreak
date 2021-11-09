/*    */
package me.mrdarkness462.winstreak.streak;

import org.bukkit.entity.Player;

public class Streak {
    public int get(Player p) {
        return PlayerStreak.getStreak(p);
    }

    public void add(Player p, int val) {
        PlayerStreak.setStreak(p, get(p) + val);
    }

    public boolean hasEnough(Player p, int val) {
        return (val <= get(p));
    }

    public void remove(Player p, int val) {
        if (hasEnough(p, val)) {
            PlayerStreak.setStreak(p, get(p) - val);
        }
    }

    public void set(Player p, int val) {
        PlayerStreak.setStreak(p, val);
    }

    public void reset(Player p) {
        PlayerStreak.setStreak(p, 0);
    }
}