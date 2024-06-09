package club.flame.disqualified.command.network;

import club.flame.disqualified.Disqualified;
import club.flame.disqualified.manager.player.PlayerData;
import club.flame.disqualified.utils.Utils;
import club.flame.disqualified.utils.lang.Lang;
import club.frozed.lib.chat.CC;
import club.frozed.lib.commands.BaseCommand;
import club.frozed.lib.commands.Command;
import club.frozed.lib.commands.CommandArgs;
import org.bukkit.entity.Player;

/**
 * Re-Work Code by HCFAlerts
 * Project: Disqualified
 * Credits: FCD
 */
public class AnnounceCommand extends BaseCommand {
    @Command(name = "announce", aliases = {"announceserver", "alertserver"}, permission = "core.network.announce")
    public void onCommand(CommandArgs command) {
        Player player = command.getPlayer();
        PlayerData playerData = PlayerData.getPlayerData(player.getUniqueId());

        String rankPrefix = playerData.getHighestRank().getPrefix();
        String rankColor = playerData.getHighestRank().getColor().toString();
        
        String message = Disqualified.getInstance().getSettingsConfig().getString("SETTINGS.SERVER-ANNOUNCE")
                .replace("<name>", player.getName())
                .replace("<rank>", rankColor + rankPrefix)
                .replace("&", "§")
                .replace("<server_name>", Lang.SERVER_NAME);

        Utils.globalBroadcast(player, CC.translate(message));
    }
}
