package me.mrdarkness462.winstreak.storage;

import org.bukkit.entity.Player;

public interface Database {
    void close();

    void create();

    int getInt(Player paramPlayer, String paramString);

    void setInt(Player paramPlayer, int paramInt, String paramString);

    boolean hasAccount(Player paramPlayer);

    void createPlayer(Player paramPlayer);
}