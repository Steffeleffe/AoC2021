import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day04Test {

    @Test
    public void testPart1() throws Exception {
        Day04.BingoGame report = new Day04.BingoGame("resources/day04part1test.txt");

        assertEquals(4512, Day04.part1(report));
    }

    @Test
    public void solvePart1() throws Exception {
        Day04.BingoGame report = new Day04.BingoGame("resources/day04part1.txt");

        assertEquals(82440, Day04.part1(report));
    }

    @Test
    public void testPart2() throws Exception {
        Day04.BingoGame report = new Day04.BingoGame("resources/day04part1test.txt");

        assertEquals(1924, Day04.part2(report));
    }

    @Test
    public void solvePart2() throws Exception {
        Day04.BingoGame report = new Day04.BingoGame("resources/day04part1.txt");

        assertEquals(20774, Day04.part2(report));
    }

}