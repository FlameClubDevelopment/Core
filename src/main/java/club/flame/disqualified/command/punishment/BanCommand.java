package club.flame.disqualified.command.punishment;

import club.flame.disqualified.Disqualified;
import club.flame.disqualified.manager.database.redis.payload.Payload;
import club.flame.disqualified.manager.database.redis.payload.RedisMessage;
import club.flame.disqualified.manager.player.PlayerData;
import club.flame.disqualified.manager.player.punishments.Punishment;
import club.flame.disqualified.manager.player.punishments.PunishmentExecutor;
import club.flame.disqualified.manager.player.punishments.PunishmentType;
import club.frozed.lib.chat.CC;
import club.frozed.lib.commands.BaseCommand;
import club.frozed.lib.commands.Command;
import club.frozed.lib.commands.CommandArgs;
import club.flame.disqualified.utils.punishment.PunishmentUtil;
import club.frozed.lib.task.TaskUtil;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

import java.util.UUID;

/**
 * Re-Work Code by HCFAlerts
 * Project: Disqualified
 * Credits: FCD
 */

public class BanCommand extends BaseCommand {

    @Command(name = "ban", inGameOnly = false, permission = "core.punishments.ban", aliases = {"tempban"})
    @Override
    public void onCommand(CommandArgs cmd) {
        CommandSender commandSender = cmd.getSender();
        String[] args = cmd.getArgs();

        TaskUtil.runAsync(() -> {
            if (args.length == 0) {
                commandSender.sendMessage(CC.translate("&c/ban <player> [duration] [reason] [-s]"));
                return;
            }
            PunishmentExecutor parameter = new PunishmentExecutor(cmd.getArgs(), commandSender);

            OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[0]);
            PlayerData data;
            if (offlinePlayer.isOnline()) {
                data = PlayerData.getPlayerData(offlinePlayer.getUniqueId());
            } else {
                commandSender.sendMessage(CC.translate("&aLoading player data..."));
                data = PlayerData.loadData(offlinePlayer.getUniqueId());
                if (data == null) {
                    commandSender.sendMessage(CC.translate("&cError! &7That player doesn't have data"));
                    return;
                }
            }
            if (!parameter.validate(commandSender, data, PunishmentType.BAN)) return;

            Punishment punishment = new Punishment(UUID.randomUUID(), PunishmentType.BAN, System.currentTimeMillis(), parameter.getReason(), parameter.getDuration());
            parameter.invoke(commandSender, punishment);

            data.getPunishments().add(punishment);
            data.saveData();

            if (Disqualified.getInstance().getRedisManager().isActive()) {
                String json = new RedisMessage(Payload.PUNISHMENTS_ADDED)
                        .setParam("PUNISHMENT", PunishmentUtil.serializePunishment(punishment))
                        .setParam("STAFF", parameter.getStaffName(commandSender))
                        .setParam("TARGET", data.getName())
                        .setParam("TARGET_IP", data.getIp())
                        .setParam("TARGET_UUID", data.getUuid().toString())
                        .setParam("SILENT", String.valueOf(parameter.isSilent()))
                        .toJSON();
                Disqualified.getInstance().getRedisManager().write(json);
            } else {
                parameter.searchAndDestroy(data, punishment);
                punishment.broadcast(parameter.getStaffName(commandSender), data.getName(), parameter.isSilent());
            }
            if (!data.isOnline()) {
                data.removeData();
            }
        });
    }
}