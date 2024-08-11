package kr.yuna.Party.util;

import kr.yuna.Party.PartySystem;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Set;
import java.util.UUID;

public class command implements CommandExecutor {
    private final PartySystem partySystem;

    public command(PartySystem partySystem) {
        this.partySystem = partySystem;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        Player player = (Player) sender;
        UUID playerUUID = player.getUniqueId();

        if (args.length < 1) {
            player.sendMessage("Usage: /p <invite|kick|accept|list|leave> [Player]");
            return true;
        }

        String subCommand = args[0];

        switch (subCommand.toLowerCase()) {
            case "초대":
                if (args.length < 2) {
                    player.sendMessage("Usage: /p invite [Player]");
                    return true;
                }
                String inviteeName = args[1];
                Player invitee = Bukkit.getPlayer(inviteeName);
                if (invitee == null) {
                    player.sendMessage(ChatColor.RED + "플레이어를 찾을 수 없습니다");
                    return true;
                }
                if (invitee.getUniqueId().equals(playerUUID)) {
                    player.sendMessage(ChatColor.RED + "자신에게 초대를 보낼수 없습니다!");
                    return true;
                }
                partySystem.invitePlayer(playerUUID, invitee.getUniqueId());

                TextComponent message = new TextComponent(player.getName() + "가 파티 초대를 보냈습니다! ");
                TextComponent clickHere = new TextComponent("여기");
                clickHere.setColor(net.md_5.bungee.api.ChatColor.YELLOW);
                clickHere.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/파티 수락"));

                message.addExtra(clickHere);
                message.addExtra("를 클릭하여 수락하세요");

                invitee.spigot().sendMessage(message);
                player.sendMessage(ChatColor.YELLOW + inviteeName + "에게 초대를 보냈습니다");
                break;
            case "추방":
                if (args.length < 2) {
                    player.sendMessage("Usage: /p kick [Player]");
                    return true;
                }
                String kickName = args[1];
                Player kickPlayer = Bukkit.getPlayer(kickName);
                if (kickPlayer == null) {
                    player.sendMessage(ChatColor.RED + "플레이어를 찾을 수 없습니다");
                    return true;
                }
                if (partySystem.kickPlayer(playerUUID, kickPlayer.getUniqueId())) {
                    player.sendMessage(ChatColor.RED + kickName + " 가 파티에서 추방되었습니다.");
                } else {
                    player.sendMessage(ChatColor.RED + kickName + "는 파티에 참가중이 아닙니다");
                }
                break;
            case "수락":
                if (partySystem.acceptInvite(playerUUID)) {
                    player.sendMessage(ChatColor.YELLOW + "파티에 참가 했습니다");
                    Set<UUID> partyMembers = partySystem.getPartyMembers(playerUUID);
                    if (partyMembers != null && !partyMembers.isEmpty()) {
                        for (UUID memberUUID : partyMembers) {
                            Player member = Bukkit.getPlayer(memberUUID);
                            if (member != null) {
                                member.sendMessage(ChatColor.YELLOW + player.getName() + "가 파티에 참가했습니다!");
                            }
                        }
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "참가할 파티가 없습니다");
                }
                break;
            case "리스트":
                Set<UUID> members = partySystem.getPartyMembers(playerUUID);
                if (members == null || members.isEmpty()) {
                    player.sendMessage(ChatColor.RED + "참가중인 파티가 없습니다");
                } else {
                    StringBuilder memberList = new StringBuilder(ChatColor.YELLOW + "파티 멤버:\n");
                    for (UUID memberUUID : members) {
                        Player member = Bukkit.getPlayer(memberUUID);
                        if (member != null) {
                            memberList.append(ChatColor.GREEN).append(member.getName()).append("\n");
                        }
                    }
                    player.sendMessage(memberList.toString());
                }
                break;
            case "나가기":
                if (partySystem.leaveParty(playerUUID)) {
                    player.sendMessage(ChatColor.YELLOW + "파티에서 나갔습니다");
                } else {
                    player.sendMessage(ChatColor.RED + "파티에 참가중이 아닙니다");
                }
                break;
            default:
                player.sendMessage(ChatColor.RED + "알 수 없는 명령어입니다");
                break;
        }

        return true;
    }
}
