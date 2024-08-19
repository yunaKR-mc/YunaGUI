package kr.yuna.commands;

import kr.yuna.Main;
import kr.yuna.managers.SpawnManager;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class spawn implements CommandExecutor {
    private Plugin plugin;
    private SpawnManager spawnManager;
    private SetSpawn setSpawn;
    private Main main;


    public spawn(Main plugin) {
        this.plugin = plugin;
        this.spawnManager = new SpawnManager(plugin);
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender instanceof Player) {
            Player player = (Player) sender;




                Location spawnLocation = spawnManager.loadSpawnLocation();
                player.teleport(spawnLocation);
                player.sendMessage("스폰으로 이동했습니다.");
                return true;


        } else {
            sender.sendMessage("이 명령어는 플레이어만 사용할 수 있습니다.");
            return false;
        }
    }
    public void SpawnTeleportPlayer(Player player) {
        Player p = player;
        spawnManager.loadSpawnLocation();
        Location location = setSpawn.getSaveSpawnLocation(this);
        p.teleport(new Location(location.getWorld(), spawnManager.loadSpawnLocation().getX(), spawnManager.loadSpawnLocation().getY(), spawnManager.loadSpawnLocation().getZ()));

    }
}