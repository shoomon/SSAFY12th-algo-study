import java.util.Scanner;

public class BOJ2309_soomin {
    static int[] answer;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] height = new int[9];
        answer = new int[7];

        for(int i = 0; i < 9; i++) {
            height[i] = sc.nextInt();
        }

        swapping(height, 0);
//		combination(height, 0, 0, new boolean[9]);


        insertionSort(answer);
//		selectionSort(answer);
//		bubbleSort(answer);

        for(int i : answer) {
            System.out.println(i);
        }
    }
    //조합
    //방법1. 재귀 조합
    static void combination(int[] arr, int depth, int idx, boolean[] isSelected) {
        if(depth == 7) {
            int sum=0;
            for(int i = 0; i < 9; i++) {
                if(isSelected[i]) {
                    sum += arr[i];
                }
            }
            if(sum == 100) {
                int k=0;
                for(int j = 0; j < 9; j++) {
                    if(isSelected[j]) {
                        answer[k++] = arr[j];
                    }
                }
                return;
            }
        }

        for(int i = idx; i < 9; i++) {
            isSelected[i] = true;
            combination(arr, depth+1, i+1, isSelected);
            isSelected[i] = false;
        }
    }
    //방법2. 스와핑 순열
    static void swapping(int[] arr, int idx) {
        if(idx == 9) {
            int sum = 0;
            for(int i = 0; i < 7; i++) {
                sum += arr[i];
            }
//			System.out.println(sum);
            if(sum == 100) {
                for(int i = 0; i < 7; i++) {
                    answer[i] = arr[i];
                }
                return;
            }
        }

        for(int i = idx; i < 9; i++) {
            swap(arr, idx, i);
            swapping(arr, idx+1);
            swap(arr, idx, i);
        }
    }

    static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
    //Sorting
    //방법1. insertion sort
    static void insertionSort(int[] arr) {
        int len = arr.length, tmp;
        for(int i = 0; i < len; i++) {
            tmp = arr[i];
            int j=i-1;
            while(j > -1 && arr[j] > tmp) {
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = tmp;
        }
    }
    //방법2. selection sort
    static void selectionSort(int[] arr) {
        int len = arr.length, min;
        for(int i = 0; i < len-1; i++) {
            min = i;
            for(int j = i+1; j < len; j++) {
                if(arr[min] >= arr[j]) min = j;
            }
            swap(arr, i, min);
        }
    }
    //방법3. bubble sort
    static void bubbleSort(int[] arr) {
        int len = arr.length;
        for(int i = 0; i < len; i++) {
            for(int j = 0; j < len-i-1; j++) {
                if(arr[j] > arr[j+1]) swap(arr, j, j+1);
            }
        }
    }
}