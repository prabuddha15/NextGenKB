package com.prabuddha.poc.sorting;


/*
    Insertion sort is a simple sorting algorithm that works the way we sort playing cards in our hands.
 */
public class InsertionSort {

    public int[] doInsertionSortInAscendingOrder(int[] unsortedIntArray) {

        int arrayLength = unsortedIntArray.length;

        for (int i = 1; i < arrayLength; ++i) {
            int key = unsortedIntArray[i];
            int j = i - 1;
            for ( ; j >= 0 && unsortedIntArray[j] > key ; --j) {
                // Swap
                unsortedIntArray[j+1] = unsortedIntArray[j];
            }
            //Since j is out of the inner loop, the value of j is -1.
            unsortedIntArray[j+1] = key;
        }

        return unsortedIntArray;
    }


    public static void main(String[] args) {
        InsertionSort object= new InsertionSort();
        int[] unsortedIntArray = new int[]{2, 5, 10, 1, 8};
        System.out.println("**************** Sort Array in Ascending Order *****************");

        for( int i : object.doInsertionSortInAscendingOrder(unsortedIntArray)){
            System.out.println(i);
        }

        System.out.println("**************** Sort Array in Descending Order *****************");

//        for( int i : object.doSelectionSortInDescendingOrder(unsortedIntArray)){
//            System.out.println(i);
//        }
    }

}
