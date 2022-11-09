package service;

import model.EnumTypeMessage;
import model.TypeMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

public class CategoryService {

    public TypeMessage mainKeyboarAfterCategory(Message message) {

        List<KeyboardRow> buttonList = new ArrayList<>();
        buttonList.add(new KeyboardRow(List.of(
                new KeyboardButton("\uD83E\uDDF8 Bolalar dunyosi"),
                new KeyboardButton("\uD83C\uDFE1 Ko'chmas  mulk")
        )));
        buttonList.add(new KeyboardRow(List.of(
                new KeyboardButton("\uD83C\uDFCE Transport")
        )));
        buttonList.add(new KeyboardRow(List.of(
                new KeyboardButton("\uD83D\uDECB Maishiy  jihozlar"),
                new KeyboardButton("\uD83D\uDCF1 Elektronika")
        )));
        buttonList.add(new KeyboardRow(List.of(
                new KeyboardButton("\uD83D\uDCBC Ish")
        )));

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(buttonList);
        replyKeyboardMarkup.setResizeKeyboard(true);

        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("Pastdan  kerakli bo'limni tanlang \uD83D\uDC47");
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyMarkup(replyKeyboardMarkup);

        TypeMessage typeMessage = new TypeMessage();
        typeMessage.setSendMessage(sendMessage);
        typeMessage.setType(EnumTypeMessage.MESSAGE);
        return typeMessage;
    }

    public TypeMessage inlineBoardAfterMainKeyboar_Children(Message message) {
        List<List<InlineKeyboardButton>> rowCollection = new ArrayList<>(List.of(
                categoryROW(button("Bollar kiyimi", "Bollar kiyimi")),
                categoryROW(button("O'yinchoqlar", "Oyinchoqlar")),
                categoryROW(button("Maktab o'quvchilari  uchun mahsulotlar", "Maktab mahsuloti")),
                categoryROW(button("Bollalar ozuqa mahsuloti", "Bollalar mahsuloti"))
        ));
        InlineKeyboardMarkup inlineKeyboardMarkup = inLineMarkup(rowCollection);
        TypeMessage typeMessage = responceWithSEND_MESSAGE(inlineKeyboardMarkup, message);
        return typeMessage;
    }

    public TypeMessage inlineBoardAfterMainKeyboar_Real_Estate(Message message) {
        List<List<InlineKeyboardButton>> rowCollection = new ArrayList<>();
        rowCollection.addAll(List.of(
                categoryROW(button("Yer uchaskalar", "Uchastka")),
                categoryROW(button("Ijara uylar", "Ijara uylar")),
                categoryROW(button("Tijorat binolari", "Tijorat binolari"))
        ));
        InlineKeyboardMarkup inlineKeyboardMarkup = inLineMarkup(rowCollection);
        return responceWithSEND_MESSAGE(inlineKeyboardMarkup, message);
    }

    public TypeMessage inlineBoardAfterMainKeyboar_Transport(Message message) {
        List<List<InlineKeyboardButton>> rowCollection = new ArrayList<>();
        rowCollection.addAll(List.of(
                categoryROW(button("Avtomobilar", "Avtomobilar")),
                categoryROW(button("Ijara avtomobilar", "Ijara avtomobilar")),
                categoryROW(button("Extiyot qismlari", "Extiyod qismalari"))
        ));
        InlineKeyboardMarkup inlineKeyboardMarkup = inLineMarkup(rowCollection);
        return responceWithSEND_MESSAGE(inlineKeyboardMarkup, message);
    }

    public TypeMessage inlineBoardAfterMainKeyboar_Appliances(Message message) {
        List<List<InlineKeyboardButton>> rowCollection = new ArrayList<>();
        rowCollection.addAll(List.of(
                categoryROW(button("Oshxona anjomlari", "Oshxona anjomlari")),
                categoryROW(button("Qurilish masulotlari", "Qurilish masulotlari")),
                categoryROW(button("Interyer jihozlar", "Interyer jihozlar"))
        ));
        InlineKeyboardMarkup inlineKeyboardMarkup = inLineMarkup(rowCollection);
        return responceWithSEND_MESSAGE(inlineKeyboardMarkup, message);
    }

