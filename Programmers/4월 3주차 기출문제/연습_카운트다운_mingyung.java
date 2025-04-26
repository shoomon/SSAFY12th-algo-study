
import java.util.*;
import java.io.*;

public class 연습_카운트다운_mingyung {
	public int[] solution(int target) {
        int[] darts = new int[target + 1]; // K점을 만들기 위해 던진 다트의 수.
        int[] count = new int[target + 1]; // K점을 만들기 위해 던진 싱글 + 불의 수
        
        // k점을 만들기 위한 경우의 수 탐색
        for(int i = 1; i <= target; ++i){
            // 최소 갯수를 던져야 하므로, 최대값으로 초기화 한다.
            darts[i] = Integer.MAX_VALUE;
            
            // 각 다트는 1~20 사이의 수이다.
            // 싱글 또는 불을 최대한 많이 던질 수록 유리하므로, 더블 트리플을 먼저 계산한다.
            for(int j = 1; j <= 20; ++j){
                // Case : Double
                int doubleScore = i - (2 * j);
                // 만들고자 하는 점수는 0점 보다 커야한다.
                if(doubleScore >= 0){
                    // 던진 다트의 수가 적다면 적은쪽으로 기록한다.
                    if(darts[doubleScore] < darts[i]){
                        darts[i] = darts[doubleScore] + 1;
                        count[i] = count[doubleScore];
                    }
                }
                // Case : Triple
                int tripleScore = i - (3 * j);
                if(tripleScore >= 0){
                    if(darts[tripleScore] < darts[i]){
                        darts[i] = darts[tripleScore] + 1;
                        count[i] = count[tripleScore];
                    }
                }
                
                // Case : Single
                int singleScore = i - j ;
                if(singleScore >= 0){
                    if(darts[singleScore] < darts[i]){
                        darts[i] = darts[singleScore] + 1;
                        count[i] = count[singleScore] + 1;
                    }
                }
            }
            
            // Case : Bull
            int bullScore = i - 50;
            if(bullScore >= 0){
                if(darts[bullScore] < darts[i]){
                    darts[i] = darts[bullScore] + 1;
                    count[i] = count[bullScore] + 1;    
                }
            }
        }
        
        return new int[] {darts[target], count[target]};
    }
}
