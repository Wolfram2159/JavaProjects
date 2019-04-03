import com.company.Sorting.BubbleSort;
import com.company.Sorting.InsertionSort;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Random;

public class SortingTest {
    static int[] arr;
    @BeforeAll
    static void init(){
        arr = new int[1000];
        for (int i = 0; i < 1000; i++) {
            arr[i] = new Random().nextInt(1000);
        }
    }

    @Test
    void testingIfSortingIsNotTooLong(){
        Assertions.assertTimeout(Duration.ofMillis(200), ()-> {BubbleSort.bubbleSort(arr);});
        Assertions.assertTimeout(Duration.ofMillis(100), ()-> {InsertionSort.sort(arr);});
    }
}
