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
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import service.ProductService;
import service.UserService;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


public class OLX_bot extends TelegramLongPollingBot implements Constanta {
    static DataBase dataBase = new BaseMethod();
    static UserService userService = new UserService();
    static ProductService productService = new ProductService();
    static List<Product> allProductsList;
    static List<User> allUsersList;
    static List<Category> allCategories;
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
        if (update.hasMessage() && update.getMessage().hasPhoto()){
            handlePhotos(update);
        }

       else if (update.getMessage().hasText()) {
            try {
                handleMessage(update.getMessage());
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else if (update.hasCallbackQuery()) {
            handleQuery(update.getCallbackQuery());
        } else if (update.getMessage().hasContact()){
            handleContact(update);
        }
    }

    private void handlePhotos(Update update) {
        String chatId = String.valueOf(update.getMessage().getChatId());
        List<PhotoSize> photos = update.getMessage().getPhoto();
        String f_id = photos.stream()
                .sorted(Comparator.comparing(PhotoSize::getFileSize).reversed())
                .findFirst()
                .orElse(null).getFileId();
        SendPhoto msg = new SendPhoto();
        InputFile file = new InputFile(f_id);
        msg.setPhoto(file);
        msg.setChatId(chatId);
        //msg.setCaption("rgkretglrkngngnrtgn");
        try {
            execute(msg); // Call method to send the photo with caption
        } catch (TelegramApiException e) {
            e.printStackTrace();
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
            case ELON_BERISH -> addProductToBase(message, chatId);
            case MENING_ELONLARIM -> myProducts(message, chatId);
            case MEN_HAQIMDA -> myInfo(chatId);
            case ALL_CATEGORIES -> allCategoriesForAdmin(message, chatId);
            case BOLALAR_DUNYOSI -> showAllCharCatFromOneParentCat(chatId, BOLALAR_DUNYOSI,1);
            case KOCHMAS_MULK -> showAllCharCatFromOneParentCat(chatId, KOCHMAS_MULK,2);
            case TRANSPORT -> showAllCharCatFromOneParentCat(chatId, TRANSPORT,3);
            case ISH -> showAllCharCatFromOneParentCat(chatId, ISH,4);
            case ELEKTRONIKA -> showAllCharCatFromOneParentCat(chatId, ELEKTRONIKA,6);
            case UY_JIHOZLARI -> showAllCharCatFromOneParentCat(chatId, UY_JIHOZLARI,5);
            case back -> headOfReplyKeyboardButtons(message, chatId);
            case BACK -> headOfReplyKeyboardButtons(message, chatId);

        }
    }

    private void myInfo(String chatId) {

        User current = checkUserByDataBaseList(chatId);
        SendMessage sendMessage1 = new SendMessage();
        assert current != null;
        sendMessage1.setText(current.toString());
        sendMessage1.setChatId(chatId);
        try {
            execute(sendMessage1);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }


    }

    private void myProducts(Message message, String chatId) {
        try {
            allProductsList = dataBase.getAllProductsList();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        List<Product> productList = new ArrayList<>();
        for (Product product : allProductsList){
            if (product.getUserId().equals(chatId)){
                try {
                    execute(SendMessage.builder().chatId(chatId).text(String.valueOf(product)).build());
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }
        }



    }

    private void addProductToBase(Message message, String chatId) {

    }

    //hasCallBackData
    private void allCategoriesForAdmin(Message message, String chatId) {


    }

    private void showAllCharCatFromOneParentCat(String chatId, String nameCategory, int categoryId){
        try {
            allCategories = dataBase.getAllCategoriesList();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        List<Category> workCategoryList = new ArrayList<>();
        for (Category category : allCategories) {
            if (category.getParentId() == categoryId) {
                workCategoryList.add(category);
            }
        }
        int size = workCategoryList.size();
        List<List<InlineKeyboardButton>> inline = new ArrayList<>();
        if (size % 2 == 0) {
            for (int i = 0; i < size; i++) {
                inline.add(List.of(
                        InlineKeyboardButton.builder().text(workCategoryList.get(i)
                                .getName()).callbackData(String.valueOf(workCategoryList.get(i).getId())).build(),
                        InlineKeyboardButton.builder().text(workCategoryList.get(i+1)
                                .getName()).callbackData(String.valueOf(workCategoryList.get(i+1).getId())).build()
                ));
                i++;
            }
        }
        else {
            InlineKeyboardButton button2 = new InlineKeyboardButton();
            for (int j = 0; j < size - 1; j++) {
                inline.add(List.of(
                        InlineKeyboardButton.builder().text(workCategoryList.get(j)
                                .getName()).callbackData(String.valueOf(workCategoryList.get(j).getId())).build(),
                        InlineKeyboardButton.builder().text(workCategoryList.get(j + 1)
                                .getName()).callbackData(String.valueOf(workCategoryList.get(j + 1).getId())).build()
                ));
                j++;
            }
            button2.setText(workCategoryList.get(size - 1).getName());
            button2.setCallbackData(String.valueOf(workCategoryList.get(size - 1).getId()));
            List<InlineKeyboardButton> list = new ArrayList<>();
            list.add(button2);

            inline.add(list);
        }
        try {
            execute(
                    SendMessage.builder()
                            .text(nameCategory)
                            .chatId(chatId)
                            .replyMarkup(new InlineKeyboardMarkup(inline)).build());
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

    private void profileForUser(Message message, String chatId) {
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

            ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
            List<KeyboardRow> rows = new ArrayList<>();
            rows.add(new KeyboardRow(List.of(
                    new KeyboardButton(MENING_ELONLARIM),
                    new KeyboardButton(MEN_HAQIMDA),
                    new KeyboardButton(ELON_BERISH)
            )));
            rows.add(new KeyboardRow(List.of(new KeyboardButton(BACK))));
            markup.setResizeKeyboard(true);
            markup.setSelective(true);
            markup.setKeyboard(rows);
            try {
                execute(SendMessage.builder().text("Choose.. ").chatId(chatId).replyMarkup(markup).build());
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
