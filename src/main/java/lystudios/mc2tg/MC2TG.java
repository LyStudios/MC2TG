package lystudios.mc2tg;

import lystudios.mc2tg.commands.ListCommand;
import lystudios.mc2tg.events.OnMinecraftChatEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public final class MC2TG extends JavaPlugin {

    public static TelegramBotsApi bot;

    public MC2TG() {}

    public void onEnable() {
        try {
            bot.registerBot(new Bot());
        } catch (TelegramApiException var2) {
            var2.printStackTrace();
        }
        this.getCommand("list").setExecutor(new ListCommand(this));
        PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvents(new OnMinecraftChatEvent(), this);
    }

    public void onDisable() {}

    static {
        try {
            bot = new TelegramBotsApi(DefaultBotSession.class);
        } catch (TelegramApiException var1) {
            var1.printStackTrace();
        }
    }

}
