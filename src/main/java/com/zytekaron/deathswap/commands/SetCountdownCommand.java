package com.zytekaron.deathswap.commands;

import com.zytekaron.deathswap.game.GameSettings;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import tk.zytekaron.spigot.commando.BaseCommand;

import java.util.ArrayList;
import java.util.List;

import static tk.zytekaron.spigot.commando.SenderType.ALL;

public class SetCountdownCommand extends BaseCommand {
    private final GameSettings settings;
    
    public SetCountdownCommand(GameSettings settings) {
        super("setcountdown", "Set the countdown timer", "/setcountdown <seconds>", new ArrayList<>());
        this.settings = settings;
        setPermission("deathswap.admin");
    }
    
    @Override
    public void run(@NotNull CommandSender sender, @NotNull String alias, @NotNull List<String> args) {
        if (args.size() == 0) {
            sender.sendMessage(ChatColor.RED + "You must enter the number of seconds for the countdown");
            return;
        }
        
        String option = args.get(0).toLowerCase();
        
        int seconds;
        try {
            seconds = Integer.parseInt(option);
        } catch (NumberFormatException e) {
            sender.sendMessage(ChatColor.RED + "You must enter a valid number between 0 and 32767");
            return;
        }
        
        if (seconds >= 0 && seconds <= 32767) {
            settings.setCountdown(seconds);
            sender.sendMessage(ChatColor.GREEN + "Successfully changed the countdown time to " + ChatColor.LIGHT_PURPLE + seconds + " seconds");
        } else {
            sender.sendMessage(ChatColor.RED + "You must enter a valid number between 0 and 32767");
        }
    }
    
    @Override
    public int getAllowedTypes() {
        return ALL;
    }
}