package com.zytekaron.deathswap;

import com.zytekaron.deathswap.commands.HelpCommand;
import com.zytekaron.deathswap.commands.JoinCommand;
import com.zytekaron.deathswap.commands.LeaveCommand;
import com.zytekaron.deathswap.commands.SetMinCommand;
import com.zytekaron.deathswap.game.Broadcaster;
import com.zytekaron.deathswap.game.GameManager;
import com.zytekaron.deathswap.game.GameSettings;
import org.bukkit.plugin.java.JavaPlugin;
import tk.zytekaron.spigot.commando.SpigotCommando;

public final class Main extends JavaPlugin {
    
    // possible behavior
    // removing players by death whilst actively shuffling causes exceptions
    
    @Override
    public void onEnable() {
        GameSettings settings = new GameSettings();
        
        Broadcaster broadcaster = new Broadcaster(getServer(), settings);

        GameManager manager = new GameManager(settings, broadcaster);
        broadcaster.setManager(manager);
    
        // Don't worry, it's compatible with Bukkit
        SpigotCommando commando = new SpigotCommando(this);
        commando.registerCommands(
                new HelpCommand(),
                new JoinCommand(manager),
                new LeaveCommand(manager),
                new SetMinCommand(settings)
        );
    
        getLogger().info("Successfully enabled plugin");
    }
    
    @Override
    public void onDisable() {
        getLogger().info("Successfully disabled plugin");
    }
}