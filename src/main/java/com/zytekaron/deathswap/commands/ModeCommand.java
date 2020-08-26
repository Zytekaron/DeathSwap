package com.zytekaron.deathswap.commands;

import com.zytekaron.deathswap.game.GameMode;
import com.zytekaron.deathswap.game.GameSettings;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import tk.zytekaron.spigot.commando.BaseCommand;

import java.util.ArrayList;
import java.util.List;

import static tk.zytekaron.spigot.commando.SenderType.ALL;

public class ModeCommand extends BaseCommand {
    private final GameSettings settings;
    
    public ModeCommand(GameSettings settings) {
        super("mode", "Set the swap mode", "/mode <rotate|shuffle>", new ArrayList<>());
        this.settings = settings;
        setPermission("deathswap.admin");
    }
    
    @Override
    public void run(@NotNull CommandSender sender, @NotNull String alias, @NotNull List<String> args) {
        if (args.size() == 0) {
            sender.sendMessage(ChatColor.RED + "You must enter a valid option (rotate, shuffle)");
            return;
        }
        
        String option = args.get(0).toLowerCase();
        
        if (option.equals("rotate") || option.equals("r")) {
            settings.setMode(GameMode.ROTATE);
            sender.sendMessage(ChatColor.GREEN + "Successfully changed the swap mode to " + ChatColor.LIGHT_PURPLE + "rotate");
        } else if (option.equals("shuffle") || option.equals("s")) {
            settings.setMode(GameMode.SHUFFLE);
            sender.sendMessage(ChatColor.GREEN + "Successfully changed the swap mode to " + ChatColor.LIGHT_PURPLE + "shuffle");
        } else {
            sender.sendMessage(ChatColor.RED + "You must enter a valid option (rotate, shuffle)");
        }
    }
    
    @Override
    public int getAllowedTypes() {
        return ALL;
    }
}