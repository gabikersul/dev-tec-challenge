package exception;

public class FileException extends RuntimeException {
    public FileException(String fileErrorMessage){
        super(fileErrorMessage);
    }
}
