import java.util.ArrayList;
import java.util.List;

public class PlayerGame {

    private final Match match;
    private final String playerName;

    public PlayerGame(Match match, String playerName) {
        this.match = match;
        this.playerName = playerName;
    }

    public PlayerFrame[] getFrames() {
        return buildFrames(match.getRolls(playerName)).toArray(new PlayerFrame[0]);
    }

    private List<PlayerFrame> buildFrames(Roll[] rolls) {
        return new FrameBuilder(rolls).build();
    }

    private class FrameBuilder {
        private final Roll[] rolls;

        public FrameBuilder(Roll[] rolls) {
            this.rolls = rolls;
        }

        public List<PlayerFrame> build() {
            List<PlayerFrame> frames = new ArrayList<>();
            PlayerFrame frame = null;
            for (int index = 0; index < rolls.length; index += stepFrom(index)) {
                frame = new PlayerFrame(frame, rolls, index);
                frames.add(frame);
            }
            return frames;
        }

        private int stepFrom(int index) {
            return new PlayerFrame(null, rolls, index).getNumberOfRolls();
        }
    }


}
