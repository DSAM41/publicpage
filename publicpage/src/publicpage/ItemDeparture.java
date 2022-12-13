package publicpage;

public class ItemDeparture {
	    private String flight;
	    private String flightTo;
	    private String gate;
	    private String status;
	    private String airline;
	    private String timeDepart;
	    private String checkIn;
	    private String row;
	    private int totalFlight;
	    private String estimate;
	    private String JFNO;
	    private String Terminal;
	    
	    public String getTerminal() {
			return Terminal;
		}
		public void setTerminal(String terminal) {
			Terminal = terminal;
		}
		public String getJFNO() {
			return JFNO;
		}
		public void setJFNO(String jFNO) {
			JFNO = jFNO;
		}
		public int getTotalFlight() {
			return totalFlight;
		}
		public void setTotalFlight(int totalFlight) {
			this.totalFlight = totalFlight;
		}
		//		public ItemDeparture(String flight, String flightTo, String gate, String status, String airline,
//				String timeDepart, String checkIn, String row) {
//		
//			this.flight = flight;
//			this.flightTo = flightTo;
//			this.gate = gate;
//			this.status = status;
//			this.airline = airline;
//			this.timeDepart = timeDepart;
//			this.checkIn = checkIn;
//			this.row = row;
//		}
		public String getFlight() {
			return flight;
		}
		public void setFlight(String flight) {
			this.flight = flight;
		}
		public String getFlightTo() {
			return flightTo;
		}
		public void setFlightTo(String flightTo) {
			this.flightTo = flightTo;
		}
		public String getGate() {
			return gate;
		}
		public void setGate(String gate) {
			this.gate = gate;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getAirline() {
			return airline;
		}
		public void setAirline(String airline) {
			this.airline = airline;
		}
		public String getTimeDepart() {
			return timeDepart;
		}
		public void setTimeDepart(String timeDepart) {
			this.timeDepart = timeDepart;
		}
		public String getCheckIn() {
			return checkIn;
		}
		public void setCheckIn(String checkIn) {
			this.checkIn = checkIn;
		}
		public String getRow() {
			return row;
		}
		public void setRow(String row) {
			this.row = row;
		}
		public String getEstimate() {
			return estimate;
		}
		public void setEstimate(String estimate) {
			this.estimate = estimate;
		}
	    
//	    public Items(int itemId, String itemName, double itemPrice){
//	        this.itemId = itemId;
//	        this.itemName = itemName;
//	        this.itemPrice = itemPrice;
//	    }

}
