package enums;

public enum Phase {
    ONE(1),
    TW0(2),
    THREE(3),
    FOUR(4)
    ;
    private final int phase;

    Phase(int phase) {
        this.phase = phase;
    }

    public int getPhase() {
        return this.phase;
    }
}
