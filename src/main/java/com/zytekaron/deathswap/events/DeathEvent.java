package com.zytekaron.deathswap.events;

import com.zytekaron.deathswap.game.GameManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathEvent implements Listener {
    private final GameManager manager;
    
    public DeathEvent(GameManager manager) {
        this.manager = manager;
    }
    
    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
    
        manager.removePlayer(player);
    
        if (manager.isGameActive() && manager.getPlayers().size() == 1) {
            manager.stopWinner();
        }
    }
}