package kr.yuna.managers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class SpawnManager {
    private final JavaPlugin plugin;
    private File spawnFile;
    private YamlConfiguration spawnConfig;
    private File ConfigFile;
    private YamlConfiguration config;

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

    // 스폰 위치를 spawn.yml 파일에서 불러오는 메서드
    public Location loadSpawnLocation() {
        double x = spawnConfig.getDouble("WorldX");
        double y = spawnConfig.getDouble("WorldY");
        double z = spawnConfig.getDouble("WorldZ");
        World world = Bukkit.getWorld(spawnConfig.getString("WorldName")); // 월드 이름을 구성에서 가져옵니다.
        if (world == null) {
            world = Bukkit.getServer().getWorlds().get(0); // 첫 번째 월드를 가져옴, 필요에 따라 변경 가능
        }
        return new Location(world, x, y, z);
    }

    // 스폰 위치를 spawn.yml 파일에 저장하는 메서드
    public void saveSpawnLocation(Location location) {
        spawnConfig.set("WorldX", location.getX());
        spawnConfig.set("WorldY", location.getY());
        spawnConfig.set("WorldZ", location.getZ());
        spawnConfig.set("WorldName", location.getWorld().getName());
        saveConfig(spawnConfig,spawnFile);
    }

    // YamlConfiguration을 파일에 저장하는 보조 메서드
    private void saveConfig(YamlConfiguration config, File configFile) {
        try {
            config.save(configFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }
}
