package ghugo.adminpack.Basic;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Lore implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) {
            Bukkit.getConsoleSender().sendMessage("Error, this command can only be used by players!");
            return true;
        }

        if (args.length < 1) {
            return false;
        }

        final Player player = (Player) sender;
        final ItemStack loreItem = player.getInventory().getItemInMainHand();
        final ItemMeta loreMeta = loreItem.getItemMeta();
        if (loreMeta == null) {
            player.sendMessage(ChatColor.DARK_RED + "Error, no item in hand.");
            return true;
        }
        final List<String> currentLore = new ArrayList<>();

        if (args[0].equalsIgnoreCase("clear")) {
            loreMeta.setLore(currentLore);
            loreItem.setItemMeta(loreMeta);
            sender.sendMessage(ChatColor.GOLD + player.getInventory().getItemInMainHand().getType().toString() + " lore has been cleared.");
        } else {
            final StringBuilder lore = new StringBuilder();
            for (String string : args) {
                lore.append(" ");
                lore.append(ChatColor.translateAlternateColorCodes('&', string));
            }
            lore.deleteCharAt(0);
            if (loreMeta.getLore() != null) {
                currentLore.addAll(loreMeta.getLore());
            }
            currentLore.add(lore.toString());
            loreMeta.setLore(currentLore);
            loreItem.setItemMeta(loreMeta);
            sender.sendMessage(ChatColor.GOLD + player.getInventory().getItemInMainHand().getType().toString() + " lore has been updated.");
        }
        return true;
    }
}
