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
    private final Plugin plugin;
    private final GameSettingsGUI gameSettingsGUI;


    public GameSettingsGUI(Main plugin,GameSettingsGUI gameSettingsGUI) {
        this.plugin = plugin;
        this.gameSettingsGUI = gameSettingsGUI;
    }

    public boolean isNVcheck() {
        return NVcheck;
    }

    public boolean isVon() {
        return NVon;
    }

    public GameSettingsGUI(Plugin plugin, GameSettingsGUI gameSettingsGUI, boolean NVon, boolean NVcheck) {
        this.plugin = plugin;
        this.gameSettingsGUI = gameSettingsGUI;
        this.NVon = NVon;
        this.NVcheck = NVcheck;
    }

    public boolean isNVon() {
        return NVon;
    }

    public void setNVcheck(boolean NVcheck) {
        this.NVcheck = NVcheck;
    }

    public void setNVon(boolean NVon) {
        this.NVon = NVon;
    }

    public Plugin getPlugin() {
        return plugin;
    }

    public GameSettingsGUI getGameSettingsGUI() {
        return gameSettingsGUI;
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
    public void openInventory(Player player) {
        Inventory inv = createInventory();
        player.openInventory(inv);
    }

    public void CloseInventory(Player player) {
        player.closeInventory();
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
                    if (!isNVon()) {
                        player.addPotionEffect(PotionEffectType.NIGHT_VISION.createEffect(999999, 255));
                        CloseInventory(player);
                        setNVon(true);
                    } else {
                        player.removePotionEffect(PotionEffectType.NIGHT_VISION);
                        CloseInventory(player);
                        setNVon(false);
                    }
                }
            }
        }
    }
}
