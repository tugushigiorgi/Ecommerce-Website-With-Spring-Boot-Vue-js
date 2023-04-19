package eshopping.com.eshopping.com.DATABASE.JPA;

import eshopping.com.eshopping.com.DATABASE.Models.BannerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BannerJPA extends JpaRepository<BannerModel,Long> {



    @Query(value = "Select * from banner_model ",nativeQuery = true)
    List<BannerModel> GetAllBanners();

    @Transactional
    @Modifying
    @Query(value = "Delete from banner_model where id=:id",nativeQuery = true)
    void DeleteBannerById(String id );



}
