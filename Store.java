import java.util.ArrayList;

public class Store {
 
	private String storeName;
	private double balance;
	private Product[] productsList;
	private User[] usersList;
	
	
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
    	
      Product[] newProductList = new Product[productsList.length +1]; // Resizing the array of products to add new product
     
      for(int i=0; i<productsList.length; i++) {
        newProductList[i]= productsList[i];	   //Copying previous products
        }
      
      newProductList[productsList.length] = product;   // Adding the new product
      
      productsList = newProductList;
    } 
    

    public void addUser(User user) {
    	
      User[] newUsersList = new User[usersList.length +1];
      
      for(int i=0; i<usersList.length; i++) {
        newUsersList[i]= usersList[i];	   //Copying previous Users
        }
      
      newUsersList[usersList.length] = user;   // Adding the new user
        
      usersList =newUsersList;
    }

    
 
    public void sell(Product product, User user) {  //this method sells the user a product
    	
    	//checking if the product is available in the store 
         boolean productAvailable = false;
         for (int i =0; i< productsList.length; i++) {
            Product p = productsList[i];
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
         if (user.getBalance() < product.getProductPrice()) {
           System.out.println("Insufficient balance, You can not buy this product.");
             return;
         }

         // Updating product quantity
         for (int i =0; i< productsList.length; i++) {
             Product p = productsList[i];
             if (p.equals(product)) {
              int x = p.getNumberOfAvailable();
              x-=1;
              p.setNumberOfAvailable(x); 
              break;
              }
         }

         
      // Updating store balance
         balance += product.getProductPrice();

         
      // Updating User balance
        user.setBalance(user.getBalance() - product.getProductPrice());
       
     //Updating user purchased products
        Product[] oldUserPurchasedProducts = user.getPurchasedProducts();
        Product[] newUserPurchasedProducts = new Product[oldUserPurchasedProducts.length +1];
        for(int i=0; i<oldUserPurchasedProducts.length; i++) {
        	newUserPurchasedProducts[i]= oldUserPurchasedProducts[i];	   //Copying previous products
        }
           newUserPurchasedProducts[oldUserPurchasedProducts.length] = product;   // Adding the new product
        	      
          user.setPurchasedProducts(newUserPurchasedProducts); 
        
        System.out.println("Product sold successfully to " + user.getName());
    }

  
    
    public void sell(User user) {  //this method sells the user the items in his shopping cart
    	
        Cart userCart = user.getShoppingCart();
    	ArrayList<Product> cartProducts = userCart.getProducts();
    	double totalCartPrice = userCart.calculateTotal();

       // Checking if the user has enough balance to purchase all products in the cart
    	if (totalCartPrice > user.getBalance()) {
    	System.out.println("Insufficient balance to purchase all products in the cart.");
    	return;
    	}

       // Selling each product in the cart
        for (Product product : cartProducts) {
         	double productPrice = product.getPrice();
            user.setBalance(user.getBalance() - productPrice);   // Reducing the user's balance
    	    balance += productPrice;     // Increasing the store's balance
    	    product.setNumberOfAvailable(product.getNumberOfAvailable() -1);  // Decrease the product quantity from the store
    	    userCart.removeProduct(product);  // Clear the product from the user's cart
    	} 
    	     	    
    } 	
}
