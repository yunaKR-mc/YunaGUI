package kr.yuna.managers;

import kr.yuna.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class SpawnManager {
    private JavaPlugin plugin;
    private File spawnFile;
    private FileConfiguration spawnConfig;

    public SpawnManager(JavaPlugin plugin) {
        this.plugin = plugin;
        spawnFile = new File(plugin.getDataFolder(), "spawn.yml");
        if (!spawnFile.exists()) {
            try {
                spawnFile.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        spawnConfig = YamlConfiguration.loadConfiguration(spawnFile);
    }

   
    public Location loadSpawnLocation() {
        File configFile = new File(plugin.getDataFolder(), "spawn.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);
        double x = config.getDouble("WorldX");
        double y = config.getDouble("WorldY");
        double z = config.getDouble("WorldZ");
        World world = plugin.getServer().getWorlds().get(0);

        return new Location(world, x, y, z);
    }

  
    public void saveSpawnLocation(Location location) {
        File configFile = new File(plugin.getDataFolder(), "spawn.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);
        config.set("WorldX", location.getX());
        config.set("WorldY", location.getY());
        config.set("WorldZ", location.getZ());
        saveConfig(config, configFile);
    }

    public Location getSpawnLocation() {
        double x = spawnConfig.getDouble("x");
        double y = spawnConfig.getDouble("y");
        double z = spawnConfig.getDouble("z");
        return new Location(Bukkit.getWorld("world"), x, y, z); // "world"는 월드 이름으로, 실제 월드 이름으로 변경해야 합니다.
    }

    // YamlConfiguration을 파일에 저장하는 보조 메서드
    private void saveConfig(YamlConfiguration config, File configFile) {
        try {
            config.save(configFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // SpawnManager 클래스 내부
    public Plugin getPlugin() {
        return this.plugin;
    }

}
