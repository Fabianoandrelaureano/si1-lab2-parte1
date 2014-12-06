package models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;

	public class Compara implements Comparator<Meta> {
	
	@Override
	public int compare(Meta meta1, Meta meta2) {
		
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		
		Calendar data1 = Calendar.getInstance();
		Calendar data2 = Calendar.getInstance();
		
		try {
			data1.setTime(formatter.parse(meta1.getData()));
			data2.setTime(formatter.parse(meta2.getData()));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		if (data1.get(Calendar.WEEK_OF_YEAR) < data2.get(Calendar.WEEK_OF_YEAR))
			return -1;
		else if (data1.get(Calendar.WEEK_OF_YEAR) > data2
				.get(Calendar.WEEK_OF_YEAR))
			return 1;

		return 0;
		
	}

}
