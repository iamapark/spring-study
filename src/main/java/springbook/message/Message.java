package springbook.message;

/**
 * @author jinyoung.park89
 * @since 8/2/16
 */
public class Message {

    String text;

    private Message(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public static Message newMessage(String text) {
        return new Message(text);
    }
}
