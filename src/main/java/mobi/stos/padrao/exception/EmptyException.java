package mobi.stos.padrao.exception;

import java.io.IOException;

public class EmptyException extends IOException {

    public EmptyException(String erro) {
        super("Registro não preenchido. " + erro);
    }

}
