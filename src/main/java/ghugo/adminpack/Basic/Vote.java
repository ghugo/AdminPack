package ghugo.adminpack.Basic;

import ghugo.adminpack.AdminPack;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Vote implements CommandExecutor {

    private final AdminPack plugin;

    private String currentQuestion;
    private final Map<String, Integer> currentVotes;
    private final Set<UUID> voters;

    public Vote(AdminPack plugin) {
        this.voters = new HashSet<>();
        this.currentVotes = new HashMap<>();
        this.currentQuestion = "";
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) {
            Bukkit.getConsoleSender().sendMessage("Error, this command can only be used by players!");
            return true;
        }

        if (args.length < 2) {
            return false;
        }

        final Player player = (Player) sender;

        switch (args[0].toLowerCase()) {
            case "create":
                if (player.isOp()) {
                    createVote(args);
                } else {
                    player.sendMessage(ChatColor.DARK_RED + "Voting can only be started by an admin");
                }
                break;
            case "cast":
                if (currentQuestion.equalsIgnoreCase("")) {
                    player.sendMessage(ChatColor.DARK_RED + "There is currently nothing to vote for");
                    return true;
                }
                return castVote(player, args);
            default:
                return false;
        }

        return true;
    }

    private void createVote(String[] voteData) {
        for (int i = 1; i < voteData.length; i++) {
            currentQuestion += " " + voteData[i];
        }
        currentQuestion = currentQuestion.trim();

        Bukkit.getServer().broadcastMessage(ChatColor.GOLD + " Attention! Voting has started for: "
                + ChatColor.WHITE + currentQuestion);
        Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "You have 30 seconds to vote!");
        this.runBallot();
    }

    private boolean castVote(Player voter, String[] voteData) {
        String choice = "";
        for (int i = 1; i < voteData.length; i++) {
            choice += " " + voteData[i];
        }
        choice = choice.trim();
        choice = choice.toLowerCase();

        if (voters.contains(voter.getUniqueId())) {
            voter.sendMessage(ChatColor.DARK_RED + "You cannot vote more than once!");
            return true;
        }
        int value = 0;
        if (currentVotes.get(choice) != null) {
            value = currentVotes.get(choice);
        }
        value++;
        currentVotes.put(choice, value);
        voters.add(voter.getUniqueId());
        voter.sendMessage(ChatColor.GOLD + "Your vote for (" + choice + ") has been submitted");
        return true;
    }

    private void clearVoting() {
        currentVotes.clear();
        voters.clear();
        currentQuestion = "";
    }

    private void runBallot() {
        new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "10 Seconds Left To Vote!");
            }
        }.runTaskLater(plugin, 400L);
        new BukkitRunnable() {
            @Override
            public void run() {
                if (voters.isEmpty()) {
                    Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "Voting is done! The ballot box is empty! :(");
                    clearVoting();
                    return;
                }

                final List<String> winningChoices = new ArrayList<>();
                int numberOfVotes = 0;
                for (String choice : currentVotes.keySet()) {
                    if (currentVotes.get(choice) > numberOfVotes) {
                        winningChoices.clear();
                        winningChoices.add(choice);
                        numberOfVotes = currentVotes.get(choice);
                    } else if (currentVotes.get(choice) == numberOfVotes) {
                        winningChoices.add(choice);
                    }
                }

                Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "Voting is done! The winner(s) are: ");
                winningChoices.stream().forEach((winningChoice) -> {
                    Bukkit.getServer().broadcastMessage(winningChoice);
                });
                clearVoting();
            }
        }.runTaskLater(plugin, 600L);
    }

}