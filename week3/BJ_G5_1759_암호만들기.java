package bj.g5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_G5_1759_암호만들기 {
	static int L, C;
	static StringBuffer ans = new StringBuffer();
	static char[] arr, word;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new char[C];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<C; i++) {
			arr[i] = st.nextToken().charAt(0);
		}
		// 반드시 사전순으로 암호를 만들어야하기때문에 미리 정렬
		Arrays.sort(arr);
		word = new char[L];
		combination(0, 0, 0, 0);
		bw.write(ans.toString());
		bw.close();
		br.close();
	}
	// 조합
	private static void combination(int start, int cnt, int gatherCnt, int consonantCnt) {
		if(cnt == L) {
			//문제에서 주어진 모음과 자음의 최소 값
			if(gatherCnt>=1 && consonantCnt>=2) {
				for(int i=0; i<L; i++) {
					ans.append(word[i]);
				}
				ans.append("\n");
			}
			return;
		}
		
		for(int i=start; i<C; i++) {
			word[cnt] = arr[i];
			// 현재 담은 문자가 모음이라면
			if(isGather(word[cnt])) {
				combination(i+1, cnt+1, gatherCnt+1, consonantCnt);
			} else {
				//현재 담은 문자가 자음이라면
				combination(i+1, cnt+1, gatherCnt, consonantCnt+1);
			}
		}
	}
	
	private static boolean isGather(char c) {
		return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
	}
}