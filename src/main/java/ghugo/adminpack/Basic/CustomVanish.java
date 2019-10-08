package ghugo.adminpack.Basic;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class CustomVanish implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) {
            Bukkit.getConsoleSender().sendMessage("Error, this command can only be used by players!");
            return true;
        }

        final Player player = (Player) sender;

        switch (cmd.getName().toLowerCase()) {
            case "batvanish":
                for (int i = 0; i < 4; i++) {
                    player.getWorld().spawnEntity(player.getEyeLocation(), EntityType.BAT);
                    player.getWorld().spawnEntity(player.getLocation(), EntityType.BAT);
                }
                player.performCommand("sv on");
                break;
            case "smokevanish":
                for (int i = 0; i < 8; i++) {
                    player.getWorld().playEffect(player.getLocation(), Effect.SMOKE, i);
                    player.getWorld().playEffect(player.getEyeLocation(), Effect.SMOKE, i);
                }
                player.performCommand("sv on");
                break;
        }

        return true;
    }
}
