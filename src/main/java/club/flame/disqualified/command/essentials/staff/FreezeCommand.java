package club.flame.disqualified.command.essentials.staff;

import club.flame.disqualified.manager.event.freeze.PlayerFreezeEvent;
import club.flame.disqualified.manager.event.freeze.PlayerUnFreezeEvent;
import club.flame.disqualified.manager.player.PlayerData;
import club.frozed.lib.chat.CC;
import club.frozed.lib.commands.BaseCommand;
import club.frozed.lib.commands.Command;
import club.frozed.lib.commands.CommandArgs;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Re-Work Code by HCFAlerts
 * Project: Disqualified
 * Credits: FCD
 */

public class FreezeCommand extends BaseCommand {

    @Command(name = "freeze", aliases = {"ss", "congelar"}, permission = "core.essentials.report")

    @Override
    public void onCommand(CommandArgs commandArgs) {
        Player player = commandArgs.getPlayer();
        String[] args = commandArgs.getArgs();
        if (args.length == 0) {
            player.sendMessage(CC.translate("&cUsage /" + commandArgs.getLabel() + " <player>"));
            return;
        }
        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            player.sendMessage(CC.translate("&cError&7! &c" + args[0] + " isn't online."));
            return;
        }
        if (target == player) {
            player.sendMessage(CC.translate("&cError&7! &cYou can't freeze your self."));
            return;
        }
        PlayerData targetData = PlayerData.getPlayerData(target.getUniqueId());
        if (targetData.isFreeze()) {
            new PlayerUnFreezeEvent(target, player).call();
        } else {
            new PlayerFreezeEvent(target, player).call();
        }
    }
}
