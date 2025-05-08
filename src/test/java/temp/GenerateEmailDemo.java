package temp;

import java.util.Date;

public class GenerateEmailDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Date date=new Date();
		String dateString=date.toString();
		String nospace=dateString.replaceAll("\\s", " ");
		String fr=nospace.replaceAll("\\:", "");
		String emails=fr+"@gmail.com";
		System.out.println(emails);
		
	}

}
