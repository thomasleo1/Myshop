package Business.Factory;

public class NotificaFactory {

    public enum TipoNotifica {EMAIL, SMS, PUSH}

    public Notifica getCanaleNotifica(TipoNotifica type) {
        if (type == null) {
            type = TipoNotifica.EMAIL;
        }

        switch (type) {
            case EMAIL: return new NotificaEmail();
            case SMS: return new NotificaSMS();
            case PUSH: return new NotificaPush();
        }

        return null;
    }
}
