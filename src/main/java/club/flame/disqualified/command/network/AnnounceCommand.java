package club.flame.disqualified.command.network;

import club.flame.disqualified.Disqualified;
import club.frozed.lib.chat.CC;
import club.frozed.lib.commands.BaseCommand;
import club.frozed.lib.commands.Command;
import club.frozed.lib.commands.CommandArgs;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AnnounceCommand extends BaseCommand {
    @Command(name = "announceserver", permission = "core.network.announce", aliases = {"announce", "as"}, inGameOnly = false)
    @Override
    public void onCommand(CommandArgs cmd) {
        CommandSender p = cmd.getSender();
        String[] args = cmd.getArgs();

        if (args.length == 0) {
            p.sendMessage("§cUsage: /" + cmd.getLabel() + " <text>");
            return;
        }

        List<String> text = new ArrayList<>();
        Collections.addAll(text, args);
        String message = StringUtils.join(text, " ");

        // Retrieve the broadcast message template from the configuration and check for null
        String template = Disqualified.getInstance().getMessagesConfig().getConfiguration().getString("COMMANDS.ANNOUNCE", "Announcement: <text>");
        
        // Check for null or missing placeholders and replace them appropriately
        if (template == null) {
            template = "Announcement: <text>";
        }
        
        if (message == null) {
            message = "";
        }

        // Replace the placeholder with the actual message
        String broadcastMessage = CC.translate(template.replace("<text>", message));
        Bukkit.broadcastMessage(broadcastMessage);
    }
}
