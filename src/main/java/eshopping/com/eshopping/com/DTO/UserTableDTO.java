package eshopping.com.eshopping.com.DTO;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserTableDTO {

   public  long userid;
    public  String FullName;
    public String Emaill;
    public String Phone;

    public String Address;



    int TotalOrders;





}
