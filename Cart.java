package p1;
import java.util.ArrayList;
public class Cart {
	private ArrayList<Product> cartProducts;
	Cart(){}
	public Cart(ArrayList<Product> cartProducts) {
		this.cartProducts = cartProducts;
	}
	public void addProducts(Product product) {
		cartProducts.add(product);
	}
	public void removeProduct(Product product) {
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
		this.cartProducts = cartProducts;
	}
}
