package me.mrdarkness462.winstreak;

import com.andrei1058.bedwars.api.events.gameplay.GameEndEvent;
import com.andrei1058.bedwars.api.events.player.PlayerKillEvent;
import com.andrei1058.bedwars.api.events.player.PlayerLeaveArenaEvent;
import me.mrdarkness462.winstreak.streak.PlayerStreak;
import me.mrdarkness462.winstreak.streak.Streak;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class Events implements Listener {

    private final Main plugin;
    private final Streak streak = new Streak();

    public Events(Main plugin){
        this.plugin = plugin;
    }

    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (!plugin.getProfile().hasAccount(p)) {
            plugin.getProfile().createPlayer(p);
        }
        new PlayerStreak(p, plugin, plugin.getProfile().getInt(p));
    }

    @EventHandler
    private void onFinalKill(PlayerKillEvent e) {
        if (e.getCause().toString().contains("FINAL_KILL") && e.getVictim() != null) {
            this.streak.reset(e.getVictim());

        }
    }

    @EventHandler
    private void onGameEnd(GameEndEvent e) {
        for (UUID uuid : e.getWinners()) {
            this.streak.add(Bukkit.getPlayer(uuid), 1);
        }
    }

    @EventHandler
    private void onPlayerLeave(PlayerLeaveArenaEvent e) {
        this.streak.reset(e.getPlayer());

    }
}