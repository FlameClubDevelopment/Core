package club.flame.disqualified.menu.chat.buttons;

import club.flame.disqualified.manager.player.PlayerData;
import club.flame.disqualified.lib.item.ItemCreator;
import club.flame.disqualified.lib.menu.Button;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;

/**
 * Re-Work Code by HCFAlerts
 * Project: Disqualified
 * Credits: FCD
 */

public class ToggleSoundButton extends Button {

    private PlayerData playerData;

    public ToggleSoundButton(PlayerData playerData) {
        this.playerData = playerData;
    }

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemCreator(Material.JUKEBOX)
                .setName("&4Toggle Sounds")
                .setLore(Collections.singletonList(this.playerData.isToggleSounds() ? "&aenabled" : "&cdisabled"))
                .get();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        playSound(!this.playerData.isToggleSounds(), player);
        this.playerData.setToggleSounds(!this.playerData.isToggleSounds());
    }

    private void playSound(boolean enabled, Player player) {
        if (enabled) {
            playSuccess(player);
        } else {
            playNeutral(player);
        }
    }
}
