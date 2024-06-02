package club.flame.disqualified.command.chat;

import club.flame.disqualified.Disqualified;
import club.frozed.lib.commands.BaseCommand;
import club.flame.disqualified.manager.player.PlayerData;
import club.frozed.lib.chat.CC;
import club.flame.disqualified.utils.Utils;
import club.frozed.lib.commands.Command;
import club.frozed.lib.commands.CommandArgs;
import club.frozed.lib.config.ConfigCursor;
import org.bukkit.entity.Player;

public class StaffChatCommand extends BaseCommand {
    @Command(name = "staffChat", permission = "core.staffChat", aliases = {"sc"}, inGameOnly = true)

    @Override
    public void onCommand(CommandArgs cmd) {
        Player player = cmd.getPlayer();
        String[] args = cmd.getArgs();
        ConfigCursor configCursor = new ConfigCursor(Disqualified.getInstance().getSettingsConfig(), "SETTINGS.STAFF-CHAT");
        PlayerData playerData = PlayerData.getPlayerData(player.getUniqueId());
        if (args.length == 0) {
            playerData.setStaffChat(!playerData.isStaffChat());
            String sound = configCursor.getString("SOUND");
            String status = (playerData.isStaffChat() ? "§aEnabled" : "§cDisabled");
            player.sendMessage(CC.translate(configCursor.getString("TOGGLE").replace("<status>", status)));
            Utils.sendPlayerSound(player, sound);
        }
    }
}
