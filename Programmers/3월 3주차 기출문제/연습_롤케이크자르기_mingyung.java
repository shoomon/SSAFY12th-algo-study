
import java.util.*;
import java.io.*;

public class 연습_롤케이크자르기_mingyung {
	public int solution(int[] topping) {
        int answer = 0;
        
        int len = topping.length;
        int[] ch = new int[len];
        int[] bro = new int[len];
        
        HashSet<Integer> cnt = new HashSet<>();
        // 철수 토핑종류수 배열
        for (int i=0; i<len; i++) {
        	cnt.add(topping[i]);
        	ch[i] = cnt.size();
        }
        cnt = new HashSet<>();
        // 동생 토핑종류수 배열
        for (int i=len-1; i>=0; i--) {
        	cnt.add(topping[i]);
        	bro[i] = cnt.size();
        }
        // 철수와 동생의 배열 개수 비교
        for (int i=1; i<len; i++) {
        	if (ch[i-1] == bro[i]) answer++;
        }
        
        /* 실패 코드 (85점. 양쪽 끝에서부터 비교해나가면 되겠다 코드)
        Set<Integer> ch = new HashSet<>(); // 철수가 먹는 토핑종류 수
        Set<Integer> bro = new HashSet<>(); // 동생이 먹는 토핑종류 수
        int idx1 = 0; // 토핑 종류 담은 마지막 인덱스
        int idx2 = 0;
        
        // 양쪽 끝에서 토핑 추가하면서 넣기
        int l = 0;
        int r = topping.length - 1;
        while (l <= r) {
            // 철수가 먹은 토핑의 종류가 동생보다 작거나 같으면 철수 더 챙겨주기
            if (ch.size() <= bro.size()) {
                if (ch.add(topping[l])) idx1 = l;
                l++;
            }
            // 동생이 먹은 토핑의 종류가 적으면 동생 챙겨주기
            else {
                if (bro.add(topping[r])) idx2 = r;
                r--;
            }
        }
        
        answer = ch.size() == bro.size() ? idx2-idx1 : 0;
        */
        
        return answer;
    }
}
