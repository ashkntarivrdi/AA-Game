package enums;

public enum Level {
    EASY("easy", 1.2, 7, 2),
    MEDIUM("medium", 1.5, 5, 2.5),
    HARD("hard", 1.8, 3, 3)
    ;

    private final String name;
    private final double windSpeed;
    private final int freezeTime;
    private final double speedRate;

    Level(String name, double windSpeed, int freezeTime, double speedRate) {
        this.name = name;
        this.windSpeed = windSpeed;
        this.freezeTime = freezeTime;
        this.speedRate = speedRate;
    }

    public String getName() {
        return this.name;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public int getFreezeTime() {
        return freezeTime;
    }

    public double getSpeedRate() {
        return speedRate;
    }
}
