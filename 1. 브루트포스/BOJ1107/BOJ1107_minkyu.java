import java.util.Scanner;
/* 리모컨
수빈이는 TV를 보고 있다. 수빈이는 채널을 돌리려고 했지만, 버튼을 너무 세게 누르는 바람에, 일부 숫자 버튼이 고장났다.

리모컨에는 버튼이 0부터 9까지 숫자, +와 -가 있다. +를 누르면 현재 보고있는 채널에서 +1된 채널로 이동하고, -를 누르면 -1된 채널로 이동한다. 채널 0에서 -를 누른 경우에는 채널이 변하지 않고, 채널은 무한대 만큼 있다.

수빈이가 지금 이동하려고 하는 채널은 N이다. 어떤 버튼이 고장났는지 주어졌을 때, 채널 N으로 이동하기 위해서 버튼을 최소 몇 번 눌러야하는지 구하는 프로그램을 작성하시오.

수빈이가 지금 보고 있는 채널은 100번이다.
*/
public class Main {
	static int channel = 0; 
	static int brokenCnt = 0;
	static int[] brokenButton = {};
	static int min = 0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		channel = sc.nextInt();
		brokenCnt = sc.nextInt();
		brokenButton = new int[brokenCnt];
		for (int i = 0; i < brokenCnt; i++)
			brokenButton[i] = sc.nextInt();
		
		min = Math.abs(channel - 100);
		
		checkChannelCnt(0, 0);
		System.out.println(min);
	}
	
	public static void checkChannelCnt(int curNum, int cnt) {
		if (cnt > 0) {
			// 현재 보고 싶은 채널이 넘어간 경우
			if (curNum > channel) {
				if (min > Math.abs(channel - curNum) + cnt)
					min = Math.abs(channel - curNum) + cnt;
				return;
			}
			// 현재 번호 기준으로 몇번 버튼을 눌러야 하는지?
			if (min > channel - curNum + cnt)
				min = channel - curNum + cnt;
		}
		
		main : for (int i = 0; i < 10; i++) {
			if (curNum == 0 && i == 0 && cnt == 1)
				continue main;
			
			// 버튼이 부서진 경우 넘어가기
			for (int j = 0; j < brokenCnt; j++) {
				if (brokenButton[j] == i)
					continue main;
			}
			
			checkChannelCnt(curNum * 10 + i, cnt + 1);
		}
	}
}