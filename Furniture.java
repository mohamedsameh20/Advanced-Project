public class Furniture{
    private String material;
    private String color;

    Furniture(){}
    Furniture(String fMaterial, String fColor ,String pName,double pPrice 
            ,int pNumberOfAvailable,String pManufacturer ,boolean pHasDiscount, double pDiscountValue ,Date pDicountExpiry ){

                super(pName, pPrice,pNumberOfAvailable,pManufacturer,pHasDiscount,pDiscountValue,pDicountExpiry); 
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
    void dispalyInfo(){
        System.out.println(this.material + "\n"+ this.color  +"\n"+ super.productName +"\n"+ super.price +"\n"
        +"\n"+ super.numberOfAvailable +"\n"+super.iD +"\n"+ super.manufacturer +"\n"+ super.hasDiscount +"\n"+
        super.discountValue +"\n"+ super.discountExpiry )
    }



}

