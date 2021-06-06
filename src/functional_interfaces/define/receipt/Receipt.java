package functional_interfaces.define.receipt;

public class Receipt {
    String item;
    double price;
    double discount;
    double tax;

    public Receipt(String item, double price, double discount, double tax) {
        this.item = item;
        this.price = price;
        this.discount = discount;
        this.tax = tax;
    }

    public Receipt(Receipt r){
        item = r.item;
        price = r.price;
        discount = r.discount;
        tax = r.tax;
    }
}
