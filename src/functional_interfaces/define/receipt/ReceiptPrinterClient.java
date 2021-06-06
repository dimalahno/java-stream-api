package functional_interfaces.define.receipt;

public class ReceiptPrinterClient {
    public static void main(String[] args) {
        ReceiptPrinter simpleReceiptPrinter = new ReceiptPrinter() {
            @Override
            public void print(Receipt receipt) {
                System.out.println("Item :\t" + receipt.item);
                System.out.println("Price:\t" + receipt.price);
                System.out.println("Disc:\t" + receipt.discount);
                System.out.println("Tax:\t" + receipt.tax);
                System.out.println("Total:\t" + computeTotal(receipt));
            }
        };

        ReceiptPrinter exemptReceiptPrinter = new ReceiptPrinter() {
            @Override
            public void print(Receipt receipt) {
                System.out.println("Item :\t" + receipt.item);
                System.out.println("Price:\t" + receipt.price);
                System.out.println("Disc:\t" + receipt.discount);
                System.out.println("Total:\t" + computeTotal(receipt));
            }

            @Override
            public double computeTotal(Receipt receipt) {
                return receipt.price - (receipt.price * receipt.discount);
            }
        };
        Receipt receipt = new Receipt("shirt", 20.00, 0.05, 0.07);

        simpleReceiptPrinter.print(receipt);

        System.out.println("---");

        exemptReceiptPrinter.print(receipt);
    }
}
