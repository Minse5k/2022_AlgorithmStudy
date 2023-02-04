import java.util.*;

class Solution {
    static int[] sale = {40, 30, 20, 10};
    static int[] answer = {0, 0};
    static int[] emoticon;
    static int[][] user;
    public int[] solution(int[][] users, int[] emoticons) {
        user = users.clone();
        emoticon = emoticons.clone();
        permutation(0, emoticons.length, new int[emoticons.length]);
        return answer;
    }
    
    public static void permutation(int cnt, int finish, int[] arr) {
        if(cnt == finish) {
            int emoCnt = 0;
            int sum = 0;
            
            for(int i=0; i<user.length; i++) {
                int userSum = 0;
                for(int j=0; j<finish; j++) {
                    if(arr[j] < user[i][0]) continue;
                    userSum += disPrice(arr[j], emoticon[j]);
                }
                if(userSum >= user[i][1]) {
                    emoCnt++;
                } else {
                    sum += userSum;
                }
            }
            if(answer[0] < emoCnt) {
                answer[0] = emoCnt;
                answer[1] = sum;
            } else if(answer[0] == emoCnt && answer[1] < sum) {
                answer[1] = sum;
            }
            return;
        }
        
        for(int i=0; i<4; i++) {
            arr[cnt] = sale[i];
            permutation(cnt + 1, finish, arr);
        }
        
    }
    
    public static int disPrice(int dis, int price) {
        return (100-dis) * price / 100;
    }
}
