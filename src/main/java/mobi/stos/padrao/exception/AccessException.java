package mobi.stos.padrao.exception;

public class AccessException extends Exception {

    public AccessException() {
        super("Acesso negado! Seu usuário não tem permissão ou acesso a área solicitada.");
    }
}
