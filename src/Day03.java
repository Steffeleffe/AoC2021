import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.function.BiFunction;

public class Day03 {

    public static long part1(Report report) {
        int commandSize = report.commands.get(0).length();
        var bitPositionCount = new int[commandSize];
        for (String command : report.commands) {
            for (int i = 0; i<commandSize; i++) {
                bitPositionCount[i] += Integer.parseInt(command.charAt(i) + "");
            }
        }
        var gammaRate = new BitSet(commandSize);
        var epsilonRate = new BitSet(commandSize);
        for (int i=0; i<commandSize; i++) {
            if (bitPositionCount[i] > report.commands.size()/2) {
                gammaRate.set(commandSize-i-1);
            } else {
                epsilonRate.set(commandSize-i-1);
            }
        }

        var gammaRateDecimal = gammaRate.toLongArray()[0];
        var epsilonRateDecimal = epsilonRate.toLongArray()[0];

        return gammaRateDecimal * epsilonRateDecimal;
    }

    public static int part2(Report report) {
        int oxygenRating = getRating(report, (integer, integer2) -> !integer.equals(integer2));
        int co2ScrubberRating = getRating(report, Integer::equals);
        return oxygenRating * co2ScrubberRating;
    }

    private static int getRating(Report report, BiFunction<Integer, Integer, Boolean> filter) {
        ArrayList<String> oxygenRatingList = new ArrayList<>(report.commands);
        int commandSize = oxygenRatingList.get(0).length();

        for (int i = 0; i < commandSize; i++) {
            int mostCommonBitOnPosition = findMostCommonBitOnPosition(oxygenRatingList, i);
            for (var iterator = oxygenRatingList.iterator(); iterator.hasNext(); ) {
                var s = iterator.next();
                if (filter.apply(Integer.parseInt(s.charAt(i) + ""), mostCommonBitOnPosition)) {
                    iterator.remove();
                    if (oxygenRatingList.size() == 1) {
                        return Integer.parseInt(oxygenRatingList.get(0),2);
                    }
                }
            }
        }
        throw new IllegalStateException("No number was found");
    }

    private static Integer findMostCommonBitOnPosition(ArrayList<String> oxygenRating, int index) {
        Integer integer = oxygenRating.stream()
                .map(s -> s.charAt(index))
                .map(c -> Integer.parseInt(c + ""))
                .reduce(Integer::sum)
                .get();
        return integer >= oxygenRating.size()/2.0 ? 1 : 0;
    }

    public record Report(List<String> commands) {
        public Report(String path) throws IOException {
            this(Files.lines(Path.of(path))
                    .map(String::trim)
                    .toList());
        }
    }

}
