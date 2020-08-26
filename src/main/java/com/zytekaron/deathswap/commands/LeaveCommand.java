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

public class LeaveCommand extends BaseCommand {
    private final GameManager manager;
    
    public LeaveCommand(GameManager manager) {
        super("leave", "Leave DeathSwap", "/leave", Arrays.asList("l", "exit"));
        this.manager = manager;
    }
    
    @Override
    public void run(@NotNull CommandSender sender, @NotNull String alias, @NotNull List<String> args) {
        Player player = (Player) sender;
    
        if (manager.isGameActive()) {
            player.sendMessage(ChatColor.RED + "You cannot leave an active game");
        } else {
            manager.removePlayer(player);
            player.sendMessage(ChatColor.GREEN + "You have been removed from the game");
        }
    }
    
    @Override
    public int getAllowedTypes() {
        return PLAYER;
    }
}