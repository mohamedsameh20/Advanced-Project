package MainProject;
import java.util.*;

public class Clothes extends Product{

    private String material;
    private String color;
    private String fitting;
    private int sSize;
    private int mSize;
    private int lSize;

    Clothes() {
    }

    // may be an exception due to number of clothes and i,m,l size
    Clothes(String c_material, String c_color, String c_fitting, int c_sSize, int c_mSize, int c_lSize,
            String pName, double pPrice, int pNumberOfAvailable, String pManufacturer, boolean pHasDiscount,
            double pDiscountValue, Date pDicountExpiry) {
        super(pName, pPrice, pNumberOfAvailable, pManufacturer, pHasDiscount, pDiscountValue, pDicountExpiry);
        this.material = c_material;
        this.color = c_color;
        this.fitting = c_fitting;
        this.sSize = c_sSize;
        this.mSize = c_mSize;
        this.lSize = c_lSize;
    }

    void setMaterial(String clothesMaterial) {
        this.material = clothesMaterial;
    }

    String getMaterial() {
        return this.material;
    }

    void setColor(String clothesColor) {
        this.color = clothesColor;
    }

    String getColor() {
        return this.color;
    }

    void setFitting(String c_fitting) {
        this.fitting = c_fitting;
    }

    String getFitting() {
        return this.fitting;
    }

    void setSize(char size, int numOfPieces) throws IllegalArgumentException  {
        switch (size) {
            case ('s' | 'S'):
                this.sSize = size;
                break;

            case ('m' | 'M'):
                this.mSize = size;
                break;

            case ('l' | 'L'):
                this.lSize = size;
                break;

            default:throw new IllegalArgumentException(); //throw illegalArgumentException exception 
                break;
        }
    }

    int getSize(char size, int numOfPieces) {
        switch (size) {
            case ('s' | 'S'):
                return this.sSize;
                

            case ('m' | 'M'):
                return this.mSize;
                

            case ('l' | 'L'):
                return this.lSize;
                

            default:throw new IllegalArgumentException(); //throw illegalArgumentException exception  
                
        }
    }

    @Override
    public void displayInfo(){};

}
