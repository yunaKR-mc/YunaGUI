package kr.yuna.guis.users.Turtorials;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

public class DecorationGUI implements Listener {
    private final JavaPlugin plugin;
    private DecorationGUI decorationGUI;


    public DecorationGUI(JavaPlugin plugin,DecorationGUI decorationGUI) {
        this.plugin = plugin;
    }



    Inventory createInventory() {


        Inventory decoInv = Bukkit.createInventory(null,27,"치장품 튜토리얼");



        return decoInv;


    }
    public void openInventory(Player player) {
        Inventory inv = createInventory();
        player.openInventory(inv);
    }


}
