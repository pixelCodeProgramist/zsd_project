package program;

import program.Sorters.HeapSorter.HeapSorter;
import program.Sorters.Sorter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	    List<Integer> integerList = Arrays.asList(9, 7, 4, 3, 6, 5, 1, 2, 8, 11);
		integerList = new ArrayList(integerList);
	    Sorter cSorter = new HeapSorter(integerList);
	    cSorter.sort();
    }
}
