package lystudios.mc2tg;

import com.ibm.icu.text.Transliterator;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {

    Config configuration = new Config();

    public Bot() {}

    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            if(update.getMessage().getText().equals("/playerlist")) {
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId("-1001828881604");
                StringBuilder construct = new StringBuilder();
                construct.append("Online Players: ");
                Player[] var6;
                int var5 = (var6 = Bukkit.getServer().getOnlinePlayers()).length;
                if (var5 > 0) {
                    for (int var4 = 0; var4 < var5; ++var4) {
                        Player p = var6[var4];
                        construct.append(p.getName()).append(", ");
                    }
                    sendMessage.setText(construct.toString());
                }
                else {
                    sendMessage.setText("There are no players online");
                }
                try {
                    (new Bot()).execute(sendMessage);
                }
                catch (TelegramApiException var4) {
                    var4.printStackTrace();
                }
            }
            String message = update.getMessage().getText().trim();
            String CYRILLIC_TO_LATIN = "Russian-Latin/BGN";
            Transliterator toLatinTrans = Transliterator.getInstance(CYRILLIC_TO_LATIN);
            String result = toLatinTrans.transliterate(message);
            String nick = update.getMessage().getFrom().getUserName();
            Bukkit.getServer().broadcastMessage("[Telegram] " + nick + " - " + result);
        }

    }

    public void clearWebhook() {}

    public String getBotUsername() { return "a"; }

    public String getBotToken() { return configuration.getConfig("token").toString(); }

}
