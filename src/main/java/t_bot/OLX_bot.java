package t_bot;

import constant.Constanta;
import dataBase.BaseMethod;
import dataBase.DataBase;
import model.Category;
import model.Product;
import model.User;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import service.UserService;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;


public class OLX_bot extends TelegramLongPollingBot implements Constanta {
    static DataBase dataBase = new BaseMethod();
    static UserService userService = new UserService();
    static List<Product> allProductsList;
    static List<User> allUsersList;
    static List<Category> allCategories;
    static final String[] parentCategory = {BOLALAR_DUNYOSI, KOCHMAS_MULK, TRANSPORT, ISH, UY_JIHOZLARI, ELEKTRONIKA};

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new OLX_bot());
    }

    @Override
    public String getBotUsername() {
        return BOT_USERNAME;

    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        Long userId = update.getMessage().getFrom().getId();
        System.out.println(userId);

        if (update.getMessage().hasText()) {
            try {
                handleMessage(update.getMessage());
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else if (update.hasCallbackQuery()) {
            handleQuery(update.getCallbackQuery());
        } else if (update.getMessage().hasContact()) {
            handleContact(update);
        }
    }

    private void handleContact(Update update) {
      userService.saveUserToData(update.getMessage());
    }

    private void handleQuery(CallbackQuery callbackQuery) {

    }

    private void handleMessage(Message message) throws FileNotFoundException {
        String text = message.getText();
        String chatId = String.valueOf(message.getChatId());
        switch (text) {
            case START -> headOfReplyKeyboardButtons(message, chatId);
            case CATEGORIES -> headCategoryKeyboardButtons(message, chatId);
            case PRODUCTS -> allProductsKeyboardButtons(message, chatId);
            case PROFILE -> profileForUser(message, chatId);
            case ADMIN -> adminPanel(message);
            case BOLALAR_DUNYOSI -> childCategory(message, chatId);
            case BACK -> headOfReplyKeyboardButtons(message, chatId);

        }
    }

    private void childCategory(Message message, String chatId) {
        try {
            allCategories = dataBase.getAllCategoriesList();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        List<Category> childrenList = new ArrayList<>();
        List<List<InlineKeyboardButton>> inline = new ArrayList<>();
        for (Category category :allCategories) {
            if (category.getParentId() == 1){
                childrenList.add(category);
            }
        }
        int size = childrenList.size();
        if (size % 2 ==0){
            for (int i = 0; i < size; i++) {
                inline.add(Arrays.asList(
                       InlineKeyboardButton.builder().text(childrenList.get(i)
                               .getName()).callbackData(String.valueOf(childrenList.get(i).getId())).build(),
                        InlineKeyboardButton.builder().text(childrenList.get(i+1)
                                .getName()).callbackData(String.valueOf(childrenList.get(i+1).getId())).build()
                ));
                i++;
            }
        }
        try {
            execute(
                    SendMessage.builder()
                            .text(BOLALAR_DUNYOSI)
                            .chatId(chatId)
                            .replyMarkup(InlineKeyboardMarkup.builder().keyboard(inline).build())
                            .build()
            );
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }


    }

    private void adminPanel(Message message) {
     boolean flag = checkAdmin();
     if (flag) {
         ReplyKeyboardMarkup reply = new ReplyKeyboardMarkup();
         reply.setResizeKeyboard(true);
         reply.setSelective(true);
         List<KeyboardRow> rows = new ArrayList<>();
         rows.add(new KeyboardRow(List.of(
                 new KeyboardButton(ALL_CATEGORIES),
                 new KeyboardButton(ALL_USERS),
                 new KeyboardButton(ADD_CATEGORY),
                 new KeyboardButton(STATISTICS))));
         reply.setKeyboard(rows);
         try {
             execute(SendMessage.builder().text("Choose.. ").replyMarkup(reply).chatId(message.getChatId()).build());
         } catch (TelegramApiException e) {
             throw new RuntimeException(e);
         }
     }
     else {
         try {
             execute(SendMessage.builder().text("You are not Admin ").chatId(message.getChatId()).build());
         } catch (TelegramApiException e) {
             throw new RuntimeException(e);
         }
     }
    }

    private void profileForUser(Message message, String chatId) throws FileNotFoundException {
        User current = checkUserByDataBaseList(chatId);
        if (current == null){

            ReplyKeyboardMarkup rep = new ReplyKeyboardMarkup();
            rep.setResizeKeyboard(true);
            rep.setSelective(true);
            rep.setOneTimeKeyboard(true);
            List<KeyboardRow> list = new ArrayList<>();
            KeyboardRow keyboardRow = new KeyboardRow();
            KeyboardButton keyboardButton = new KeyboardButton();
            keyboardButton.setText("Please Share Contact ");
            keyboardButton.setRequestContact(true);
            keyboardRow.add(keyboardButton);
            list.add(keyboardRow);
            rep.setKeyboard(list);
            try {
                execute(SendMessage.builder().text("Share Contact").chatId(chatId).replyMarkup(rep).build());
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setText(current.toString());
            sendMessage.setChatId(chatId);
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void allProductsKeyboardButtons(Message message, String chatId) throws FileNotFoundException {
        allProductsList = dataBase.getAllProductsList();
        if (allProductsList == null) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setText("Mahsulotlar Yo`q");
            sendMessage.setChatId(chatId);
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        } else {
            for (Product pro : allProductsList) {
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(chatId);
                sendMessage.setText(pro.toString());
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    private void headOfReplyKeyboardButtons(Message message, String chatId) throws FileNotFoundException {

        ReplyKeyboardMarkup reply = new ReplyKeyboardMarkup();
        reply.setSelective(true);
        reply.setResizeKeyboard(true);
        List<KeyboardRow> rows = new ArrayList<>();
        rows.add(new KeyboardRow(Arrays.asList(
                new KeyboardButton(CATEGORIES),
                new KeyboardButton(PRODUCTS),
                new KeyboardButton(PROFILE),
                new KeyboardButton(FAVORITES)
        )));
        reply.setKeyboard(rows);
        try {
            execute(SendMessage.builder().chatId(chatId).text("Choose One of ").replyMarkup(reply).build());
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private void headCategoryKeyboardButtons(Message message, String chatId) {
        ReplyKeyboardMarkup reply = new ReplyKeyboardMarkup();
        reply.setSelective(true);
        reply.setResizeKeyboard(true);
        List<KeyboardRow> rows = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            rows.add(new KeyboardRow(List.of(
                    new KeyboardButton(parentCategory[i]),
                    new KeyboardButton(parentCategory[i + 1]),
                    new KeyboardButton(parentCategory[i + 2])
            )));
            i += 2;
        }
        rows.add(new KeyboardRow(List.of(new KeyboardButton(BACK))));
        reply.setKeyboard(rows);
        try {
            execute(SendMessage.builder().
                    chatId(chatId).text("Choose One of ").parseMode(ParseMode.MARKDOWNV2).
                    replyMarkup(reply).build());
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private User checkUserByDataBaseList(String chatId){
        try {
            allUsersList = dataBase.getAllUsersList();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        boolean flag = false;
        User current = new User();
        for (User user : allUsersList) {
            if (user.getChatId().equals(chatId)) {
                current = user;
                flag = true;
                break;
            }
        }
        if (flag) {
            return current;
        }
        return null;
    }

    private boolean checkAdmin(){
        try {
            allUsersList = dataBase.getAllUsersList();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        boolean flag = false;
        for (User user : allUsersList) {
            if (user.getChatId().equals(ADMIN_ID)) {
                flag = true;
                break;
            }
        }
        return flag;

    }


}

