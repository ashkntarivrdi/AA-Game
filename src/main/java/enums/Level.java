package enums;

public enum Level {
    EASY("easy"),
    MEDIUM("medium"),
    HARD("hard")
    ;

    private final String name;
    Level(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
