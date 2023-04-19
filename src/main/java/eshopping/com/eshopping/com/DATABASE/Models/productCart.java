package eshopping.com.eshopping.com.DATABASE.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import eshopping.com.eshopping.com.DATABASE.ProductStuffs.Order;
import jakarta.persistence.*;
import lombok.*;

@Entity

@NoArgsConstructor
@Builder

@Getter
@Setter

public class productCart {

    @Id
    @GeneratedValue
    private long ID;



    @JsonIgnore
    @JoinColumn
    @ManyToOne(fetch =  FetchType.LAZY)
    private  ProductModel product;

    @JsonIgnore
    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn
    private UserModel  user;


    @JsonIgnore

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;



    @Column(name = "quantity")
    private int quantity;


    private float  ListPrice;

    private float  totalPrice;





    public productCart(long ID, ProductModel product, UserModel user, Order order, int quantity, float listPrice, float totalPrice) {
        this.ID = ID;
        this.product = product;
        this.user = user;
        this.order = order;
        this.quantity = quantity;
        ListPrice = listPrice;
        this.totalPrice =totalPrice;
    }

    public void setToOrder(Order order){
        this.order=order;


    }





}
