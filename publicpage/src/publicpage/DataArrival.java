package publicpage;
	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
	import java.util.ArrayList;
	import java.util.Calendar;
	import java.util.Date;
	import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataArrival {
	public static int end=2; //2Hr After STA
	public static int minrecords = 15;//Minimum Record is 20
	public static List<ItemArrival> getDataArrival(){
		List<ItemArrival> list=new ArrayList<ItemArrival>();
		try {
			String ARRDATA = DataFlight.getURLText("http://10.121.0.15:8099/WebSocketHome/DownloadFile?filename=datafile/BKKARRIVAL.txt");
			JSONParser jp = new JSONParser();
			JSONObject arr = (JSONObject)jp.parse(ARRDATA);
			JSONArray data = (JSONArray)arr.get("DATA");
			int counter = 0;
			for(int i=0;i<data.size();i++) {
				ItemArrival obj=new ItemArrival();
				JSONObject depdata = (JSONObject)data.get(i);
				
				String FTYP=depdata.get("FTYP")+"";
				String SIBT=depdata.get("SIBT")+"";
				String MAINFLNO=depdata.get("MAINFLNO")+"";
				String AIBT=depdata.get("AIBT")+"";
				
				
				boolean timeframeCheck = CheckUTCEnd(SIBT);
				if(i<minrecords) {
					timeframeCheck=true;
				}
				
				boolean cancelrolloff = false;
				if(FTYP.equals("X") && !CheckUTC10mins(SIBT)) {
					cancelrolloff=true;
				}
				boolean mainflight = MAINFLNO.equals("1");
				
				boolean aibtrolloff = false;
				if(AIBT.trim().length()>0&&!CheckUTC120mins(AIBT)) {
					aibtrolloff=true;
				}
				
				if(!cancelrolloff && mainflight && !aibtrolloff && timeframeCheck) {
					obj.setAirline(CallData.getAirlineImage(depdata.get("ALC2")+""));
					obj.setFlight(depdata.get("FLNO")+"");
					obj.setFlightFrom(depdata.get("CITYNAMEALL")+"");
					obj.setBelt(depdata.get("BELT")+"");
					obj.setStatus(RemoveThaiRemark(depdata.get("REMARK")+""));
					obj.setTimeArrival(depdata.get("STA")+"");
					obj.setEstimate(depdata.get("ETA")+"");
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
	
	public static List<ItemArrival> getDataArrivalDMK(){
		List<ItemArrival> list=new ArrayList<ItemArrival>();
		try {
			String ARRDATA = DataFlight.getURLText("http://10.121.0.15:8099/WebSocketHome/DownloadFile?filename=datafile/DMKARRIVAL.txt");
			JSONParser jp = new JSONParser();
			JSONObject arr = (JSONObject)jp.parse(ARRDATA);
			JSONArray data = (JSONArray)arr.get("DATA");
			int counter = 0;
			for(int i=0;i<data.size();i++) {
				ItemArrival obj=new ItemArrival();
				JSONObject depdata = (JSONObject)data.get(i);
				
				String FTYP=depdata.get("FTYP")+"";
				String SIBT=depdata.get("SIBT")+"";
				String MAINFLNO=depdata.get("MAINFLNO")+"";
				String AIBT=depdata.get("AIBT")+"";
				boolean timeframeCheck = CheckUTCEnd(SIBT);
				if(i<minrecords) {
					timeframeCheck=true;
				}
				boolean cancelrolloff = false;
				if(FTYP.equals("X") && !CheckUTC10mins(SIBT)) {
					cancelrolloff=true;
				}
				boolean mainflight = MAINFLNO.equals("1");
				
				boolean aibtrolloff = false;
				if(AIBT.trim().length()>0&&!CheckUTC120mins(AIBT)) {
					aibtrolloff=true;
				}
				
				if(!cancelrolloff && mainflight && !aibtrolloff && timeframeCheck) {
					obj.setAirline(CallData.getAirlineImage(depdata.get("ALC2")+""));
					obj.setFlight(depdata.get("FLNO")+"");
					obj.setFlightFrom(depdata.get("CITYNAMEALL")+"");
					obj.setBelt(depdata.get("BELT")+"");
					obj.setStatus(RemoveThaiAndChineseRemark(depdata.get("REMARK")+""));
					obj.setTimeArrival(depdata.get("STA")+"");
					obj.setEstimate(depdata.get("ETA")+"");
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
	
	public static List<ItemArrival> getDataArrivalHKT(){
		List<ItemArrival> list=new ArrayList<ItemArrival>();
		try {
			String ARRDATA = DataFlight.getURLText("http://10.121.0.15:8099/WebSocketHome/DownloadFile?filename=datafile/HKTARRIVAL.txt");
			JSONParser jp = new JSONParser();
			JSONObject arr = (JSONObject)jp.parse(ARRDATA);
			JSONArray data = (JSONArray)arr.get("DATA");
			int counter = 0;
			for(int i=0;i<data.size();i++) {
				ItemArrival obj=new ItemArrival();
				JSONObject depdata = (JSONObject)data.get(i);
				
				String FTYP=depdata.get("FTYP")+"";
				String SIBT=depdata.get("SIBT")+"";
				String MAINFLNO=depdata.get("MAINFLNO")+"";
				String AIBT=depdata.get("AIBT")+"";
				boolean timeframeCheck = CheckUTCEnd(SIBT);
				if(i<minrecords) {
					timeframeCheck=true;
				}
				boolean cancelrolloff = false;
				if(FTYP.equals("X") && !CheckUTC10mins(SIBT)) {
					cancelrolloff=true;
				}
				boolean mainflight = MAINFLNO.equals("1");
				
				boolean aibtrolloff = false;
				if(AIBT.trim().length()>0&&!CheckUTC120mins(AIBT)) {
					aibtrolloff=true;
				}
				
				if(!cancelrolloff && mainflight && !aibtrolloff&&timeframeCheck) {
					obj.setAirline(CallData.getAirlineImage(depdata.get("ALC2")+""));
					obj.setFlight(depdata.get("FLNO")+"");
					obj.setFlightFrom(depdata.get("CITYNAMEALL")+"");
					obj.setBelt(depdata.get("BELT")+"");
					obj.setStatus(RemoveThaiAndChineseRemark(depdata.get("REMARK")+""));
					obj.setTimeArrival(depdata.get("STA")+"");
					obj.setEstimate(depdata.get("ETA")+"");
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
	
	public static List<ItemArrival> getDataArrivalCNX(){
		List<ItemArrival> list=new ArrayList<ItemArrival>();
		try {
			String ARRDATA = DataFlight.getURLText("http://10.121.0.15:8099/WebSocketHome/DownloadFile?filename=datafile/CNXARRIVAL.txt");
			JSONParser jp = new JSONParser();
			JSONObject arr = (JSONObject)jp.parse(ARRDATA);
			JSONArray data = (JSONArray)arr.get("DATA");
			int counter = 0;
			for(int i=0;i<data.size();i++) {
				ItemArrival obj=new ItemArrival();
				JSONObject depdata = (JSONObject)data.get(i);
				
				String FTYP=depdata.get("FTYP")+"";
				String SIBT=depdata.get("SIBT")+"";
				String MAINFLNO=depdata.get("MAINFLNO")+"";
				String AIBT=depdata.get("AIBT")+"";
				boolean timeframeCheck = CheckUTCEnd(SIBT);
				if(i<minrecords) {
					timeframeCheck=true;
				}
				boolean cancelrolloff = false;
				if(FTYP.equals("X") && !CheckUTC10mins(SIBT)) {
					cancelrolloff=true;
				}
				boolean mainflight = MAINFLNO.equals("1");
				
				boolean aibtrolloff = false;
				if(AIBT.trim().length()>0&&!CheckUTC120mins(AIBT)) {
					aibtrolloff=true;
				}
				
				if(!cancelrolloff && mainflight && !aibtrolloff&&timeframeCheck) {
					obj.setAirline(CallData.getAirlineImage(depdata.get("ALC2")+""));
					obj.setFlight(depdata.get("FLNO")+"");
					obj.setFlightFrom(depdata.get("CITYNAMEALL")+"");
					obj.setBelt(depdata.get("BELT")+"");
					obj.setStatus(RemoveThaiAndChineseRemark(depdata.get("REMARK")+""));
					obj.setTimeArrival(depdata.get("STA")+"");
					obj.setEstimate(depdata.get("ETA")+"");
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
	
	public static List<ItemArrival> getDataArrivalCEI(){
		List<ItemArrival> list=new ArrayList<ItemArrival>();
		try {
			String ARRDATA = DataFlight.getURLText("http://10.121.0.15:8099/WebSocketHome/DownloadFile?filename=datafile/CEIARRIVAL.txt");
			JSONParser jp = new JSONParser();
			JSONObject arr = (JSONObject)jp.parse(ARRDATA);
			JSONArray data = (JSONArray)arr.get("DATA");
			int counter = 0;
			for(int i=0;i<data.size();i++) {
				ItemArrival obj=new ItemArrival();
				JSONObject depdata = (JSONObject)data.get(i);
				
				String FTYP=depdata.get("FTYP")+"";
				String SIBT=depdata.get("SIBT")+"";
				String MAINFLNO=depdata.get("MAINFLNO")+"";
				String AIBT=depdata.get("AIBT")+"";
				boolean timeframeCheck = CheckUTCEnd(SIBT);
				if(i<minrecords) {
					timeframeCheck=true;
				}
				boolean cancelrolloff = false;
				if(FTYP.equals("X") && !CheckUTC10mins(SIBT)) {
					cancelrolloff=true;
				}
				boolean mainflight = MAINFLNO.equals("1");
				
				boolean aibtrolloff = false;
				if(AIBT.trim().length()>0&&!CheckUTC120mins(AIBT)) {
					aibtrolloff=true;
				}
				
				if(!cancelrolloff && mainflight && !aibtrolloff&&timeframeCheck) {
					obj.setAirline(CallData.getAirlineImage(depdata.get("ALC2")+""));
					obj.setFlight(depdata.get("FLNO")+"");
					obj.setFlightFrom(depdata.get("CITYNAMEALL")+"");
					obj.setBelt(depdata.get("BELT")+"");
					obj.setStatus(RemoveThaiAndChineseRemark(depdata.get("REMARK")+""));
					obj.setTimeArrival(depdata.get("STA")+"");
					obj.setEstimate(depdata.get("ETA")+"");
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
	public static List<ItemArrival> getDataArrivalHDY(){
		List<ItemArrival> list=new ArrayList<ItemArrival>();
		try {
			String ARRDATA = DataFlight.getURLText("http://10.121.0.15:8099/WebSocketHome/DownloadFile?filename=datafile/HDYARRIVAL.txt");
			JSONParser jp = new JSONParser();
			JSONObject arr = (JSONObject)jp.parse(ARRDATA);
			JSONArray data = (JSONArray)arr.get("DATA");
			int counter = 0;
			for(int i=0;i<data.size();i++) {
				ItemArrival obj=new ItemArrival();
				JSONObject depdata = (JSONObject)data.get(i);
				
				String FTYP=depdata.get("FTYP")+"";
				String SIBT=depdata.get("SIBT")+"";
				String MAINFLNO=depdata.get("MAINFLNO")+"";
				String AIBT=depdata.get("AIBT")+"";
				boolean timeframeCheck = CheckUTCEnd(SIBT);
				if(i<minrecords) {
					timeframeCheck=true;
				}
				
				boolean cancelrolloff = false;
				if(FTYP.equals("X") && !CheckUTC10mins(SIBT)) {
					cancelrolloff=true;
				}
				boolean mainflight = MAINFLNO.equals("1");
				
				boolean aibtrolloff = false;
				if(AIBT.trim().length()>0&&!CheckUTC120mins(AIBT)) {
					aibtrolloff=true;
				}
				
				if(!cancelrolloff && mainflight && !aibtrolloff&&timeframeCheck) {
					obj.setAirline(CallData.getAirlineImage(depdata.get("ALC2")+""));
					obj.setFlight(depdata.get("FLNO")+"");
					obj.setFlightFrom(depdata.get("CITYNAMEALL")+"");
					obj.setBelt(depdata.get("BELT")+"");
					obj.setStatus(RemoveThaiAndChineseRemark(depdata.get("REMARK")+""));
					obj.setTimeArrival(depdata.get("STA")+"");
					obj.setEstimate(depdata.get("ETA")+"");
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
		
		public static String getRemarkStyle(String input) {
			if(input.equals("LANDED")) {
				return "<span class=\"badge badge-danger\">"+input+"</span>";
			}else if (input.equals("DELAYED")) {
				return "<span class=\"badge badge-light\">"+input+"</span>";
			}else if (input.equals("CANCELLED")) {
				return "<span class=\"badge badge-primary\">"+input+"</span>";
			}else if (input.equals("CONFIRMED")) {
				return "<span class=\"badge badge-dark\">"+input+"</span>";
			}else {
				return input;
			}
		}
		public static boolean CheckUTC10mins(String UTCTime) {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MINUTE, -10);
			
			SimpleDateFormat sp = new SimpleDateFormat("yyyyMMddHHmmss");
			Calendar t = Calendar.getInstance();
			try {
				t.setTime(sp.parse(UTCTime));
				t.add(Calendar.HOUR, 7);
				
				//System.out.println(t.getTime()); //เวลาที่ส่งมา +7ชั่วโมง
				//System.out.println(cal.getTime()); //เวลาปัจจุบัน -10นาที
				
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
		
		public static boolean CheckUTC120mins(String UTCTime) {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MINUTE, -120);
			
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
			cal.add(Calendar.MINUTE, end*60);
			
			SimpleDateFormat sp = new SimpleDateFormat("yyyyMMddHHmmss");
			Calendar t = Calendar.getInstance();
			try {
				t.setTime(sp.parse(UTCTime));
				t.add(Calendar.HOUR, 7); 
			
				//System.out.println(t.getTime()); //เวลาที่ส่งมา +7ชั่วโมง
				//System.out.println(cal.getTime()); //เวลาปัจจุบัน +2 ชั่วโมง
				
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


}