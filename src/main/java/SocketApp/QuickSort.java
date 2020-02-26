package SocketApp;


import java.util.ArrayList;

public class QuickSort {
    public int partition(ArrayList<Integer> arr, ArrayList<String> dict, int low, int hight) {
        int i, pivot;
        String temp;
        i = low - 1;
        pivot = arr.get(hight);
        for (int j = low; j < hight; j++) {
            if (arr.get(j) >= pivot) {
                i++;

                int swapTemp = arr.get(i);
                temp = dict.get(i);
                arr.set(i,arr.get(j));
                dict.set(i,dict.get(j));
                arr.set(j, swapTemp);
                dict.set(j, temp);

            }
        }

        int swapTemp = arr.get(i+1);
        temp = dict.get(i+1);
        arr.set(i+1,arr.get(hight));
        dict.set(i+1,dict.get(hight));
        arr.set(hight,swapTemp);
        dict.set(hight,temp);

        return i+1;

    }

    public void quickSort(ArrayList<Integer> arr, ArrayList<String> dict,int low, int hight) {
        if (low < hight) {
            int partitionIndex = partition(arr, dict, low, hight);

            quickSort(arr, dict, low, partitionIndex - 1);
            quickSort(arr, dict, partitionIndex + 1, hight);
        }
    }

    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        ArrayList<String> dict = new ArrayList<>();
        arr.add(1387);
        arr.add(120);
        arr.add(1);
        arr.add(1011);
        dict.add("dog");
        dict.add("chicken");
        dict.add("parrot");
        dict.add("cat");
        QuickSort q = new QuickSort();
        q.quickSort(arr,dict,0, arr.size() - 1);
        for(int i = 0; i < arr.size(); i++){
            System.out.println(dict.get(i) + ": " + arr.get(i));
        }
    }

}
