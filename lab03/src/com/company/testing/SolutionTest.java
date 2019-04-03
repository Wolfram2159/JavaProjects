import com.company.solution.Solution;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class SolutionTest {
    private List<Integer> firstArray;
    private List<Integer> secondArray;
    private List<Integer> thirdArray;
    private float[] firstFloat;
    private float[] secondFloat;
    private float[] thirdFloat;

    @BeforeEach
    void init() {
        firstArray = new ArrayList<>(Arrays.asList(1, 3, 6, 4, 1, 2));
        secondArray = new ArrayList<>(Arrays.asList(1, 2, 3));
        thirdArray = new ArrayList<>(Arrays.asList(-1, -3));

        firstFloat = new float[]{1f, 2f, 3f, 4f, 5f, 6f};
        secondFloat = new float[]{1.2f, 1.3f, 2f, 4f};
        thirdFloat = new float[]{-1f, -2f, 1f, 3f, 2.5f};
    }

    @Test
    void testingResultFromSolutionMethod() {
        Assertions.assertEquals(Solution.solution(firstArray), 5);
        Assertions.assertEquals(Solution.solution(secondArray), 4);
        Assertions.assertEquals(Solution.solution(thirdArray), 1);

        Assertions.assertArrayEquals(Solution.solution(firstFloat, 4), new int[]{0, 2});
        Assertions.assertArrayEquals(Solution.solution(secondFloat, 6), new int[]{2, 3});
    }

    @Test
    void shouldThrowNoSuchElementException() {
        Assertions.assertThrows(NoSuchElementException.class,
                () -> Solution.solution(thirdFloat, 7));
    }
}
