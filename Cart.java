package p1;
import java.util.ArrayList;
public class Cart {
	private ArrayList<Product> cartProducts;
	Cart(){}
	public Cart(ArrayList<Product> cartProducts) {
		
		if (cartProducts == null) {	
       		throw new IllegalArgumentException("Cart products can't be null !!");}
		
		this.cartProducts = cartProducts;
	}
	public void addProducts(Product product) {
		
		if (product == null) {
		throw new IllegalArgumentException("Product can't be null !!");}
		
		cartProducts.add(product);
	}
	public void removeProduct(Product product) {
		
		if (product == null) {
		throw new IllegalArgumentException("Product can't be null !!");}
		if (!cartProducts.contains(product)) {
		throw new IllegalArgumentException("Product not found !!");}
		
		cartProducts.remove(product);
	}
	public double calculateTotal() {
        double total = 0;
        for (Product product : cartProducts) {
            total += product.getPrice();
        }
        return total;
    }
	public ArrayList<Product> getCartProducts() {
		return cartProducts;
	}
	public void setCartProducts(ArrayList<Product> cartProducts) {
		
		if (cartProducts == null) {
		throw new IllegalArgumentException("Cart products can't be null !!");}
		
		this.cartProducts = cartProducts;
	}
}
