package eshopping.com.eshopping.com.DATABASE.JPA;

import eshopping.com.eshopping.com.DATABASE.Models.UserModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface USERJPA extends JpaRepository<UserModel,Long > {


    @Query(value = "Select * from user  where id=:ID",nativeQuery = true)
    @Transactional
    UserModel FindByID(Long ID);




    @Query(value = "Select * from user ",nativeQuery = true)
    Page<UserModel> GetUsers(Pageable pageable);


    @Query(value = " SELECT COUNT(*) AS total_quantity from user ",nativeQuery = true)
    int CountUsers();


    @Query(value = "Select * From user where fullname  LIKE  %:name%",nativeQuery = true)
    UserModel FindUserByName(String name );


    @Query(value = "Select * From user where email=:email",nativeQuery = true)
    UserModel FindUserWithEmail(String email );


}
