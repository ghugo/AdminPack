package ghugo.adminpack.Basic;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Rename implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (args.length < 1) {
            return false;
        }
        if (!(sender instanceof Player)) {
            Bukkit.getConsoleSender().sendMessage("Error, this command can only be used by players!");
            return true;
        }

        final String itemName = getItemName(args);
        renameItemInHand((Player) sender, itemName);
        return true;
    }

    private String getItemName(String[] args) {
        final StringBuilder renameItemName = new StringBuilder();
        for (String string : args) {
            renameItemName.append(" ");
            renameItemName.append(ChatColor.translateAlternateColorCodes('&', string));
        }
        return renameItemName.toString().replaceFirst(" ", "");
    }

    private void renameItemInHand(Player player, String itemName) {
        final ItemStack renameItem = player.getInventory().getItemInMainHand();
        final ItemMeta renameMeta = renameItem.getItemMeta();
        if (renameMeta == null) {
            player.sendMessage(ChatColor.DARK_RED + "Error, no item in hand.");
            return;
        }
        renameMeta.setDisplayName(itemName);
        renameItem.setItemMeta(renameMeta);
        player.sendMessage(ChatColor.GOLD + renameItem.getType().toString() + " has been renamed!");
    }
}