package javaPrograms;

import java.util.*;
import java.text.*;

public class EpochToDate {
  public static void main(String[] args)
   {
   double[] a={1372339860, 1506709800};
   int len=a.length;
   for(int i=0; i<len ;i++){
	   
	   Date date = new Date((long) (a[i]*1000L)); 
	   // format of the date
	   SimpleDateFormat jdf = new SimpleDateFormat("EEEE, dd-MMMM-yyyy HH:mm:ss z");
	   jdf.setTimeZone(TimeZone.getTimeZone("GMT-4"));
	   String java_date = jdf.format(date);
	   System.out.println(java_date);
   }
   
   }
}
