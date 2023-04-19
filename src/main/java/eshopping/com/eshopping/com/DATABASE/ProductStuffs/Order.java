package eshopping.com.eshopping.com.DATABASE.ProductStuffs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import eshopping.com.eshopping.com.DATABASE.Models.UserModel;
import eshopping.com.eshopping.com.DATABASE.Models.productCart;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter
@Table(name = "orderTable")
@Entity
public class Order {

    @Id
    @GeneratedValue()
    private Long id;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JsonIgnore
    @JoinColumn
    private UserModel user;


    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDateTime;


    private float   TotalPrice=0;


    private String Status ;

    @OneToMany(mappedBy = "order",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<productCart> productOrders=new ArrayList<>();


    public void AddProduct(  productCart  productCart ){
        this.productOrders.add(productCart );

        productCart .setOrder(this);







    }

    public void  setUser( UserModel user){


            this.user = user;





    }



}
