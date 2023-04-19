package eshopping.com.eshopping.com.Controllers;

import com.github.javafaker.Faker;
import eshopping.com.eshopping.com.Converters.DBData;
import eshopping.com.eshopping.com.Converters.PageRequestGenerator;
import eshopping.com.eshopping.com.DATABASE.Models.BannerModel;
import eshopping.com.eshopping.com.DATABASE.Models.FileMetadata;
import eshopping.com.eshopping.com.DATABASE.JPA.*;
import eshopping.com.eshopping.com.DATABASE.Models.ProductModel;

import eshopping.com.eshopping.com.DATABASE.Models.UserModel;
import eshopping.com.eshopping.com.DATABASE.ProductStuffs.Order;
import eshopping.com.eshopping.com.DTO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Value("C:\\Users\\Giorgi Tughushi\\Downloads\\ecommerce\\Springeshopping.com\\FRONTEND\\eshopping.com\\src\\components\\IMAGES")

    private String uploadDir;


    public ProductJPA productJPA;

    public ImageJPA imageJPA;

    public ProductSpecsJPA productSpecsJPA;


    public USERJPA userjpa;

    public OrderJPA orderJPA;

    public productCartJPA productCartJPA;

    BannerJPA bannerJPA;
    @Autowired
    PasswordEncoder passwordEncoder;
    public ProductController(ProductJPA productJPA, ImageJPA imageJPA, ProductSpecsJPA productSpecsJPA, USERJPA userjpa, OrderJPA orderJPA, eshopping.com.eshopping.com.DATABASE.JPA.productCartJPA productCartJPA, BannerJPA bannerJPA) {
        this.productJPA = productJPA;
        this.imageJPA = imageJPA;
        this.productSpecsJPA = productSpecsJPA;
        this.userjpa = userjpa;
        this.orderJPA = orderJPA;
        this.productCartJPA = productCartJPA;
        this.bannerJPA = bannerJPA;
    }




