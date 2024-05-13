package MainProject;
import java.util.ArrayList;

public class Store {
  private String storeName;
  private double balance;
  private ArrayList<Product> productsList = new ArrayList<>();
  private ArrayList<User> usersList = new ArrayList<>();

  public Store() {
    // No-Arg Constructor
  }

  public Store(String storeName, double balance) { // Parameterized Constructor
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
    productsList.add(product); 
  }

  public ArrayList<Product> getProducts() {
    return productsList;
  }

  public void addUser(User user) {
    usersList.add(user); 
  }

  public ArrayList<User> getUsers() { 
    return usersList;
  }

  public void sell(Product product, User user) {
    // Check if the product is available in the store
    boolean productAvailable = productsList.contains(product);  

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
    product.decrementAvailable();
    

    // Updating store balance
    balance += product.getPrice();

    // Updating User balance
    user.setBalance(user.getBalance() - product.getPrice());

    // Updating user purchased products 
    Product[] oldUserPurchasedProducts = new Product[user.getPurchasedProducts().length];
    Product[] newUserPurchasedProducts = new Product[oldUserPurchasedProducts.length + 1];

    for (int i = 0; i < user.getPurchasedProducts().length; i++) {
      oldUserPurchasedProducts[i] = user.getPurchasedProducts()[i];
    }

    
    public void sell(Product product, User user) throws IllegalArgumentException {  //this method sells the user a product
       //Checking if the product is available in the store
        if(user.getBalance()<product.getPrice()){
            throw new IllegalArgumentException("Insufficient Balance!!\n" + "Please refile your balance ");
        }
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
       Product[] oldUserPurchasedProducts = new Product[user.getPurchasedProducts().length];            
       Product[] newUserPurchasedProducts = new Product[oldUserPurchasedProducts.length +1];

        for(int i=0; i<user.getPurchasedProducts().length; i++) {                                       
            oldUserPurchasedProducts[i]= user.getPurchasedProducts()[i];                               
        }                                                                                               

       for(int i=0; i<oldUserPurchasedProducts.length; i++) {
         newUserPurchasedProducts[i]= oldUserPurchasedProducts[i];   //Copying previous products
       }
       newUserPurchasedProducts[oldUserPurchasedProducts.length] = product;   // Adding the new product
     
       user.setPurchasedProducts(newUserPurchasedProducts); 
       System.out.println("Product sold successfully to " + user.getName());


    for (int i = 0; i < oldUserPurchasedProducts.length; i++) {
      newUserPurchasedProducts[i] = oldUserPurchasedProducts[i]; // Copying previous products
    }
    newUserPurchasedProducts[oldUserPurchasedProducts.length] = product;  // Adding the new product
    user.setPurchasedProducts(newUserPurchasedProducts);
    System.out.println("Product sold successfully to " + user.getName());
  }
  

  public Boolean sell(User user) {  //this method sells the user the items in his shopping cart
      Cart userCart = user.getShoppingCart();
  	  ArrayList<Product> cartProducts = userCart.getCartProducts();

      if(cartProducts.isEmpty()){return false;}                                                                       

  	  double totalCartPrice = userCart.calculateTotal();

      // Checking if the user has enough balance to purchase all products in the cart
  	 if (totalCartPrice > user.getBalance()) {
  	  System.out.println("Insufficient balance to purchase all products in the cart.");
  	 }
    

      // Selling each product in the cart
      int i =0;
      boolean flag = true;
      while (flag){                                                                  
        double productPrice = cartProducts.get(i).getPrice();
        user.setBalance(user.getBalance() - productPrice);   // Reducing user's balance
  	    balance += productPrice;     // Increasing store's balance
        cartProducts.get(i).setNumberOfAvailable(cartProducts.get(i).getNumberOfAvailable() -1);  // Decrease the product quantity from the store
  	    userCart.removeProduct(cartProducts.get(i));  // Clear the product from the user's cart
        if(cartProducts.isEmpty()){flag = false;}
  	   }
      return true;
  } 	    

    
    public void sell(User user) throws IndexOutOfBoundsException {  //this method sells the user the items in his shopping cart
        Cart userCart = user.getShoppingCart();
    	ArrayList<Product> cartProducts = userCart.getCartProducts();

        if(cartProducts.isEmpty()){ throw new IndexOutOfBoundsException();}

    	double totalCartPrice = userCart.calculateTotal();

        // Checking if the user has enough balance to purchase all products in the cart
    	if (totalCartPrice > user.getBalance()) {
    	  System.out.println("Insufficient balance to purchase all products in the cart.");
    	}
        // Selling each product in the cart
        int i =0;
        boolean flag = true;
        while (flag){                                                                  
            double productPrice = cartProducts.get(i).getPrice();
            user.setBalance(user.getBalance() - productPrice);   // Reducing user's balance
    	    balance += productPrice;     // Increasing store's balance
            cartProducts.get(i).setNumberOfAvailable(cartProducts.get(i).getNumberOfAvailable() -1);  // Decrease the product quantity from the store
    	    userCart.removeProduct(cartProducts.get(i));  // Clear the product from the user's cart
            if(cartProducts.isEmpty()){flag = false;}
    	}

    }

}
