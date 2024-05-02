package p1;
import java.util.Date;
public class MainClass {
	public static void main(String[] args) {
		try {
		Book b1 = new Book("Reference X",1200.0,54,26687,"Manufacturer X",true,0.18,new Date(),"Writer X");
		Book b2 = new Book("Reference Y",1450.0,42,26577,"Manufacturer Y",true,0.15,new Date(),"Writer Y");
		Device d1 = new Device("Mobile Phone",32500,14,1388,"Samsung",true,0.12,new Date(),"Galaxy S");
		b1.displayInfo();
		b2.displayInfo();
		d1.displayInfo();
		}
		catch (Exception e) {e.printStackTrace();}
	}
}
