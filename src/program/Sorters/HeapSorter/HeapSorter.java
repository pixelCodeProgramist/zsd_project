package program.Sorters.HeapSorter;

import program.Sorters.Comments;
import program.Sorters.Sorter;

import java.util.ArrayList;
import java.util.List;

public class HeapSorter implements Sorter {
    private List<Integer> list;
    private List<List<Integer>> historyChangeList;
    private Comments comments;

    public HeapSorter(List<Integer> list) {
        this.list = list;
        this.historyChangeList = new ArrayList<>();
    }

    @Override
    public void sort(Comments comments) {
        this.comments = comments;
        long startTime = System.currentTimeMillis();
        int n = list.size();
        for (int i = n / 2 - 1; i >= 0; i--)
            validateMaxHeap(n, i);
        for (int i = n - 1; i > 0; i--) {
            swap(0, i);
            --n;
            validateMaxHeap(n, 0);
        }
        long elapsedTime = System.currentTimeMillis() - startTime;
        if (comments.equals(Comments.WITH_COMMENTS))
            for (int i = 0; i < historyChangeList.size(); i++) {
                System.out.println("Iteracja " + (i + 1) + ": " + historyChangeList.get(i));
            }
        System.out.println("Czas wykonania w milisekundach: " + elapsedTime);
    }

    private void validateMaxHeap(int heapSize, int parentIndex) {
        int maxIndex = parentIndex;
        int leftChild = parentIndex * 2 + 1;
        int rightChild = parentIndex * 2 + 2;

        if (leftChild < heapSize && list.get(leftChild) > list.get(maxIndex)) {
            maxIndex = leftChild;
        }
        if (rightChild < heapSize && list.get(rightChild) > list.get(maxIndex)) {
            maxIndex = rightChild;
        }
        if (maxIndex != parentIndex) {
            swap(maxIndex, parentIndex);
            if (comments.equals(Comments.WITH_COMMENTS))
                historyChangeList.add(new ArrayList<>(list));
            validateMaxHeap(heapSize, maxIndex);
        }
    }

    private void swap(int index1, int index2) {
        int temp = list.get(index1);
        list.set(index1, list.get(index2));
        list.set(index2, temp);
    }
}
