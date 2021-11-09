/*    */
package me.mrdarkness462.winstreak.utils;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class FileManager {
    private YamlConfiguration yml;
    private final File config;

    public FileManager(String string, String string2) {

        File file = new File(string2);
        if (!file.exists()) {
            file.mkdir();
        }

        this.config = new File(string2 + "/" + string + ".yml");
        if (!this.config.exists()) {
            try {
                this.config.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        this.yml = YamlConfiguration.loadConfiguration(this.config);
    }

    public void reload() {
        this.yml = YamlConfiguration.loadConfiguration(this.config);
    }

    public void save() {
        try {
            this.yml.save(this.config);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void load() {
        try {
            this.yml.load(this.config);
        } catch (IOException | org.bukkit.configuration.InvalidConfigurationException ex) {
            ex.printStackTrace();
        }
    }

    public YamlConfiguration getYml() {
        return this.yml;
    }
}