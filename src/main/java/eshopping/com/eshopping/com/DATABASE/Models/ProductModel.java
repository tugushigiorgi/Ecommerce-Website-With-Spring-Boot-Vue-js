package eshopping.com.eshopping.com.DATABASE.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import com.github.slugify.Slugify;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter
@Setter
public class ProductModel {
    @Id
    @GeneratedValue
    public Long ID;

    public String ProductName;

    public float ProductPrice;
    public float ProductSalePrice;
    public int ProductQuantity;
    public String Category;

    public String Description;

    public int TotalSold;



    @OneToMany(mappedBy = "productModel",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    public List<Productspecs> specs=new ArrayList<>();

    @OneToMany(mappedBy = "productModel",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    public List<FileMetadata> Pictures=new ArrayList<>();

    public String Slug;

    @OneToMany(mappedBy = "product",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<productCart> productOrders=new ArrayList<>();



    public void  AddproductOrder( productCart orders){

        productOrders.add(orders);
       orders.setProduct(this);


    }


    public ProductModel(String productName, float productPrice, float productSalePrice, int productQuantity, String category, String description, List<Productspecs> specs, List<FileMetadata> pictures, String slug, List<productCart> productOrders) {
        ProductName = productName;
        ProductPrice = productPrice;
        ProductSalePrice = productSalePrice;
        ProductQuantity = productQuantity;
        Category = category;
        Description = description;
        this.specs = specs;
        Pictures = pictures;
        Slug = slug;
        this.productOrders = productOrders;
    }

    public void setPictures(FileMetadata  pictures) {

        pictures.setProductModel(this);
        Pictures.add(pictures);
    }



    public void setSpecs(Productspecs spec){
        spec.setProductModel(this);
        specs.add(spec);


    }

    public ProductModel(String productName, float productPrice, float productSalePrice, int productQuantity, String category, String description ) {
        ProductName = productName;
        ProductPrice = productPrice;
        ProductSalePrice = productSalePrice;
        ProductQuantity = productQuantity;
        Category = category;
        Description = description;

        Slugify slugify = new Slugify();
        Slug  = slugify.slugify(ProductName);


    }




    public ProductModel(Long ID, String productName, float productPrice, float productSalePrice, int productQuantity, String category, String description, List<Productspecs> specs, List<FileMetadata> pictures, String slug) {
        this.ID = ID;
        ProductName = productName;
        ProductPrice = productPrice;
        ProductSalePrice = productSalePrice;
        ProductQuantity = productQuantity;
        Category = category;
        Description = description;
        this.specs = specs;
        Pictures = pictures;
        Slug = slug;
    }


}
