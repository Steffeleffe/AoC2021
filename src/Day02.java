import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day02 {

    public static int part1(Commands commands) {
        Integer forwardSum = commands.commands.stream()
                .filter(c -> c.direction == Direction.forward)
                .map(c -> c.amount)
                .reduce(Integer::sum).get();
        Integer downSum = commands.commands.stream()
                .filter(c -> c.direction == Direction.down)
                .map(c -> c.amount)
                .reduce(Integer::sum).get();
        Integer upSum = commands.commands.stream()
                .filter(c -> c.direction == Direction.up)
                .map(c -> c.amount)
                .reduce(Integer::sum).get();
        return forwardSum * (downSum - upSum);
    }

    public static int part2(Commands commands) {
        var currentAim = 0;
        var currentDepth = 0;
        var currentHorizontalPosition = 0;
        for (Commands.Command command : commands.commands) {
            switch (command.direction) {
                case forward -> {
                    currentHorizontalPosition += command.amount;
                    currentDepth += currentAim * command.amount;
                }
                case down -> currentAim += command.amount;
                case up -> currentAim -= command.amount;
            }
        }

        return currentHorizontalPosition * currentDepth;
    }

    public record Commands(List<Command> commands) {
        public Commands(String path) throws IOException {
            this(Files.lines(Path.of(path))
                    .map(String::trim)
                    .map(Command::new)
                    .toList());
        }

        public record Command(Direction direction, Integer amount) {
            public Command(String string) {
                this(Direction.valueOf(string.split(" ")[0]), Integer.parseInt(string.split(" ")[1]));
            }
        }
    }

    public enum Direction {
        forward, down, up
    }

}
