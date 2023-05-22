package enums;

public enum Level {
    EASY("easy", 1.2, 7, 2, 1),
    MEDIUM("medium", 1.5, 5, 2.5, 2),
    HARD("hard", 1.8, 3, 3, 3)
    ;

    private final String name;
    private final double windSpeed;
    private final int freezeTime;
    private final double speedRate;
    private final int scoreMultiply;

    Level(String name, double windSpeed, int freezeTime, double speedRate, int scoreMultiply) {
        this.name = name;
        this.windSpeed = windSpeed;
        this.freezeTime = freezeTime;
        this.speedRate = speedRate;
        this.scoreMultiply = scoreMultiply;
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

    public int getScoreMultiply() {
        return scoreMultiply;
    }
}
