import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int[] kyuArr, inNum, inArr;
	static int winCnt;
	static int loseCnt;
	static boolean[] visited, isNum;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuffer ans = new StringBuffer();
		StringTokenizer st;
		
		int TC = Integer.parseInt(br.readLine());
		for(int t=1; t<=TC; t++) {
			winCnt = 0;
			loseCnt = 0;
			kyuArr = new int[9];
			inNum = new int[9];
			inArr = new int[9];
			isNum = new boolean[18];
			st = new StringTokenizer(br.readLine());
			
			//규영이 카드 입력 받으면서 규영이가 이미 받은 번호인지 isNum에 체크해준다.
			for(int i=0; i<9; i++) {
				kyuArr[i] = Integer.parseInt(st.nextToken());
				isNum[kyuArr[i]-1] = true;
			}
			// 규영이가 받지 않은 번호 즉 인영이가 낼 수 있는 카드 숫자를 가지고있는 isNum배열을 입력해준다.
			int j=0;
			for(int i=0; i<18; i++) {
				if(isNum[i]) continue;
				inNum[j++] = i+1;
			}
			//순열
			visited = new boolean[9];
			permutation(0);
			ans.append("#"+t+" "+winCnt+" "+loseCnt+"\n");
		}
		bw.write(ans.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	//순열을 통해 인영이가 내는 경우의수를 찾는다.
	public static void permutation(int cnt) {
		if(cnt == 9) {
			// 인영이가 내는 수가 정해지면 solve함수 수행
			solve();
			return;
		}
		
		for(int i=0; i<9; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			inArr[cnt] = inNum[i];
			permutation(cnt+1);
			visited[i] = false;
		}
	}
	
	public static void solve() {
		int kyuSum = 0;
		int inSum = 0;
		for(int i=0; i<9; i++) {
			//인영이가 규영이보다 더 높은수를 내면
			if(inArr[i] > kyuArr[i]) {
				//inSum에 두 수의 합을 더해준다.
				inSum+=(kyuArr[i] + inArr[i]);
				//규영이가 인영이보다 높은 수면
			} else if(inArr[i] < kyuArr[i]) {
				//kyuSum에 두 수의 합 더한다.
				kyuSum+=(kyuArr[i] + inArr[i]);
			}
		}
		//규영이가 이겼다면 승리 Cnt를 1 더해준다.
		if(kyuSum > inSum) {
			winCnt++;
		} else if(kyuSum < inSum) {
			loseCnt++;
		}
	}
}
