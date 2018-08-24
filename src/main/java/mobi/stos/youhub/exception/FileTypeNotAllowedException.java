package mobi.stos.youhub.exception;

public class FileTypeNotAllowedException extends Exception {

    public FileTypeNotAllowedException(String message) {
        super("O tipo de arquivo enviado não é permitido. " + message);
    }

}