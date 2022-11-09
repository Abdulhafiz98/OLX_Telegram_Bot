package model;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

public class TypeMessage {

    public EnumTypeMessage type;

    private SendMessage sendMessage;
    private EditMessageText editMessageText;

    public EnumTypeMessage getType() {
        return type;
    }

    public void setType(EnumTypeMessage type) {
        this.type = type;
    }

    public SendMessage getSendMessage() {
        return sendMessage;
    }

    public void setSendMessage(SendMessage sendMessage) {
        this.sendMessage = sendMessage;
    }

    public EditMessageText getEditMessageText() {
        return editMessageText;
    }

    public void setEditMessageText(EditMessageText editMessageText) {
        this.editMessageText = editMessageText;
    }
}
