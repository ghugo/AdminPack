package ghugo.adminpack.Basic;

import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Twixxo implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (args.length != 0) {
            return false;
        }

        final Player targetPlayer = (Player) sender;
        final String message = ChatColor.DARK_PURPLE + " Loves Everyone";
        final String intro = ChatColor.DARK_PURPLE + "* ";
        Bukkit.getServer().broadcastMessage(intro + targetPlayer.getDisplayName()
                + message);
        if (new Random().nextInt(4) == 0) {
            final ItemStack rose = new ItemStack(Material.ROSE_BUSH, 1, (byte) 4);
            final ItemMeta roseName = (ItemMeta) rose.getItemMeta();
            roseName.setDisplayName(ChatColor.LIGHT_PURPLE + "Twixxo Rose");
            roseName.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 1, true);
            rose.setItemMeta(roseName);
            targetPlayer.getWorld().dropItemNaturally(targetPlayer.getLocation(), rose);
        }

        return true;
    }
}