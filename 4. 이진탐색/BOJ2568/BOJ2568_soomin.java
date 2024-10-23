import java.util.*;
import java.io.*;
//날짜 24.10.13
//설계 시간: 분
//구현 시간: 60분
//메모리: 58972 kb
//시간: 604 ms
public class Main {
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[][] line = new int[N][2];
        ArrayList<Integer> lis = new ArrayList<>(N); //i자리 lis 중 최솟값 저장
        int[] log = new int[N];
//        ArrayList<Integer> consist = new ArrayList<>(N);

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine().trim());

            line[i][0] = Integer.parseInt(st.nextToken());
            line[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(line, (o1, o2) -> o1[0]-o2[0]);
//        for(int i = 0; i < N; i++)
//        {
//            System.out.println(line[i][0]+" "+line[i][1]);
//        }
        for(int i = 0; i < N; i++){
            int idx = Collections.binarySearch(lis, line[i][1]);

            if(idx < 0) idx = -(idx+1);

            if(idx == lis.size()){
                lis.add(line[i][1]);
            }else{
                lis.set(idx, line[i][1]);
            }
            log[i] = idx;
        }

//        for(int i : log) System.out.print(i+" ");
//        System.out.println();

        int size = lis.size();
        List<Integer> toPrint = new LinkedList<>();
        for(int i = N-1; i > -1; i--){
            if(log[i] == size-1){
//                consist.add(0,line[i][0]);
                size--;
            }else{
                toPrint.add(0,line[i][0]);
            }
        }
        bw.write(toPrint.size()+"\n");
        for(int i: toPrint) bw.write(i+"\n");
//        for(int i : consist) System.out.print(i+" ");

        bw.close();

    }
}
