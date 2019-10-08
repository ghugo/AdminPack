package ghugo.adminpack.Basic;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PiggyBack implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) {
            Bukkit.getConsoleSender().sendMessage("Error, this command can only be used by players!");
            return true;
        }

        if (args.length != 1) {
            return false;
        }
        final Player player = Bukkit.getPlayer(args[0]);
        if (player == null || player.equals((Player) sender)) {
            sender.sendMessage(ChatColor.RED + "Player not found");
            return true;
        }
        player.addPassenger((Player) sender);

        return true;
    }
}