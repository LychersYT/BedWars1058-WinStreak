/*    */
package me.mrdarkness462.winstreak.commands;

import me.mrdarkness462.winstreak.files.Messages;
import me.mrdarkness462.winstreak.streak.Streak;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {
    private final Messages messages = new Messages();
    private final Streak streak = new Streak();

    public boolean onCommand(CommandSender snd, Command cmd, String lbl, String[] arg) {
        if (snd instanceof Player) {
            Player p = (Player) snd;
            p.sendMessage(this.messages.getString("commands.player-streak")
                    .replace("%streaks%", String.valueOf(this.streak.get(p))));
        } else {
            snd.sendMessage(this.messages.getString("addon.only-player"));
        }
        return true;
    }
}