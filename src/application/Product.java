package application;
import java.util.Date;
import java.util.UUID;

public abstract class Product implements Discountable{

	private String productName;
	private double price;
	private int numberOfAvailable;
	private String iD;
	private String manufacturer;
	private boolean hasDiscount;
	private double discountValue;
	private Date discountExpiry;
	public abstract void displayInfo();


	Product(){}
    Product(String pn, double p, int noa){
        this.productName = pn;
        this.price = p;
        this.numberOfAvailable = noa;
        this.iD=UUID.randomUUID().toString();
    
    }
	Product(String pn, double p, int noa, String m,boolean hd, double dv, Date de) {
		this.productName = pn;
		this.price = p;
		this.numberOfAvailable = noa;
		this.iD=UUID.randomUUID().toString();
		this.manufacturer = m;
		this.hasDiscount = hd;
		this.discountValue = dv;
		this.discountExpiry = de;
	}
	

	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public double getPrice() {
        if(hasDiscount && discountExpiry.after(new Date()))
            return makeDiscount();
        else
            return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}


	public int getNumberOfAvailable() {
		return numberOfAvailable;
	}


	public void setNumberOfAvailable(int numberOfAvailable) {
		this.numberOfAvailable = numberOfAvailable;
	}


	public String getiD() {
		return iD;
	}


	public String getManufacturer() {
		return manufacturer;
	}


	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}


	public boolean getHasDiscount() {
		return hasDiscount;
	}


	public void setHasDiscount(boolean hasDiscount) {
		this.hasDiscount = hasDiscount;
	}


	public double getDiscountValue() {
		return discountValue;
	}


	public void setDiscountValue(double discountValue) {
		this.discountValue = discountValue;
	}


	public Date getDiscountExpiry() {
		return discountExpiry;
	}


	public void setDiscountExpiry(Date discountExpiry) {
		this.discountExpiry = discountExpiry;
	}

    public boolean isHasDiscount() {
        return hasDiscount;
    }


    public double makeDiscount() {
        return (price - (price * discountValue));
		
    }


}
