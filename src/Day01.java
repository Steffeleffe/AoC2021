import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day01 {

    public static int part1(Report report) {
        var increaseCounter = 0;
        Integer lastDepth = report.depths.get(0);
        for (int i = 1; i < report.depths.size(); i++) {
            Integer depth = report.depths.get(i);
            if (depth > lastDepth) {
                increaseCounter++;
            }
            lastDepth = depth;
        }
        return increaseCounter;
    }

    public static int part2(Report report) {
        var increaseCounter = 0;
        Integer lastDepthSum = report.depths.get(0) + report.depths.get(1) + report.depths.get(2);
        for (int i = 1; i < report.depths.size()-2; i++) {
            Integer depthSum = report.depths.get(i) + report.depths.get(i+1) + report.depths.get(i+2);
            if (depthSum > lastDepthSum) {
                increaseCounter++;
            }
            lastDepthSum = depthSum;
        }
        return increaseCounter;
    }

    public record Report(List<Integer> depths) {
        public Report(String path) throws IOException {
            this(Files.lines(Path.of(path))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .toList());
        }

    }


}
