package publicpage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class DataFlight {
	public static int counter =0;
	public static int end=5; //5Hr before STD
	public static int minrecords = 15;//Minimum Record is 20
	
	public static List<ItemDeparture> getDataDeparture(){
		
		List<ItemDeparture> list=new ArrayList<ItemDeparture>();
		try {
			String DEPDATA = getURLText("http://cedafid:8099/WebSocketHome/DownloadFile?filename=datafile/BKKDEPARTURE.txt");
			JSONParser jp = new JSONParser();
			JSONObject dep = (JSONObject)jp.parse(DEPDATA);
			JSONArray data = (JSONArray)dep.get("DATA");
			counter = 0;
			for(int i=0;i<data.size();i++) {
				ItemDeparture obj=new ItemDeparture();
				JSONObject depdata = (JSONObject)data.get(i);
				String FTYP=depdata.get("FTYP")+"";
				String SOBT=depdata.get("SOBT")+"";
				String MAINFLNO=depdata.get("MAINFLNO")+"";
				boolean timeframeCheck = CheckUTCEnd(SOBT);
				if(i<minrecords) {
					timeframeCheck=true;
				}
				
				boolean cancelrolloff = false;
				if(FTYP.equals("X") && !CheckUTC10mins(SOBT)) {
					cancelrolloff=true;
				}
				boolean mainflight = MAINFLNO.equals("1");
				
				if(!cancelrolloff && mainflight&&timeframeCheck) {
					obj.setAirline(CallData.getAirlineImage(depdata.get("ALC2")+""));
					obj.setCheckIn(depdata.get("REMCTR")+"");
					obj.setFlight(depdata.get("FLNO")+"");
					obj.setFlightTo(depdata.get("CITYNAMEALL")+"");
					obj.setGate(depdata.get("GATE")+"");
					obj.setRow(getRowString(depdata.get("CHKROW")+""));
					obj.setStatus(RemoveThaiRemark(depdata.get("REMCTR")+""));
					obj.setTimeDepart(depdata.get("STD")+"");
					obj.setEstimate(depdata.get("ETD")+"");
					String JFNO=depdata.get("JFNO")+"";
					if(JFNO.length()>0) {
						obj.setJFNO(getJFNOString(depdata.get("ALLFLNO")+""));
					}else {
						obj.setJFNO("");
					}
					list.add(obj);
					if(counter>=120) {
						break;
					}
					counter++;
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
	
	public static List<ItemDeparture> getDataDepartureDMK(){
		List<ItemDeparture> list=new ArrayList<ItemDeparture>();
		try {
			String DEPDATA = getURLText("http://10.121.0.15:8099/WebSocketHome/DownloadFile?filename=datafile/DMKDEPARTURE.txt");
			JSONParser jp = new JSONParser();
			JSONObject dep = (JSONObject)jp.parse(DEPDATA);
			JSONArray data = (JSONArray)dep.get("DATA");
			counter = 0;
			for(int i=0;i<data.size();i++) {
				ItemDeparture obj=new ItemDeparture();
				JSONObject depdata = (JSONObject)data.get(i);
				String FTYP=depdata.get("FTYP")+"";
				String SOBT=depdata.get("SOBT")+"";
				String MAINFLNO=depdata.get("MAINFLNO")+"";
				boolean timeframeCheck = CheckUTCEnd(SOBT);
				if(i<minrecords) {
					timeframeCheck=true;
				}
				
				boolean cancelrolloff = false;
				if(FTYP.equals("X") && !CheckUTC10mins(SOBT)) {
					cancelrolloff=true;
				}
				boolean mainflight = MAINFLNO.equals("1");
				
				if(!cancelrolloff && mainflight&&timeframeCheck) {
					obj.setAirline(CallData.getAirlineImage(depdata.get("ALC2")+""));
					obj.setCheckIn(depdata.get("REMCTR")+"");
					obj.setFlight(depdata.get("FLNO")+"");
					obj.setFlightTo(depdata.get("CITYNAMEALL")+"");
					obj.setGate(depdata.get("GATE")+"");
					obj.setRow(getRowStringCommaSeparate(depdata.get("CHKROW2")+""));
					obj.setStatus(RemoveThaiAndChineseRemark(depdata.get("TERMFLTREMARK")+""));
					obj.setTimeDepart(depdata.get("STD")+"");
					obj.setEstimate(depdata.get("ETD")+"");
					String JFNO=depdata.get("JFNO")+"";
					if(JFNO.length()>0) {
						obj.setJFNO(getJFNOString(depdata.get("ALLFLNO")+""));
					}else {
						obj.setJFNO("");
					}
					list.add(obj);
					if(counter>=120) {
						break;
					}
					counter++;
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
	
	public static List<ItemDeparture> getDataDepartureHKT(){
		List<ItemDeparture> list=new ArrayList<ItemDeparture>();
		try {
			String DEPDATA = getURLText("http://10.121.0.15:8099/WebSocketHome/DownloadFile?filename=datafile/HKTDEPARTURE.txt");
			JSONParser jp = new JSONParser();
			JSONObject dep = (JSONObject)jp.parse(DEPDATA);
			JSONArray data = (JSONArray)dep.get("DATA");
			counter = 0;
			for(int i=0;i<data.size();i++) {
				ItemDeparture obj=new ItemDeparture();
				JSONObject depdata = (JSONObject)data.get(i);
				String FTYP=depdata.get("FTYP")+"";
				String SOBT=depdata.get("SOBT")+"";
				String MAINFLNO=depdata.get("MAINFLNO")+"";
				boolean timeframeCheck = CheckUTCEnd(SOBT);
				if(i<minrecords) {
					timeframeCheck=true;
				}
				
				boolean cancelrolloff = false;
				if(FTYP.equals("X") && !CheckUTC10mins(SOBT)) {
					cancelrolloff=true;
				}
				boolean mainflight = MAINFLNO.equals("1");
				
				if(!cancelrolloff && mainflight&&timeframeCheck) {
					obj.setAirline(CallData.getAirlineImage(depdata.get("ALC2")+""));
					obj.setCheckIn(depdata.get("REMCTR")+"");
					obj.setFlight(depdata.get("FLNO")+"");
					obj.setFlightTo(depdata.get("CITYNAMEALL")+"");
					obj.setGate(depdata.get("GATE")+"");
					obj.setRow(getRowStringNotSeparate(depdata.get("CHKROWHKT")+""));
					obj.setStatus(RemoveThaiAndChineseRemark(depdata.get("REMCTR")+""));
					obj.setTimeDepart(depdata.get("STD")+"");
					obj.setEstimate(depdata.get("ETD")+"");
					String TERM=RemoveThaiAndChineseRemarkWithoutRemStyle(depdata.get("TERMINAL")+"");
					//System.out.println(TERM);
					obj.setTerminal(TERM.substring(TERM.length()-1,TERM.length()));
					String JFNO=depdata.get("JFNO")+"";
					if(JFNO.length()>0) {
						obj.setJFNO(getJFNOString(depdata.get("ALLFLNO")+""));
					}else {
						obj.setJFNO("");
					}
					list.add(obj);
					if(counter>=120) {
						break;
					}
					counter++;
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
	
	public static List<ItemDeparture> getDataDepartureCNX(){
		List<ItemDeparture> list=new ArrayList<ItemDeparture>();
		try {
			String DEPDATA = getURLText("http://10.121.0.15:8099/WebSocketHome/DownloadFile?filename=datafile/CNXDEPARTURE.txt");
			JSONParser jp = new JSONParser();
			JSONObject dep = (JSONObject)jp.parse(DEPDATA);
			JSONArray data = (JSONArray)dep.get("DATA");
			counter = 0;
			for(int i=0;i<data.size();i++) {
				ItemDeparture obj=new ItemDeparture();
				JSONObject depdata = (JSONObject)data.get(i);
				String FTYP=depdata.get("FTYP")+"";
				String SOBT=depdata.get("SOBT")+"";
				String MAINFLNO=depdata.get("MAINFLNO")+"";
				boolean timeframeCheck = CheckUTCEnd(SOBT);
				if(i<minrecords) {
					timeframeCheck=true;
				}
				
				boolean cancelrolloff = false;
				if(FTYP.equals("X") && !CheckUTC10mins(SOBT)) {
					cancelrolloff=true;
				}
				boolean mainflight = MAINFLNO.equals("1");
				
				if(!cancelrolloff && mainflight&&timeframeCheck) {
					obj.setAirline(CallData.getAirlineImage(depdata.get("ALC2")+""));
					obj.setCheckIn(depdata.get("REMCTR")+"");
					obj.setFlight(depdata.get("FLNO")+"");
					obj.setFlightTo(depdata.get("CITYNAMEALL")+"");
					obj.setGate(depdata.get("GATE")+"");
					obj.setRow(getRowStringNotSeparate(depdata.get("CHKROWCNX")+""));
					obj.setStatus(RemoveThaiAndChineseRemark(depdata.get("REMCTR")+""));
					obj.setTimeDepart(depdata.get("STD")+"");
					obj.setEstimate(depdata.get("ETD")+"");
					String TERM=RemoveThaiAndChineseRemarkWithoutRemStyle(depdata.get("TERMINAL")+"");
					//System.out.println(TERM);
					obj.setTerminal(TERM.substring(TERM.length()-1,TERM.length()));
					String JFNO=depdata.get("JFNO")+"";
					if(JFNO.length()>0) {
						obj.setJFNO(getJFNOString(depdata.get("ALLFLNO")+""));
					}else {
						obj.setJFNO("");
					}
					list.add(obj);
					if(counter>=120) {
						break;
					}
					counter++;
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
	
	public static List<ItemDeparture> getDataDepartureCEI(){
		List<ItemDeparture> list=new ArrayList<ItemDeparture>();
		try {
			String DEPDATA = getURLText("http://10.121.0.15:8099/WebSocketHome/DownloadFile?filename=datafile/CEIDEPARTURE.txt");
			JSONParser jp = new JSONParser();
			JSONObject dep = (JSONObject)jp.parse(DEPDATA);
			JSONArray data = (JSONArray)dep.get("DATA");
			counter = 0;
			for(int i=0;i<data.size();i++) {
				ItemDeparture obj=new ItemDeparture();
				JSONObject depdata = (JSONObject)data.get(i);
				String FTYP=depdata.get("FTYP")+"";
				String SOBT=depdata.get("SOBT")+"";
				String MAINFLNO=depdata.get("MAINFLNO")+"";
				boolean timeframeCheck = CheckUTCEnd(SOBT);
				if(i<minrecords) {
					timeframeCheck=true;
				}
				
				boolean cancelrolloff = false;
				if(FTYP.equals("X") && !CheckUTC10mins(SOBT)) {
					cancelrolloff=true;
				}
				boolean mainflight = MAINFLNO.equals("1");
				
				if(!cancelrolloff && mainflight&&timeframeCheck) {
					obj.setAirline(CallData.getAirlineImage(depdata.get("ALC2")+""));
					obj.setCheckIn(depdata.get("REMCTR")+"");
					obj.setFlight(depdata.get("FLNO")+"");
					obj.setFlightTo(depdata.get("CITYNAMEALL")+"");
					obj.setGate(depdata.get("GATE")+"");
					obj.setRow(getRowStringNotSeparate(depdata.get("CHKROW3")+""));
					obj.setStatus(RemoveThaiAndChineseRemark(depdata.get("REMCTR")+""));
					obj.setTimeDepart(depdata.get("STD")+"");
					obj.setEstimate(depdata.get("ETD")+"");
					String TERM=RemoveThaiAndChineseRemarkWithoutRemStyle(depdata.get("TERMINAL")+"");
					//System.out.println(TERM);
					obj.setTerminal(TERM.substring(TERM.length()-1,TERM.length()));
					String JFNO=depdata.get("JFNO")+"";
					if(JFNO.length()>0) {
						obj.setJFNO(getJFNOString(depdata.get("ALLFLNO")+""));
					}else {
						obj.setJFNO("");
					}
					list.add(obj);
					if(counter>=120) {
						break;
					}
					counter++;
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
	
	public static List<ItemDeparture> getDataDepartureHDY(){
		List<ItemDeparture> list=new ArrayList<ItemDeparture>();
		try {
			String DEPDATA = getURLText("http://10.121.0.15:8099/WebSocketHome/DownloadFile?filename=datafile/HDYDEPARTURE.txt");
			JSONParser jp = new JSONParser();
			JSONObject dep = (JSONObject)jp.parse(DEPDATA);
			JSONArray data = (JSONArray)dep.get("DATA");
			counter = 0;
			for(int i=0;i<data.size();i++) {
				ItemDeparture obj=new ItemDeparture();
				JSONObject depdata = (JSONObject)data.get(i);
				String FTYP=depdata.get("FTYP")+"";
				String SOBT=depdata.get("SOBT")+"";
				String MAINFLNO=depdata.get("MAINFLNO")+"";
				boolean timeframeCheck = CheckUTCEnd(SOBT);
				if(i<minrecords) {
					timeframeCheck=true;
				}
				
				boolean cancelrolloff = false;
				if(FTYP.equals("X") && !CheckUTC10mins(SOBT)) {
					cancelrolloff=true;
				}
				boolean mainflight = MAINFLNO.equals("1");
				
				if(!cancelrolloff && mainflight&&timeframeCheck) {
					obj.setAirline(CallData.getAirlineImage(depdata.get("ALC2")+""));
					obj.setCheckIn(depdata.get("REMCTR")+"");
					obj.setFlight(depdata.get("FLNO")+"");
					obj.setFlightTo(depdata.get("CITYNAMEALL")+"");
					obj.setGate(depdata.get("GATE")+"");
					obj.setRow(getRowStringNotSeparate(depdata.get("CHKROW3")+""));
					obj.setStatus(RemoveThaiAndChineseRemark(depdata.get("REMCTR")+""));
					obj.setTimeDepart(depdata.get("STD")+"");
					obj.setEstimate(depdata.get("ETD")+"");
					String TERM=RemoveThaiAndChineseRemarkWithoutRemStyle(depdata.get("TERMINAL")+"");
					//System.out.println(TERM);
					obj.setTerminal(TERM.substring(TERM.length()-1,TERM.length()));
					String JFNO=depdata.get("JFNO")+"";
					if(JFNO.length()>0) {
						obj.setJFNO(getJFNOString(depdata.get("ALLFLNO")+""));
					}else {
						obj.setJFNO("");
					}
					list.add(obj);
					if(counter>=120) {
						break;
					}
					counter++;
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
	
	public static int getSpeed() throws SQLException{
//		   Connection conn=DatabaseConnect.getConnection();  
//	        PreparedStatement ps2=conn.prepareStatement("select count(*) from publicpage_dep WHERE ROWNUM <= 120");  
//	        ResultSet rs2=ps2.executeQuery();  
//	     
//	        int total=0;
//	        if(rs2.next()){  
//	        	total=rs2.getInt(1);
//	        }
//	        
//	        rs2.close();
//	        ps2.close();
//	        conn.close();
//	        return total;
			return counter;
	    }
	public static String getRow(String CounterList) {
		 if(CounterList == null) {
			 return "";
		 }
		   String word = "" ;
		   HashMap<String,String> result = new HashMap<String,String>();
		   
		   String[] arrB = CounterList.split(",");
		   for(int i = 0; i<arrB.length; i++){ 
		    String a = arrB[i].substring(0,2);
		    result.put(a, "0");
		   }
		   
		   Object[] ret = result.keySet().toArray();
		   for(int i =0;i<ret.length;i++) {
		    if(word.length()>0) {
		     word+=","+ret[i];
		    }else {
		     word+=ret[i];
		    }
		    
		   }
		   
		   return word;
		 }

	public static String getURLText(String URL) throws IOException,MalformedURLException{
		URL url = new URL(URL);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		con.setReadTimeout(15000);
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + URL);
		//System.out.println("\nSending 'GET' request to URL : " + URL);
		System.out.println("Response Code : " + responseCode);
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		//System.out.println(response.toString());
		return response.toString();
		
	}
	
	public static boolean CheckUTC10mins(String UTCTime) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, -10);
		
		SimpleDateFormat sp = new SimpleDateFormat("yyyyMMddHHmmss");
		Calendar t = Calendar.getInstance();
		try {
			t.setTime(sp.parse(UTCTime));
			t.add(Calendar.HOUR, 7);
			if(t.before(cal)) {//Old Flight
				return false;
			}else {
				return true;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return true;
		}
	}
	
	public static boolean CheckUTCEnd(String UTCTime) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, end*60);//5 Hrs.
		
		SimpleDateFormat sp = new SimpleDateFormat("yyyyMMddHHmmss");
		Calendar t = Calendar.getInstance();
		try {
			t.setTime(sp.parse(UTCTime));
			t.add(Calendar.HOUR, 7);
			if(t.before(cal)) {//Old Flight
				return true;
			}else {
				return false;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return true;
		}
	}
	
	public static String RemoveThaiRemark(String input) {
		String result = "";
		String[] output = input.split("\\|");
		for(int i=0;i<output.length;i++) {
			if(i%2==0) {
				if(i==0) {
					result=getRemarkStyle(output[i]);
				}else {
					result+="/"+getRemarkStyle(output[i]);
				}
			}
		}
		return result;
	}
	
	public static String RemoveThaiAndChineseRemark(String input) {
		String result = "";
		String[] output = input.split("\\|");
		for(int i=0;i<output.length;i++) {
			if(i%3==0) {
				if(i==0) {
					result=getRemarkStyle(output[i]);
				}else {
					result+="/"+getRemarkStyle(output[i]);
				}
			}
		}
		return result;
	}
	
	public static String RemoveThaiAndChineseRemarkWithoutRemStyle(String input) {
		String result = "";
		String[] output = input.split("\\|");
		for(int i=0;i<output.length;i++) {
			if(i%3==0) {
				if(i==0) {
					result=output[i];
				}else {
					result+="/"+output[i];
				}
			}
		}
		return result;
	}
	
	public static String getRemarkStyle(String input) {
		if(input.equals("Ck-in Close")) {
			return "<span class=\"badge badge-danger\" style=\"color:#ffffff\">"+input+"</span>";
//			return "<font color=\"red\">"+input+"</font>";
		}else if (input.equals("DELAYED")) {
			return "<span class=\"badge badge-light\">"+input+"</span>";
//			return "<font color=\"#ffff00\">"+input+"</font>";
		}else if (input.equals("CANCELLED")) {
			return "<span class=\"badge badge-primary\">"+input+"</span>";
//			return "<font color=\"#cc6600\">"+input+"</font>";
		}else if (input.equals("Ck-in Open")) {
			return "<span class=\"badge badge-success\" style=\"color:#ffffff\">"+input+"</span>";
//			return "<font color=\"#00ff00\">"+input+"</font>";
		}else if (input.equals("DEPARTED")) {
			return "<span class=\"blink_black_text\">"+input+"</font>";
		}else {
			return "<font color=\"#ffffff\">"+input+"</font>";
		}
	}
	
	public static String getJFNOString(String input) {
		String result = "";
		String JFNOList[]=input.split("\\|");
		int counter=0;
		for(int i=1;i<JFNOList.length;i++) {
			String FLNO=JFNOList[i];
			String Airline = FLNO.substring(0,3).trim();
			result+="<span class=\"badge badge-secondary\"><img valign=\"middle\" style=\"width:100px !important;height:30px !important;border-radius: 3px; border: 1px solid #ffffff;\" src='"+CallData.getAirlineImage(Airline)+"'/>"+FLNO+"</span> ";
			counter++;
			if(counter%7==0) {
				result+="<br>";
			}
		}
		return result;
	}
	
	public static String getRowString(String input) {
		String result = "";
		for(int i=0;i<input.length();i++) {
			String row = Character.toString(input.charAt(i));
			result+="<span class=\"badge badge-light\">"+row+"</span> ";
		}
		return result;
	}
	
	public static String getRowStringCommaSeparate(String input) {
		String result = "";
		String[] rowlist = input.split(",");
		for(int i=0;i<rowlist.length;i++) {
			String row = rowlist[i];
			result+="<span class=\"badge badge-light\">"+row+"</span> ";
		}
		return result;
	}
	
	public static String getRowStringNotSeparate(String input) {
		String result = "";
		if(input.trim().length()>0) {
			result+="<span class=\"badge badge-light\">"+input+"</span> ";
		}
		return result;
	}
}
