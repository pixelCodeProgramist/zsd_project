package program.Sorters.HeapSorter;

import program.Sorters.Sorter;

import java.util.ArrayList;
import java.util.List;

public class HeapSorter implements Sorter {
    private List<Integer> list;
    private List<List<Integer>> historyChangeList;

    public HeapSorter(List<Integer> list) {
        this.list = list;
        this.historyChangeList = new ArrayList<>();
    }

    @Override
    public void sort() {
        System.out.println("HEAP SORT");
        System.out.println("unsorted list: " + list);
        long startTime = System.nanoTime();
        int n = list.size();
        for(int i = n / 2 - 1; i >= 0; i--){
            validateMaxHeap(n, i);
        }
        for(int i = n - 1; i > 0; i--){
            swap(0, i);
            --n;
            validateMaxHeap(n, 0);
        }
        long elapsedTime = System.nanoTime() - startTime;
        for(int i=0;i<historyChangeList.size();i++){
            System.out.println("ITERACJA "+(i+1)+": "+historyChangeList.get(i));
        }
        System.out.println("sorted list: " + list);
        System.out.println("Total execution time in milis: "
                + elapsedTime/1000000);


    }

    private void validateMaxHeap(int heapSize, int parentIndex) {
        int maxIndex = parentIndex;
        int leftChild = parentIndex * 2 + 1;
        int rightChild = parentIndex * 2 + 2;

        if(leftChild < heapSize && list.get(leftChild) > list.get(maxIndex)){
            maxIndex = leftChild;
        }
        if(rightChild < heapSize && list.get(rightChild) > list.get(maxIndex)){
            maxIndex = rightChild;
        }
        if(maxIndex != parentIndex){
            swap(maxIndex, parentIndex);
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
