package kve.ru.musicshop;

public class Order {
  private String clientName;
  private String good;
  private int quantity;
  private  double orderPrice;

  public Order(String clientName, String good, int quantity, double orderPrice) {
    this.clientName = clientName;
    this.good = good;
    this.quantity = quantity;
    this.orderPrice = orderPrice;
  }

  public String getClientName() {
    return clientName;
  }

  public void setClientName(String clientName) {
    this.clientName = clientName;
  }

  public String getGood() {
    return good;
  }

  public void setGood(String good) {
    this.good = good;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public double getOrderPrice() {
    return orderPrice;
  }

  public void setOrderPrice(double orderPrice) {
    this.orderPrice = orderPrice;
  }
}
