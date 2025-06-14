package spring_boot.demo.helper;

public class errorException extends RuntimeException {
    public errorException(String errorMessage) {
        super(errorMessage);
    }

}
