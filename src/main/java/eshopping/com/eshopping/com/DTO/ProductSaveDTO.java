package eshopping.com.eshopping.com.DTO;

import eshopping.com.eshopping.com.DATABASE.Models.FileMetadata;
import lombok.*;

import java.util.HashMap;
import java.util.List;
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductSaveDTO {

    public String ProductName;

    public String ProductPrice;
    public String ProductSalePrice;
    public String ProductQuantity;
    public String Category;

    public String Description;
    public List<FileMetadata> images;

    public HashMap<String, String>  Specs;


}
