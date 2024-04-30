package p1;
import java.util.Date;
public class Device extends Product{
	private String model;
	public Device(String pn, double p, int noa, int iD, String m, boolean hd, double dv, Date de, String ml) {
	  super(pn, p, noa, iD, m, hd,dv, de);
	  this.model=ml;
	}
	
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void displayInfo() {
		System.out.println("Device name is: "+getProductName()+", of ID: "+getiD()+", Its price = "+getPrice()+
		", number of available: "+getNumberOfAvailable()+", manufacturer: "+getManufacturer()+
		", does it have discount? --> "+isHasDiscount()+", of "+getDiscountValue()*getPrice()+" LE"+
		", its expiry date: "+getDiscountExpiry()+", model: "+getModel());
	}
	@Override
	public void makeDiscount(double discount, Product discountProduct) {
		super.makeDiscount(discount, discountProduct);
	}
	
}
