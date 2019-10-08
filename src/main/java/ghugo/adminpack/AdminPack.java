package ghugo.adminpack;

import ghugo.adminpack.Basic.*;
import ghugo.adminpack.RPG.NPC;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class AdminPack extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();

        getCommand("batvanish").setExecutor(new CustomVanish());
        getCommand("bookedit").setExecutor(new BookEdit());
        getCommand("dura").setExecutor(new Durability());
        getCommand("imstuck").setExecutor(new Stuck());
        getCommand("lore").setExecutor(new Lore());
        getCommand("music").setExecutor(new Music());
        getCommand("npc").setExecutor(new NPC());
        getCommand("piggyback").setExecutor(new PiggyBack());
        getCommand("playerhead").setExecutor(new Playerhead());
        getCommand("rename").setExecutor(new Rename());
        getCommand("rocket").setExecutor(new Rocket(this));
        getCommand("roll").setExecutor(new Roll());
        getCommand("scare").setExecutor(new Scare());
        getCommand("slap").setExecutor(new Slap());
        getCommand("smokevanish").setExecutor(new CustomVanish());
        getCommand("twixxo").setExecutor(new Twixxo());
        getCommand("vote").setExecutor(new Vote(this));
    }

    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(this);
        saveConfig();
    }
}
