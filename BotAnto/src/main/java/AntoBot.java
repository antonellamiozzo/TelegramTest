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
    boolean LOGIN = false;

    private String username = "Username";
    private String password = "Password";

    public void onUpdateReceived(Update update) {

        long eventID =0;


        ChatID = update.getMessage().getChatId();

        String command = update.getMessage().getText();
        SendMessage message = new SendMessage();
        Integer id = update.getUpdateId();

        if(command.equals("/login")) {
            if((username.equals("Username")) || (password.equals("Password"))  ) {
                System.out.println(username + " " + password);

                MandarMensaje("Please set your username and password to login");
            } else{
            //llamo al metodo de login

                LOGIN = true;
                System.out.println(username + " " + password);
            MandarMensaje("Se loguea bebe");
            MandarMensaje("Login Successfully");
            }
        }

        if(command.contains("/start")){

            MandarMensaje("Bienvenido, ingrese su usuario y contraseña para poder loguearse y acceder a los servicios de EventManager. \n" +
                    "Los comandos disponibles son :\n" +
                    "login - Loguearse\n" +
                    "username - Setear Username para loguerse\n" +
                    "password - Setear Password para loguearse\n" +
                    "buscarevento - Buscar Evento\n" +
                    "agregarevento - Agregar evento\n" +
                    "revisarevento - Revisar evento\n" +
                    "resetear - Volver a loguerse con otra cuenta ");
        }

        if(command.contains("/username")) {
            List <String> parametros = ParsearComando(command);
            System.out.println(update.getMessage().getText());
            username= parametros.get(0);

            System.out.println(username);
           // username=parametros.get(0);
            MandarMensaje("Ok " + parametros.get(0));
        }
        if(command.contains("/password")) {
            List <String> parametros = ParsearComando(command);
            System.out.println(update.getMessage().getText());
           // password.replace(password,parametros.get(0));
            password= parametros.get(0);
            MandarMensaje("Ok");
        }


        if(command.contains("/buscarevento")){

            if(LOGIN){
           List <String> parametros = ParsearComando(command);

            if(parametros.size() != 0){
                Long eventId = Long.parseLong(parametros.get(0));
                MandarMensaje(parametros.get(0));
            }else {
                MandarMensaje("Falta un parametros");
            }}else
                MandarMensaje("Debe loguearse primero");
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
