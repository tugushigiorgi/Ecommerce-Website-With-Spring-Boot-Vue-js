package eshopping.com.eshopping.com.DATABASE.JPA;

import eshopping.com.eshopping.com.DATABASE.Models.productCart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  productCartJPA extends JpaRepository<productCart,Long> {



    @Query(value = "Select * from product_cart where user_id=:userid and order_id IS NULL ",nativeQuery = true)
    List<productCart> getUserCart(Long userid);


    @Query(value = "Select * from product_cart where id=:id",nativeQuery = true)
    productCart findbyID(Long id );



}