@GetMapping("/getpopularproducts")
    public List<ProductItemDTO> getPopularProducts(){

        List<ProductModel> data =    productJPA.getpopularproducts();


        return      DBData.ConverterToListDTO(data);

    }






    @GetMapping("/product/{slug}")

    public     ProductDetailedDTO  getProductbySlug(@PathVariable String slug){

    try {
        ProductModel productmodel = productJPA.getProductModelBySlug(slug);
        List<String> images = new ArrayList<>();

        if (productmodel.getPictures() != null) {


            for (FileMetadata img : productmodel.getPictures()) {

                images.add("http://localhost:5173/src/components/IMAGES/" + img.getFilename());


            }
        }
        ProductDetailedDTO productDetailedDTO = ProductDetailedDTO.builder()
                .id(productmodel.getID())
                .ProductName(productmodel.getProductName())
                .Price(String.valueOf(productmodel.getProductPrice()))
                .Description(productmodel.getDescription())
                .specs(productmodel.getSpecs())
                .Images(images).build();


        return productDetailedDTO;
    }catch (Exception e){
        System.out.println(e);
        return  null;
    }

    }

    @GetMapping("/searchby/{keyword}")
    public DtowithCount getDataByKeyword(@PathVariable("keyword" ) String keyword,
            @RequestParam(value = "orderby",required = false) String orderby,
            @RequestParam(value = "minprice",required = false) String  minprice,
            @RequestParam(value = "maxprice",required = false) String maxprice,
            @RequestParam(value = "sale",required = false) boolean issale,

            @RequestParam(value="page" ,required=false) String page



   ){

        int totalitem=productJPA.ByproductsnameItemCount(keyword,minprice,maxprice);
        PageRequest pageable= PageRequestGenerator.PageRequestGenerator(totalitem,Integer.parseInt(page),12);

        List<ProductModel> data;
            if(Objects.equals(orderby, "asc")){
                   data=productJPA.findproductsbynameASC(keyword, minprice,maxprice,pageable).getContent();



            }else{
                     data=productJPA.findproductsbynameDESC(keyword,  minprice,maxprice,pageable).getContent();

            }





       return   new DtowithCount(DBData.ConverterToListDTO(data),totalitem);


    }



    @GetMapping("/searchwith/{category}")
    public DtowithCount  SearchBycategory(  @PathVariable(value = "category",required = false) String category,

            @RequestParam(value = "orderby",required = false) String orderby,
            @RequestParam(value = "minprice",required = false) String  minprice,
            @RequestParam(value = "maxprice",required = false) String maxprice,
            @RequestParam(value = "sale",required = false) boolean issale,

            @RequestParam(value="page" ,required=false) String page  ){



        List<ProductModel> data=new ArrayList<>() ;


        int totalcount=productJPA.CategoryItemCount(category,minprice,maxprice);

        PageRequest pageable= PageRequestGenerator.PageRequestGenerator( totalcount,Integer.parseInt(page),12);




        if(Objects.equals(orderby, "asc")){

            data=productJPA.SearchByCategoryASC(category, minprice,maxprice,pageable ) .getContent();

        }  if(Objects.equals(orderby, "desc"))  {

                data=productJPA.SearchByCategoryDESC(category ,minprice,maxprice,pageable) .getContent();



        }



        return      new DtowithCount(DBData.ConverterToListDTO(data),totalcount);
    }







    @GetMapping("/banners")
    public    List<String> GetAllBanners(){

        List<BannerModel>  bannerModels=bannerJPA.GetAllBanners();
        List<String> images=new ArrayList<>();


        bannerModels.forEach(image->{

            images.add("http://localhost:5173/src/components/IMAGES/" +  image.getFilename());

        });

        return images;





    }








    @GetMapping("/search")
    public     List<ProductItemDTO> ProductSearchController(

            @RequestParam(value = "orderby",required = false) String orderby,
            @RequestParam(value = "minprice",required = false) String  minprice,
            @RequestParam(value = "maxprice",required = false) String maxprice,
            @RequestParam(value = "sale",required = false) boolean issale,

            @RequestParam(value="page" ,required=false) String page

    ) {


        List<ProductModel> data = new ArrayList<>();
        PageRequest pageable = PageRequest.of( Integer.parseInt(page)-1, 12);




        if (Objects.equals(orderby, "asc")) {



            data = (List<ProductModel>) productJPA.ProductFilterASC(minprice, maxprice,  pageable).getContent();

        } else {
            if (Objects.equals(orderby, "desc")) {
                data = (List<ProductModel>) productJPA.ProductFilterDESC (minprice, maxprice,  pageable).getContent();
            }
        }

return  DBData.ConverterToListDTO(data);
    }



    @GetMapping("/getcartsbyid/{id}")
    public   List<productcartmodelDTO> GetOrderCartListByID(@PathVariable Long id ){
        Order order =orderJPA.FindByID(id);


        List<productcartmodelDTO> data =new ArrayList<>();

        order.getProductOrders().forEach(cart->{
            productcartmodelDTO productcartmodelDTO=new productcartmodelDTO();
            productcartmodelDTO.setProductName(cart.getProduct().getProductName());
            productcartmodelDTO.setORDERID(order.getId());
            productcartmodelDTO.setUSERID(order.getUser().getID());
            productcartmodelDTO.setUserName(order.getUser().getFullname());
            productcartmodelDTO.setTotalPrice(cart.getTotalPrice());
            productcartmodelDTO.setProtuctID(cart.getProduct().getID());
            productcartmodelDTO.setProductListPrice(cart.getListPrice());
            productcartmodelDTO.setProductQuantity(cart.getQuantity());
            productcartmodelDTO.setTOTAL_ORDER_PRICE(order.getTotalPrice());

            data.add(  productcartmodelDTO);



        });




        return data;











    }


    @GetMapping("/checkuserrole")
    public String  checkifadmin(Authentication authentication){
        if(userjpa.FindUserWithEmail(authentication.getName()).getID() == 1) {
            return "SUCCESS";
        }
        return  null;




    }











}
