import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;
import java.util.function.BiFunction;

public class Day04 {

    public static long part1(BingoGame game) {
        for (Integer numberDraw : game.numberDraws) {
            for (BingoBoard board : game.boards) {
                board.mark(numberDraw);
                if (board.hasBingo()) {
                    return (long) numberDraw * board.sumOfUnmarked();
                }
            }
        }

        throw new IllegalStateException("Found no winning board");
    }

    public static int part2(BingoGame game) {

        for (Integer numberDraw : game.numberDraws) {
            for (BingoBoard board : game.boards) {
                board.mark(numberDraw);
                if (board.hasBingo() && game.allHasBingo()) {
                    return numberDraw * board.sumOfUnmarked();
                }
            }
        }
        throw new IllegalStateException("Found no winning board");
    }

    public record BingoGame(List<Integer> numberDraws, List<BingoBoard> boards) {
        public BingoGame(String path) throws IOException {
            this(readNumberDrawsFromFile(path), readBingoBoardsFromFile(path));
        }

        private static List<BingoBoard> readBingoBoardsFromFile(String path) throws IOException {
            List<BingoBoard> boards = new ArrayList<>();
            List<String> strings = Files.readAllLines(Path.of(path));
            for (int i = 2; i<strings.size(); i++) {
                BingoBoardNumber[] row1 = Arrays.stream(strings.get(i++).trim().split("\s+")).map(Integer::parseInt).map(BingoBoardNumber::new).toArray(BingoBoardNumber[]::new);
                BingoBoardNumber[] row2 = Arrays.stream(strings.get(i++).trim().split("\s+")).map(Integer::parseInt).map(BingoBoardNumber::new).toArray(BingoBoardNumber[]::new);
                BingoBoardNumber[] row3 = Arrays.stream(strings.get(i++).trim().split("\s+")).map(Integer::parseInt).map(BingoBoardNumber::new).toArray(BingoBoardNumber[]::new);
                BingoBoardNumber[] row4 = Arrays.stream(strings.get(i++).trim().split("\s+")).map(Integer::parseInt).map(BingoBoardNumber::new).toArray(BingoBoardNumber[]::new);
                BingoBoardNumber[] row5 = Arrays.stream(strings.get(i++).trim().split("\s+")).map(Integer::parseInt).map(BingoBoardNumber::new).toArray(BingoBoardNumber[]::new);
                boards.add(new BingoBoard(new BingoBoardNumber[][]{row1, row2, row3, row4, row5}));
            }
            return boards;
        }

        private static List<Integer> readNumberDrawsFromFile(String path) throws IOException {
            return Arrays.stream(Files.readAllLines(Path.of(path)).get(0).split(","))
                    .map(Integer::parseInt)
                    .toList();
        }

        public boolean allHasBingo() {
            return boards.stream().allMatch(BingoBoard::hasBingo);
        }
    }

    public record BingoBoard(BingoBoardNumber[][] numbers) {
        public void mark(Integer numberDraw) {
            for (int i = 0; i < numbers.length; i++) {
                for (int j = 0; j < numbers.length; j++) {
                    if (numbers[i][j].number.equals(numberDraw)) {
                        numbers[i][j] = new BingoBoardNumber(numberDraw, true);
                    }
                }
            }
        }

        public boolean hasBingo() {
            for (int i = 0; i < numbers.length; i++) {
                int count = 0;
                for (int j = 0; j < numbers.length; j++) {
                    if (numbers[i][j].marked) {
                        count++;
                    }
                    if (count == 5) {
                        return true;
                    }
                }
            }

            for (int i = 0; i < numbers.length; i++) {
                int count = 0;
                for (int j = 0; j < numbers.length; j++) {
                    if (numbers[j][i].marked) {
                        count++;
                    }
                    if (count == 5) {
                        return true;
                    }
                }
            }

            return false;
        }

        public int sumOfUnmarked() {
            int sum = 0;
            for (int i = 0; i < numbers.length; i++) {
                for (int j = 0; j < numbers.length; j++) {
                    if (!numbers[i][j].marked) {
                        sum+=numbers[i][j].number;
                    }
                }
            }
            return sum;
        }
    }

    public record BingoBoardNumber(Integer number, Boolean marked) {
        public BingoBoardNumber(Integer number) {
            this(number, false);
        }
    }

}
