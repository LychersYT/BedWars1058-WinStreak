package me.mrdarkness462.winstreak.storage;

import me.mrdarkness462.winstreak.Main;
import org.bukkit.entity.Player;


public class Profile {
    private final Database db;

    public Profile(Main plugin) {

        if (plugin.getBedWarsAPI().getConfigs().getMainConfig().getBoolean("database.enable")) {
            this.db = MySQL.getInstance();
        } else {
            this.db = SQLite.getInstance();
        }
    }

    public boolean hasAccount(Player p) {
        return this.db.hasAccount(p);
    }

    public void setInt(Player p, int value) {
        this.db.setInt(p, value, "WinStreak");
    }

    public int getInt(Player p) {
        return this.db.getInt(p, "WinStreak");
    }

    public void createPlayer(Player p) {
        this.db.createPlayer(p);
    }

    public void loadDatabase() {
        this.db.create();
    }
}
