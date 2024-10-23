import java.util.*;
import java.io.*;
//날짜 24.10.05
//설계 시간: 10분
//구현 시간: 분 10:20
//메모리: 28568 kb
//시간: 344 ms
//이분탐색으로 위치 찾기
// -> N개 경우에 대해 log(N)만큼 탐색하므로
//시간복잡도는 Nlog(N)
//합이 0이 되는 수가 있으면 리턴
//인덱스는 음수이므로 해당 위치와 그 이전 위치를 비교해 리턴
public class Main {
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        long ans1=1000000000,ans2=1000000000;
        long[] nums = new long[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) nums[i] = Integer.parseInt(st.nextToken());

        for(int i = 0; i < N; i++){
            int idx = Arrays.binarySearch(nums, -nums[i]);

            if(idx == i) continue;
//            System.out.println(i+" "+idx);
            if(idx >=  0) {
                ans1 = nums[i];
                ans2 = nums[idx];
                break;
            }
            idx = -(idx+1);

            if(idx < N && idx != i){
//                System.out.println(nums[i]+" "+nums[idx]);
                if(Math.abs(nums[i]+nums[idx]) < Math.abs(ans1+ans2)){
                    ans1 = nums[i];
                    ans2 = nums[idx];
                }
            }
            if(idx > 0 && idx-1 != i){
//                System.out.println(nums[i]+" "+nums[idx-1]);
                if(Math.abs(nums[i]+nums[idx-1]) < Math.abs(ans1+ans2)){
                    ans1 = nums[i];
                    ans2 = nums[idx-1];
                }
            }
            if(i > 0){
//                System.out.println(nums[i]+" "+nums[i-1]);
                if(Math.abs(nums[i]+nums[i-1]) < Math.abs(ans1+ans2)){
                    ans1 = nums[i];
                    ans2 = nums[i-1];
                }
            }
            if(i < N-1){
//                System.out.println(nums[i]+" "+nums[i+1]);
                if(Math.abs(nums[i]+nums[i+1]) < Math.abs(ans1+ans2)){
                    ans1 = nums[i];
                    ans2 = nums[i+1];
                }
            }
//            System.out.println(nums[i]+" "+idx+" "+ans1+" "+ans2);
        }
        bw.write(ans1+" "+ans2+"\n");
        bw.close();
    }
}