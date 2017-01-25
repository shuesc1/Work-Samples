import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class timeParser {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub

//		String startTime = trip.getStartTime();
		String startTime = "8/3/16 05:00";
		java.util.Date parsedStartTime = new SimpleDateFormat("MM/dd/yy HH:mm").parse(startTime);
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(parsedStartTime);
		
//		String endTime = trip.getEndTime();
		String endTime = "8/3/16 09:00";
		java.util.Date parsedEndTime = new SimpleDateFormat("MM/dd/yy HH:mm").parse(endTime);
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(parsedEndTime);
//		calendar2.add(Calendar.DATE, amount);
		
		String targetTime = "8/3/16 07:00";
		java.util.Date parsedTargetTime = new SimpleDateFormat("MM/dd/yy HH:mm").parse(targetTime);
		Calendar calendar3 = Calendar.getInstance();
		calendar3.setTime(parsedTargetTime);
		
		java.util.Date x = calendar3.getTime();
		if (startTime.contains("8/3/16") && endTime.contains("8/3/16") & x.after(parsedStartTime) && x.before(parsedEndTime)){
			System.out.println("Time falls in interval");
		}
		else { System.out.println("Time not in interval");
		}	
		
	}
}


