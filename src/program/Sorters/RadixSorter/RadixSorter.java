package program.Sorters.RadixSorter;

import program.Main;
import program.Sorters.Comments;
import program.Sorters.Sorter;

import java.util.ArrayList;
import java.util.List;

public class RadixSorter implements Sorter {
    private List<Integer> array;
    private List<Integer> negative;
    private List<Integer> positive;

    public RadixSorter(List<Integer> array) {
        this.array = array;
        negative = new ArrayList<>();
        positive = new ArrayList<>();
    }

    @Override
    public void sort(Comments comments) {
        long time = System.currentTimeMillis();
        int max = array.get(0);
        int min = array.get(0);
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i) > 0) positive.add(array.get(i));
            else negative.add(array.get(i));
            if (array.get(i) > max) max = array.get(i);
            if (array.get(i) < min) min = array.get(i);
        }
        List<Integer> tempArray = new ArrayList<>(array);
        if (negative.size() > 0 && positive.size() > 0) sortPositiveAndNegativeNumbers(max, min, tempArray, comments);
        else if (positive.size() == 0) sortNegativeNumbers(min, tempArray, comments);
        else sortPositiveNumbers(max, tempArray, comments);
        System.out.println("Sort time = " + (System.currentTimeMillis() - time) + " ms");
        if (comments.equals(Comments.withComments))
            System.out.println(array);
    }

    private void sortPositiveAndNegativeNumbers(int max, int min, List<Integer> tempArray, Comments comments) {
        max = Math.max(max, Math.abs(min));
        int i = 0;
        for (int power = 1; max / power > 0; power *= 10) {
            countingPositiveNumberSort(positive, tempArray, power);
            if (comments.equals(Comments.withComments))
                System.out.println("Iteration " + i + " of radix sort for positive array " + positive);
            countingNegativeNumberSort(negative, tempArray, power);
            if (comments.equals(Comments.withComments))
                System.out.println("Iteration " + i++ + " of radix sort for negative array " + negative);
        }
        array.clear();
        array.addAll(negative);
        array.addAll(positive);
    }

    private void sortNegativeNumbers(int min, List<Integer> tempArray, Comments comments) {
        int i = 0;
        for (int power = 1; min / power > 0; power *= 10) {
            countingNegativeNumberSort(negative, tempArray, power);
            if (comments.equals(Comments.withComments))
                System.out.println("Iteration " + i + " of radix sort for negative array " + negative);
        }
        array.clear();
        array.addAll(negative);
    }

    private void sortPositiveNumbers(int max, List<Integer> tempArray, Comments comments) {
        int i = 0;
        for (int power = 1; max / power > 0; power *= 10) {
            countingPositiveNumberSort(positive, tempArray, power);
            if (comments.equals(Comments.withComments))
                System.out.println("Iteration " + i++ + " of radix sort for positive array " + positive);
        }
        array.clear();
        array.addAll(positive);
    }

    private void countingNegativeNumberSort(List<Integer> array, List<Integer> tempArray, int power) {
        int[] counters = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        for (int i = 0; i < array.size(); i++)
            counters[9 - (Math.abs(array.get(i)) / power) % 10]++;
        for (int i = 1; i < 10; i++)
            counters[i] += counters[i - 1];
        int position;
        for (int i = array.size() - 1; i >= 0; i--) {
            position = 9 - (Math.abs(array.get(i)) / power) % 10;
            tempArray.set(counters[position] - 1, array.get(i));
            counters[position]--;
        }
        for (int i = 0; i < array.size(); i++)
            array.set(i, tempArray.get(i));
    }

    private void countingPositiveNumberSort(List<Integer> array, List<Integer> tempArray, int power) {
        int[] counters = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        for (int i = 0; i < array.size(); i++)
            counters[(Math.abs(array.get(i)) / power) % 10]++;
        for (int i = 1; i < 10; i++)
            counters[i] += counters[i - 1];
        int position;
        for (int i = array.size() - 1; i >= 0; i--) {
            position = (Math.abs(array.get(i)) / power) % 10;
            tempArray.set(counters[position] - 1, array.get(i));
            counters[position]--;
        }
        for (int i = 0; i < array.size(); i++)
            array.set(i, tempArray.get(i));
    }
}
