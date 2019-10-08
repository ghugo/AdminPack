package ghugo.adminpack.Basic;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Stuck implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) {
            Bukkit.getConsoleSender().sendMessage("Error, this command can only be used by players!");
            return true;
        }

        final Player player = (Player) sender;
        if (player.isInsideVehicle()) {
            player.eject();
        }

        player.teleport(player.getLocation().add(0, 2, 0));
        player.sendMessage(ChatColor.AQUA + "There you go!");
        return true;
    }
}
