package club.flame.disqualified.menu.tags.buttons;

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

public class RemoveTagButton extends Button {

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemCreator(Material.RED_ROSE).setName("&cRemove your current Tag").get();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        PlayerData data = PlayerData.getPlayerData(player.getUniqueId());
        if (data.getTag() != null) {
            data.setTag(null);
            playSuccess(player);
        } else {
            player.sendMessage("§cYou don't have a tag");
            playNeutral(player);
        }

        player.closeInventory();
    }
}
