package kr.yuna.event;

import kr.yuna.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class HideNameTag implements Listener {
    private Main plugin;

    public HideNameTag(Main plugin) {
        this.plugin = plugin;
    }
    public boolean HideNameTag = true;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        // 플레이어의 이름표를 숨김
        for (Player otherPlayer : Bukkit.getOnlinePlayers()) {

            if (HideNameTag)
                player.hidePlayer(plugin,player);
            otherPlayer.hidePlayer(plugin, player);
            player.hidePlayer(plugin, otherPlayer);
        }
    }
}
