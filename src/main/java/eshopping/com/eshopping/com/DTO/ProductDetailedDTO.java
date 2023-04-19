package eshopping.com.eshopping.com.DTO;

import eshopping.com.eshopping.com.DATABASE.Models.Productspecs;
import lombok.*;

import java.io.Serializable;
import java.util.List;
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDetailedDTO implements Serializable  {
    public long id;
    public String ProductName;
    public String Price;
    public List<String> Images;
   public String Description;
   public List<Productspecs> specs;




}
