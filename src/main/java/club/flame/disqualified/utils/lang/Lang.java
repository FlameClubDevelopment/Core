package club.flame.disqualified.utils.lang;

import club.flame.disqualified.Disqualified;
import club.frozed.lib.chat.CC;
import lombok.Getter;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

@Getter
public class Lang {

    // Social commands messages
    public static final String TS = getMessage("COMMANDS.SOCIAL.TEAMSPEAK");
    public static final String DISCORD = getMessage("COMMANDS.SOCIAL.DISCORD");
    public static final String TWITTER = getMessage("COMMANDS.SOCIAL.TWITTER");
    public static final String STORE = getMessage("COMMANDS.SOCIAL.STORE");

    // Server settings messages
    public static final String SERVER_IP = getSetting("SETTINGS.NAME-MC-CHECK.SERVER-IP");
    public static final String SERVER_NAME = getSetting("SETTINGS.SERVER-NAME");
    public static final String PREFIX = CC.translate(getSetting("SETTINGS.PREFIX"));

    // Common messages
    public static final String OFFLINE_PLAYER = CC.translate("&cPlayer not found.");
    public static final String NO_PERMS = CC.translate("&cYou don't have permissions.");
    public static final String NO_NUMBER = CC.translate("&cIt must be a number");

    /**
     * Plays a sound to the player.
     *
     * @param player       The player to play the sound to.
     * @param confirmation If true, plays a confirmation sound; otherwise, plays an error sound.
     */
    public static void playSound(Player player, boolean confirmation) {
        Sound sound = confirmation ? Sound.NOTE_PLING : Sound.ITEM_BREAK;
        player.playSound(player.getLocation(), sound, 2F, 2F);
    }

    /**
     * Retrieves a message from the messages configuration.
     *
     * @param path The path to the message in the configuration.
     * @return The message as a String.
     */
    private static String getMessage(String path) {
        return Disqualified.getInstance().getMessagesConfig().getConfiguration().getString(path);
    }

    /**
     * Retrieves a setting from the settings configuration.
     *
     * @param path The path to the setting in the configuration.
     * @return The setting as a String.
     */
    private static String getSetting(String path) {
        return Disqualified.getInstance().getSettingsConfig().getConfiguration().getString(path);
    }
}
