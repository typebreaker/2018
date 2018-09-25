package main.java.kr.typebreaker.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import scala.collection.Seq;
import com.twitter.penguin.korean.tokenizer.KoreanTokenizer;
import com.twitter.penguin.korean.TwitterKoreanProcessorJava;
import com.twitter.penguin.korean.phrase_extractor.KoreanPhraseExtractor;

public class Utils {

	
	public static final String fileRead(String filePath) {
		String total="";
		BufferedInputStream bis = null;
		
		try {
			bis = new BufferedInputStream(new FileInputStream(filePath));
			BufferedReader fileReader = new BufferedReader(new InputStreamReader(bis));

			String temp=null;
			while((temp = fileReader.readLine())!=null) {
				total+=temp;
			}
			bis.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return total;
		
	}
	
	public static final Map<String, Integer> parsingToHashMap(String sentense) {
		
		CharSequence normalized = TwitterKoreanProcessorJava.normalize(sentense);
		Seq<KoreanTokenizer.KoreanToken> tokens = TwitterKoreanProcessorJava.tokenize(normalized);
		List<KoreanPhraseExtractor.KoreanPhrase> phrases = TwitterKoreanProcessorJava.extractPhrases(tokens, true, true);
		
		
		Map<String, Integer> hash = new HashMap<>();
		
		for(KoreanPhraseExtractor.KoreanPhrase item : phrases) {
			if(hash.get(item.text()) != null)
				hash.put(item.text(), 1);
			else
				hash.put(item.text(), hash.get(item.text())+1);
		}
		
		return hash;
		
	}
	
	public static final void makeFile(Map<String, Integer> result, String filePath) {
		BufferedOutputStream bos;
		try {
			bos = new BufferedOutputStream(new FileOutputStream(filePath));
			Iterator itor = sort(result).iterator();
			
			while(itor.hasNext()) {
				String nowSentense = (String) itor.next();
				if(nowSentense.equals(""))
					continue;
				String putSentense=nowSentense+" "+result.get(nowSentense)+"\r\n";
				bos.write(putSentense.getBytes());
			}
			bos.flush();
			bos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
