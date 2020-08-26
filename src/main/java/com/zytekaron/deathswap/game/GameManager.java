package com.zytekaron.deathswap.game;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class GameManager {
    private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    private final List<Player> players = new ArrayList<>();
    private final Random random = new Random();
    
    private final GameSettings settings;
    private final Broadcaster broadcaster;
    private boolean gameActive = false;
    
    public GameManager(GameSettings settings, Broadcaster broadcaster) {
        this.settings = settings;
        this.broadcaster = broadcaster;
    }
    
    public void addPlayer(Player player) {
        players.add(player);
    }
    
    public void removePlayer(Player player) {
        players.remove(player);
    }
    
    public void startGame() {
        gameActive = true;
        broadcaster.broadcast(ChatColor.GOLD.toString() + ChatColor.BOLD + "DeathSwap has started!");
        gameLoop();
    }
    
    public void stopGameAbrupt() {
        gameActive = false;
        broadcaster.broadcast(ChatColor.GOLD.toString() + ChatColor.BOLD + "DeathSwap has been stopped.");
    }
    
    public void stopWinner() {
        gameActive = false;
        broadcaster.broadcast(ChatColor.GOLD.toString() + ChatColor.BOLD + players.get(0).getName() + ChatColor.GREEN + " won the game!");
    }
    
    private void gameLoop() {
        int min = settings.getTeleportMin();
        int max = settings.getTeleportMax();
        int time = random.nextInt(max - min + 1) + min;
    
        executor.schedule(this::swap, time, TimeUnit.SECONDS);
        
        if (settings.isCountdownEnabled()) {
            executor.schedule(broadcaster::broadcastTeleportCountdown, time - settings.getCountdown(), TimeUnit.SECONDS);
        }
    }
    
    private void swap() {
        if (!gameActive) return;
        broadcaster.broadcast("Swapping...");
        
        List<Location> locations = players.stream()
                .map(Player::getLocation)
                .collect(Collectors.toList());
        
        switch (settings.getMode()) {
            case SHUFFLE:
                Lists.shuffle(locations);
                break;
            case ROTATE:
                Lists.rotate(locations);
                break;
        }
        
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            Location location = locations.get(i);
            player.teleport(location);
        }
        
        gameLoop();
    }
    
    public boolean isGameActive() {
        return gameActive;
    }
    
    public List<Player> getPlayers() {
        return players;
    }
}