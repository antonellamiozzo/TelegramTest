import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

public class AntoBot extends TelegramLongPollingBot {


    public void onUpdateReceived(Update update) {

        System.out.println(update.getMessage().getText());

    }

    public String getBotUsername() {
        return "POny" ;
    }

    public String getBotToken() {
        return "655295765:AAGSCBaDUWA_Dw6XHw_bOxh6iptVBYKiPa8";
    }
}
