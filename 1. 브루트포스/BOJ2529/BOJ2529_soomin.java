import java.util.*;
import java.io.*;
//날짜 24.09.19
//설계 시간: 10분
//구현 시간: 40분
//메모리: 42684 kb
//시간: 296 ms
//시간복잡도 : 9! -> 순열 가능
public class Code2529 {
    static int K;
    static long min, max;
    static String[] input;
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        K = Integer.parseInt(br.readLine());
        //입력 값 공백 처리 필요
        input = br.readLine().split(" ");
        min = Long.MAX_VALUE;
        max = Long.MIN_VALUE;


        //첫번째 수 선택
        boolean[] isSelected = new boolean[10];
        int[] seq = new int[K+1];
        for(int i = 0; i < 10; i++){
            isSelected[i] = true;
            seq[0] = i;
            permutation(1, isSelected, seq, i+"");
            isSelected[i] = false;
        }
        //포멧 맞추는 데 시간 많이 씀
        bw.write(String.format("%0"+(K+1)+"d", max)+"\n"+String.format("%0"+(K+1)+"d", min));
        bw.close();
    }
    //K+1개 수 나얼
    static void permutation(int depth, boolean[] isSelected, int[] seq, String str){
        if(depth == K+1){
            long cur = Long.parseLong(str);
            min = Math.min(min, cur);
            max = Math.max(max, cur);
            return;
        }
        for(int i = 0; i < 10; i++){
            if(input[depth-1].equals("<")){
                if(seq[depth-1] < i && !isSelected[i]){
                    isSelected[i] = true;
                    seq[depth] = i;
                    permutation(depth+1, isSelected, seq, str.concat(i+""));
                    isSelected[i] = false;
                }
            }else{
                if(seq[depth-1] > i && !isSelected[i]){
                    isSelected[i] = true;
                    seq[depth] = i;
                    permutation(depth+1, isSelected, seq, str.concat(i+""));
                    isSelected[i] = false;
                }
            }
        }
    }
}
