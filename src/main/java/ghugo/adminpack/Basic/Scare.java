package ghugo.adminpack.Basic;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Scare implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (args.length != 2) {
            return false;
        }
        final Player targetPlayer = Bukkit.getPlayer(args[1]);
        if (targetPlayer == null) {
            sender.sendMessage(ChatColor.RED + "Player not found");
            return true;
        }
        Sound sound = getSound(args[0]);
        targetPlayer.playSound(targetPlayer.getLocation(), sound, 1F, 1F);
        return true;
    }

    private Sound getSound(String arg) {
        switch(arg){
            case "-c":
                return Sound.ENTITY_CREEPER_PRIMED;
            case "-e":
                return Sound.ENTITY_ENDERMAN_STARE;
            case "-g":
                return Sound.ENTITY_GHAST_SCREAM;
            case "-p":
                return Sound.ENTITY_PHANTOM_SWOOP;
            case "-w":
                return Sound.ENTITY_WITCH_CELEBRATE;
            default:
                return Sound.ENTITY_ELDER_GUARDIAN_AMBIENT;
        }
    }
}