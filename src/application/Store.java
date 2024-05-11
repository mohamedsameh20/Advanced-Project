import java.util.ArrayList;

public class Store {
    private String storeName;
    private double balance;
    private Product[] productsList = {};
    private User[] usersList = {};
    
    public Store(){
      //No-Arg Constructor
    }
    
    public Store(String storeName, double balance){ //parameterized Constructor
     this.storeName = storeName;
     this.balance = balance;
    }
    
    public void setStoreName(String storeName) {
     this.storeName = storeName;
    }

	
    public String getStoreName() {
     return storeName;
    }
    
    
    public void setBalance(double balance) {
     this.balance = balance;
    }

    
    public double getBalance() {
     return balance;
    }


    
    
    public void addProduct(Product product) {
       // Resizing the array of products to add a new product   
       Product[] newProductList = new Product[productsList.length +1]; 
     
       for(int i=0; i<productsList.length; i++) {
        newProductList[i]= productsList[i];  //Copying previous products
       }
     
       newProductList[productsList.length] = product;  // Adding the new product
       productsList = newProductList;
    }
    public Product[] getProducts() {return productsList; }
    
    public void addUser(User user) {
       // Resizing the array of Users to add a new user
       User[] newUsersList = new User[usersList.length +1];
      
       for(int i=0; i<usersList.length; i++) {
        newUsersList[i]= usersList[i];	//Copying previous Users
       }
      
       newUsersList[usersList.length] = user;  // Adding the new user   
       usersList = newUsersList;
    }
    
    
    public void sell(Product product, User user) {  //this method sells the user a product	
       //Checking if the product is available in the store 
       boolean productAvailable = false;
     
       for (Product p : productsList) {
          if (p.equals(product)) {
            productAvailable = true;
            break;
          }
       }

       if (!productAvailable) {
         System.out.println("This Product is not available in the store.");
         return;
       }

         
       // Check if the user has enough balance to buy this product
       if (user.getBalance() < product.getPrice()) {
         System.out.println("Insufficient balance, You can not buy this product.");
         return;
       }

     
       // Updating product quantity
       for (Product p : productsList ) {
          if (p.equals(product)) {
            int x = p.getNumberOfAvailable();
            x-=1;
            p.setNumberOfAvailable(x); 
            break;
          }
       }


       // Updating store balance
       balance += product.getPrice();

       // Updating User balance
       user.setBalance(user.getBalance() - product.getPrice());

       //Updating user purchased products
       Product[] oldUserPurchasedProducts = new Product[user.getPurchasedProducts().length];            // new
       Product[] newUserPurchasedProducts = new Product[oldUserPurchasedProducts.length +1];

        for(int i=0; i<user.getPurchasedProducts().length; i++) {                                       // new
            oldUserPurchasedProducts[i]= user.getPurchasedProducts()[i];                                // new
        }                                                                                               // new

       for(int i=0; i<oldUserPurchasedProducts.length; i++) {
         newUserPurchasedProducts[i]= oldUserPurchasedProducts[i];   //Copying previous products
       }
       newUserPurchasedProducts[oldUserPurchasedProducts.length] = product;   // Adding the new product
     
       user.setPurchasedProducts(newUserPurchasedProducts); 
       System.out.println("Product sold successfully to " + user.getName());

    }
    
    
    
    public Boolean sell(User user) {  //this method sells the user the items in his shopping cart
        Cart userCart = user.getShoppingCart();
    	ArrayList<Product> cartProducts = userCart.getCartProducts();

        if(cartProducts.isEmpty()){return false;}                                                                       //new

    	double totalCartPrice = userCart.calculateTotal();

        // Checking if the user has enough balance to purchase all products in the cart
    	if (totalCartPrice > user.getBalance()) {
    	  System.out.println("Insufficient balance to purchase all products in the cart.");
    	}
        // Selling each product in the cart
        int i =0;
        boolean flag = true;
        while (flag){                                                                   //new
            double productPrice = cartProducts.get(i).getPrice();
            user.setBalance(user.getBalance() - productPrice);   // Reducing user's balance
    	    balance += productPrice;     // Increasing store's balance
            cartProducts.get(i).setNumberOfAvailable(cartProducts.get(i).getNumberOfAvailable() -1);  // Decrease the product quantity from the store
    	    userCart.removeProduct(cartProducts.get(i));  // Clear the product from the user's cart
            if(cartProducts.isEmpty()){flag = false;}
    	}
        return true;
    } 	    
}
