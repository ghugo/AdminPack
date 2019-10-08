package ghugo.adminpack.RPG;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class NPC implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (args.length < 2) {
            return false;
        }
        final StringBuilder npcMessage = new StringBuilder();
        npcMessage.append(ChatColor.WHITE);
        npcMessage.append("<");
        npcMessage.append(ChatColor.translateAlternateColorCodes('&', args[0].replaceAll("_", " ")));
        npcMessage.append(ChatColor.WHITE);
        npcMessage.append("> ");

        StringBuilder npcChat = new StringBuilder();
        int i, argLength = args.length;
        for (i = 1; i < argLength; i++) {
            npcChat.append(" ");
            npcChat.append(ChatColor.translateAlternateColorCodes('&', args[i]));
        }
        npcChat.deleteCharAt(0);
        npcMessage.append(npcChat.toString());
        sender.getServer().broadcastMessage(npcMessage.toString());

        return true;
    }
}