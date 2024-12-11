package waps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int n, k;
    static int arr[][];
    static int b[];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //범위가 매우 크므로 이분탐색 일듯하다
        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
        //테케 답은 나오는데...
        //메모리 초과 => arr배열 받지 말고 바로 b에 넣어도 메모리 초과
        //gold1 이니 뭔가 더 있을 듯 한데
//        arr = new int[n+1][n+1]; //1부터 시작하므로
        b = new int[n * n+1];//1부터 시작하므로
//        for(int i = 1; i < n+1; i++) {
//            for(int j = 1; j < n+1; j++) {
//                arr[i][j] = i * j;
//            }
//        }
        //어떻게 1차원 배열로 옮기지?
        int idx = 1;
        for(int i = 1; i < n+1; i++) {
            for(int j = 1; j < n+1; j++) {
                b[idx++] = i * j;
            }
        }
        //정렬 1부터 주의
        Arrays.sort(b, 1, b.length);
        
        int left = 0;
        int right = b.length-1;
        int mid = (left+right) / 2; 
        devide(left, right, mid);
        System.out.println(b[k]);
    }
    //이분탐색
    static int devide(int left, int right, int mid) {

        while(left <= right) {
            //같은 경우
            if(k == mid)return b[mid];
                //미드보다 왼쪽에 있는 경우
            else if(k < mid) {
                right = mid;
                mid = (left+mid)/2;
                devide(left, right, mid);
            }else {
                left = mid;
                mid = (left+right)/2;
                devide(left, right, mid);
                //미드보다 오른쪽인 경우
            }
        }
        return -1;
    }
}
