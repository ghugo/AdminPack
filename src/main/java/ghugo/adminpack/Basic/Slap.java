package ghugo.adminpack.Basic;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Slap implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        if (args.length < 1) {
            return false;
        }

        final Player targetPlayer = Bukkit.getPlayer(args[0]);
        if (targetPlayer == null) {
            sender.sendMessage(ChatColor.RED + "Player not found");
            return true;
        }
        String slapFlag = "";
        if (args.length > 1) {
            slapFlag = args[1];
        }

        slapPlayer(targetPlayer, sender.getName(), slapFlag);
        return true;
    }

    private void slapPlayer(Player targetPlayer, String slapper, String slapFlag) {
        final Random slapRandom = new Random();
        switch (slapFlag) {
            case "-h":
                targetPlayer.setVelocity(new Vector(
                        slapRandom.nextDouble() * 5.0 - 2.5,
                        slapRandom.nextDouble() * 5,
                        slapRandom.nextDouble() * 5.0 - 2.5));
                announceSlap(targetPlayer, slapper);
                break;
            case "-s":
                targetPlayer.setVelocity(new Vector(
                        slapRandom.nextDouble() * 8.0 - 1,
                        slapRandom.nextDouble() * 2,
                        slapRandom.nextDouble() * 8.0 - 1));
                targetPlayer.sendMessage(ChatColor.YELLOW + "You were "
                        + "slapped by " + slapper);
                break;
            default:
                targetPlayer.setVelocity(new Vector(
                        slapRandom.nextDouble() * 2.0 - 1,
                        slapRandom.nextDouble() * 1,
                        slapRandom.nextDouble() * 2.0 - 1));
                announceSlap(targetPlayer, slapper);
                break;
        }
        targetPlayer.damage(0);
    }

    private void announceSlap(Player targetPlayer, String slapper) {
        Bukkit.getServer().broadcastMessage(
                ChatColor.YELLOW + slapper
                        + " slapped " + targetPlayer.getName());
    }
}