package com.zytekaron.deathswap.commands;

import com.zytekaron.deathswap.game.GameManager;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import tk.zytekaron.spigot.commando.BaseCommand;

import java.util.Arrays;
import java.util.List;

import static tk.zytekaron.spigot.commando.SenderType.PLAYER;

public class JoinCommand extends BaseCommand {
    private final GameManager manager;
    
    public JoinCommand(GameManager manager) {
        super("join", "Join DeathSwap", "/join", Arrays.asList("j", "participate"));
        this.manager = manager;
    }
    
    @Override
    public void run(@NotNull CommandSender sender, @NotNull String alias, @NotNull List<String> args) {
        Player player = (Player) sender;
        
        if (manager.isGameActive()) {
            player.sendMessage(ChatColor.RED + "You cannot join an active game");
        } else {
            manager.addPlayer(player);
            player.sendMessage(ChatColor.GREEN + "You have joined the game");
        }
    }
    
    @Override
    public int getAllowedTypes() {
        return PLAYER;
    }
}