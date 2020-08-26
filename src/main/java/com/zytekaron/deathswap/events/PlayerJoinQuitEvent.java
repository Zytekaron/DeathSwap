package com.zytekaron.deathswap.events;

import com.zytekaron.deathswap.game.GameManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerJoinQuitEvent implements Listener {
    private final GameManager manager;
    
    public PlayerJoinQuitEvent(GameManager manager) {
        this.manager = manager;
    }
    
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
    
        manager.removePlayer(player);
        
        if (manager.isGameActive() && manager.getPlayers().size() == 1) {
            manager.stopWinner();
        }
    }
}