package eshopping.com.eshopping.com.DTO;

import lombok.*;

import java.util.List;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
 @Builder
public class DtowithCount{
    public List<ProductItemDTO> maindata;
    public int Total;





}
