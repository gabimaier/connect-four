package gm.connectfour.domain.entity;

public enum Player {
    X('x'), O('o'), NONE('.');
    private final char code;
    Player(char code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return ""+code;
    }

    public static Player valueOf(char code){
        for(Player value : values()){
            if(value.code == code){
                return value;
            }
        }
        throw new IllegalArgumentException("unknown: " + code);
    }
}
