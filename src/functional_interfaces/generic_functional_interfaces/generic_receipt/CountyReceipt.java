package functional_interfaces.generic_functional_interfaces.generic_receipt;

public class CountyReceipt extends Receipt{

    double countyTax;

    public CountyReceipt(Receipt r, double countyTax) {
        super(r);
        this.countyTax = countyTax;
    }
}
