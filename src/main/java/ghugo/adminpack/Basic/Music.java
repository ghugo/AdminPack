package ghugo.adminpack.Basic;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Music implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (args.length != 2) {
            return false;
        }

        final Player targetPlayer = Bukkit.getPlayer(args[0]);
        if (targetPlayer == null) {
            sender.sendMessage(ChatColor.DARK_RED + "Player not found.");
            return true;
        }

        int number;
        switch (args[1]) {
            case "13":
                number = 2256;
                break;
            case "cat":
                number = 2257;
                break;
            case "blocks":
                number = 2258;
                break;
            case "chirp":
                number = 2259;
                break;
            case "far":
                number = 2260;
                break;
            case "mall":
                number = 2261;
                break;
            case "mellohi":
                number = 2262;
                break;
            case "stal":
                number = 2263;
                break;
            case "strad":
                number = 2264;
                break;
            case "ward":
                number = 2265;
                break;
            case "11":
                number = 2266;
                break;
            case "wait":
                number = 2267;
                break;
            case "stop":
                number = 0;
                break;
            default:
                sender.sendMessage(ChatColor.DARK_RED + "Invalid music disc name.");
                return true;
        }
        targetPlayer.getWorld().playEffect(targetPlayer.getLocation(), Effect.RECORD_PLAY, number);

        return true;
    }
}