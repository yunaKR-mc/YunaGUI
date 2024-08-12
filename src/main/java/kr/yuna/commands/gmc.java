package kr.yuna.commands;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class gmc implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {

            Player player = (Player) sender;

            if (player.isOp()) {

                player.setGameMode(GameMode.CREATIVE);
                return true;
            } else {
                player.sendMessage(ChatColor.RED + "이 명령어를 사용할 권한이 없습니다");
                return false;
            }
        } else {
            sender.sendMessage("이 명령어는 콘솔에서 사용할 수 없습니다.");
            return false;
        }
    }
}

