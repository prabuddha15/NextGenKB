package com.prabuddha.poc.sorting;

/*


 */
public class BubbleSort {

    public int[] doBubbleSortInAscendingOrder(int[] unsortedIntArray){

        int arrayLength= unsortedIntArray.length;

        //Note: for each iteration of i, the largest number is placed at the end of the array.
        for(int i=0; i < arrayLength -1; ++i){
            for(int j = 0; j < arrayLength -1 -i ; ++j ){
                if(unsortedIntArray[j] > unsortedIntArray[j+1]){
                    int tempVar = unsortedIntArray[j];
                    unsortedIntArray[j]= unsortedIntArray[j+1];
                    unsortedIntArray[j+1] = tempVar;
                }
            }
        }

        return unsortedIntArray;
    }

    public int[] doBubbleSortInDescendingOrder(int[] unsortedIntArray){

        int arrayLength= unsortedIntArray.length;

        //Note: for each iteration of i, the largest number is placed at the first of the array.
        for(int i=0; i < arrayLength -1; ++i){
            for(int j = 0; j < arrayLength -1 -i; ++j ){
                if(unsortedIntArray[j] < unsortedIntArray[j+1]){
                    int tempVar = unsortedIntArray[j];
                    unsortedIntArray[j]= unsortedIntArray[j+1];
                    unsortedIntArray[j+1] = tempVar;
                }
            }
        }

        return unsortedIntArray;
    }

    public static void main(String[] args) {
        BubbleSort object=new BubbleSort();
        int [] unsortedIntArray= new int[]{ 5, 8 , 1, 3, 9, 6 };
        System.out.println("**************** Sort Array in Ascending Order *****************");

        for( int i : object.doBubbleSortInAscendingOrder(unsortedIntArray)){
            System.out.println(i);
        }
        System.out.println("**************** Sort Array in Descending Order *****************");

        for( int i : object.doBubbleSortInDescendingOrder(unsortedIntArray)){
            System.out.println(i);
        }
    }

}
