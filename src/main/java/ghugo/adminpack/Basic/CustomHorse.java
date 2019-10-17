package ghugo.adminpack.Basic;

import ghugo.adminpack.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Horse.Color;
import org.bukkit.entity.Horse.Style;
import org.bukkit.entity.Horse.Variant;
import org.bukkit.entity.Player;

public class CustomHorse implements CommandExecutor {

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
        final Variant horseVariant;

        try {
            horseVariant = Horse.Variant.valueOf(args[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            player.sendMessage(ChatColor.RED + "Invalid Horse Variant.");
            return true;
        }

        Block block = Utils.getTargetBlock(player);
        if(block == null) {
            return true;
        }
        block = block.getRelative(BlockFace.UP);

        switch (horseVariant) {
            case HORSE:
                if (args.length < 3) {
                    player.sendMessage(ChatColor.RED + "Invalid Horse Color and Style.");
                    return true;
                }
                final Color horseColor;
                final Style horseStyle;
                try {
                    horseColor = Horse.Color.valueOf(args[1].toUpperCase());
                    horseStyle = Horse.Style.valueOf(args[2].toUpperCase());
                } catch (IllegalArgumentException e) {
                    player.sendMessage(ChatColor.RED + "Invalid Horse Color or Style.");
                    return true;
                }
                final Horse horsey = (Horse) player.getWorld().spawnEntity(block.getLocation(), EntityType.HORSE);
                horsey.setColor(horseColor);
                horsey.setStyle(horseStyle);
                if (args.length > 3) {
                    checkFlags(horsey, args[3]);
                }
                break;
            default:
                final Horse otherHorsey = (Horse) player.getWorld().spawnEntity(block.getLocation(), EntityType.HORSE);
                otherHorsey.setVariant(horseVariant);
                if (args.length > 1) {
                    checkFlags(otherHorsey, args[1]);
                }
                break;
        }
        return true;
    }

    private void checkFlags(final Horse horsey, final String flag) {
        switch (flag) {
            case "-t":
                horsey.setTamed(true);
                break;
            case "-b":
                horsey.setBaby();
                break;
            default:
                break;
        }
    }
}