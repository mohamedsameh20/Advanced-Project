package MainProject;
import java.util.Date;
import java.util.UUID;

public abstract class Product implements Discountable, Comparable<Product>{

	private String productName;
	private double price;
	private int numberOfAvailable;
	private String iD;
	private String manufacturer;
	private boolean hasDiscount;
	private double discountValue;
	private Date discountExpiry;
    private String Image_url;
    private String genre;
	public abstract void displayInfo();


	Product(){}
    Product(String pn, double p, int noa){
        this.productName = pn;
        this.price = p;
        this.numberOfAvailable = noa;
        this.iD=UUID.randomUUID().toString();
    
    }
	Product(String pn, double p, int noa, String m,boolean hd, double dv, Date de,String genre, String url) {			//new
        this.setProductName(pn);
        this.setPrice(p);
        this.setNumberOfAvailable(noa);
		this.iD=UUID.randomUUID().toString();
        this.setManufacturer(m);
        this.setHasDiscount(hd);
        this.setDiscountValue(dv);
        this.setDiscountExpiry(de);
		this.genre = genre;			//new
        this.set_genre(genre);		//new
		this.setImage_url(url);		//new
	}
	

	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}

        public void setImage_url(String url ) {  this.Image_url = url;}		//new

	public String getImage_url() {return Image_url;}					//new

	public void set_genre(String genre ) {  this.genre = genre;}		//new

	public String get_genre() {return genre;}						//new


	public double getPrice() {
        if(hasDiscount && discountExpiry.after(new Date()))
            return makeDiscount();
        else
            return price;
	}

	public void setPrice(double price) {
        if (price < 0)
            throw new IllegalArgumentException("Price cannot be negative");
        else
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
        if (discountValue < 0 || discountValue > 1)
            throw new IllegalArgumentException("Discount value must be between 0 and 1");
        else
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

    public void decrementAvailable() {
        if(numberOfAvailable > 0)
            numberOfAvailable--;
        else
            throw new IllegalArgumentException("No more available products");
    }

    @Override
    public int compareTo(Product o) {
        if(this.getPrice() > o.getPrice())
            return 1;
        else if(this.getPrice() < o.getPrice())
            return -1;
        else
            return 0;
    }


}