    public TypeMessage inlineBoardAfterMainKeyboar_Electronics(Message message) {
        List<List<InlineKeyboardButton>> rowCollection = new ArrayList<>();
        rowCollection.addAll(List.of(
                categoryROW(button("Telefon", "Telefon")),
                categoryROW(button("Kompyuter", "Kompyuter")),
                categoryROW(button("TV/Video texnikalar", "TV/Video")),
                categoryROW(button("Elektron ehtiyod qismlar", "Elektron ehtiyod"))
        ));
        InlineKeyboardMarkup inlineKeyboardMarkup = inLineMarkup(rowCollection);
        return responceWithSEND_MESSAGE(inlineKeyboardMarkup, message);
    }

    public TypeMessage inlineBoardAfterMainKeyboar_WORK(Message message) {
        List<List<InlineKeyboardButton>> rowCollection = new ArrayList<>();
        rowCollection.addAll(List.of(
                categoryROW(button("Talim", "Talim")),
                categoryROW(button("Qurilish", "Qurilish")),
                categoryROW(button("IT", "IT")),
                categoryROW(button("Tibbiyot", "Tibbiyot"))
        ));
        InlineKeyboardMarkup inlineKeyboardMarkup = inLineMarkup(rowCollection);
        return responceWithSEND_MESSAGE(inlineKeyboardMarkup, message);
    }

    public TypeMessage inlineBoardAfterMainKeyboar_Product(Message message) {
        List<List<InlineKeyboardButton>> rowCollection = new ArrayList<>();
        rowCollection.addAll(List.of(
                categoryROW(button("➡️","Pback")),
                categoryROW(button("1️⃣", "P1"), button("2️⃣", "P2"), button("3️⃣", "P3"), button("4️⃣", "P4")),
                categoryROW(button("⬅️","Pforword"))

        ));
        InlineKeyboardMarkup inlineKeyboardMarkup = inLineMarkup(rowCollection);
        return responceWithEDIT_MESSAGE(inlineKeyboardMarkup,message);
    }

    private TypeMessage responceWithSEND_MESSAGE(InlineKeyboardMarkup inlineKeyboardMarkup, Message message) {
        SendMessage sendMessage = new SendMessage();
        TypeMessage typeMessage = new TypeMessage();

        sendMessage.setText("quyidagi bolimdan birini tanlang \uD83D\uDC47");

        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        typeMessage.setType(EnumTypeMessage.MESSAGE);
        typeMessage.setSendMessage(sendMessage);
        return typeMessage;

    }

    private TypeMessage responceWithEDIT_MESSAGE(InlineKeyboardMarkup inlineKeyboardMarkup, Message message) {
        TypeMessage typeMessage = new TypeMessage();

        EditMessageText editMessageText = new EditMessageText();

        editMessageText.setChatId(message.getChatId().toString());
        editMessageText.setMessageId(message.getMessageId());
        typeMessage.setType(EnumTypeMessage.EDIT_MESSAGE);

        editMessageText.setText("quyidagi bolimdan birini tanlang \uD83D\uDC47");
        editMessageText.setReplyMarkup(inlineKeyboardMarkup);
        typeMessage.setEditMessageText(editMessageText);

        return typeMessage;
    }

    private InlineKeyboardButton button(String text, String callBack) {
        InlineKeyboardButton iButton = new InlineKeyboardButton();
        iButton.setText(text);
        iButton.setCallbackData(callBack);
        return iButton;
    }

    private List<InlineKeyboardButton> categoryROW(InlineKeyboardButton... inlineButtons) {
        ArrayList<InlineKeyboardButton> arrayList = new ArrayList<>();
        arrayList.addAll(List.of(inlineButtons));
        return arrayList;
    }

    private InlineKeyboardMarkup inLineMarkup(List<List<InlineKeyboardButton>> currList) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(currList);
        return inlineKeyboardMarkup;
    }


}
