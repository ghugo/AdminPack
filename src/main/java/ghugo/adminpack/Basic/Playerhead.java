package ghugo.adminpack.Basic;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class Playerhead implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) {
            Bukkit.getConsoleSender().sendMessage("Error, this command can only be used by players!");
            return true;
        }
        if (args.length != 1) {
            return false;
        }
        getPlayerHead((Player) sender, args[0]);
        return true;
    }

    private void getPlayerHead(Player player, String playerName) {
        final ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1);
        final SkullMeta meta = (SkullMeta) skull.getItemMeta();
        if (playerName.matches("[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}")) {
            OfflinePlayer op = player.getServer().getOfflinePlayer(playerName);
            meta.setOwningPlayer(op);
        } else {
            meta.setOwner(playerName);
        }
        skull.setItemMeta(meta);
        player.sendMessage(ChatColor.GREEN + "Received " + playerName + "'s head");
        player.getWorld().dropItemNaturally(player.getLocation(), skull);
    }
}
