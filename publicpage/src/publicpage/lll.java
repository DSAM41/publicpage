package publicpage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class lll {

	public static void main(String[] args) {
		  String CounterList = ("14A  ,14B  ,14C  ,14D  ,14E  ,14F  ,14G  ,14H  ,14K  ,14J  ,14L  ,14M  ,14N  ");
		    
	       String word = "" ;
	       HashMap<String,String> result = new HashMap<String,String>();       
	       String[] arrB = CounterList.split(",");
	      
	       for(int i = 0; i<arrB.length; i++){ 
	        String a = arrB[i].substring(0,3);
	       // String a = arrB[i].substring(0,2);
	        String b = arrB[i].substring(2,3);
	        result.put(a,"0");
	        result.put(b,"0");
	        char e =b.charAt(0);
	        char character = e;
	        int number = (int) character;
	        
	        int[] newNum=new int[]{number};
	        for (int j = 0; j < newNum.length; j++) {
//	        	System.out.println(newNum[j]);
			}
	        Arrays.sort(newNum);
	        System.out.println(Arrays.toString(newNum));
	        
//	int[] myArray= new int[]{number};
//	 System.out.println(Arrays.toString(myArray));
//		for(int j = 0; j< myArray.length; j++){
//			if(myArray[j+1]-myArray[j]==1) {
//				System.out.println("true");
//			}
//			else {
//				System.out.println("false");
//			}
//		}
		
//	        for(int j = 0; j < myArray.length-1; j++){
//	        	System.out.println(myArray[j]);
//	            if (myArray[j+1]-myArray[j]==1){ //if the current number is less than the one next to it
////	                int temp = myArray[j]; //save the current number 
////	                myArray[j] = myArray[j+1]; //put the one next to it in its spot
////	                myArray[j+1] = temp; //put the current number in the next spot
//	            	System.out.println("true");
//	            }
//	            else {
//	            	System.out.println("false");
//
//	            }
//	        }	
//	        System.out.println(Arrays.toString(myArray));
	}

	}

}
