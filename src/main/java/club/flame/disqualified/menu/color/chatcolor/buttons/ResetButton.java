package club.flame.disqualified.menu.color.chatcolor.buttons;

import club.flame.disqualified.manager.player.PlayerData;
import club.frozed.lib.item.ItemCreator;
import club.frozed.lib.menu.Button;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

/**
 * Re-Work Code by HCFAlerts
 * Project: Disqualified
 * Credits: FCD
 */
public class ResetButton extends Button {

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemCreator(Material.INK_SACK).setDurability(8).setName("&cReset Chat Color").get();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        PlayerData data = PlayerData.getPlayerData(player.getUniqueId());
        if (data.getChatColor() != null) {
            data.setChatColor(null);
            playSuccess(player);
        } else {
            player.sendMessage("§cYou don't have any chat color");
            playNeutral(player);
            player.closeInventory();
        }
    }
}
