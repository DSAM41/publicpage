package publicpage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

public class TestData {


		public static void main(String[] argv) {
			String CM ="10A  ,10B  ,10C  ,11A  ,11B  ,11C  ,11D"; 
			   String cm = null ;
			   String word = null ;
			   //if (CM==null) {
			   // word=null;
			   //}else {
			   //System.out.println(CM);
			    String[] arrB = CM.split(",");
			    for(int i = 0; i<arrB.length; i++){ 
			     //word = arrB[i];
			     String a = arrB[i].substring(0,2);
			     String b = arrB[i].substring(2,3);
			     Map<Object, Object> map = new HashMap<Object, Object>();
			     map.put(a,b);
	
			     Set<Object> uniqueValues = new HashSet<Object>(map.values());

			     System.out.println(uniqueValues);
		}
		}
		
}

		