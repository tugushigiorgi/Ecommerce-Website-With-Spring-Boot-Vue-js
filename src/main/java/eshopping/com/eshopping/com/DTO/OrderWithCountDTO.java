package eshopping.com.eshopping.com.DTO;

import lombok.*;

import java.util.List;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderWithCountDTO {


    List<OrderDto> maindata;
    int total;





}
