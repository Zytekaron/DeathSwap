package com.zytekaron.deathswap.commands;

import com.zytekaron.deathswap.game.GameSettings;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import tk.zytekaron.spigot.commando.BaseCommand;

import java.util.ArrayList;
import java.util.List;

import static tk.zytekaron.spigot.commando.SenderType.ALL;

public class SetNotifyCommand extends BaseCommand {
    private final GameSettings settings;
    
    public SetNotifyCommand(GameSettings settings) {
        super("setnotify", "Enable or disable notifications", "/setnotify <on|off>", new ArrayList<>());
        this.settings = settings;
        setPermission("deathswap.admin");
    }
    
    @Override
    public void run(@NotNull CommandSender sender, @NotNull String alias, @NotNull List<String> args) {
        if (args.size() == 0) {
            sender.sendMessage(ChatColor.RED + "You must enter a valid option (on, off)");
            return;
        }
        
        String option = args.get(0).toLowerCase();
        
        if (option.equals("on") || option.equals("true") || option.equals("enable")) {
            settings.setCountdownEnabled(true);
            sender.sendMessage(ChatColor.GREEN + "Successfully " + ChatColor.LIGHT_PURPLE + "enabled" + ChatColor.GREEN + " the swap mode");
        } else if (option.equals("off") || option.equals("false") || option.equals("disable")) {
            settings.setCountdownEnabled(false);
            sender.sendMessage(ChatColor.GREEN + "Successfully " + ChatColor.LIGHT_PURPLE + "disabled" + ChatColor.GREEN + " the swap mode");
        } else {
            sender.sendMessage(ChatColor.RED + "You must enter a valid option (on, off)");
        }
    }
    
    @Override
    public int getAllowedTypes() {
        return ALL;
    }
}