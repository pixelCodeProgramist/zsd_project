package program.Sorters.QuickSorter;

import program.Sorters.Comments;
import program.Sorters.Sorter;

import java.util.ArrayList;
import java.util.List;

public class QuickSorter implements Sorter {
    private List<Integer> list;
    private List<Integer> copyList;
    private List<List<Integer>> steps;
    private Comments comments;

    public QuickSorter(List<Integer> list) {
        this.list = list;
        this.copyList = new ArrayList<>(list);
        this.steps = new ArrayList<>();
    }

    public void quickSort(int low, int high) {
        if (low < high) {
            int pi = partition(low, high);
            if (comments.equals(Comments.withComments))
                steps.add(new ArrayList<>(list));
            quickSort(low, pi - 1);
            quickSort(pi + 1, high);
        }
    }

    private int partition(int low, int high) {
        int pivot = list.get(high);
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (list.get(j) <= pivot) {
                i++;
                int temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
            }
        }
        int temp = list.get(i + 1);
        list.set(i + 1, list.get(high));
        list.set(high, temp);
        return (i + 1);
    }

    @Override
    public void sort(Comments comments) {
        this.comments = comments;
        System.out.println("QUICKSORTER");
        if (comments.equals(Comments.withComments))
            System.out.println("unsorted list: " + list);
        long startTime = System.nanoTime();
        quickSort(0, list.size() - 1);
        long elapsedTime = System.nanoTime() - startTime;
        if (comments.equals(Comments.withComments)) {
            for (int i = 1; i <= steps.size(); i++) {
                System.out.println("ITERACJA " + i + ": " + steps.get(i - 1));
            }
            System.out.println("sorted list: " + list);
        }
        System.out.println("Total execution time in milis: "
                + elapsedTime / 1000000);

    }
}
