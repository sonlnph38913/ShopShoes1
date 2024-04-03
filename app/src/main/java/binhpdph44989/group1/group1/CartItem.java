package binhpdph44989.group1.group1;

import binhpdph44989.group1.group1.model.Giay;

public class CartItem {
    private Giay product;
    private int quantity;

    public CartItem() {
    }

    public CartItem(Giay product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Giay getProduct() {
        return product;
    }

    public void setProduct(Giay product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
