package kr.yuna.guis.users;

import kr.yuna.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;

public class GameSettingsGUI implements Listener {

    public boolean NVcheck = false;
    public boolean NVon = false;
    private Plugin plugin;


    public GameSettingsGUI(Main plugin) {
        this.plugin = plugin;

    }

    public boolean isNVcheck() {
        return NVcheck;
    }

    public boolean isNVon() {
        return NVon;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getView().getTitle().equals("[게임설정]")) {
            e.setCancelled(true); // 아이템을 가져가지 못하도록 이벤트 취소

            Player player = (Player) e.getWhoClicked();
            ItemStack clickedItem = e.getCurrentItem();
            if (clickedItem != null && clickedItem.hasItemMeta()) {
                ItemMeta NVmeta = clickedItem.getItemMeta();
                if (NVmeta.getItemName().equals("야간투시 켜기/끄기")) {
                    NVcheck = true;
                    NVon = true;
                    if (isNVcheck() == true)
                    player.addPotionEffect(PotionEffectType.NIGHT_VISION.createEffect(9999,255));

                    NVcheck = false;

                    if (isNVcheck() == false)
                        player.removePotionEffect(PotionEffectType.NIGHT_VISION);


                }
            }
        }
    }


    public Inventory createInventory() {
        Inventory GameS = Bukkit.createInventory(null,54, "[게임설정]");


        ItemStack NV = new ItemStack(Material.ENDER_EYE);
        ItemMeta NVmeta = NV.getItemMeta();
        NVmeta.setItemName("야간투시 켜기/끄기");
        NV.setItemMeta(NVmeta);

        GameS.setItem(10,NV);






        return GameS;
    }


}
