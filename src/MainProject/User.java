package MainProject;
import java.lang.Comparable;
import java.util.ArrayList;

public class User implements Comparable<User> {
    private String name;
    private String email;
    private double balance;
    private Cart shoppingCart = new Cart(new ArrayList<Product>());
    private Product[] purchasedProducts = {};  
    
    public User(){
      //No-Arg Constructor    
    }
    
    public User(String name, String email, double balance){  //Parameterized Constructor
    this.name = name;
    this.email = email;
    this.balance = balance;
    }
    
    
    
    public String getName(){
     return name;     
    }
     
    
    public void setName(String name){
     this.name = name;     
    }
    
    
    public String getEmail(){
     return email;     
    }
     
     
     
    public void setEmail(String email){
     this.email = email;     
    }
     
     
     
    public double getBalance(){
     return balance;     
    }
     
    
    
    public void setBalance(double balance){
     this.balance = balance;      
    }
    
    
    public Cart getShoppingCart(){
     return shoppingCart;     
    }
    
    public Product[] getPurchasedProducts(){
     return purchasedProducts;     
    }

    public void setPurchasedProducts(Product[] arr){
        this.purchasedProducts = arr;
    }

    public double totalPrice(){
        double totalSum = 0;
        for(int i =0;i<shoppingCart.getCartProducts().size();i++){
            totalSum+= shoppingCart.getCartProducts().get(i).getPrice();
        }
        return totalSum;
    }

    
    @Override
    //Implementation of compareTo method defined in the Comparable interface
    public int compareTo(User user){
        if (getBalance() > user.getBalance())
         return 1;
        
        else if(getBalance() < user.getBalance())
        return -1;
         
        else 
        return 0;    
    }
    
}
