import java.util.*;
import java.io.*;

class Solution {
		class Assignment{
			String name;
			int startTime;
			int playTime;
		}
		
	    public String[] solution(String[][] plans) {
	        String[] answer = new String[plans.length];
	        
	        // 시작시간 순서대로 정
	        Arrays.sort(plans, (String[] o1, String[] o2)->{
	        	int o1Time , o2Time;
	        	String[] timeString = o1[1].split(":");
	        	o1Time = Integer.parseInt(timeString[0]) * 60 + Integer.parseInt(timeString[1]);
	        	timeString = o2[1].split(":");
	        	o2Time = Integer.parseInt(timeString[0]) * 60 + Integer.parseInt(timeString[1]);
	        	return Integer.compare(o1Time, o2Time);
	        });
	        
	        // 현재 작업중 혹은 작업 대기 중인 stack.
	        Stack<Assignment> stack = new Stack<>();
	        
	        int ansIdx = 0;
	        for (int i = 0; i < plans.length; i++) {
	        	Assignment assign = new Assignment();
	        	assign.name = plans[i][0];
	        	String[] timeString = plans[i][1].split(":");
	        	assign.startTime = Integer.parseInt(timeString[0]) * 60 + Integer.parseInt(timeString[1]);
	        	assign.playTime = Integer.parseInt(plans[i][2]);
	        	// 비어있으면 작업 대기줄에 입력
	        	if (stack.isEmpty()) {
	        		stack.push(assign);
	        	}else {
	        		// 마지막 작업의 시작 시점을 통해서 현재 작업과 비교했을때 여전히 작업중일 것인지 파악
	        		int startTime = stack.peek().startTime;
	        		// 뒤에 새로 들어오는 시작시간보다 먼저 끝나는 작업이면 먼저 끝내놓을 것.
	        		while (startTime + stack.peek().playTime <= assign.startTime ) {
	        			Assignment popped = stack.pop();
	        			answer[ansIdx++] = popped.name;
	        			startTime += popped.playTime;
	        			// 스택이 빈 경우 빠져나간다.
	        			if (stack.isEmpty()) break;
	        		}
	        		// 모든 진행중이던 과제가 끝난 경우 과제에 대한 진행 값을 추가.
	        		if (stack.isEmpty()) {
	        			stack.push(assign);
	        		// 진행중이던 과제가 남아있는 경우, 해당 과제의 진행도를 그대로 가지고 멈춤.
	        		}else {
	        			// 작업을 중단하는 시점을 수정한다.
	        			stack.peek().playTime -= assign.startTime - startTime;
	        			stack.push(assign);
	        		}
	        	}
	        }
	        // 마지막에 진행중이던 과제들을 멈춘 순서의 역순으로 완료시킨다.
	        while(!stack.isEmpty()) {
	        	Assignment popped = stack.pop();
	        	answer[ansIdx++] = popped.name;
	        }
	        
	        return answer;
	    }
	}