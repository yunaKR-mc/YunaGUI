package kr.yuna.Party;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PartySystem {
    private final Map<UUID, Set<UUID>> parties = new ConcurrentHashMap<>();
    private final Map<UUID, UUID> invites = new ConcurrentHashMap<>();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public void invitePlayer(UUID inviter, UUID invitee) {
        invites.put(invitee, inviter);
        // Debug message
        System.out.println("Invite sent from " + inviter + " to " + invitee);
    }

    public boolean acceptInvite(UUID invitee) {
        UUID inviter = invites.remove(invitee);
        if (inviter != null) {
            parties.computeIfAbsent(inviter, k -> new HashSet<>()).add(invitee);
            // Ensure the inviter is added to their own party
            parties.get(inviter).add(inviter);
            // Debug message
            System.out.println("Invite accepted by " + invitee + ". Party leader: " + inviter);
            return true;
        }
        return false;
    }

    public boolean kickPlayer(UUID leader, UUID member) {
        Set<UUID> party = parties.get(leader);
        if (party != null && party.remove(member)) {
            if (party.isEmpty()) {
                parties.remove(leader);
            }
            // Debug message
            System.out.println("Player " + member + " kicked from party led by " + leader);
            return true;
        }
        return false;
    }

    public boolean leaveParty(UUID member) {
        for (UUID leader : parties.keySet()) {
            Set<UUID> party = parties.get(leader);
            if (party != null && party.remove(member)) {
                if (party.isEmpty()) {
                    parties.remove(leader);
                }
                // Debug message
                System.out.println("Player " + member + " left the party led by " + leader);
                return true;
            }
        }
        return false;
    }

    public Set<UUID> getPartyMembers(UUID member) {
        for (UUID leader : parties.keySet()) {
            Set<UUID> party = parties.get(leader);
            if (party != null && party.contains(member)) {
                return party;
            }
        }
        return new HashSet<>();
    }

    public String listPartyMembers(UUID member) {
        Set<UUID> members = getPartyMembers(member);
        if (members.isEmpty()) {
            return "참가중인 파티가 없습니다.";
        }

        StringBuilder sb = new StringBuilder("참가 중인 플레이어 : ");
        for (UUID memberUUID : members) {
            Player player = Bukkit.getPlayer(memberUUID);
            if (player != null) {
                sb.append(player.getName()).append(", ");
            } else {
                sb.append("알 수 없는 플레이어 (").append(memberUUID.toString()).append(")\n ");
            }
        }
        return sb.toString();
    }

    public void leaderLeft(Player leader) {
        scheduler.schedule(() -> {
            if (parties.containsKey(leader.getUniqueId())) {
                parties.remove(leader.getUniqueId());
                System.out.println("Party led by " + leader.getName() + " has been disbanded.");
            }
        }, 5, TimeUnit.MINUTES);
    }
}
