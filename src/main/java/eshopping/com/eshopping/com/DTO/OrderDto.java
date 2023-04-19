package eshopping.com.eshopping.com.DTO;

import lombok.*;

import java.util.Date;
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDto {


        public Long id;
        public
        String OrderDate;
        public float TotalPrice;
        public String UserFullname;
        public String UserEmail;
        public String UserPhone;
        public String UserAddress;

        public String OrderStatus;










}
