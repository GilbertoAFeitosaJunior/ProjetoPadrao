package mobi.stos.padrao.exception;

public class LoginException extends Exception {

    public LoginException() {
        super("Esta conta de acesso não existe.");
    }

}
