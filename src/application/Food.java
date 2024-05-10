package application;
import java.util.Date;

public class Food extends Product{

    private Date expiryDate;

	public Food(String pn, double p, int noa, String m, boolean hd, double dv, Date de, Date ed) {
	  super(pn, p, noa, m, hd, dv, de);
	  this.expiryDate=ed;
	}


    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

	public void displayInfo() {
		System.out.println("Food name is: "+getProductName()+", of ID: "+getiD()+", Its price = "+getPrice()+
		", number of available: "+getNumberOfAvailable()+", manufacturer: "+getManufacturer()+
		", does it have discount? --> "+isHasDiscount()+", of "+getPrice()+" $"+
		", its expiry date: "+getDiscountExpiry());
	}


	public void makeDiscount(double discount, Product discountProduct) {
		// TODO Auto-generated method stub
		
	}

	
}
