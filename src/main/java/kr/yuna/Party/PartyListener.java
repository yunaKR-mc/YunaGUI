package kr.yuna.Party;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class PartyListener implements Listener {
    private final PartySystem partySystem;

    public PartyListener(PartySystem partySystem) {
        this.partySystem = partySystem;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player playerUUID = event.getPlayer().getPlayer();
        partySystem.leaderLeft(playerUUID);
    }
}
