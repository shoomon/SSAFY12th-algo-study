package PRO_3월_4주차;

class KAKAO_양궁대회_hyunjin {
    static int[] answer = {-1};
    static int max = Integer.MIN_VALUE; // 점수 차이 중 최대값
    
    public int[] solution(int n, int[] info) {

        dfs(0, n, info, new int[11]);
        
        return answer;
    }
    
    public void dfs(int idx, int arrowLeft, int[] apeachInfo, int[] lionCurrentInfo ){
        
        // 11개의 점수를 모두 탐색 -> 점수 계산하기
        if(idx == 11){
            // 남은 화살(arrowLeft)이 있다면, 모두 0점에 주기
            if(arrowLeft > 0){
                lionCurrentInfo[10] += arrowLeft;
            }
            
            // 점수 계산하기
            int apeachScore = 0;
            int lionScore = 0;
            
            for(int i =0 ; i<11; i++){
                // 둘 다 해당 점수 과녁에 쏘지 않은 경우
                if(apeachInfo[i] == 0 && lionCurrentInfo[i] == 0) continue;
                // 라이언이 어피치보다 더 많이 쏘은 경우, 라이언 점수 가져감
                if(lionCurrentInfo[i] > apeachInfo[i]){
                    lionScore += (10 - i);
                // 그 외, 어피치가 더 많이 쏘거나, 동일한 갯수로 쏠 경우, 어피치 점수 가져감   
                }else{
                    apeachScore += (10 - i);
                }
            }
            
            // 라이언이 어피치를 이긴 경우
            if(lionScore > apeachScore){
                int diff = lionScore - apeachScore; // 둘의 점수 차이
                
                if(diff > max){
                    max = diff;
                    answer = lionCurrentInfo.clone(); 
                }else if (diff == max){
                    for(int i = 10; i>= 0; i--){
                        if (lionCurrentInfo[i] > answer[i]) {
                            answer = lionCurrentInfo.clone();
                            break;
                        } else if (lionCurrentInfo[i] < answer[i]) {
                            break;
                        }
                    }
                }
            }
            
            if(arrowLeft > 0){
                lionCurrentInfo[10] -= arrowLeft;
            }
            
            return;
        }
        
        // 1. 라이언이 점수를 가져갈 수 있게 쏘기
        if(arrowLeft > apeachInfo[idx]){
            lionCurrentInfo[idx] = apeachInfo[idx] + 1;
            dfs(idx + 1, arrowLeft - lionCurrentInfo[idx], apeachInfo, lionCurrentInfo);
            lionCurrentInfo[idx] = 0; // 원상 복구(백트래킹)
        }
        
        // 2. 어피치한테 점수 주기
        dfs(idx + 1, arrowLeft, apeachInfo, lionCurrentInfo);
        
    }
}