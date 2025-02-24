
import java.util.*;
import java.io.*;

public class 연습_마법의엘리베이터_mingyung {
	public int solution(int storey) {
        int answer = 0;
        
        // 최소로 누르려면 1자리부터 차근차근 0으로 바꾸면서 누르기
        char[] num = Integer.toString(storey).toCharArray();
        int len = num.length;
        for (int i=len-1; i>0; i--) {
            // 5랑 같으면 앞자리에 따라 고민해야 됨
            if (num[i] == '5') {
                // 앞에가 5 이상이면 올리는게 나음!
                if (num[i-1] >= '5') {
                    answer += 5;
                    num[i-1]++;
                } else {
                    answer += 5;
                }
            }
            // 5보다 크면 올리기
            else if (num[i] > '5') {
                answer += 10 - (num[i] - '0');
                num[i-1]++;
            } 
            // 5보다 작으면 내리기
            else {
                answer += (num[i] - '0');
            }
        }
        
        // 마지막으로 맨 앞 숫자 처리
        if (num[0] <= '5') answer += (num[0] - '0');
        else answer += 10 - (num[0] - '0') + 1;
        
        return answer;
    }
}
