import java.util.Arrays;
import java.util.List;

public class PlayerFrame {

    private final List<Roll> rolls;
    private final int index;
    private PlayerFrame previousFrame;

    public PlayerFrame(PlayerFrame previousFrame, Roll[] rolls, int index) {
        this.previousFrame = previousFrame;
        this.rolls = Arrays.asList(rolls);
        this.index = index;
    }

    public int getNumberOfRolls() {
        return isStrike() ? 1 : 2;
    }

    public Scoring getScoring() {
        if (!isComplete()) return null;
        return new Scoring((isStrike() || isSpare() ? pinsInThreeRolls() : pinsInTwoRolls()) + accumulatedPoints());
    }

    private int accumulatedPoints() {
        return previousFrame == null || previousFrame.getScoring() == null ? 0 : previousFrame.getScoring().getPoints();
    }

    private boolean isStrike() {
        return getPinsOfRoll(index) == 10;
    }

    private boolean isSpare() {
        return hasTwoRolls() && pinsInTwoRolls() == 10;
    }

    private int pinsInTwoRolls() {
        return getPinsOfRoll(index) + getPinsOfRoll(index + 1);
    }

    private int pinsInThreeRolls() {
        return pinsInTwoRolls() + getPinsOfRoll(index + 2);
    }

    private boolean isComplete() {
        return hasRequiredRolls(toCompleteFrame());
    }

    private boolean hasRequiredRolls(int number) {
        return index + number <= rolls.size();
    }

    private boolean hasTwoRolls() {
        return hasRequiredRolls(2);
    }

    private int toCompleteFrame(){
        return isStrike() || isSpare() ? 3 : 2;
    }

    private int getPinsOfRoll(int index) {
        if (index >= rolls.size()) return 0;
        return rolls.get(index).getPins();
    }

    public class Scoring {
        private int points;

        public Scoring(int points) {
            this.points = points;
        }

        public int getPoints() {
            return points;
        }
    }
}
