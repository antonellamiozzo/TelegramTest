import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class AntoBot extends TelegramLongPollingBot {
    static String BotToken = "625563171:AAFyoxqMiAua2gLEGVRYcYF00KhAa2aYyG0";
    long ChatID ;


    public void onUpdateReceived(Update update) {
        String username = "Username";
        String password ="Password" ;

        long eventID =0;


        ChatID = update.getMessage().getChatId();

        String command = update.getMessage().getText();
        SendMessage message = new SendMessage();
        Integer id = update.getUpdateId();

        if (command.contains("/buscarEvento")){
            MandarMensaje(update.getMessage().getText());
        }
        if(command.equals("/login")) {
            if((username.equals("Username")) || (password.equals("Password"))  )
                MandarMensaje("Please set your username and password to login");
            else{
            //llamo al metodo de login
            MandarMensaje("Se loguea bebe");
            MandarMensaje("Login Successfully");
            }
        }

        if(command.equals("/setusername")) {
            System.out.println(update.getMessage().getText());
            username= update.getMessage().getText();
            MandarMensaje("Ok");
        }
        if(command.equals("/setpassword")) {
            System.out.println(update.getMessage().getText());
            password= update.getMessage().getText();
        }


        if(command.contains("/buscarevento")){
            List <String> parametros = ParsearComando(command);

            if(parametros.size() != 0){
                Long eventId = Long.parseLong(parametros.get(0));
                MandarMensaje(parametros.get(0));
            }else {
                MandarMensaje("Falta un parametros");
            }
        }


        if(command.contains("/agregarevento")){

            List <String> parametros = ParsearComando(command);
            if(parametros.size() < 1){
            long eventId = Long.parseLong(parametros.get(0));

            // EventList eventList = parametros.get(1).tolist();
            // Uso el servicio de agregar
                MandarMensaje("Agregado Correctamente");
            }
            else{
            MandarMensaje("Falta un parametros");
            }

        }
        if(command.contains("/revisarevento")) {
            List<String> parametros = ParsearComando(command);
            if (parametros.size() < 1) {
                long eventId = Long.parseLong(parametros.get(0));
                MandarMensaje("Modificado Correctamente");
            } else{
                MandarMensaje("Falta un parametros");
            }
        }

    }
    public void MandarMensaje(String message){

        SendMessage msg = new SendMessage();
        msg.setText(message);
        msg.setChatId(ChatID);
        try {
            execute(msg);
        }catch (TelegramApiException e){
            e.printStackTrace();
        }


    }

    public String getBotUsername() {
        return "POny" ;
    }

    public String getBotToken() {
        return "625563171:AAFyoxqMiAua2gLEGVRYcYF00KhAa2aYyG0";
    }

    public List<String> ParsearComando(String comando){
        List<String> parametros = new ArrayList<String>() ;
        String[] param = comando.split(" ");
        for(String item : param) {
            if (!item.contains("/"))
                parametros.add(item);
        }

        return parametros;
    }
}
