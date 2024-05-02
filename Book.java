package p1;
import java.util.Date;

public class Book extends Product {
    private String writer;

    public Book(String pn, double p, int noa, String m, boolean hd, double dv, 
		Date de, String w) throws Exception {
        super(pn, p, noa, m, hd, dv, de);
	    
        if (w == null || w.isBlank() || w.isEmpty()) {
        throw new Exception("Writer name can't be empty !!");}
	    
        this.writer = w;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) throws Exception {
	    
        if (writer == null || writer.isBlank() || writer.isEmpty()) {
        throw new Exception("Writer name can't be empty !!");}
	    
        this.writer = writer;
    }

    @Override
    public void displayInfo() {
        System.out.println("Book name is: "+getProductName()+", of ID: "+getiD()+", Its price = "+getPrice()+
        ", number of available: "+getNumberOfAvailable()+", manufacturer: "+getManufacturer()+
        ", does it have discount? --> "+getHasDiscount()+", of "+getDiscountValue()*getPrice()+" $"+
        ", its expiry date: "+getDiscountExpiry()+", writer: "+getWriter());
    }

	public void makeDiscount(double discount, Product discountProduct) {
	}
}
