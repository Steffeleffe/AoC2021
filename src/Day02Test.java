import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day02Test {

    @Test
    public void testPart1() throws Exception {
        Day02.Commands report = new Day02.Commands("resources/day02part1test.txt");

        assertEquals(150, Day02.part1(report));
    }

    @Test
    public void solvePart1() throws Exception {
        Day02.Commands report = new Day02.Commands("resources/day02part1.txt");

        assertEquals(1670340, Day02.part1(report));
    }

    @Test
    public void testPart2() throws Exception {
        Day02.Commands report = new Day02.Commands("resources/day02part1test.txt");

        assertEquals(900, Day02.part2(report));
    }

    @Test
    public void solvePart2() throws Exception {
        Day02.Commands report = new Day02.Commands("resources/day02part1.txt");

        assertEquals(1954293920, Day02.part2(report));
    }

}