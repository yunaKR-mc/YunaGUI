package kr.yuna.guis.users;


import kr.yuna.Main;
import kr.yuna.managers.GUImanager;
import kr.yuna.guis.users.Turtorials.BattleGUI;
import kr.yuna.guis.users.Turtorials.DecorationGUI;
import kr.yuna.guis.users.Turtorials.DungeonGUI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;


public class MainGUI implements Listener {
    private EntityType en;
    private TutorialGUI tutorial;
    private Inventory Tuinv;
    private JavaPlugin plugin;

    private MainGUI mainGUI;
    private GUImanager guImanager;
    private GameSettingsGUI gamegui;
    private DungeonGUI dungeonGUI;
    private BattleGUI battleGUI;
    private DecorationGUI decorationGUI;
    private Inventory i;


    public MainGUI(Main plugin,TutorialGUI tutorialGUI,GameSettingsGUI gamegui) {

        this.plugin = plugin;
        this.mainGUI = mainGUI;
        this.tutorial = tutorialGUI;
        this.i = i;
        this.gamegui = gamegui;
    }


    public Inventory createInventory() {
        Inventory Maininv = Bukkit.createInventory(null, 27, "[능력자 서버]");

        // 밝은 회색 유리판 설정
        ItemStack lightGrayGlassPane = new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE);
        ItemMeta paneMeta = lightGrayGlassPane.getItemMeta();
        paneMeta.setDisplayName(" "); // 공백으로 이름 설정
        paneMeta.setHideTooltip(true);
        lightGrayGlassPane.setItemMeta(paneMeta);


        // 능력도감 아이템 설정
        ItemStack FunColl = new ItemStack(Material.RECOVERY_COMPASS);
        ItemMeta FunColl2 = FunColl.getItemMeta();
        FunColl2.setItemName("능력도감");
        FunColl.setItemMeta(FunColl2);

        // 튜토리얼 아이템 설정
        ItemStack Tutorial = new ItemStack(Material.COMPASS);
        ItemMeta TuMeta = Tutorial.getItemMeta();
        TuMeta.setItemName("튜토리얼");
        Tutorial.setItemMeta(TuMeta);

        // 게임 설정 아이템 설정
        ItemStack GameSettings = new ItemStack(Material.CLOCK);
        ItemMeta GameSettings2 = GameSettings.getItemMeta();
        GameSettings2.setItemName("게임 설정");
        GameSettings.setItemMeta(GameSettings2);


        // 돌아가기 설정
        ItemStack BackIn = new ItemStack(Material.BARRIER);
        ItemMeta BackMeta = BackIn.getItemMeta();
        BackMeta.setItemName("돌아가기");
        BackIn.setItemMeta(BackMeta);

        // 인벤토리 슬롯 설정
        Maininv.setItem(10, FunColl);
        Maininv.setItem(13, Tutorial);
        Maininv.setItem(16, GameSettings);
        Maininv.setItem(22,BackIn);

        for (int i = 0; i < Maininv.getSize(); i++) {
            if (i != 10 && i != 13 && i != 16 && i != 22) {
                Maininv.setItem(i, lightGrayGlassPane);
            }
        }

        return Maininv;
    }

    public void openInventory(Player player) {
        Inventory inv = createInventory();
        OpeninventorySound(player);
        player.openInventory(inv);
    }


    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getView().getTitle().equals("[능력자 서버]")) {
            e.setCancelled(true); // 아이템을 가져가지 못하도록 이벤트 취소


            Player player = (Player) e.getWhoClicked();
            ItemStack clickedItem = e.getCurrentItem();
            if (clickedItem != null && clickedItem.hasItemMeta()) {
                ItemMeta TuMeta = clickedItem.getItemMeta();
                ItemMeta BackMeta = clickedItem.getItemMeta();
                ItemMeta GameSettings2 = clickedItem.getItemMeta();
                if (TuMeta.getItemName().equals("튜토리얼")) {
                    tutorial.openInventory(player);
                    PlayTeleportSound(player);
                }
                if (BackMeta.getItemName().equals("돌아가기")) {
                    CloseInventory(player);
                }
                if (GameSettings2.getItemName().equals("게임 설정")) {
                    gamegui.openInventory(player);

                }



            }
        }
    }
    public void PlayTeleportSound(Player player) {
        Location location = player.getLocation();
        player.playSound(location, Sound.BLOCK_NOTE_BLOCK_BELL, 1.0f, 1.0f);
    }


    public  void OpeninventorySound(Player player) {
        Location location = player.getLocation();
        player.playSound(location,Sound.BLOCK_NOTE_BLOCK_HARP,2.0f,1.0f);


    }


    public void CloseInventory(Player player) {
        player.closeInventory();
    }
}
