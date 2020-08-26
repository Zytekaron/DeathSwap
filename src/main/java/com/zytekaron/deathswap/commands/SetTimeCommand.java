package com.zytekaron.deathswap.commands;

import com.zytekaron.deathswap.game.GameSettings;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import tk.zytekaron.spigot.commando.BaseCommand;

import java.util.ArrayList;
import java.util.List;

import static tk.zytekaron.spigot.commando.SenderType.ALL;

public class SetTimeCommand extends BaseCommand {
    private final GameSettings settings;
    
    public SetTimeCommand(GameSettings settings) {
        super("settime", "Set the time between swaps", "/settime <seconds>", new ArrayList<>());
        this.settings = settings;
        setPermission("deathswap.admin");
    }
    
    @Override
    public void run(@NotNull CommandSender sender, @NotNull String alias, @NotNull List<String> args) {
        if (args.size() == 0) {
            sender.sendMessage(ChatColor.RED + "You must enter the number of seconds between swaps");
            return;
        }
        
        String option = args.get(0).toLowerCase();
        
        int seconds;
        try {
            seconds = Integer.parseInt(option);
        } catch (NumberFormatException e) {
            sender.sendMessage(ChatColor.RED + "You must enter a valid number between 10 and 3600");
            return;
        }
        
        if (seconds >= 10 && seconds <= 3600) {
            settings.setTeleportMin(seconds);
            settings.setTeleportMax(seconds);
            sender.sendMessage(ChatColor.GREEN + "Successfully changed the teleport time to " + ChatColor.LIGHT_PURPLE + seconds + " seconds");
        } else {
            sender.sendMessage(ChatColor.RED + "You must enter a valid number between 10 and 3600");
        }
    }
    
    @Override
    public int getAllowedTypes() {
        return ALL;
    }
}