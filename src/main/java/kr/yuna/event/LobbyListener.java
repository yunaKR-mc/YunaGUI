package kr.yuna.event;

import kr.yuna.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;

public class LobbyListener implements Listener {

    private Main plugin;

    public LobbyListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        File joinForceFile = new File(plugin.getDataFolder(), "JoinForce.yml");
        if (!joinForceFile.exists()) {
            plugin.getLogger().info("JoinForce.yml 파일이 존재하지 않습니다. 기본 스폰 위치로 이동합니다.");
            return; // 파일이 없으면 기본 스폰으로 이동
        }

        FileConfiguration joinForceConfig = YamlConfiguration.loadConfiguration(joinForceFile);
        double x = joinForceConfig.getDouble("ForceTeleport.X", event.getPlayer().getWorld().getSpawnLocation().getX());
        double y = joinForceConfig.getDouble("ForceTeleport.Y", event.getPlayer().getWorld().getSpawnLocation().getY());
        double z = joinForceConfig.getDouble("ForceTeleport.Z", event.getPlayer().getWorld().getSpawnLocation().getZ());

        Location teleportLocation = new Location(event.getPlayer().getWorld(), x, y, z);
        event.getPlayer().teleport(teleportLocation);

        plugin.getLogger().info("(" + x + "),(" + y + "),(" + z + ")쪽으로 강제 이동 설정이 완료되었습니다.");
    }
}
