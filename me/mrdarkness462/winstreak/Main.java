/*    */
package me.mrdarkness462.winstreak;
/*    */

import com.andrei1058.bedwars.api.BedWars;
import com.andrei1058.bedwars.commands.bedwars.MainCommand;
import me.mrdarkness462.winstreak.commands.AddonCommands;
import me.mrdarkness462.winstreak.commands.Commands;
import me.mrdarkness462.winstreak.storage.Profile;
import me.mrdarkness462.winstreak.support.placeholders.MVdWPlaceholderAPI;
import me.mrdarkness462.winstreak.support.placeholders.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Main extends JavaPlugin {

    private Profile profile;
    private BedWars bedWars;

    public void onEnable() {
        if (Bukkit.getPluginManager().getPlugin("BedWars1058") != null) {
            bedWars = Bukkit.getServicesManager().getRegistration(BedWars.class).getProvider();
            profile = new Profile(this);
            files();
            Bukkit.getPluginManager().registerEvents(new Events(this), this);
            commands();
            papi();
            mvdwpapi();
        }
    }

    private void files() {
        (new File("plugins/BedWars1058/Addons/WinStreak")).mkdirs();
        this.profile.loadDatabase();
    }

    private void commands() {
        new AddonCommands(MainCommand.getInstance(), "streak");
        getCommand("streak").setExecutor(new Commands());
    }

    private void papi() {
        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            new PlaceholderAPI().register();
        }
    }

    private void mvdwpapi() {
        if (Bukkit.getPluginManager().isPluginEnabled("MVdWPlaceholderAPI"))
            (new MVdWPlaceholderAPI()).hook();
    }

    public Profile getProfile(){
        return profile;
    }

    public BedWars getBedWarsAPI() {
        return bedWars;
    }
}