import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day03Test {

    @Test
    public void testPart1() throws Exception {
        Day03.Report report = new Day03.Report("resources/day03part1test.txt");

        assertEquals(198, Day03.part1(report));
    }

    @Test
    public void solvePart1() throws Exception {
        Day03.Report report = new Day03.Report("resources/day03part1.txt");

        assertEquals(1458194, Day03.part1(report));
    }

    @Test
    public void testPart2() throws Exception {
        Day03.Report report = new Day03.Report("resources/day03part1test.txt");

        assertEquals(230, Day03.part2(report));
    }

    @Test
    public void solvePart2() throws Exception {
        Day03.Report report = new Day03.Report("resources/day03part1.txt");

        assertEquals(2829354, Day03.part2(report));
    }

}