package com.prabuddha.poc.sorting;

public class MergeSort {

    public void doMergeSort(int [] unsortedIntArray, int start, int end){

        if(start < end){
            int middle= (start+end)/2;
            doMergeSort(unsortedIntArray,start,middle);
            doMergeSort(unsortedIntArray,middle+1, end);
            merge(unsortedIntArray,start,middle,end);
        }

    }

    private void merge(int[] unsortedIntArray, int start, int middle, int end) {

        int leftArrayEndIndex=middle;
        int rightArrayStartIndex=middle+1;

        int [] leftArray = new int[middle+1];
        int [] rightArray = new int[end-middle];

        for(int i=0;i<=leftArrayEndIndex;++i){
            leftArray[i]=unsortedIntArray[i];
        }
        int rightArrayCounter=0;
        for(int i=rightArrayStartIndex;i<=end;++i){
            rightArray[rightArrayCounter++]=unsortedIntArray[i];
        }

        int i=0,j=0;
        for(int k=0;k<=end && i<leftArray.length && j<rightArray.length;++k){
            if(leftArray[i] <= rightArray[j]){
                unsortedIntArray[k]=leftArray[i++];
            }else{
                unsortedIntArray[k]=rightArray[j++];
            }
        }
        for(int x: leftArray){
            System.out.println(x);
        }
        System.out.println("******************");
        for(int x: rightArray){
            System.out.println(x);
        }
    }

    public static void main(String[] args) {
        MergeSort object=new MergeSort();
        int [] unsortedIntArray= new int[]{ 2,12, 5, 8 , 1, 3, 9, 6 };
        System.out.println("**************** Sort Array in Ascending Order *****************");
        //object.doMergeSort(unsortedIntArray,0,unsortedIntArray.length-1);
        object.merge(unsortedIntArray,0,(unsortedIntArray.length-1)/2,unsortedIntArray.length-1);
        for(int i: unsortedIntArray){
            System.out.println(i);
        }
    }

}
