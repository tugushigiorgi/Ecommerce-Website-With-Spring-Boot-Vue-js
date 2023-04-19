package eshopping.com.eshopping.com.DATABASE.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import eshopping.com.eshopping.com.DATABASE.Models.ProductModel;
import jakarta.persistence.*;

@Entity
public class Productspecs {
    @Id
    @GeneratedValue
    public Long ID;


    public String Specification_name;
    @Column( length = 2000)

    public  String Specification_value;

    @ManyToOne(cascade = CascadeType.ALL,fetch =FetchType.LAZY )
    @JsonIgnore
    @JoinColumn()
    public ProductModel productModel;
public Productspecs(){

}

    public Productspecs(String specification_name, String specification_value) {
        Specification_name = specification_name;
        Specification_value = specification_value;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public void setSpecification_name(String specification_name) {
        Specification_name = specification_name;
    }

    public void setSpecification_value(String specification_value) {
        Specification_value = specification_value;
    }

    public Long getID() {
        return ID;
    }

    public String getSpecification_name() {
        return Specification_name;
    }

    public String getSpecification_value() {



                return Specification_value ;
    }

    public ProductModel getProductModel() {
        return productModel;
    }

    public void setProductModel(ProductModel productModel){
        this.productModel=productModel;
    }

}
