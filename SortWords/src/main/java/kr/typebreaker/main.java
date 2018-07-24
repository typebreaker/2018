package main.java.kr.typebreaker;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class main {

	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Map<String, Integer> hash = new HashMap<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String filePath = br.readLine();
		
		BufferedInputStream bis = null;
		bis = new BufferedInputStream(new FileInputStream(filePath));
		BufferedReader fileReader = new BufferedReader(new InputStreamReader(bis));
		
		String total = "";
//		while(bis.available() > 0) {
//			total+=(char)bis.read();
//		}
		String temp=null;
		while((temp = fileReader.readLine())!=null) {
			total+=temp;
		}
		bis.close();
		total=total.replaceAll("은", "");
		total=total.replaceAll("하는", "");
		total=total.replaceAll("는", "");
		total=total.replaceAll("이", "");
		total=total.replaceAll("가", "");
		total=total.replaceAll("을", "");
		total=total.replaceAll("를", "");
		total=total.replaceAll("에게", "");
		total=total.replaceAll("이라서", "");
		String[] words = total.split(" ");
	
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("D:/words.txt"));
	
		for(int i=0;i<words.length;i++) {
			words[i]+="\r\n";
			bos.write(words[i].getBytes());
		}
		bos.flush();
		bos.close();
		
	}

}
