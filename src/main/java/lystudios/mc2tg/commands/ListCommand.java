package lystudios.mc2tg.commands;

import lystudios.mc2tg.MC2TG;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ListCommand implements CommandExecutor {
    private final MC2TG plugin;

    public ListCommand(MC2TG plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("list")) { // If the player typed /basic then do the following...
            StringBuilder construct = new StringBuilder();
            construct.append(ChatColor.GREEN + "Online Players: ");
            Player[] var6;
            int var5 = (var6 = Bukkit.getServer().getOnlinePlayers()).length;
            if (var5 > 0) {
                for (int var4 = 0; var4 < var5; ++var4) {
                    Player p = var6[var4];
                    construct.append(p.getName()).append(", ");
                }
                sender.sendMessage(construct.toString());
            }
            else {
                sender.sendMessage(ChatColor.YELLOW + "There are no players online");
            }
            return true;
        }
        else{
            return false;
        }
    }
}
