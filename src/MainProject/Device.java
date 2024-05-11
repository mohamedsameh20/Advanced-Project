package MainProject;
import java.util.Date;

public class Device extends Product {
    private String model;

    public Device(String pn, double p, int noa, String m, boolean hd,
    			  double dv, Date de, String ml,String genre ,String url) throws Exception{         //new
        super(pn, p, noa, m, hd, dv, de, genre,url);        //new
        
        if (ml == null || ml.isBlank() || ml.isEmpty()) {
        throw new Exception("Model can't be empty !!");}
        
        this.model = ml;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) throws Exception{
    	
    	if (model == null || model.isEmpty()) {  //new
    	throw new Exception("Model can't be empty !!");}
    	
        this.model = model;
    }

    @Override
    public void displayInfo() {
        System.out.println("Device name is: "+getProductName()+", of ID: "+getiD()+", Its price = "+getPrice()+
        ", number of available: "+getNumberOfAvailable()+", manufacturer: "+getManufacturer()+
        ", does it have discount? --> "+getHasDiscount()+", of "+getDiscountValue()*getPrice()+" LE"+
        ", its expiry date: "+getDiscountExpiry()+", model: "+getModel());
    }

}
