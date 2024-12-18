import java.util.*;
import java.io.*;

class KAKAO_주사위고르기_mingyung {
	static int N, win, aWin;
    static int[] answer, aDice, bDice;
    List<Integer> aSumComb, bSumComb;
    boolean[] vis = new boolean[15];
    
    // 이분탐색
    public int lowerBound(List<Integer> arr, int t) {
        int s = 0;
        int e = arr.size();
        while (s<e) {
            Integer mid = (s+e) /2;
            if (arr.get(mid) < t) s = mid+1;
            else e = mid;
        }
        return e;
    }
    
    public void game(List<Integer> aSumComb, List<Integer> bSumComb) {
        // 이분탐색 위해 정렬
        Collections.sort(bSumComb);
        // A의 합보다 작은 B의 합 개수 구해서 aWin에 더함
        for (Integer i : aSumComb) {
            aWin = aWin + lowerBound(bSumComb, i);
        }
    }
    
    // 주사위 굴리기
    public void rollDice(int n, int sum, int[] nowDice, int[][] dice, List<Integer> sumComb) {
        if (n == N/2) {
            // 합 리스트에 담기
            sumComb.add(sum);
            return;
        }
        for (int i=0; i<6; i++) {
            rollDice(n+1, sum + dice[nowDice[n]][i], nowDice, dice, sumComb);
        }
    }
    
    public void expectGame(int[][] dice) {
        aDice = new int[N/2];
        bDice = new int[N/2];
        int aSize = 0, bSize = 0;
        
        // vis에서 구한 A, B의 주사위 조합 배열에 담기
        for (int i=0; i<N; i++) {
            if (vis[i]) aDice[aSize++] = i;
            else bDice[bSize++] = i;
        }
        
        // A, B 주사위 결과의 합을 담을 리스트
        aSumComb = new ArrayList<>(10000);
        bSumComb = new ArrayList<>(10000);
        
        // A, B의 주사위 결과의 합 따로 구함
        rollDice(0, 0, aDice, dice, aSumComb);
        rollDice(0, 0, bDice, dice, bSumComb);
        
        // A가 이기는 경우의 수 구함
        game(aSumComb, bSumComb);
        
        return;
    }
    
    // 주사위 조합 구하기
    public void combination(int n, int k, int[][] dice) {
        if (n== N/2) {
            aWin = 0;
            expectGame(dice);
            if (aWin > win) {
                for (int i=0; i<N/2; i++) answer[i] = aDice[i]+1;
                win = aWin;
            }
            return;
        }
        
        for (int i=k; i<N; i++) {
            vis[i] = true;
            combination(n+1, i+1, dice);
            vis[i] = false;
        }
    }
    
    public int[] solution(int[][] dice) {        
        N = dice.length;
        answer = new int[N/2];
        win = 0;
        combination(0, 0, dice);
        
        return answer;
    }
    
	/*
	static int[] diceNum, sel; // 뽑은 주사위 배열
    static int n; // 뽑아야하는 수
    
    public int[] solution(int[][] dice) {
        int[] answer = {};
        diceNum = new int[dice.length];
        n = dice.length/2;
        sel = new int[n];
        // diceNum 구성
        for (int i=0; i<dice.length; i++) {
            diceNum[i] = i+1;
        }
        
        conb(0, 0);
        
        return answer;
    }
    
    // 주사위 조합 구하기
    static void comb(int idx, int sidx) {
        if (sidx == n) {
            // 조합 구하면 계산하기
            // 해당 주사위에서 나올 수 있는 합 구하기 : 뽑은 주사위 갯수만큼 반복
            int[] a = new int[Math.pow(6, n)]
            for (int i=0; i<n; i++) {
                for (int j=0; j<6; j++) { // 
                    a[i*(j+1)] += dice[]
                }
            }
            int[] result = new int[3]; // 승, 무, 패 저장할 배열
            
        }
        if (idx == diceNum.length) return;
        
        sel[sidx] = diceNum[idx];
        comb(idx+1, sidx+1);
        comb(idx+1, sidx);
    }
    */
}