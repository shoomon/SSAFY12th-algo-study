import java.util.*;
import java.io.*;

public class 연습_혼자서하는틱택토_mingyung {
	public int solution(String[] board) {
        int answer = 1;
        
        // 선공이 O, 후공이 X
        // => O가 한 개 더 많거나 개수가 같아야 함.
        // 3개가 같은 표시인 게 두 개 있으면 종료 후 진행한 것
        int oCnt = 0;
        int xCnt = 0;
        
        // 열 우선 순회로 세로 체크 위해 맵 변경
        char[][] map = new char[3][3];
        
        // 행 돌면서 맵으로 바꿔주고, O, X 갯수 카운트
        for (int r=0; r<3; r++) {
            for (int c=0; c<3; c++) {
                map[r][c] = board[r].charAt(c);
                if (map[r][c] == 'O') oCnt++;
                else if (map[r][c] == 'X') xCnt++;
            }
        }
        
        // 일단 X가 많거나 차이가 2 이상이면 0 반환
        if (xCnt>oCnt || Math.abs(xCnt-oCnt)>=2) return 0;
        
        // 한 줄 생긴 것 체크
        int oLine = 0;
        int xLine = 0;
        for (int i=0; i<3; i++) {
            // 행
            if (map[i][0] == map[i][1] && map[i][1] == map[i][2]) {
                if (map[i][0] == 'O') oLine++;
                else if (map[i][0] == 'X') xLine++;
            }
            // 열
            if (map[0][i] == map[1][i] && map[1][i] == map[2][i]) {
                if (map[0][i] == 'O') oLine++;
                else if (map[0][i] == 'X') xLine++;
            }
        }
        // 대각선 체크
        if (map[0][0] == map[1][1] && map[1][1] == map[2][2]) {
            if (map[0][0] == 'O') oLine++;
            else if (map[0][0] == 'X') xLine++;
        }
        if (map[0][2] == map[1][1] && map[1][1] == map[2][0]) {
            if (map[2][0] == 'O') oLine++;
            else if (map[2][0] == 'X') xLine++;
        }
        
        // // 만약 두 줄 이상 생겼으면 요상하다
        // if (oLine+xLine>=2) return 0;
        // 마지막 수에 가운데 O를 넣는 경우, 두 줄로 이기는 경우가 생김
        // 승리에 따라 개수체크 해야 된다..
        if (oLine == 1 && oCnt != xCnt+1) return 0; 
        if (xLine == 1 && oCnt != xCnt) return 0; 
        
        return answer;
    }
}
