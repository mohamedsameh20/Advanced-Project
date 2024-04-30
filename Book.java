package p1;
import java.util.Date;
public class Book extends Product{
	private String writer;
	public Book(String pn, double p, int noa, int iD, String m, boolean hd, double dv, Date de, String w) {
	  super(pn, p, noa, iD, m, hd,dv, de);
	  this.writer=w;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public void displayInfo() {
		System.out.println("Book name is: "+getProductName()+", of ID: "+getiD()+", Its price = "+getPrice()+
		", number of available: "+getNumberOfAvailable()+", manufacturer: "+getManufacturer()+
		", does it have discount? --> "+isHasDiscount()+", of "+getDiscountValue()*getPrice()+" $"+
		", its expiry date: "+getDiscountExpiry()+", writer: "+getWriter());
	}
	@Override
	public void makeDiscount(double discount, Product discountProduct) {
		super.makeDiscount(discount, discountProduct);
	}
	
}
