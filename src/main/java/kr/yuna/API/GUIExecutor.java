package kr.yuna.API;

import kr.yuna.guis.users.FunCollectingGUI;
import kr.yuna.guis.users.GameSettingsGUI;
import kr.yuna.guis.users.MainGUI;
import kr.yuna.guis.users.TutorialGUI;
import org.bukkit.entity.Player;

public interface GUIExecutor {


  public void GUIimporter(MainGUI mainGUI, TutorialGUI tutorialGUI, GameSettingsGUI gameSettingsGUI, FunCollectingGUI funCollectingGUI, Player player);
}
