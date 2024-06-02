package club.flame.disqualified.command.social;

import club.flame.disqualified.Disqualified;
import club.frozed.lib.chat.CC;
import club.frozed.lib.commands.BaseCommand;
import club.frozed.lib.commands.Command;
import club.frozed.lib.commands.CommandArgs;
import club.flame.disqualified.utils.lang.Lang;
import org.bukkit.entity.Player;

public class StoreCommand extends BaseCommand {
    @Command(name = "store")

    @Override
    public void onCommand(CommandArgs cmd) {
        Player p = cmd.getPlayer();
        if (cmd.getArgs().length == 0) {
            p.sendMessage(CC.translate(Disqualified.getInstance().getMessagesConfig().getConfiguration().getString("COMMANDS.SOCIAL.MESSAGES")
                    .replace("<command>", "Store")
                    .replace("<social>", Lang.STORE))
            );
        }
    }
}
