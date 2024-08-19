package kr.yuna.guis.users.Turtorials;

import kr.yuna.guis.users.TutorialGUI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.inventory.ItemFlag;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

public class BattleGUI implements Listener {

    private final JavaPlugin plugin;
    private final TutorialGUI tutorialGUI;

    public BattleGUI(JavaPlugin plugin, TutorialGUI tutorialGUI) {
        this.plugin = plugin;
        this.tutorialGUI = tutorialGUI;
    }

            Inventory createInventory() {
                Inventory BattleInv = Bukkit.createInventory(null, 27, "전투 튜토리얼");

                // 밝은 회색 유리판 설정
                ItemStack lightGrayGlassPane = new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE);
                ItemMeta paneMeta = lightGrayGlassPane.getItemMeta();
                paneMeta.setItemName(" "); // 공백으로 이름 설정
                paneMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                lightGrayGlassPane.setItemMeta(paneMeta);

                // 스킬셋 아이템
                ItemStack SkillSet = new ItemStack(Material.IRON_SWORD);
                ItemMeta SkillMeta = SkillSet.getItemMeta();
                SkillMeta.setItemName("훈련장");
                SkillSet.setItemMeta(SkillMeta);
                BattleInv.setItem(10, SkillSet);

                // 마지막 뭐였지?
                ItemStack StartC = new ItemStack(Material.BOW);
                ItemMeta StartCmeta = StartC.getItemMeta();
                StartCmeta.setItemName("게임모드 훈련장");
                StartC.setItemMeta(StartCmeta);
                BattleInv.setItem(16, StartC);


                ItemStack BackIn = new ItemStack(Material.BARRIER);
                ItemMeta BackMeta = BackIn.getItemMeta();
                BackMeta.setItemName("돌아가기");
                BackIn.setItemMeta(BackMeta);
                BattleInv.setItem(22, BackIn);

                for (int i = 0; i < BattleInv.getSize(); i++) {
                    if (i != 10 && i != 16 && i != 22) {
                        BattleInv.setItem(i, lightGrayGlassPane);
                    }
                }

                return BattleInv;
            }


            public void openInventory(Player player) {
                Inventory inv = createInventory();
                player.openInventory(inv);
            }


            @EventHandler
            public void onInventoryClick(InventoryClickEvent e) {
                Listener InvClick = new Listener() {

                    @EventHandler
                    public void onInventoryClick(InventoryClickEvent e) {
                        if (e.getView().getTitle().equals("전투 튜토리얼")) {
                            e.setCancelled(true); // 아이템을 가져가지 못하도록 이벤트 취소
                            Player player = (Player) e.getWhoClicked();
                            ItemStack clickedItem = e.getCurrentItem();
                            if (clickedItem != null && clickedItem.hasItemMeta()) {
                                ItemMeta SkillMeta = clickedItem.getItemMeta();
                                ItemMeta StartCmeta = clickedItem.getItemMeta();
                                ItemMeta BackMeta = clickedItem.getItemMeta();
                                if (SkillMeta.getItemName().equals("훈련장")) {
                                    player.sendMessage("null 방지용(미완성)");
                                    CloseInventory(player);
                                }
                                if (StartCmeta.getItemName().equals("게임모드 훈련장")) {
                                    player.sendMessage("null 방지용2(미완성)");
                                    CloseInventory(player);
                                }
                                if (BackMeta.getItemName().equals("돌아가기")) {
                                    tutorialGUI.openInventory(player);
                                }
                            }
                        }
                    }


                    public void CloseInventory(Player player) {
                        player.closeInventory();
                    }
                };
            }
        }



