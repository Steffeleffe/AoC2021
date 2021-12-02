import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day01Test {

    @Test
    public void testPart1() throws Exception {
        Day01.Report report = new Day01.Report("resources/day01part1test.txt");

        assertEquals(7, Day01.part1(report));
    }

    @Test
    public void solvePart1() throws Exception {
        Day01.Report report = new Day01.Report("resources/day01part1.txt");

        assertEquals(1228, Day01.part1(report));
    }

    @Test
    public void testPart2() throws Exception {
        Day01.Report report = new Day01.Report("resources/day01part1test.txt");

        assertEquals(5, Day01.part2(report));
    }

    @Test
    public void solvePart2() throws Exception {
        Day01.Report report = new Day01.Report("resources/day01part1.txt");

        assertEquals(1257, Day01.part2(report));
    }

}