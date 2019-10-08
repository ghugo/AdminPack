package ghugo.adminpack.Basic;

import java.util.Random;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Roll implements CommandExecutor  {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        final int rollNumber = (new Random().nextInt(100) + 1);
        sender.getServer().broadcastMessage(sender.getName() + " rolled " + String.valueOf(rollNumber));

        return true;
    }
}