package com.zytekaron.deathswap.commands;

import com.zytekaron.deathswap.game.GameManager;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import tk.zytekaron.spigot.commando.BaseCommand;

import java.util.ArrayList;
import java.util.List;

import static tk.zytekaron.spigot.commando.SenderType.ALL;

public class StartCommand extends BaseCommand {
    private final GameManager manager;
    
    public StartCommand(GameManager manager) {
        super("start", "Start the DeathSwap game", "/start", new ArrayList<>());
        this.manager = manager;
    }
    
    @Override
    public void run(@NotNull CommandSender sender, @NotNull String alias, @NotNull List<String> args) {
        if (manager.isGameActive()) {
            sender.sendMessage(ChatColor.RED + "The game is already active!");
            return;
        }
        
        manager.startGame();
        
        sender.sendMessage(ChatColor.GREEN + "The game has been started!");
    }
    
    @Override
    public int getAllowedTypes() {
        return ALL;
    }
}