package com.zytekaron.deathswap.commands;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import tk.zytekaron.spigot.commando.BaseCommand;

import java.util.ArrayList;
import java.util.List;

import static tk.zytekaron.spigot.commando.SenderType.ALL;

public class HelpCommand extends BaseCommand {
    
    public HelpCommand() {
        super("help", "Get help", "/help", new ArrayList<>());
    }
    
    @Override
    public void run(@NotNull CommandSender sender, @NotNull String alias, @NotNull List<String> args) {
        sender.sendMessage("no help yet");
    }
    
    @Override
    public int getAllowedTypes() {
        return ALL;
    }
}