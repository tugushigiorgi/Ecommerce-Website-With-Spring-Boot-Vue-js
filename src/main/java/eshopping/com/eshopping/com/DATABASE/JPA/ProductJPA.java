package eshopping.com.eshopping.com.DATABASE.JPA;

import eshopping.com.eshopping.com.DATABASE.Models.ProductModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductJPA extends JpaRepository<ProductModel,Long> {


    @Query(value="SELECT * FROM Product_Model  WHERE slug = :slug", nativeQuery = true)
    ProductModel  getProductModelBySlug(@Param("slug") String slug);

    @Query(value = "Select * from Product_model WHERE id=:id and product_quantity>=1",nativeQuery = true)
    ProductModel FindProductById(@Param("id") Long id);




     @Query(value = "SELECT * FROM product_model WHERE product_price >= :minprice AND product_price <= :maxprice and product_quantity>=1 ORDER BY product_price  desc", nativeQuery = true)
    Page<ProductModel> ProductFilterDESC(@Param("minprice") String minPrice, @Param("maxprice") String maxPrice,
                                         Pageable pageable  );


    @Query(value = "SELECT * FROM product_model WHERE product_price >= :minprice AND product_price <= :maxprice and product_quantity>=1  ORDER BY product_price  ASC", nativeQuery = true)
    Page<ProductModel> ProductFilterASC(@Param("minprice") String minPrice, @Param("maxprice") String maxPrice,
                                          Pageable pageable);

    @Query(value = "SELECT COUNT(*) AS total_quantity FROM product_model",nativeQuery = true)
    int totalcaunt();



    @Query(value = "Select * from product_model",nativeQuery = true)
    Page<ProductModel> GETALLPRODUCTDATA(Pageable page );




    @Query(value = "Select * from product_model where category=:category and  product_price >= :minprice AND product_price <= :maxprice and product_quantity>=1 ORDER BY product_price  ASC  ",nativeQuery = true)
     Page<ProductModel> SearchByCategoryASC (@Param("category") String category ,@Param("minprice") String minprice,@Param("maxprice") String maxprice,Pageable page);



    @Query(value = "Select * from product_model where category=:category and  product_price >= :minprice AND product_price <= :maxprice and product_quantity>=1  ORDER BY product_price  desc ",nativeQuery = true)
    Page<ProductModel> SearchByCategoryDESC(@Param("category") String category ,@Param("minprice") String minprice,@Param("maxprice") String maxprice,Pageable page);



    @Query(value = "SELECT * FROM product_model  WHERE product_name LIKE  %:name% and product_price >= :minprice AND product_price <= :maxprice and product_quantity>=1  ORDER BY product_price  desc  ",nativeQuery = true)
    Page<ProductModel> findproductsbynameDESC(String name, @Param("minprice") String minprice,@Param("maxprice") String maxprice,Pageable page );

    @Query(value = "SELECT * FROM product_model  WHERE product_name LIKE  %:name% and product_price >= :minprice AND product_price <= :maxprice and product_quantity>=1  ORDER BY product_price  asc  ",nativeQuery = true)
    Page<ProductModel> findproductsbynameASC(String name, @Param("minprice") String minprice,@Param("maxprice") String maxprice,Pageable page);






    @Query(value = " SELECT COUNT(*) AS total_quantity FROM product_model  WHERE product_name LIKE  %:name% and product_price >= :minprice AND  product_price <= :maxprice    ",nativeQuery = true)
    int  ByproductsnameItemCount(@Param("name") String  name, @Param("minprice") String minprice,@Param("maxprice") String maxprice );



    @Query(value = " SELECT COUNT(*) AS total_quantity FROM product_model where category=:Category and product_price >= :minprice AND product_price <= :maxprice    ",nativeQuery = true)
    int CategoryItemCount(@Param("Category") String  Category, @Param("minprice") String minprice,@Param("maxprice") String maxprice);






    @Query(value = "SELECT * FROM product_model ORDER BY total_sold DESC    LIMIT 12",nativeQuery = true)
    List<ProductModel> getpopularproducts();



}
