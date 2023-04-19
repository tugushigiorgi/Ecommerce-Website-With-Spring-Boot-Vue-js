package eshopping.com.eshopping.com.DATABASE.JPA;

import eshopping.com.eshopping.com.DATABASE.Models.FileMetadata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ImageJPA extends JpaRepository<FileMetadata,Long> {
    @Modifying
    @Transactional
    @Query(value = "Delete From product_images where  product_model_id=:product_model_id",nativeQuery = true)
    void DeleteImagesByProductID(Long  product_model_id);


    @Modifying
    @Transactional
    @Query(value="Delete From product_images where id=:id",nativeQuery = true)
    void DeleteImageByID(Long id );
}
