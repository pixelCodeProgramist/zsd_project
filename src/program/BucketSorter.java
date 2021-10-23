package program;

import java.util.ArrayList;
import java.util.List;

class Bucket {
    private List<Integer> list;
    private double rangeFrom;
    private double rangeTo;

    public Bucket(double rangeFrom, double rangeTo) {
        this.list = new ArrayList<>();
        this.rangeFrom = rangeFrom;
        this.rangeTo = rangeTo;
    }

    public void add(int number) {
        list.add(number);
    }

    public int size() {
        return list.size();
    }
}

public class BucketSorter implements Sorter {
    private List<Integer> list;
    private List<Integer> unsortedList;
    private List<Bucket> buckets;

    public BucketSorter(List<Integer> list) {
        this.list = list;
        this.list = new ArrayList<>(list);
        this.buckets = new ArrayList<>();
    }

    private int findMaxValue(){
        int max = list.get(0);
        for(int i: list){
            if(i>max) max=i;
        }
        return max;
    }

    private void fillBuckets() {
        int n = list.size();
        int max = findMaxValue();
        for (int i = 0; i < n; i++) {

            double from = (i * max / n);
            double to = ((i + 1) *max / n);
            Bucket bucket = new Bucket(from, to);
            if (list.get(i) >= from && list.get(i) < to)
                bucket.add(list.get(i));
            buckets.add(bucket);
        }
        System.out.println();
    }

    @Override
    public void sort() {
        fillBuckets();
    }
}
