import java.util.Date;

public class Furniture extends Product{
    private String material;
    private String color;

    Furniture(){}
    Furniture(String pName,double pPrice,int pNumberOfAvailable,String pManufacturer ,boolean pHasDiscount,
              double pDiscountValue ,Date pDiscountExpiry,String fMaterial, String fColor ,String genre ,String url ){         //new

                super(pName, pPrice,pNumberOfAvailable,pManufacturer,pHasDiscount,pDiscountValue,pDiscountExpiry,genre,url);        //new
                this.material = fMaterial;
                this.color = fColor;
    }

    void setMaterial (String furMaterial){
        this.material=furMaterial;
    }

    String getMaterial (){
        return this.material;
    }

    void setColor(String furColor){
        this.color = furColor;
    }

    String getColor (){
        return this.color;
    }

    @Override
    public void displayInfo(){
        System.out.println(this.material + "\n"+ this.color  +"\n"+ super.getProductName() +"\n"+ super.getPrice() +"\n"
        +"\n"+ super.getNumberOfAvailable() +"\n"+super.getiD() +"\n"+ super.getManufacturer() +"\n"+ super.isHasDiscount() +"\n"+
        super.getDiscountValue() +"\n"+ super.getDiscountExpiry() );
    }



}

