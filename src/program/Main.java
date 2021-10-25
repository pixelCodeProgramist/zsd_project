package program;

import program.Sorters.BucketSorter.BucketSorter;
import program.Sorters.Comments;
import program.Sorters.CountingSorter.CoutingSorter;
import program.Sorters.HeapSorter.HeapSorter;
import program.Sorters.QuickSorter.QuickSorter;
import program.Sorters.RadixSorter.RadixSorter;
import program.Sorters.Sorter;

import java.util.*;

public class Main {

    static Sorter cSorter;

    public static List<Integer> randomList(int min, int max, int size) {
        List<Integer> integerList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            integerList.add((int) Math.floor(Math.random() * (max - min + 1) + min));
        }
        return integerList;
    }

    public static void sortMenu(int number, List<Integer> integerList) {
        switch (number) {
            case 1:
                cSorter = new HeapSorter(integerList);
                break;
            case 2:
                cSorter = new RadixSorter(integerList);
                break;
            case 3:
                cSorter = new BucketSorter(integerList);
                break;
            case 4:
                cSorter = new CoutingSorter(integerList);
                break;
            case 5:
                cSorter = new QuickSorter(integerList);
                break;
        }

    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Określ minimalną wartość listy:");
        int min = scan.nextInt();
        System.out.println("Określ maksymalną wartość listy:");
        int max = scan.nextInt();
        System.out.println("Określ rozmiar listy:");
        int size = scan.nextInt();
        if (size < 2) {
            System.out.println("Za mała lista proszę podać większą:");
            size = scan.nextInt();
        }
        List<Integer> integerListOriginal = randomList(min, max, size);

        while (true) {
            List<Integer> integerList = new ArrayList<>(integerListOriginal);
            System.out.println("Wybierz rodzaj sortowania:\n1-Heap Sort\n2-Radix Sort\n3-Bucket Sort\n4-Counting Sort\n5-Quick Sort\n0-Zakończenie programu");
            int type = scan.nextInt();
            if (type == 0)
                return;
            sortMenu(type, integerList);
            if (size < 12)
                cSorter.sort(Comments.withComments);
            else
                cSorter.sort(Comments.withoutComments);
        }


    }
}
