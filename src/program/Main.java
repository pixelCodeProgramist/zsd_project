package program;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	    List<Integer> integerList = Arrays.asList(12,-1,-5,90,21,0,20,21,-100,500,2,-1);
		integerList = new ArrayList(integerList);
	    Sorter cSorter = new BucketSorter(integerList);
	    cSorter.sort();
    }
}
