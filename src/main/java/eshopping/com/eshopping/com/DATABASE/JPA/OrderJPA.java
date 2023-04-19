package eshopping.com.eshopping.com.DATABASE.JPA;

import eshopping.com.eshopping.com.DATABASE.ProductStuffs.Order;
import eshopping.com.eshopping.com.DTO.ReportModel;
import jakarta.persistence.Tuple;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public  interface OrderJPA  extends JpaRepository<Order,Long> {

    @Query(value = "Select * from order_table where id=:id", nativeQuery = true)
    Order FindByID(Long id);


    @Query(value = "Select * from order_table where status='Delivered'", nativeQuery = true)
    Page<Order> getDeliveredOrders(Pageable pageable);


    @Query(value = "  SELECT COUNT(*) AS total_quantity from order_table where status='Delivered'", nativeQuery = true)
    int GetTotalDeliveredOrdersCount();


    @Query(value = "Select * from order_table where status='Cancelled'", nativeQuery = true)
    Page<Order> getCanceledOrders(Pageable pageable);


    @Query(value = "  SELECT COUNT(*) AS total_quantity from order_table where  status='Cancelled'", nativeQuery = true)
    int GetTotalCanceledOrdersCount();


    @Query(value = "Select * from order_table where status='Running Late' or status='Preparing to Deliver' or status='courier is coming' ", nativeQuery = true)
    Page<Order> getCurrentOrders(Pageable pageable);


    @Query(value = "  SELECT COUNT(*) AS total_quantity from order_table   where status='Running Late' or status='Preparing to Deliver' or status='courier is coming'", nativeQuery = true)
    int GetTotalCurrentOrdersCount();


    @Query(value = "SELECT COUNT(*) AS total_quantity from order_table   where user_id=:userid", nativeQuery = true)
    int CountUserOrders(String userid);


    @Query(value = "Select * from order_table where user_id=:userid", nativeQuery = true)
    List<Order> GetUserAllOrders(Long userid);


    @Query(value = "Select * from order_table where user_id=:userid and status='Preparing to Deliver' or status='courier is coming' or status='Running Late'", nativeQuery = true)
    Page<Order> GetuserCurrentOrders(Long userid, Pageable pageable);

    @Query(value = "SELECT COUNT(*) AS total_quantity from order_table  where user_id=:userid and status='Preparing to Deliver' or status='courier is coming' or status='Running Late' ", nativeQuery = true)
    int coutuserCurrentorders(Long userid);


    @Query(value = "Select * from order_table where user_id=:userid and status='Delivered'", nativeQuery = true)
    Page<Order> GetuserDeliveredrders(Long userid, Pageable pageable);


    @Query(value = "Select  COUNT(*) AS total_quantity from order_table where user_id=:userid and status='Delivered'", nativeQuery = true)
    int countuserdeliveredorders(Long userid);


    @Query(value = "Select * from order_table where user_id=:userid and status='Cancelled'", nativeQuery = true)
    Page<Order> GetuserCanceleddrders(Long userid, Pageable pageable);


    @Query(value = "Select   COUNT(*) AS total_quantity from order_table where user_id=:userid and status='Cancelled'", nativeQuery = true)
    int countusercanceledorders(Long userid);


//    @Query(value = "SELECT DATE_FORMAT(creation_date_time, '%M') AS month_name, COUNT(*) AS total_orders, MAX(creation_date_time) AS max_creation_date_time\n" +
//            "FROM order_table \n" +
//            "GROUP BY DATE_FORMAT(creation_date_time, '%Y-%m'), creation_date_time", nativeQuery = true)
//    List<Tuple> reportrevenue();

    @Query(value = "SELECT DATE_FORMAT(creation_date_time, '%M') AS month_name, COUNT(*) AS total_orders,  SUM(total_price)" +
            "FROM order_table \n" +
            "GROUP BY DATE_FORMAT(creation_date_time, '%Y-%m'), creation_date_time", nativeQuery = true)
    List<Tuple> reportrevenue();




}