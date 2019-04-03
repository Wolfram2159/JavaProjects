import com.company.matrix.Matrix;
import com.company.matrix.WrongSizeException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;


public class MatrixTest {
    private Matrix<Integer> firstMatrix;
    private Matrix<Integer> secondMatrix;
    private Iterator iterator;
    private Matrix<Integer> firstAdd;
    private Matrix<Integer> secondAdd;

    @BeforeEach
    void initializeComponentsForTesting() {
        firstMatrix = new Matrix<>(1, 2);
        secondMatrix = new Matrix<>(2, 1);
        iterator = firstMatrix.iterator();
        firstAdd = new Matrix(2,2);
        secondAdd = new Matrix<>(2,2);
        for (int i = 0; i < firstAdd.getHeight(); i++) {
            for (int j = 0; j < firstAdd.getWidth(); j++) {
                firstAdd.setElement(i,j,2);
                secondAdd.setElement(i,j,1);
            }
        }
    }

    @Test
    void shouldThrowWrongSizeException() {

        Assertions.assertThrows(WrongSizeException.class,
                () -> firstMatrix.add(secondMatrix, (first, second) -> first + second));
    }

    @Test
    void shouldReturnTrueBecauseMatrixHasNextElement() {
        Assertions.assertTrue(iterator.hasNext());
    }

    @Test
    void shouldReturnFalseOnMatrixHasNotNextElement() {
        while (iterator.hasNext()) {
            iterator.next();
        }
        Assertions.assertFalse(iterator.hasNext());
    }

    @Test
    void shouldThrowExceptionOnIndexIsOutOfArrayBounds() {
        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> firstMatrix.setElement(3,3,5));
    }

    @Test
    void testingMatrixAfterAddingToOtherMatrix() throws WrongSizeException{
        firstAdd.add(secondAdd,(first,second) -> first+second);
        for (int i = 0; i < firstAdd.getHeight(); i++) {
            for (int j = 0; j < firstAdd.getWidth(); j++) {
                Assertions.assertEquals(firstAdd.getElement(i,j),3);
            }
        }
    }
}