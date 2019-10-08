package ghugo.adminpack.Basic;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class BookEdit implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) {
            Bukkit.getConsoleSender().sendMessage("Error, this command can only be used by players!");
            return true;
        }

        if (args.length == 0) {
            return false;
        }

        final Player player = (Player) sender;
        final ItemStack book = player.getInventory().getItemInMainHand();

        if (!book.getType().equals(Material.WRITTEN_BOOK)) {
            return false;
        }
        final BookMeta bookMeta = (BookMeta) book.getItemMeta();

        switch (args[0]) {
            case "copy":
                if (args.length != 1) {
                    return false;
                }
                final ItemStack copyBook = new ItemStack(Material.WRITTEN_BOOK);
                copyBook.setItemMeta(bookMeta);
                player.getWorld().dropItemNaturally(player.getLocation(), copyBook);
                player.sendMessage(ChatColor.GOLD + "Book has been copied");
                break;
            case "author":
                if (args.length < 2) {
                    return false;
                }
                StringBuilder bookAuthor = new StringBuilder();
                for (int i = 1; i < args.length; i++) {
                    bookAuthor.append(" ");
                    bookAuthor.append(ChatColor.translateAlternateColorCodes('&', args[i]));
                }
                String newBookAuthor = bookAuthor.toString();
                newBookAuthor = newBookAuthor.replaceFirst(" ", "");
                bookMeta.setAuthor(newBookAuthor);
                book.setItemMeta(bookMeta);
                player.sendMessage(ChatColor.GOLD + "Author has been changed to " + newBookAuthor);
                break;
            default:
                return false;
        }
        return true;
    }
}
