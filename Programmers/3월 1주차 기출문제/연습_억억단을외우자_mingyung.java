import java.util.*;
import java.io.*;

public class 연습_억억단을외우자_mingyung {
	public int[] solution(int e, int[] starts) {
        int[] answer = new int[starts.length];
        
        // 약수의 개수만큼 해당 숫자가 나옴을 이용
        // 일단 배열에 약수의 개수 저장하기
        int[] nums = new int[e+1];
        for (int i=1; i<=e; i++) {
            // 곱해서 e보다 작을 때까지 곱해가면서 약수 체크
            for (int j=1; j*i<=e; j++) {
                nums[i*j]++;
            }
        }
        
        // 역순으로 돌면서 최대 갯수 체크해 배열에 저장
        int[] count = new int[e+1];
        int[] max = new int[2];
        for (int i=e; i>=0; i--) {
            // 최대 갯수가 작다면 max 전체 갱신
            if (max[1] < nums[i]) max = new int[] {i, nums[i]};
            // 최대 갯수가 같다면 숫자만 갱신
            if (max[1] == nums[i]) max[0] = i;
            // 배열에 가장 많은 숫자 넣어두기
            count[i] = max[0];
        }
        
        // starts 배열 돌면서 체크
        for (int i=0; i<starts.length; i++) {
            answer[i] = count[starts[i]];
        }
        
        return answer;
    }
}
