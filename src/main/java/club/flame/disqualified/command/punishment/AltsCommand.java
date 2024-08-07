package club.flame.disqualified.command.punishment;

import club.flame.disqualified.Disqualified;
import club.flame.disqualified.manager.player.PlayerData;
import club.flame.disqualified.manager.player.punishments.PunishmentType;
import club.flame.disqualified.lib.chat.CC;
import club.flame.disqualified.lib.commands.BaseCommand;
import club.flame.disqualified.lib.commands.Command;
import club.flame.disqualified.lib.commands.CommandArgs;
import club.flame.disqualified.lib.task.TaskUtil;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

/**
 * Re-Work Code by HCFAlerts
 * Project: Disqualified
 * Credits: FCD
 */

public class AltsCommand extends BaseCommand {

    @Command(name = "alts", permission = "core.punishments.alts", inGameOnly = false)
    @Override
    public void onCommand(CommandArgs cmd) {
        CommandSender sender = cmd.getSender();
        String[] args = cmd.getArgs();

        TaskUtil.runAsync(() -> {
        if (args.length == 0){
            sender.sendMessage(CC.translate("&c/alts <player>"));
            return;
        }
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[0]);
        PlayerData data;
        if (offlinePlayer.isOnline()) {
            data = PlayerData.getPlayerData(offlinePlayer.getUniqueId());
        } else {
            sender.sendMessage(CC.translate("&aLoading player data..."));
            data = PlayerData.loadData(offlinePlayer.getUniqueId());
            if (data == null){
                sender.sendMessage(CC.translate("&cThat player doesn't have data"));
                return;
            }
            data.findAlts();
        }
        List<String> altsList = new ArrayList<>();
        if (data.getAlts().isEmpty()){
            sender.sendMessage(CC.translate("&cError! &7" + data.getName() + " doesn't have alts."));
            return;
        }
        data.getAlts().forEach(alts -> {
            if (alts != null) {
                altsList.add(CC.translate(Disqualified.getInstance().getPunishmentConfig().getConfiguration().getString("ALTS-FORMAT.ALT-FORMAT")
                        .replace("<player>", alts.getName() == null ? "None" : alts.getName())
                        .replace("<status>", getStatusPunishment(alts))
                ));
            }
        });
        List<String> text = new ArrayList<>();
        Disqualified.getInstance().getPunishmentConfig().getConfiguration().getStringList("ALTS-FORMAT.FORMAT").forEach(msg -> {
            text.add(CC.translate(msg)
                    .replace("<player>", data.getName())
                    .replace("<alts>", StringUtils.join(altsList, "\n"))
            );
        });
        sender.sendMessage(CC.translate(StringUtils.join(text, "\n")));
        if (!data.isOnline()) {
            data.removeData();
        }
        });
    }

    private String getStatusPunishment(PlayerData playerData){
        String text;
        if (playerData.getActivePunishment(PunishmentType.BLACKLIST) != null){
            text = playerData.isOnline() ?  CC.translate("&7(&4Blacklist&7) + &7(&aOnline&7)") : CC.translate("&7(&4Blacklist&7) + &7(&cOffline&7)");
        } else if (playerData.getActivePunishment(PunishmentType.BAN) != null){
            text = playerData.isOnline() ?  CC.translate("&7(&cBan&7) + &7(&aOnline&7)") : CC.translate("&7(&cBan&7) + &7(&cOffline&7)");
        } else {
            text = playerData.isOnline() ? CC.translate("&7(&aOnline&7)") : CC.translate("&7(&cOffline&7)");
        }
        return text;
    }
}
