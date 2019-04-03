package com.company.matrix;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Matrix<T> implements Iterable<T> {
    private int width, height;
    private List<List<T>> elements;
    //private Addition<T> addition;

    public Matrix(int width, int height) {
        this.width = width;
        this.height = height;
        //this.addition = addition;
        elements = new ArrayList<>(height);
        for (int i = 0; i < height; i++) {
            elements.add(new ArrayList<>(width));
            for (int j = 0; j < width; j++) {
                elements.get(i).add(j, null);
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public T getElement(int row, int column) {
        return elements.get(row).get(column);
    }

    public void setElement(int row, int column, T value) {
        if (row >= getHeight() || column >= getWidth()) {
            throw new IndexOutOfBoundsException();
        }
        elements.get(row).set(column, value);
    }

    public void add(Matrix<T> matrixToAdd, Addition<T> addition)  throws WrongSizeException{
        if (width != matrixToAdd.getWidth() || height != matrixToAdd.getHeight()) {
            throw new WrongSizeException();
        }
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                T first = elements.get(i).get(j);
                T second = matrixToAdd.elements.get(i).get(j);
                //Addition<T> adding = addition;
                T sum = addition.add(first, second);
                elements.get(i).set(j, sum);
            }
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new MatrixIterator();
    }

    public class MatrixIterator implements Iterator<T> {
        private int row = 0;
        private int column = -1;

        @Override
        public boolean hasNext() {
            return (row + 1 < height || column + 1 < width);
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            column++;
            if (column >= width) {
                column = 0;
                row++;
            }
            return elements.get(row).get(column);
        }
    }
}
