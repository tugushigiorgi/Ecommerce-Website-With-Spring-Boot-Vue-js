package eshopping.com.eshopping.com.DTO;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ProductCartDTO    {
    private Long Id;
    private String productName;
    private  String ProductImage;
    private float  productPrice;
    private int productQuantity;
    private float  totalPrice;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductCartDTO cartDTO = (ProductCartDTO) o;
        return Float.compare(cartDTO.productPrice, productPrice) == 0 && productQuantity == cartDTO.productQuantity && Float.compare(cartDTO.totalPrice, totalPrice) == 0 && Objects.equals(Id, cartDTO.Id) && Objects.equals(productName, cartDTO.productName) && Objects.equals(ProductImage, cartDTO.ProductImage);
    }

}
