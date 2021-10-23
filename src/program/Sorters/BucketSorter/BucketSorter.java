package program.Sorters.BucketSorter;

import program.Sorters.BucketSorter.InsertionSorter.InsertionSorter;
import program.Sorters.Sorter;

import java.util.ArrayList;
import java.util.List;


public class BucketSorter implements Sorter {
    private List<Integer> list;
    private List<Bucket> buckets;
    private int min;
    private int max;


    public BucketSorter(List<Integer> list) {
        this.list = list;
        this.buckets = new ArrayList<>();
    }

    private void findMinAndMaxNumber() {
        this.min = this.max = list.get(0);
        for (int i : list) {
            if (i < this.min) this.min = i;
            if (i > this.max) this.max = i;
        }
    }

    private double findDistance(double min,double max){
        if(min*max<0) {
            double fromLocal = Math.abs(min);
            double toLocal = Math.abs(max);
            return fromLocal+toLocal;
        }
        double fromLocal = Math.abs(min);
        double toLocal = Math.abs(max);
        return Math.abs(fromLocal-toLocal);
    }

    private void fillBuckets() {
        int n = list.size();
        findMinAndMaxNumber();
        double distance = findDistance(min,max)/n;
        for (int i = 0; i < n; i++) {
            double from = min+i*distance;
            double to = from+distance;
            Bucket bucket = new Bucket(from, to);
            for(int j=0;j<list.size();j++) {
                if (list.get(j) >= from && list.get(j) < to)
                    bucket.add(list.get(j));
                if((i+1)==n&&list.get(j) == max){
                    bucket.add(max);
                }
            }
            buckets.add(bucket);
        }
    }

    private void sortInBucket(){
        for(Bucket b: buckets){
            if(b.size()==0) continue;
            Sorter sorter = new InsertionSorter(b);
            sorter.sort();
        }
    }
    @Override
    public void sort() {
        System.out.println("BUCKET SORTER");
        System.out.println("unsorted list: " + list);
        long startTime = System.nanoTime();
        fillBuckets();
        sortInBucket();
        System.out.println(buckets);
        list.clear();
        for(Bucket b: buckets){
            if(b.size()==0) continue;
            for(int i: b.getList()) list.add(i);
        }
        long elapsedTime = System.nanoTime() - startTime;
        System.out.println("sorted list: " + list);
        System.out.println("Total execution time in milis: "
                + elapsedTime/1000000);
    }
}
