package eshopping.com.eshopping.com.DTO;

import lombok.*;

@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class productcartmodelDTO {



    private Long  ProtuctID  ;
    private Long ORDERID;
    public Long USERID;
    private String UserName;
    private String  ProductName;


    private float  productListPrice;
    private int productQuantity;
    private float  totalPrice;

    private  float TOTAL_ORDER_PRICE;












}
