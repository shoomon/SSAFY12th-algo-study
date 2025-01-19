package codingTest;

import java.util.*;
import java.util.stream.*;
import java.lang.Math;


public class 연습_광물캐기_wooseok {


    PriorityQueue<Integer> pq=new PriorityQueue<>();



    public void dfs(int[] caps,String[] minerals,int progress,int fat ){

        //--------------------  한번쓰고있는곡괭이를 계속쓰고있는지 아닌지 체크 하는 알고리즘
        int div=0;
        for(int i=0;i<3;i++){
            if(caps[i]%5==0){
                div++;
            }
        }
        if(div<2){
            return ;
        }
        //---------------------
        if(progress==minerals.length || Arrays.stream(caps).max().orElseThrow()==0 ){//끝
            // System.out.println(caps[0] + " " + caps[1] + " " + caps[2] + " " + " fat:" + fat);


            pq.add(fat);
            return;
        }






        for(int i=0;i<caps.length;i++){// i 012 pick_ dia iron stone;
            if(caps[i]==0){continue;}


            int tempPicks[]=new int[]{caps[0],caps[1],caps[2]};
            tempPicks[i]=caps[i]-1;




            switch (i){
                case 0: //다이아곡괭이일떄
                    switch (minerals[progress]){
                        case "diamond":

                            dfs(tempPicks,minerals,progress+1,fat+1);
                            break;
                        case "iron":
                            dfs(tempPicks,minerals,progress+1,fat+1);
                            break;
                        case "stone":
                            dfs(tempPicks,minerals,progress+1,fat+1);
                            break;
                    }
                    break;
                case 1://iron 곡괭이일때
                    switch (minerals[progress]){
                        case "diamond":

                            dfs(tempPicks,minerals,progress+1,fat+5);
                            break;
                        case "iron":
                            dfs(tempPicks,minerals,progress+1,fat+1);
                            break;
                        case "stone":
                            dfs(tempPicks,minerals,progress+1,fat+1);
                            break;
                    }
                    break;
                case 2:
                    switch (minerals[progress]){
                        case "diamond":

                            dfs(tempPicks,minerals,progress+1,fat+25);
                            break;
                        case "iron":
                            dfs(tempPicks,minerals,progress+1,fat+5);
                            break;
                        case "stone":
                            dfs(tempPicks,minerals,progress+1,fat+1);
                            break;
                    }
                    break;
            }


        }



    }


    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        int progress=0;

        dfs(new int[]{picks[0]*5,picks[1]*5,picks[2]*5},minerals, progress, 0) ;
        answer=pq.poll();

        return answer;

    }
}
