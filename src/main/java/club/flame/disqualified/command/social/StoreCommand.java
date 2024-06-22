package club.flame.disqualified.command.social;

import club.flame.disqualified.Disqualified;
import club.frozed.lib.chat.CC;
import club.frozed.lib.commands.BaseCommand;
import club.frozed.lib.commands.Command;
import club.frozed.lib.commands.CommandArgs;
import club.flame.disqualified.utils.lang.Lang;
import org.bukkit.entity.Player;

public class StoreCommand extends BaseCommand {

    private static final String COMMAND_NAME = "discord";
    private static final String MESSAGE_KEY = "COMMANDS.SOCIAL.MESSAGES";

    @Command(name = COMMAND_NAME)
    @Override
    public void onCommand(CommandArgs cmd) {
        Player player = cmd.getPlayer();

        if (cmd.getArgs().length == 0) {
            String message = getFormattedMessage();
            if (message != null) {
                player.sendMessage(CC.translate(message));
            } else {
                player.sendMessage(CC.translate("&cMessage not found in configuration."));
            }
        } else {
            player.sendMessage(CC.translate("&cUsage: /" + COMMAND_NAME));
        }
    }

    private String getFormattedMessage() {
        String messageTemplate = Disqualified.getInstance().getMessagesConfig().getConfiguration().getString(MESSAGE_KEY);
        if (messageTemplate == null) {
            return null;
        }
        return messageTemplate.replace("<command>", "Discord").replace("<social>", Lang.DISCORD);
    }
}
