
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataQatar {
	public static List<ItemQatar> getDataDeparture() {
		List<ItemQatar> list = new ArrayList<ItemQatar>();
		try {
			SimpleDateFormat d1=new SimpleDateFormat("yyyyMMddHHmmss");
	        SimpleDateFormat d2 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			String DEPDATA = getURLText(
					"http://cedafid:8099/WebSocketHome/DownloadFile?filename=datafile/BKKDEPARTURE.txt");
			JSONParser jp = new JSONParser();
			JSONObject dep = (JSONObject) jp.parse(DEPDATA);
			JSONArray data = (JSONArray) dep.get("DATA");
			int counter = 0;
			for (int i = 0; i < data.size(); i++) {
				ItemQatar obj = new ItemQatar();
				JSONObject depdata = (JSONObject) data.get(i);
				String FTYP = depdata.get("FTYP") + "";
				String SOBT = depdata.get("SOBT") + "";
		        if(!SOBT.trim().equals("")) {
			        Date SOBTDate=d1.parse(SOBT);
			        Calendar cal = Calendar.getInstance(); // creates calendar
			        cal.setTime(SOBTDate); // sets calendar time/date
			        cal.add(Calendar.HOUR, 7); // adds one hour
			        cal.getTime(); // returns new date object, one hour in the future
			        String newTime = d2.format(cal.getTime());
			        SOBT=newTime; 
		        }
				String MAINFLNO = depdata.get("MAINFLNO") + "";
				String FLNO = getFLNOByAirline(depdata.get("ALLFLNO") + "", "QR");
		        String estimateTime = depdata.get("EOBT")+"";
		        if(!estimateTime.trim().equals("")) {
			        Date estDate=d1.parse(estimateTime);
			        Calendar cal = Calendar.getInstance(); // creates calendar
			        cal.setTime(estDate); // sets calendar time/date
			        cal.add(Calendar.HOUR, 7); // adds one hour
			        cal.getTime(); // returns new date object, one hour in the future
			        String newTime = d2.format(cal.getTime());
			        estimateTime=newTime; 
		        }

				boolean cancelrolloff = false;
				if (FTYP.equals("X") && !CheckUTC10mins(SOBT)) {
					cancelrolloff = true;
				}
				boolean mainflight = MAINFLNO.equals("1");

				if (!cancelrolloff && mainflight && FLNO != null) {
					// obj.setAirline(CallData.getAirlineImage(depdata.get("ALC2")+""));
					obj.setFlight(getMainFlight(FLNO, FLNO));
					obj.setFlightTo(getMainFlight(depdata.get("CITYNAMEALL")+"", FLNO));
					obj.setGate(depdata.get("GATE") + "");
					obj.setStatus(RemoveThaiRemark(depdata.get("REMGATE") + ""));
//					obj.setTimeDepart(depdata.get("STD") + "");
//					obj.setEstimate(depdata.get("ETD") + "");
					obj.setTimeDepart(SOBT);
					obj.setEstimate(estimateTime);
					list.add(obj);
					if (counter >= 39) {
						break;
					}
					counter++;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	public static List<ItemQatar> getDataDeparture2() {
		List<ItemQatar> list = new ArrayList<ItemQatar>();

		try {
			Connection con = DatabaseConnect.getConnection();
			PreparedStatement ps = con
					.prepareStatement("SELECT COUNT(*) OVER () total, c.* FROM publicpage_qr c WHERE ROWNUM <= 39");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

//				   -------------------------------------------------  Time----------------------------	        	
				String dates = rs.getString("sobt");
				SimpleDateFormat d1 = new SimpleDateFormat("yyyyMMddHHmmss");
				Date f1 = d1.parse(dates);
				Calendar cal = Calendar.getInstance(); // creates calendar
				cal.setTime(f1); // sets calendar time/date
				cal.add(Calendar.HOUR, 7); // adds one hour
				cal.getTime(); // returns new date object, one hour in the future
				SimpleDateFormat d2 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				String timeDep = d2.format(cal.getTime());
//			         System.out.println(d2.format(cal.getTime()));

//			       -------------------------------------------------  Status Flight----------------------------
				String status = rs.getString("ftyp");
				String eobt = rs.getString("eobt");
				String estimate = null;
//			         System.out.print(rs.getString("common"));
				if (status.equals("O")) {
					if (eobt.equals("              ")) {
						estimate = " ";
					} else {
						SimpleDateFormat s1 = new SimpleDateFormat("yyyyMMddHHmmss");
						Date a1 = s1.parse(eobt);
						Calendar cal2 = Calendar.getInstance(); // creates calendar
						cal2.setTime(a1); // sets calendar time/date
						cal2.add(Calendar.HOUR, 7); // adds one hour
						cal2.getTime(); // returns new date object, one hour in the future
						SimpleDateFormat d3 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
						String newTime = d3.format(cal.getTime());
						estimate = newTime;
					}
				} else if (status.equals("X")) {
					estimate = "CANCEL";
				}
//				   ------------------------------------------------- Flight----------------------------
				String jfno = rs.getString("jfno");
				String newFlight;
				String flno = rs.getString("flno");
				String flnoFlight;

				if (jfno.equals(" ")) {
					newFlight = jfno;
					flnoFlight = flno;
				} else {
					String str = new String(jfno);
					String a = str.substring(str.indexOf("QR", 5));
					String b = a.substring(0, 7);
//						   System.out.println(b);
					flnoFlight = b;
				}
//				    ------------------------------------------------- Gate----------------------------  
				String gate1 = rs.getString("gtd1");
				String gate2 = rs.getString("gtd2");
				String newGate;
				// System.out.println(rs.getString("gtd2"));
				if (gate2.equals("     ")) {
					newGate = gate1;
				} else {
					newGate = gate1 + "/" + gate2;
				}
//					   ------------------------------------------------- Status----------------------------
				String remgate1 = rs.getString("remgate1");
				String remgate2 = rs.getString("remgate2");

//					   ------------------------------------------------- List Model----------------------------		        
				ItemQatar dep = new ItemQatar();
				dep.setTimeDepart(timeDep);
				dep.setGate(newGate);
				dep.setFlight(flnoFlight);
				dep.setFlightTo(rs.getString("apfn"));
//		        	dep.setCheckIn(rs.getString("common"));
				dep.setEstimate(estimate);
//		            dep.setStatus(status);
				dep.setStatus(remgate1);
				dep.setStatus2(remgate2);
//		            dep.setTotalRecord(rs.getString("total"));
				list.add(dep);

			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}

	public static String getURLText(String URL) throws IOException, MalformedURLException {
		URL url = new URL(URL);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		con.setReadTimeout(15000);
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + URL);
		// System.out.println("\nSending 'GET' request to URL : " + URL);
		System.out.println("Response Code : " + responseCode);
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		// System.out.println(response.toString());
		return response.toString();

	}

	public static boolean CheckUTC10mins(String UTCTime) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, -10);

		SimpleDateFormat sp = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Calendar t = Calendar.getInstance();
		try {
			t.setTime(sp.parse(UTCTime));
			t.add(Calendar.HOUR, 7);
			if (t.before(cal)) {// Old Flight
				return false;
			} else {
				return true;
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
		for (int i = 0; i < output.length; i++) {
			if (i % 2 == 0) {
				if (i == 0) {
					result = getRemarkStyle(output[i]);
				} else {
					result += "/" + getRemarkStyle(output[i]);
				}
			}
		}
		return result;
	}

	public static String getMainFlight(String input, String FLNO) {
		String[] output = FLNO.split(" ");
		if(output.length==2) {
			if(output[1].length()==3) {
				return "<span class=\"badge badge-warning\" style=\"font-size:24px;\">"+input+"</span>";
			}else {
				return input;
			}
		}else {
			return input;
		}
	}

	public static String getFLNOByAirline(String input, String Airline) {
		String[] output = input.split("\\|");
		for (int i = 0; i < output.length; i++) {
			String FLNO = output[i];
			if (FLNO.contains(Airline)) {
				return FLNO;
			}
		}
		return null;
	}
	
	public static String getRemarkStyle(String input) {
		if (input.equals("FINAL CALL")) {
			return "<span class=\"badge badge-danger\">" + input + "</span>";
		} else if (input.equals("DELAYED")) {
			return "<span class=\"badge badge-primary\">" + input + "</span>";
		} else if (input.equals("CANCELLED")) {
			return "<span class=\"badge badge-secondary\">" + input + "</span>";
		} else if (input.equals("GATE OPEN")) {
			return "<span class=\"badge badge-success\">" + input + "</span>";
		} else if (input.equals("GATE CLOSED")) {
			return "<span class=\"badge badge-secondary\">" + input + "</span>";
		} else if (input.equals("BOARDING")) {
			return "<span class=\"badge badge-warning\">" + input + "</span>";
		} else if (input.equals("NEW GATE")) {
			return "<span class=\"badge badge-info\">" + input + "</span>";
		} else if (input.equals("DEPARTED")) {
			return "<span class=\"badge badge-light\">" + input + "</span>";
		} else {
			return input;
		}
	}
}
