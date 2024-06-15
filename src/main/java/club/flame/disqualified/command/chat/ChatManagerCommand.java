package club.flame.disqualified.command.chat;

import club.flame.disqualified.Disqualified;
import club.flame.disqualified.manager.chat.ChatManager;
import club.flame.disqualified.utils.Utils;
import club.frozed.lib.chat.CC;
import club.frozed.lib.commands.BaseCommand;
import club.frozed.lib.commands.Command;
import club.frozed.lib.commands.CommandArgs;
import club.frozed.lib.commands.Completer;
import club.frozed.lib.config.ConfigCursor;
import club.frozed.lib.number.NumberUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ChatManagerCommand extends BaseCommand {

    @Command(name = "chat.slow", permission = "chat.slow")
    public void onChatSlow(CommandArgs args) {
        Player player = args.getPlayer();
        String[] arguments = args.getArgs();

        if (arguments.length < 1) {
            player.sendMessage(CC.RED + "Usage: /chat slow <seconds>");
            return;
        }

        if (!NumberUtils.isInteger(arguments[0])) {
            player.sendMessage(CC.RED + "The delay must be a number.");
            return;
        }

        int delay = Integer.parseInt(arguments[0]);
        ChatManager chatManager = Disqualified.getInstance().getChatManager();
        chatManager.setChatDelay(delay);

        Bukkit.broadcastMessage(CC.translate("&eChat has been slowed by &c" + delay + " &eseconds."));
    }

    @Completer(name = "chat.slow")
    public List<String> onChatSlowComplete(CommandArgs args) {
        List<String> completions = new ArrayList<>();
        completions.add("5");
        completions.add("10");
        completions.add("15");
        completions.add("30");
        completions.add("60");
        return completions;
    }
}
