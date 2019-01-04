package com.prabuddha.poc.sorting;

public class QuickSort {

    public void doQuickSort(int [] unsortedIntArray, int start, int end){

        if(start < end){
            int pivot = partition(unsortedIntArray,start,end);
            doQuickSort(unsortedIntArray,start,pivot-1);
            doQuickSort(unsortedIntArray,pivot+1,end);
        }

    }

    private int partition(int[] unsortedIntArray, int start, int end) {
        int n = unsortedIntArray.length;
        int pivot=unsortedIntArray[end];
        int i= 0;
        for(int j=i; j< n ;++j){
            if(unsortedIntArray[j] < pivot){
                int tempVar= unsortedIntArray[j];
                unsortedIntArray[j]=unsortedIntArray[i];
                unsortedIntArray[i]=tempVar;
                i++;
            }
        }
        int tempVar= unsortedIntArray[i];
        unsortedIntArray[i]=unsortedIntArray[n-1];
        unsortedIntArray[n-1]=tempVar;
        return i;
    }

    public static void main(String[] args) {
        QuickSort object=new QuickSort();
        int [] unsortedIntArray= new int[]{ 2,12, 5, 8 , 1, 3, 9, 6 };
        System.out.println("**************** Sort Array in Ascending Order *****************");
        object.doQuickSort(unsortedIntArray,0,unsortedIntArray.length-1);
        for(int i: unsortedIntArray){
            System.out.println(i);
        }
    }

}
