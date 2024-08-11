package kr.yuna.commands;

import kr.yuna.managers.SpawnManager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import kr.yuna.Main;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;


public class SetSpawn implements CommandExecutor {

    private Plugin plugin;
    private SpawnManager spawnManager;

    public SetSpawn(Main plugin) {
        this.plugin = plugin;
    }



    public Location getSaveSpawnLocation(spawn spawnManager) {



        return null;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;


            if (player.isOp()) {


                Location location = player.getLocation();
                saveSpawnLocation(location);
                player.sendMessage("스폰 위치가 설정되었습니다.");
                return true;
            } else {
                player.sendMessage(ChatColor.RED + "이 명령어를 사용할 권한이 없습니다");
                return false;
            }

        } else {
            sender.sendMessage("이 명령어는 콘솔에서 사용할 수 없습니다.");
        }

        return false;
    }

    private void saveSpawnLocation(Location location) {
        File configFile = new File(plugin.getDataFolder(), "spawn.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);
        config.set("WorldX", location.getX());
        config.set("WorldY", location.getY());
        config.set("WorldZ", location.getZ());
        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
