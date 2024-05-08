//import java.lang.Cloneable;
//import java.lang.Comparable;
//
//
//
//interface Sellable<Product>{
//   void sellProduct();
//}
//
//interface Discountable<Product>{
//   void makeDiscount(double discount,Product discountProduct);
//}
//
//public class Interfaces implements Cloneable,Comparable<Product>,Comparable<User>,Sellable<Product>,Discountable<Product> {
//
//   public Object clone() throws CloneNotSupportedException {
//      return super.clone();
//   }
//
//   public int compareTo(Product P) {
//      if (P.price == this.price)
//         return 0;
//      else if (P.price < this.price)
//         return 1;
//      else
//         return -1;
//
//   }
//
//   public int compareTo(User U) {
//      if (U.purchasedProducts.length == this.purchasedProducts.length)
//         return 0;
//      else if (U.purchasedProducts.length < this.purchasedProducts.length)
//         return 1;
//      else
//         return -1;
//
//   }
//
//   public void sellProduct() {
//      setNumberOfAvailable(this.numberOfAvailable -= 1);
//
//   }
//
//   public void makeDiscount(double discount,Product discountProduct){
//
//      discountProduct.discountValue = discountProduct.price * discount/100;
//
//
//   }
//}
//
//
//
//
//
//
//
//
//
