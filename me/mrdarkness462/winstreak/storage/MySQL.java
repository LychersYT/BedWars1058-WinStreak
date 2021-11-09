package me.mrdarkness462.winstreak.storage;

import com.andrei1058.bedwars.api.BedWars;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQL implements Database {
    private static final MySQL instance = new MySQL();
    private final String table = "win_streak";
    BedWars bedWars = Bukkit.getServicesManager().getRegistration(BedWars.class).getProvider();
    private final String host = bedWars.getConfigs().getMainConfig().getString("database.host");
    private final int port = bedWars.getConfigs().getMainConfig().getInt("database.port");
    private final String database = bedWars.getConfigs().getMainConfig().getString("database.database");
    private final boolean ssl = bedWars.getConfigs().getMainConfig().getBoolean("database.ssl");
    private final String username = bedWars.getConfigs().getMainConfig().getString("database.user");
    private final String password = bedWars.getConfigs().getMainConfig().getString("database.pass");
    private Connection connection;

    private MySQL() {

        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database + "?autoReconnect=true&user=" + this.username + "&password=" + this.password + "&useSSL=" + this.ssl + "&autoReconnect=true");
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        isConnected();
    }

    public static MySQL getInstance() {
        return instance;
    }

    private void connect() {
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database + "?user=" + this.username + "&password=" + this.password + "&useSSL=" + this.ssl + "&autoReconnect=true");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    private boolean connected() {
        return (this.connection != null);
    }

    public void close() {

        if (connected()) {
            try {
                this.connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public int getInt(Player p, String stats) {

        isConnected();

        try {
            ResultSet rs = this.connection.prepareStatement("SELECT " + stats + " FROM " + this.table + " WHERE UUID = '" + p.getUniqueId() + "';").executeQuery();
            if (rs.next()) {
                return rs.getInt(stats);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    public void setInt(Player p, int value, String stats) {

        isConnected();

        if (hasAccount(p)) {
            try {
                this.connection.prepareStatement("UPDATE " + this.table + " SET " + stats + " = '" + value + "' WHERE UUID = '" + p.getUniqueId() + "';").executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void create() {

        isConnected();

        try {
            this.connection.prepareStatement("CREATE TABLE IF NOT EXISTS `" + this.table + "` (`UUID` varchar(50) NOT NULL, `WinStreak` INT(11) NOT NULL);").execute();
        } catch (SQLException ex) {

            ex.printStackTrace();
        }
    }

    public boolean hasAccount(Player p) {

        isConnected();

        try {
            ResultSet rs = this.connection.prepareStatement("SELECT UUID FROM " + this.table + " WHERE UUID = '" + p.getUniqueId() + "';").executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }


    public void createPlayer(Player p) {
        if (!connected()) {
            connect();
        }
        try {
            this.connection.prepareStatement("INSERT INTO " + this.table + " (UUID, WinStreak) VALUES ('" + p.getUniqueId() + "', '0');").execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void isConnected() {

        if (!connected()) {
            connect();
        }
    }
}