package kr.yuna.managers;

import kr.yuna.Main;
import kr.yuna.guis.users.GameSettingsGUI;
import kr.yuna.guis.users.MainGUI;
import kr.yuna.guis.users.Turtorials.BattleGUI;
import kr.yuna.guis.users.Turtorials.DecorationGUI;
import kr.yuna.guis.users.Turtorials.DungeonGUI;
import kr.yuna.guis.users.TutorialGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
public class GUImanager implements CommandExecutor {
    private MainGUI mainGUI;
    private Main main;
    private Plugin plugin;
    private BattleGUI battleGUI;
    private DecorationGUI decorationGUI;
    private DungeonGUI dungeonGUI;
    private final TutorialGUI tutorialGUI;
    private GameSettingsGUI gameSettingsGUI;

    public GUImanager(Main plugin,BattleGUI battleGUI,DungeonGUI dungeonGUI,DecorationGUI decorationGUI,TutorialGUI tutorialGUI,GameSettingsGUI gameSettingsGUI) {
      this.mainGUI = new MainGUI(plugin,tutorialGUI,gameSettingsGUI);
      this.battleGUI = new BattleGUI(plugin,tutorialGUI);
      this.dungeonGUI = new DungeonGUI(plugin,dungeonGUI);
      this.decorationGUI = new DecorationGUI(plugin,decorationGUI);
      this.tutorialGUI = new TutorialGUI(plugin, tutorialGUI, battleGUI, decorationGUI, dungeonGUI,gameSettingsGUI);

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                // MainGUI 인벤토리를 열어줌
                this.mainGUI.openInventory(player);
                return true;
            } else {
                sender.sendMessage("이 명령어는 플레이어만 사용할 수 있습니다.");
                return false;
            }
        }
    }
