package kr.yuna.commands;

import kr.yuna.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class day implements CommandExecutor {
    private final World world;
    private final JavaPlugin plugin;


    public day(JavaPlugin plugin, World world) {
        this.world = world;
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.isOp()) {

                World world1 = player.getWorld();
                world1.setTime(1000);
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

