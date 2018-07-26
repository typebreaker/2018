package main.java.kr.typebreaker;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Main {

	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		new MainGUI();
		Map<String, Integer> hash = new HashMap<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String filePath = br.readLine();
		
		BufferedInputStream bis = null;
		bis = new BufferedInputStream(new FileInputStream(filePath));
		BufferedReader fileReader = new BufferedReader(new InputStreamReader(bis));
		
		String total = "";

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
			if(hash.get(words[i])==null)
				hash.put(words[i], 0);
			else
				hash.put(words[i], hash.get(words[i])+1);
		}
		
		Iterator itor = sort(hash).iterator();
		while(itor.hasNext()) {
			String nowSentense = (String) itor.next();
			String putSentense=nowSentense+" "+hash.get(nowSentense)+"\r\n";
			bos.write(putSentense.getBytes());
		}
		
		bos.flush();
		bos.close();
		
	}
	
	public static List sort(final Map map) {
		List<String> list = new ArrayList();
		list.addAll(map.keySet());
		Collections.sort(list, new Comparator() {

			@Override
			public int compare(Object arg0, Object arg1) {
				Object v1 = map.get(arg0);
				Object v2 = map.get(arg1);
				return ((Comparable)v2).compareTo(v1);
			}
		});
		return list;
	}

}
