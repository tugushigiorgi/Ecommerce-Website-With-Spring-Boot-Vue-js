package eshopping.com.eshopping.com.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UsercartsWithBallance {

   public  List<ProductCartDTO> maindata;
     public double UserBallance;




}
