import model.TypeMessage;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import service.CategoryService;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

public class Main1 extends TelegramLongPollingBot {
    private CategoryService categoryService;

    Main1() {
        this.categoryService = new CategoryService();
    }


    @Override
    public String getBotUsername() {
        return "t.me/abhavo_bot";
    }

    @Override
    public String getBotToken() {
        return "5470637623:AAH1O1XQcnti53-Qcx_AaylXPjaPQKoqS74";
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            Message message = update.getMessage();

            String text = message.getText();

            if (text.equals("/start")) {//categories
                this.sendMSG(this.categoryService.mainKeyboarAfterCategory(message));
            } else if (text.substring(3).equals("Bolalar dunyosi")) {

                TypeMessage typeMessage = this.categoryService.inlineBoardAfterMainKeyboar_Children(message);
                this.sendMSG(typeMessage);

            } else if (text.substring(3).equals("Ko'chmas  mulk")) {

                this.sendMSG(this.categoryService.inlineBoardAfterMainKeyboar_Real_Estate(message));

            } else if (text.substring(3).equals("Transport")) {

                this.sendMSG(this.categoryService.inlineBoardAfterMainKeyboar_Transport(message));

            } else if (text.substring(3).equals("Maishiy  jihozlar")) {

                this.sendMSG(this.categoryService.inlineBoardAfterMainKeyboar_Appliances(message));

            } else if (text.substring(3).equals("Elektronika")) {

                this.sendMSG(this.categoryService.inlineBoardAfterMainKeyboar_Electronics(message));

            } else if (text.substring(3).equals("Ish")) {
                this.sendMSG(this.categoryService.inlineBoardAfterMainKeyboar_WORK(message));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void sendMSG(TypeMessage typeMessage) {
        try {
            switch (typeMessage.getType()) {
                case MESSAGE -> execute(typeMessage.getSendMessage());
                case EDIT_MESSAGE -> execute(typeMessage.getEditMessageText());
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendPhoto(SendPhoto sendPhoto) {
        try {
            execute(sendPhoto);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
/*
File file=new File("C:\\HP\\b24\\1copy.png");
        SendPhoto message = new SendPhoto();
        message.setPhoto(new InputFile(file));
        message.setChatId(String.valueOf(message1.getChatId()));
        this.execute(message);
 */
