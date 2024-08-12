package kr.yuna.guis.users;

import kr.yuna.Main;
import kr.yuna.guis.users.Turtorials.BattleGUI;
import kr.yuna.guis.users.Turtorials.DecorationGUI;
import kr.yuna.guis.users.Turtorials.DungeonGUI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.entity.Player;
import org.bukkit.Sound;

public class TutorialGUI implements Listener {
    private Player p;
    private Main main;
    private Player player;
    private final DungeonGUI dungeonGUI;
    private final BattleGUI battleGUI;
    private final DecorationGUI decorationGUI;
    private final MainGUI mainGUI;
    private GameSettingsGUI gameSettingsGUI;




    public TutorialGUI(Main plugin, TutorialGUI tutorialGUI, BattleGUI battleGUI, DecorationGUI decorationGUI, DungeonGUI dungeonGUI,GameSettingsGUI gameSettingsGUI) {
        this.p = p;
        this.decorationGUI = new DecorationGUI(plugin,decorationGUI);
        this.battleGUI = new BattleGUI(plugin,tutorialGUI);
        this.dungeonGUI = new DungeonGUI(plugin,dungeonGUI);
        this.mainGUI = new MainGUI(plugin, tutorialGUI,gameSettingsGUI);
        this.gameSettingsGUI = gameSettingsGUI;
        this.gameSettingsGUI = new GameSettingsGUI(plugin,gameSettingsGUI);
    }



    public Inventory createInventory() {




        Inventory Tuinv = Bukkit.createInventory(null, 27, ("[튜토리얼]"));


        // 밝은 회색 유리판 설정
        ItemStack lightGrayGlassPane = new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE);
        ItemMeta paneMeta = lightGrayGlassPane.getItemMeta();
        paneMeta.setItemName(" "); // 공백으로 이름 설정
        paneMeta.setHideTooltip(true);
        lightGrayGlassPane.setItemMeta(paneMeta);


        // 던전 튜토리얼 아이템
        ItemStack TurtorialTel = new ItemStack(Material.CANDLE);
        ItemMeta Itemturtorial = TurtorialTel.getItemMeta();
        Itemturtorial.setItemName("던전 튜토리얼");
        TurtorialTel.setItemMeta(Itemturtorial);




        // 전투 튜토리얼 아이템
        ItemStack BattleT = new ItemStack(Material.RED_CANDLE);
        ItemMeta BattleTmeta = BattleT.getItemMeta();
        BattleTmeta.setItemName("전투 튜토리얼");
        BattleT.setItemMeta(BattleTmeta);

        // 치장품 튜토리얼 아이템
        ItemStack decoration = new ItemStack(Material.WHITE_CANDLE);
        ItemMeta decorationMeta = decoration.getItemMeta();
        decorationMeta.setItemName("치장품 튜토리얼");
        decoration.setItemMeta(decorationMeta);


        ItemStack BackIn = new ItemStack(Material.BARRIER);
        ItemMeta BackMeta = BackIn.getItemMeta();
        BackMeta.setItemName("돌아가기");
        BackIn.setItemMeta(BackMeta);


        // 아이템 위치 설정
        Tuinv.setItem(10,TurtorialTel);
        Tuinv.setItem(13,BattleT);
        Tuinv.setItem(16,decoration);
        Tuinv.setItem(22,BackIn);




        for (int i = 0; i < Tuinv.getSize(); i++) {
            if (i != 10 && i != 13 && i != 16 && i != 22) {
                Tuinv.setItem(i, lightGrayGlassPane);
            }
        }

        return Tuinv;

    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getView().getTitle().equals("[튜토리얼]")) {
            e.setCancelled(true); // 아이템을 가져가지 못하도록 이벤트 취소

            Player player = (Player) e.getWhoClicked();
            ItemStack clickedItem = e.getCurrentItem();
            if (clickedItem != null && clickedItem.hasItemMeta()) {
                ItemMeta Itemturtorial = clickedItem.getItemMeta();
                ItemMeta BattleTmeta = clickedItem.getItemMeta();
                ItemMeta decorationMeta = clickedItem.getItemMeta();
                ItemMeta BackMeta = clickedItem.getItemMeta();
                if (Itemturtorial.getItemName().equals("던전 튜토리얼")) {
                    PlayTeleportSound(player);
                    dungeonGUI.openInventory(player);
                    
                    
                }
                if (BattleTmeta.getItemName().equals("전투 튜토리얼")) {
                    PlayTeleportSound(player);
                    battleGUI.openInventory(player);
                }
                if (decorationMeta.getItemName().equals("치장품 튜토리얼")) {
                    PlayTeleportSound(player);
                   decorationGUI.openInventory(player);
                }
                if (BackMeta.getItemName().equals("돌아가기")) {
                    mainGUI.openInventory(player);

                }
            }
        }
    }



    public void PlayTeleportSound(Player player) {
        Location location = player.getLocation();
        player.playSound(location, Sound.BLOCK_NOTE_BLOCK_BELL, 1.0f, 1.0f);
    }



//    public void DungeonTeleportPlayer(Player player) {
//        double TeleportX = 5.50;
//        double TeleportY = 2.00;
//        double TeleportZ = 15.50;
//        Player p = player;
//        Location location = player.getLocation(new Location(p.getWorld(), TeleportX,TeleportY,TeleportZ));
//        p.teleport(new Location(location.getWorld(), TeleportX,TeleportY,TeleportZ));
//        PlayTeleportSound(player);
//
//    }

//    public void BattleTeleportPlayer(Player player) {
//        double TeleportX = 3.50;
//        double TeleportY = 2.00;
//        double TeleportZ = 15.50;
//        Player p = player;
//        Location location = player.getLocation(new Location(p.getWorld(), TeleportX,TeleportY,TeleportZ));
//        p.teleport(new Location(location.getWorld(), TeleportX,TeleportY,TeleportZ));
//        PlayTeleportSound(player);
//    }

//    public void decorationTeleportPlayer(Player player) {
//        double TeleportX = 1.50;
//        double TeleportY = 2.00;
//        double TeleportZ = 15.50;
//        Player p = player;
//        Location location = player.getLocation(new Location(p.getWorld(), TeleportX,TeleportY,TeleportZ));
//        p.teleport(new Location(location.getWorld(), TeleportX,TeleportY,TeleportZ));
//        PlayTeleportSound(player);
//    }

    public void openInventory(Player player) {
        Inventory inv = createInventory();
        player.openInventory(inv);
    }
}




