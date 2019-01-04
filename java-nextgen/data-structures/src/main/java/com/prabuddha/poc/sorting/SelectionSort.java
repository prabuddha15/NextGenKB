package com.prabuddha.poc.sorting;

/*
    The selection sort algorithm sorts an array by repeatedly finding the minimum element (considering ascending order)
    from unsorted part and putting it at the beginning.

    In Selection Sort the inner Loop should always be like below

    -> for (int j = i + 1; j < arrayLength; ++j)
 */
public class SelectionSort {

    public int[] doSelectionSortInAscendingOrder(int[] unsortedIntArray) {

        int arrayLength = unsortedIntArray.length;

       for(int i=0;i < arrayLength-1;++i) {
           int minIndex=i;

           for(int j= i+1; j< arrayLength; ++ j){
                if(unsortedIntArray[minIndex] > unsortedIntArray[j]){
                    minIndex=j;
                }
           }

           int minVar = unsortedIntArray[minIndex];
           unsortedIntArray[minIndex] = unsortedIntArray[i];
           unsortedIntArray[i] = minVar;

       }

        return unsortedIntArray;
    }

    public int[] doSelectionSortInDescendingOrder(int[] unsortedIntArray) {

        int arrayLength = unsortedIntArray.length;

        for (int i = 0; i < arrayLength - 1; ++i) {

            int maximumIndex = i;

            for (int j = i + 1; j < arrayLength; ++j) {
                if (unsortedIntArray[maximumIndex] < unsortedIntArray[j]) {
                    maximumIndex = j;
                }
            }

            int tempVar = unsortedIntArray[maximumIndex];
            unsortedIntArray[maximumIndex] = unsortedIntArray[i];
            unsortedIntArray[i] = tempVar;

        }

        return unsortedIntArray;
    }

    public static void main(String[] args) {
        SelectionSort object= new SelectionSort();
        int[] unsortedIntArray = new int[]{2, 5, 10, 1, 8};
        System.out.println("**************** Sort Array in Ascending Order *****************");

        for( int i : object.doSelectionSortInAscendingOrder(unsortedIntArray)){
            System.out.println(i);
        }

        System.out.println("**************** Sort Array in Descending Order *****************");

        for( int i : object.doSelectionSortInDescendingOrder(unsortedIntArray)){
            System.out.println(i);
        }
    }

}
