package program.Sorters.CountingSorter;


import program.Sorters.Comments;
import program.Sorters.Sorter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CoutingSorter implements Sorter {
    private List<Integer> unsortedList;
    private List<Integer> list;
    private Map<Integer, Integer> numberOfNumbers;
    private int min;
    private int max;

    public CoutingSorter(List<Integer> list) {
        this.list = list;
        this.unsortedList = new ArrayList<>(list);
        this.numberOfNumbers = new LinkedHashMap<>();
    }

    private void findMinAndMaxNumber() {
        this.min = this.max = list.get(0);
        for (int i : list) {
            if (i < this.min) this.min = i;
            if (i > this.max) this.max = i;
        }
    }

    private void updateMapNumberOfNumbers() {
        for (int i : list) {
            Integer currentNumber = this.numberOfNumbers.get(i);
            currentNumber++;
            this.numberOfNumbers.put(i, currentNumber);
        }
    }

    private void fillMapNumberOfNumbers() {
        for (int i = min; i <= max; i++) this.numberOfNumbers.put(i, 0);
    }

    private void buildSortedList() {
        list.clear();
        for (Map.Entry<Integer, Integer> entry : numberOfNumbers.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                list.add(entry.getKey());
            }
        }
    }

    @Override
    public void sort(Comments comments) {
        long startTime = System.currentTimeMillis();

        this.findMinAndMaxNumber();
        this.fillMapNumberOfNumbers();
        this.updateMapNumberOfNumbers();
        this.buildSortedList();

        long elapsedTime = System.currentTimeMillis() - startTime;

        if (comments.equals(Comments.WITH_COMMENTS)) {
            System.out.println("Miniamlny element: " + min + " maksymalny element: " + max);
            System.out.println("liczba = liczba jej wystąpień: " +
                    numberOfNumbers.entrySet().stream()
                            .map(e -> e.getKey() + " = " + e.getValue())
                            .collect(Collectors.toList())
            );
        }

        System.out.println("Czas wykonania w milisekundach: " + elapsedTime);
    }
}
