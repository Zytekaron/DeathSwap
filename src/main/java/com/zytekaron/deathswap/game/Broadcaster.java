package com.zytekaron.deathswap.game;

import org.bukkit.Server;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Broadcaster {
    private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    private final Server server;
    private final GameSettings settings;
    private GameManager manager;
    
    public Broadcaster(Server server, GameSettings settings) {
        this.server = server;
        this.settings = settings;
    }
    
    public void broadcastTeleportCountdown() {
        int countdown = settings.getCountdown();
        for (int i = 0; i < countdown; i++) {
            String message = "Swapping in " + (countdown - i) + " seconds...";
            executor.schedule(() -> activeBroadcast(message), i, TimeUnit.SECONDS);
        }
    }
    
    public void activeBroadcast(String message) {
        if (manager.isGameActive()) {
            broadcast(message);
        }
    }
    
    public void broadcast(String message) {
        server.broadcastMessage(message);
    }
    
    public void setManager(GameManager manager) {
        this.manager = manager;
    }
}