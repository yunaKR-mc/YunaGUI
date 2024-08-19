package kr.yuna;

import kr.yuna.Party.PartyListener;
import kr.yuna.Party.PartySystem;
import kr.yuna.Party.util.command;
import kr.yuna.Party.util.tabcompleter;
import kr.yuna.commands.*;
import kr.yuna.event.LobbyListener;
import kr.yuna.event.NoBlockExplodeListener;
import kr.yuna.event.NoExplodeListener;
import kr.yuna.guis.users.GameSettingsGUI;
import kr.yuna.managers.GUImanager;
import kr.yuna.guis.users.MainGUI;
import kr.yuna.guis.users.Turtorials.BattleGUI;
import kr.yuna.guis.users.Turtorials.DecorationGUI;
import kr.yuna.guis.users.Turtorials.DungeonGUI;
import kr.yuna.guis.users.TutorialGUI;
import kr.yuna.managers.SpawnManager;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;


public final class Main extends JavaPlugin {

    private GUImanager guImanager;
    private Plugin plugin;
    private World w;

    private TutorialGUI tutorialGUI;
    private BattleGUI battleGUI;
    private DecorationGUI decorationGUI;
    private DungeonGUI dungeonGUI;
    private PartySystem partySystem;
    private GameSettingsGUI gameSettingsGUI;
    private Main main;


    public GUImanager getGuImanager() {
        return guImanager;
    }

    public void setGuImanager(GUImanager guImanager) {
        this.guImanager = guImanager;
    }

    @Override
    public void onEnable() {
        JavaPlugin Enable = new JavaPlugin() {
            public void OnEnable() {
                getLogger().info("YP GUI Enabled.");

                // 인스턴스 생성
                SpawnManager spawnManager = new SpawnManager(this);
                day day = new day(this, w);
                MainGUI mainGUI = new MainGUI(main, tutorialGUI, gameSettingsGUI);
                TutorialGUI tutorialgui = new TutorialGUI(main, tutorialGUI, battleGUI, decorationGUI, dungeonGUI, gameSettingsGUI);
                BattleGUI battleGUI1 = new BattleGUI(this, tutorialGUI);
                DecorationGUI decorationGUI1 = new DecorationGUI(this, decorationGUI);
                DungeonGUI dungeonGUI1 = new DungeonGUI(this, dungeonGUI);
                GUImanager guiManager = new GUImanager(main, battleGUI, dungeonGUI, decorationGUI, tutorialGUI, gameSettingsGUI);
                partySystem = new PartySystem();
                JoinForceTel joinForceTel = new JoinForceTel(main);


                // 객체
                tutorialGUI = new TutorialGUI(main, tutorialGUI, battleGUI, decorationGUI, dungeonGUI, gameSettingsGUI); // TutorialGUI 객체 생성
                mainGUI = new MainGUI(main, tutorialGUI, gameSettingsGUI); // MainGUI 객체 생성 시 TutorialGUI 객체 전달
                gameSettingsGUI = new GameSettingsGUI(main, gameSettingsGUI);


                //이벤트 처리

                getServer().getPluginManager().registerEvents(new NoExplodeListener(), (this));
                getServer().getPluginManager().registerEvents(new NoBlockExplodeListener(), (this));
                getServer().getPluginManager().registerEvents(new MainGUI(main, tutorialGUI, gameSettingsGUI), this);
                getServer().getPluginManager().registerEvents(new TutorialGUI(main, tutorialGUI, battleGUI, decorationGUI, dungeonGUI, gameSettingsGUI), this);
                getServer().getPluginManager().registerEvents(new GameSettingsGUI(main, gameSettingsGUI), this);
                getServer().getPluginManager().registerEvents(new BattleGUI(this, tutorialGUI), this);
                getServer().getPluginManager().registerEvents(new PartyListener(partySystem), this);
                getServer().getPluginManager().registerEvents(new LobbyListener(main), this);
                getServer().getPluginManager().registerEvents(new LobbyListener(main), this);

                // 플레이어 숨기기 getServer().getPluginManager().registerEvents(new HideNameTag(this), (this));


                // 명령여 등록

                getCommand("메뉴").setExecutor(guiManager);
                getCommand("gmc").setExecutor(new gmc());
                getCommand("gms").setExecutor(new gms());
                getCommand("gma").setExecutor(new gma());
                getCommand("gmsp").setExecutor(new gmsp());
                getCommand("아침").setExecutor(new day(this, w));
                getCommand("스폰").setExecutor(new spawn(main));
                getCommand("셋스폰").setExecutor(new SetSpawn(main));
                getCommand("파티").setExecutor(new command(partySystem));
                getCommand("파티").setTabCompleter(new tabcompleter());
                getCommand("리롯").setExecutor(new rl(spawnManager));
            }


            @Override
            public void onDisable() {
                JavaPlugin Disable = new JavaPlugin() {
                    public void OnDisable() {
                        getLogger().info("YP GUI Disabled.");

                    }
                };
            }
        };
    }
}
