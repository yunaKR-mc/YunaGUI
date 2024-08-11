package kr.yuna.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;

public class YunaGUI implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand( CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;
        if (player instanceof Player) {


            }




        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender,  Command command,  String label, String[] args) {
        return List.of();
    }
}
