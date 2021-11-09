package me.mrdarkness462.winstreak.commands;


import com.andrei1058.bedwars.api.command.ParentCommand;
import com.andrei1058.bedwars.api.command.SubCommand;
import com.andrei1058.bedwars.arena.Misc;
import com.andrei1058.bedwars.commands.bedwars.MainCommand;
import me.mrdarkness462.winstreak.files.Messages;
import me.mrdarkness462.winstreak.streak.Streak;
import net.md_5.bungee.api.chat.ClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class AddonCommands extends SubCommand {
    private final Messages messages = new Messages();
    private final Streak streak = new Streak();

    public AddonCommands(ParentCommand parentCommand, String name) {
        super(parentCommand, name);
        setPermission("bw.winstreak");
        showInList(true);
        setPriority(20);
        setDisplayInfo(Misc.msgHoverClick("§6 ▪ §7/" + MainCommand.getInstance().getName() + " " + getSubCommandName() + "         §8 - §e view streak cmds", "§fView Streak commands.", "/" + getParent().getName() + " " + getSubCommandName(), ClickEvent.Action.SUGGEST_COMMAND));

    }

    public boolean execute(String[] arg, CommandSender snd) {
        if (arg.length == 0) {
            for (String str : this.messages.getStringList("commands.available"))
                snd.sendMessage(str);

        } else {

            Player target;
            switch (arg[0].toLowerCase()) {
                case "add":
                    if (arg.length != 3) {
                        snd.sendMessage(this.messages.getString("commands.add-streak.usage"));
                        break;

                    }
                    target = Bukkit.getPlayer(arg[1]);
                    if (target != null) {
                        if (Misc.isNumber(arg[2])) {
                            this.streak.add(target, Integer.parseInt(arg[2]));
                            snd.sendMessage(this.messages.getString("commands.add-streak.success").replace("%streaks%", String.valueOf(Integer.valueOf(arg[2])))
                                    .replace("%target%", arg[1])
                                    .replace("%pstreaks%", String.valueOf(this.streak.get(target))));
                            break;

                        }
                        snd.sendMessage(this.messages.getString("addon.not-number").replace("%value%", arg[2]));
                        break;
                    }

                    snd.sendMessage(this.messages.getString("addon.unknown-player").replace("%target%", arg[1]));
                    break;

                case "remove":
                    if (arg.length != 3) {
                        snd.sendMessage(this.messages.getString("commands.remove-streak.usage"));
                        break;
                    }
                    target = Bukkit.getPlayer(arg[1]);
                    if (target != null) {
                        if (Misc.isNumber(arg[2])) {
                            if (this.streak.hasEnough(target, Integer.parseInt(arg[2]))) {
                                this.streak.remove(target, Integer.parseInt(arg[2]));
                                snd.sendMessage(this.messages.getString("commands.remove-streak.success")
                                        .replace("%streaks%", String.valueOf(Integer.parseInt(arg[2])))
                                        .replace("%target%", arg[1])
                                        .replace("%pstreaks%", String.valueOf(this.streak.get(target))));
                                break;
                            }
                            snd.sendMessage(this.messages.getString("addon.not-enough").replace("%target%", target.getName()));
                            break;
                        }
                        snd.sendMessage(this.messages.getString("addon.not-number").replace("%value%", arg[2]));
                        break;
                    }
                    snd.sendMessage(this.messages.getString("addon.unknown-player").replace("%target%", arg[1]));
                    break;

                case "set":
                    if (arg.length != 3) {
                        snd.sendMessage(this.messages.getString("commands.set-streak.usage"));
                        break;

                    }
                    target = Bukkit.getPlayer(arg[1]);
                    if (target != null) {
                        if (Misc.isNumber(arg[2])) {
                            this.streak.set(target, Integer.parseInt(arg[2]));
                            snd.sendMessage(this.messages.getString("commands.set-streak.success")
                                    .replace("%target%", arg[1])
                                    .replace("%streaks%", String.valueOf(this.streak.get(target))));
                            break;

                        }
                        snd.sendMessage(this.messages.getString("addon.not-number").replace("%value%", arg[2]));
                        break;
                    }
                    snd.sendMessage(this.messages.getString("addon.unknown-player").replace("%target%", arg[1]));

                    break;

                case "reset":
                    if (arg.length != 2) {
                        snd.sendMessage(this.messages.getString("commands.reset-streak.usage"));
                        break;

                    }
                    target = Bukkit.getPlayer(arg[1]);
                    if (target != null) {
                        this.streak.reset(target);
                        snd.sendMessage(this.messages.getString("commands.reset-streak.success")
                                .replace("%target%", arg[1]));
                        break;

                    }
                    snd.sendMessage(this.messages.getString("addon.unknown-player").replace("%target%", arg[1]));

                    break;

                case "reload":
                    this.messages.reload();
                    snd.sendMessage(this.messages.getString("addon.reloaded"));

                    break;

            }
        }
        return true;

    }

    public List<String> getTabComplete() {
        return null;
    }
}