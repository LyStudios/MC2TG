package lystudios.mc2tg.events;

import lystudios.mc2tg.Bot;
import lystudios.mc2tg.Config;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class OnMinecraftChatEvent extends PlayerListener {

    Config configuration = new Config();

    public OnMinecraftChatEvent() {}

    @EventHandler
    public void onMessage(PlayerChatEvent event) {
        SendMessage sendMessage = new SendMessage();

        sendMessage.setChatId(configuration.getConfig("chat-id"));
        sendMessage.setText("<" + event.getPlayer().getName() + "> " + event.getMessage());
        try {
            (new Bot()).execute(sendMessage);
        }
        catch (TelegramApiException var4) {
            var4.printStackTrace();
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(configuration.getConfig("chat-id"));
        sendMessage.setText(event.getJoinMessage().substring(2));
        try {
            (new Bot()).execute(sendMessage);
        }
        catch (TelegramApiException var4) {
            var4.printStackTrace();
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(configuration.getConfig("chat-id"));
        sendMessage.setText(event.getQuitMessage().substring(2));
        try {
            (new Bot()).execute(sendMessage);
        }
        catch (TelegramApiException var4) {
            var4.printStackTrace();
        }
    }

}
