package gm.connectfour.exception;

public class IllegalMoveException extends RuntimeException {
    public static enum Code {
        OUT_OF_BOUNDS("OUT_OF_BOUNDS"), COLUMN_FULL("COLUMN_FULL");
        private final String code;
        Code(String code){
            this.code = code;
        }
    }

    private Code reason;

    public IllegalMoveException(Code reason) {
        this.reason = reason;
    }

    public Code getReason() {
        return reason;
    }
}
