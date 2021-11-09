package me.mrdarkness462.winstreak.storage;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.sql.*;


public class SQLite implements Database {
    private final static SQLite instance = new SQLite();
    private final String table = "WinStreak";
    private Connection connection;

    private SQLite() {
        connect();
    }

    public static SQLite getInstance() {
        return instance;
    }

    private void connect() {
        if (!connected()) {
            File file = new File("plugins/BedWars1058/Addons/WinStreak/", "winstreak.db");
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

            try {
                if (this.connection != null && !this.connection.isClosed()) {
                    return;
                }
                ClassLoader classLoader = Bukkit.getServer().getClass().getClassLoader();
                Driver driver = (Driver) classLoader.loadClass("org.sqlite.JDBC").newInstance();
                DriverManager.registerDriver(driver);
                this.connection = DriverManager.getConnection("jdbc:sqlite:" + file);
            } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
                ex.printStackTrace();
            }
        }
    }

    public boolean connected() {
        if (this.connection == null) {
            return false;
        }
        try {
            return !this.connection.isClosed();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
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
        if (!connected()) {
            connect();
        }
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
        if (!connected()) {
            connect();
        }
        if (hasAccount(p)) {
            try {
                this.connection.prepareStatement("UPDATE " + this.table + " SET " + stats + " = '" + value + "' WHERE UUID = '" + p.getUniqueId() + "';").executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }


    public void create() {
        if (!connected()) {
            connect();
        }
        try {
            this.connection.prepareStatement("CREATE TABLE IF NOT EXISTS `" + this.table + "` (`UUID` varchar(50) NOT NULL, `WinStreak` INT(11) NOT NULL);").execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public boolean hasAccount(Player p) {
        if (!connected()) {
            connect();
        }
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
}
