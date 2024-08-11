package kr.yuna.commands;

import kr.yuna.managers.SpawnManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;

public class rl implements CommandExecutor {
    private SpawnManager spawnManager;

    public rl(SpawnManager spawnManager) {
        this.spawnManager = spawnManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0 && args[0].equalsIgnoreCase("리롯")) {
            File configFile = new File(spawnManager.getPlugin().getDataFolder(), "spawn.yml");
            FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
            config = YamlConfiguration.loadConfiguration(configFile); // 파일을 다시 불러옴 (리로드)
            sender.sendMessage("spawn.yml 파일이 리로드되었습니다.");
            return true;
        }
        return false;
    }
}
