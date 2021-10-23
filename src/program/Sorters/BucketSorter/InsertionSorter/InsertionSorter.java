package program.Sorters.BucketSorter.InsertionSorter;

import program.Sorters.BucketSorter.Bucket;
import program.Sorters.Sorter;

import java.util.List;

public class InsertionSorter implements Sorter {
    private Bucket bucket;
    private List<Integer> bucketNumbersList;


    public InsertionSorter(Bucket bucket) {
        this.bucket = bucket;
        this.bucketNumbersList = bucket.getList();
    }

    @Override
    public void sort() {
        int n = bucketNumbersList.size();
        int current;
        int otherIndex;
        System.out.println("Before sort bucket " +bucket);
        System.out.println("INSERTION SORT");
        for(int i = 1;i<n;i++){
            current = bucketNumbersList.get(i);
            otherIndex = i;
            while (otherIndex>0&&current < bucketNumbersList.get(otherIndex-1)){
                bucketNumbersList.set(otherIndex,bucketNumbersList.get(otherIndex-1));
                otherIndex--;
            }
            bucketNumbersList.set(otherIndex,current);

            System.out.println("iteration: "+i + ":  list: " +bucketNumbersList);
        }
    }
}
