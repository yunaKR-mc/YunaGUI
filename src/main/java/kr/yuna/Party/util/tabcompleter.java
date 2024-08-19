package kr.yuna.Party.util;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class tabcompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        TabCompleter TabCom = new TabCompleter() {
            public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
                if (args.length == 1) {
                    return Arrays.asList("초대", "추방", "수락", "리스트", "나가기");
                } else if (args.length == 2 && args[0].equalsIgnoreCase("초대")) {
                    List<String> playerNames = new ArrayList<>();
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        playerNames.add(player.getName());
                    }
                    return playerNames;
                }
                return List.of();
            }
        };
        return List.of();
    }
}
