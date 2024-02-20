import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class TestNetology {

    private static long suiteStartTime;
    private long testStartTime;
    Hashing hashing;
    NationalTeam nationalTeam;
    StringFilter stringFilter;

    @BeforeAll
    public static void initSuite() {
        System.out.println("Running StringTest");
        suiteStartTime = System.nanoTime();
    }

    @AfterAll
    public static void completeSuite() {
        System.out.println("StringTest complete: " + (System.nanoTime() - suiteStartTime));
    }

    @BeforeEach
    public void initTest() {
        System.out.println("Starting new nest");
        testStartTime = System.nanoTime();
        hashing = new Hashing();
        nationalTeam = new NationalTeam();
        stringFilter = new StringFilter();
    }

    @AfterEach
    public void finalizeTest() {
        System.out.println("Test complete:" + (System.nanoTime() - testStartTime));
    }

    @ParameterizedTest
    @MethodSource("sourceOne")
    void testSearch(String s, String p, List<String> expected) {
        // act
        List<String> result = hashing.search(s, p);

        // assert
        Assertions.assertLinesMatch(expected, result);
    }

    public static Stream<Arguments> sourceOne() {
        return Stream.of(
                Arguments.of("Alibaba or Alibubab? I do not know!", "b*b", Arrays.asList("3", "14", "16")),
                Arguments.of("Alibaba or Alibubab? I do not know!", "A*i", Arrays.asList("0", "11")));
    }

    @ParameterizedTest
    @MethodSource("sourceTwo")
    void testNational_team(int[][] a, int[] expected) {
        // act
        int[] result = nationalTeam.national_team(a);

        // assert
        Assertions.assertArrayEquals(expected, result);
    }

    public static Stream<Arguments> sourceTwo() {
        return Stream.of(
                Arguments.of(new int[][]{{45, 31, 24, 22, 20, 17, 14, 13, 12, 10},
                        {31, 18, 15, 12, 10, 8, 6, 4, 2, 1},
                        {51, 30, 10, 9, 8, 7, 6, 5, 2, 1}}, new int[]{51, 45, 31, 31, 30, 24, 22, 20, 18, 17}),
                Arguments.of(new int[][]{{52, 31, 24, 22, 20, 17, 14, 13, 12, 10},
                        {31, 18, 15, 12, 10, 8, 6, 4, 2, 1},
                        {51, 30, 10, 9, 8, 7, 6, 5, 2, 1}}, new int[]{52, 51, 31, 31, 30, 24, 22, 20, 18, 17}));
    }

    @ParameterizedTest
    @MethodSource("sourceThree")
    void testFilterByLength(List<String> a, int b, List<String> expected) {
        // act
        List<String> result = stringFilter.filterByLength(a, b);

        // assert
        Assertions.assertLinesMatch(expected, result);
    }

    public static Stream<Arguments> sourceThree() {
        return Stream.of(
                Arguments.of(Arrays.asList("apple", "banana", "cherry", "kiwi", "orange"), 6, Arrays.asList("banana", "cherry", "orange")),
                Arguments.of(Arrays.asList("cat", "dog", "elephant", "lion"), 3, Arrays.asList("cat", "dog")));
    }
}
