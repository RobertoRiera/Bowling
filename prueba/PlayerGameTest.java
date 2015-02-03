import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerGameTest {

    @Test
    public void withNoRolls() throws Exception {
        Match match = createMatch();
        PlayerGame playerGame = new PlayerGame(match, "one");
        assertEquals(0, playerGame.getFrames().length);
    }

    @Test
    public void withOneRoll() throws Exception {
        Match match = createMatch();
        match.addRolls(4).toPlayer("one");
        PlayerGame playerGame = new PlayerGame(match, "one");
        assertEquals(1,playerGame.getFrames().length);
        assertNull(playerGame.getFrames()[0].getScoring());
    }

    @Test
    public void withTwoRolls() throws Exception {
        Match match = createMatch();
        match.addRolls(4, 5).toPlayer("one");
        PlayerGame playerGame = new PlayerGame(match, "one");
        assertEquals(1,playerGame.getFrames().length);
        assertEquals(9,playerGame.getFrames()[0].getScoring().getPoints());
    }
    @Test
    public void withOtherTwoRolls() {
        Match match = createMatch();
        match.addRolls(1, 4).toPlayer("one");
        PlayerGame playerGame = new PlayerGame(match, "one");
        assertEquals(1,playerGame.getFrames().length);
        assertEquals(5, playerGame.getFrames()[0].getScoring().getPoints());
    }

    @Test
    public void withThreeRolls() {
        Match match = createMatch();
        match.addRolls(1, 4, 3).toPlayer("one");
        PlayerGame playerGame = new PlayerGame(match, "one");
        assertEquals(2,playerGame.getFrames().length);
        assertNotNull(playerGame.getFrames()[0].getScoring());
        assertEquals(5,playerGame.getFrames()[0].getScoring().getPoints());
        assertNull(playerGame.getFrames()[1].getScoring());
    }

    @Test
    public void withStrike() throws Exception {
        Match match = createMatch();
        match.addRolls(10,5).toPlayer("one");
        PlayerGame playerGame = new PlayerGame(match, "one");
        assertEquals(2, playerGame.getFrames().length);
        assertNull(playerGame.getFrames()[0].getScoring());
        assertNull(playerGame.getFrames()[1].getScoring());
    }

    @Test
    public void withStrikeAndTwoMoreRolls() throws Exception {
        Match match = createMatch();
        match.addRolls(10,5,3).toPlayer("one");
        PlayerGame playerGame = new PlayerGame(match, "one");
        assertEquals(2, playerGame.getFrames().length);
        assertEquals(18, playerGame.getFrames()[0].getScoring().getPoints());
        assertEquals(26, playerGame.getFrames()[1].getScoring().getPoints());
    }

    @Test
    public void withSpare() throws Exception {
        Match match = createMatch();
        match.addRolls(5,5).toPlayer("one");
        PlayerGame playerGame = new PlayerGame(match, "one");
        assertEquals(1, playerGame.getFrames().length);
        assertNull(playerGame.getFrames()[0].getScoring());
    }

    @Test
    public void withSpareAndOneMoreRoll() throws Exception {
        Match match = createMatch();
        match.addRolls(5,5,5).toPlayer("one");
        PlayerGame playerGame = new PlayerGame(match, "one");
        assertEquals(2,playerGame.getFrames().length);
        assertEquals(15, playerGame.getFrames()[0].getScoring().getPoints());
        assertNull(playerGame.getFrames()[1].getScoring());
    }

    private Match createMatch() {
        Match match = new Match();
        match.addPlayer(new Player("one"));
        match.addPlayer(new Player("two"));
        return match;
    }


}









