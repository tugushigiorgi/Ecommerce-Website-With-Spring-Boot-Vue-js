package eshopping.com.eshopping.com.DATABASE.JPA;

import eshopping.com.eshopping.com.DATABASE.Models.Productspecs;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface ProductSpecsJPA extends JpaRepository<Productspecs,Long> {
    @Modifying
    @Transactional
    @Query(value = "Delete From productspecs where  product_model_id=:product_model_id",nativeQuery = true)
    void DeleteSpecsByProductID( Long  product_model_id);

}
