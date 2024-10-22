public class Player {
    private String name;
    private String position;
    private int seasonGoals;
    private boolean isStarPlayer;
    private boolean isInjured;
    private boolean isReported;
    private int kicks;
    private int goals;
    private int behinds;
    private int effectiveDisposals;
    private String setPosition;

    public Player(String name, String position, int seasonGoals) {
        this.name = name;
        this.position = position;
        this.seasonGoals = seasonGoals;
        this.isStarPlayer = false;
        this.isInjured = false;
        this.isReported = false;
        this.kicks = 0;
        this.goals = 0;
        this.behinds = 0;
        this.effectiveDisposals = 0;
    }

    // Getters and Setters
    public String getName() { return name; }
    public String getPosition() { return position; }
    public int getSeasonGoals() { return seasonGoals; }
    public boolean isStarPlayer() { return isStarPlayer; }
    public boolean isInjured() { return isInjured; }
    public boolean isReported() { return isReported; }
    public int getKicks() { return kicks; }
    public int getGoals() { return goals; }
    public int getBehinds() { return behinds; }
    public int getEffectiveDisposals() { return effectiveDisposals; }

    public void setPosition(String position) {this.setPosition = position;}
    public void setStarPlayer ( boolean starPlayer){
        this.isStarPlayer = starPlayer;
    }
    public void setInjured ( boolean injured){
        this.isInjured = injured;
    }
    public void setReported ( boolean reported){
        this.isReported = reported;
    }

    public void addKick () {
        this.kicks++;
    }
    public void addGoal () {
        this.goals++;
        this.seasonGoals++;
        this.effectiveDisposals++;
    }
    public void addBehind () {
        this.behinds++;
        this.effectiveDisposals++;
    }
    public void addEffectiveDisposal () {
        this.effectiveDisposals++;
    }

    public double getEffectiveDisposalPercentage () {
        if (kicks == 0) return 0;
        return (effectiveDisposals * 100.0) / kicks;
    }

    public String getSetPosition() {
        return setPosition;
    }
}