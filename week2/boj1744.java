package week2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class boj1744 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		ArrayList<Integer> positiveNum = new ArrayList<Integer>();
		ArrayList<Integer> negativeNum = new ArrayList<Integer>();
		int zeroCnt = 0;
		int N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());
			if (num < 0) {
				negativeNum.add(num);
			} else if(num > 0){
				positiveNum.add(num);
			} else {
				zeroCnt++;
			}
		}
		Collections.sort(positiveNum, Collections.reverseOrder());
		Collections.sort(negativeNum);

		int sum = 0;
		if(positiveNum.size()%2 == 0) {
			
			for(int i=0; i<positiveNum.size(); i+=2) {
				if(positiveNum.get(i) == 1 || positiveNum.get(i+1) ==1) {
					sum += positiveNum.get(i) + positiveNum.get(i+1);
				} else {
					sum += (positiveNum.get(i) * positiveNum.get(i+1));
				}
			}
		} else {
			
			for(int i=0; i<positiveNum.size()-1; i+=2) {
				if(positiveNum.get(i) == 1 || positiveNum.get(i+1) ==1) {
					sum += positiveNum.get(i) + positiveNum.get(i+1);
				} else {
					sum += (positiveNum.get(i) * positiveNum.get(i+1));
				}
			}
			sum+=positiveNum.get(positiveNum.size()-1);
		}
		
		if(negativeNum.size()%2 == 0) {
			
			for (int i = 0; i < negativeNum.size(); i+=2) {
				sum += (negativeNum.get(i) * negativeNum.get(i+1));
			}
		} else {
			
			for(int i=0; i<negativeNum.size()-1; i+=2) {
				sum += (negativeNum.get(i) * negativeNum.get(i+1));
			}
			
			if(zeroCnt==0) {
				sum+=negativeNum.get(negativeNum.size()-1);
			}
		}
		
		bw.write(sum + "");
		bw.flush();
		bw.close();
		br.close();
	}
}
