package program.BucketSorter;

import java.util.ArrayList;
import java.util.List;

public class Bucket {
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

    public List<Integer> getList() {
        return list;
    }

    @Override
    public String toString() {
        return "Bucket{" +
                "from=" + rangeFrom+", "+
                "to=" + rangeTo +", "+
                "list=" + list +
                '}';
    }
}
