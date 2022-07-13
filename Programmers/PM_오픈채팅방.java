package 프로그래머스;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class PM_오픈채팅방 {
    public static void main(String[] args) throws Exception {
        System.out.println(Arrays.toString(solution(new String[]{"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"})));

    }

    public static String[] solution(String[] record) {
        ArrayList<String[]> list = new ArrayList<>();
        HashMap<String, String> hm = new HashMap<>();

        for(String str: record) {
            String cmd = str.split(" ")[0];
            String id = str.split(" ")[1];

            if(cmd.equals("Leave")) {
                list.add(new String[]{id, "님이 나갔습니다."});
            } else {
                String name = str.split(" ")[2];
                hm.put(id, name);

                if(cmd.equals("Enter")) {
                    list.add(new String[]{id, "님이 들어왔습니다."});
                }
            }
        }

        String[] ans = new String[list.size()];
        int idx=0;
        for(String[] now: list) {
            ans[idx++] = (hm.get(now[0]) + now[1]);
        }
        return ans;
    }
}
