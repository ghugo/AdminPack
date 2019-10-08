package ghugo.adminpack.Basic;

import ghugo.adminpack.AdminPack;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class Rocket implements CommandExecutor {

    private final AdminPack plugin;

    public Rocket(AdminPack plugin) {
        this.plugin = plugin;
    }

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

        String rocketFlag = "";
        if (args.length > 1) {
            rocketFlag = args[1];
        }

        Bukkit.getServer().broadcastMessage(ChatColor.YELLOW + sender.getName()
                + " rocketed " + targetPlayer.getName());

        launchPlayer(targetPlayer, rocketFlag);
        return true;
    }

    private void launchPlayer(Player targetPlayer, String rocketFlag) {
        switch (rocketFlag) {
            case "-h":
                targetPlayer.setVelocity(new Vector(0, 10, 0));
                break;
            case "-v":
                final Player astronaut = targetPlayer;
                astronaut.setAllowFlight(true);
                final double maxY = 1000.0;
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (!astronaut.isOnline() || astronaut.getLocation().getY() > maxY) {
                            astronaut.setAllowFlight(false);
                            this.cancel();
                            return;
                        }
                        astronaut.setVelocity(new Vector(0, 8.0, 0));
                    }
                }.runTaskTimer(plugin, 0L, 10L);
                break;
            default:
                targetPlayer.setVelocity(new Vector(0, 2, 0));
                break;
        }
    }
}