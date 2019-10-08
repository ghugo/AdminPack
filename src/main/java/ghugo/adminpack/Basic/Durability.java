package ghugo.adminpack.Basic;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.Damageable;

public class Durability implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) {
            Bukkit.getConsoleSender().sendMessage("Error, this command can only be used by players!");
            return true;
        }

        if (args.length != 1) {
            return false;
        }

        int durabilityValue;
        final Player player = (Player) sender;
        final ItemStack duraItem = player.getInventory().getItemInMainHand();

        if (duraItem.getType().getMaxDurability() == (short) 0) {
            player.sendMessage(ChatColor.DARK_RED + "Invalid Item");
            return true;
        }
        try {
            durabilityValue = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            return false;
        }
        if (durabilityValue < 0 || durabilityValue > 100) {
            player.sendMessage(ChatColor.DARK_RED + "Invalid Durability Percentage (0-100)");
            return true;
        }
        durabilityValue = 100 - durabilityValue;
        final int duraPercent = (int) (duraItem.getType().getMaxDurability() * (durabilityValue / 100.0));
        final Damageable duraMeta = (Damageable) duraItem.getItemMeta();
        duraMeta.setDamage(duraPercent);
        duraItem.setItemMeta((ItemMeta) duraMeta);

        sender.sendMessage(duraItem.getType().toString() + " durability has been set to " + args[0]);
        return true;
    }
}
