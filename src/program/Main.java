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

    private static Sorter cSorter;

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
                System.out.println("HEAP SORTER");
                break;
            case 2:
                cSorter = new RadixSorter(integerList);
                System.out.println("RADIX SORTER");
                break;
            case 3:
                cSorter = new BucketSorter(integerList);
                System.out.println("BUCKET SORTER");
                break;
            case 4:
                cSorter = new CoutingSorter(integerList);
                System.out.println("COUNTING SORTER");
                break;
            default:
                cSorter = new QuickSorter(integerList);
                System.out.println("QUICK SORTER");
                break;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        List<Integer> integerListOriginal = generateSampleArrayListToSort(scan);

        while (true) {
            List<Integer> integerList = new ArrayList<>(integerListOriginal);
            System.out.println("Wybierz rodzaj sortowania:\n1-Heap Sort\n2-Radix Sort\n3-Bucket Sort\n4-Counting Sort\n5-Quick Sort\n0-Zakończenie programu");
            int type = getNumberFromStandardInput(scan);
            if (type == 0) return;
            sortMenu(type, integerList);
            if (integerList.size() < 12) {
                System.out.println("Nieposortowana lista: " + integerList);
                cSorter.sort(Comments.WITH_COMMENTS);
                System.out.println("Posortowana lista: " + integerList);
            } else cSorter.sort(Comments.WITHOUT_COMMENTS);
            System.out.println();
        }
    }

    private static List<Integer> generateSampleArrayListToSort(Scanner scan) {
        System.out.println("Określ minimalną wartość listy:");
        int min = getNumberFromStandardInput(scan);
        System.out.println("Określ maksymalną wartość listy:");
        int max = getNumberFromStandardInput(scan);
        while (max <= min) {
            System.out.println("Maksymalna wartość musi być większa od minimalnej:");
            max = getNumberFromStandardInput(scan);
        }
        System.out.println("Określ rozmiar listy:");
        int size = getNumberFromStandardInput(scan);
        while (size < 2) {
            System.out.println("Za mała lista proszę podać większą:");
            size = getNumberFromStandardInput(scan);
        }
        return randomList(min, max, size);
    }

    private static int getNumberFromStandardInput(Scanner scanner) {
        int number = 5;
        try {
            number = scanner.nextInt();
        } catch (InputMismatchException ex) {
            System.out.println("Podana liczba jest nieprawidłowa. Domyślna liczba wynosi 5");
            scanner.nextLine();
        }
        return number;
    }
}
