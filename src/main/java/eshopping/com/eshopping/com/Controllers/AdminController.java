package eshopping.com.eshopping.com.Controllers;

import eshopping.com.eshopping.com.Converters.DBData;
import eshopping.com.eshopping.com.Converters.PageRequestGenerator;
import eshopping.com.eshopping.com.Converters.ReportModelConverter;
import eshopping.com.eshopping.com.DATABASE.Models.*;
import eshopping.com.eshopping.com.DATABASE.JPA.*;
import eshopping.com.eshopping.com.DATABASE.ProductStuffs.Order;
import eshopping.com.eshopping.com.DTO.*;
import eshopping.com.eshopping.com.Scrapping.scrap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Value("C:\\Users\\Giorgi Tughushi\\Downloads\\ecommerce\\Springeshopping.com\\FRONTEND\\eshopping.com\\src\\components\\IMAGES")
    private String uploadDir;


    public ProductJPA productJPA;

    public ImageJPA imageJPA;

    public ProductSpecsJPA productSpecsJPA;


    public USERJPA userjpa;

    public OrderJPA orderJPA;

    public productCartJPA productCartJPA;
    DBData dbDataa;

    BannerJPA bannerJPA;

    public AdminController(ProductJPA productJPA, ImageJPA imageJPA, ProductSpecsJPA productSpecsJPA, USERJPA userjpa, OrderJPA orderJPA, eshopping.com.eshopping.com.DATABASE.JPA.productCartJPA productCartJPA, DBData dbDataa, BannerJPA bannerJPA) {
        this.productJPA = productJPA;
        this.imageJPA = imageJPA;
        this.productSpecsJPA = productSpecsJPA;
        this.userjpa = userjpa;
        this.orderJPA = orderJPA;
        this.productCartJPA = productCartJPA;
        this.dbDataa = dbDataa;
        this.bannerJPA = bannerJPA;
    }

    @DeleteMapping("/product/{id}")
    public String DeleteProductById(@PathVariable Long id ){

        productJPA.deleteById(id);
        return "Success";


    }

    @PostMapping("/getdatatoupdate/{id}")
    public ProductSaveDTO GetDataForUpdate(@PathVariable Long id){

        ProductModel productModel=  productJPA.FindProductById(id);

        if(productModel!=null){


            HashMap<String,String> Specs=new HashMap<>();

            for (Productspecs i :productModel.getSpecs()) {

                Specs.put(i.Specification_name,i.Specification_value);

            }








            ProductSaveDTO dto = ProductSaveDTO.builder()

                    .ProductName(productModel.getProductName())
                    .ProductPrice(String.valueOf(productModel.getProductPrice()))
                    .ProductQuantity(String.valueOf(productModel.getProductQuantity()))
                    .Category(productModel.getCategory())
                    .Description(productModel.getDescription())
                    .Specs(Specs)
                    .images(productModel.getPictures()).build();

            return  dto;

        }

        return null;





    }



    @PutMapping("/product/{id}")
    public    ManageproductDto updateProduct(@RequestBody ProductUpdateDTO product, @PathVariable Long id) {
        ProductModel productModel = productJPA.FindProductById(id);








        if (productModel != null) {

            productSpecsJPA.DeleteSpecsByProductID(productModel.ID);
            for (HashMap.Entry<String,String> entry : product.getSpecs().entrySet()) {
                Productspecs  specs=new Productspecs(entry.getKey(),entry.getValue());
                productModel.setSpecs(specs);
                productSpecsJPA.save(specs);
            }


            if(product.getIDOfImagesToBeDeleted()!=null ){


                for (String image_id : product.getIDOfImagesToBeDeleted()) {


                    imageJPA.DeleteImageByID(Long.parseLong(image_id));


                }
            }

            if(product.getImages()!=null){


                for (FileMetadata image : product.getImages()) {

                    productModel.setPictures(image);


                    imageJPA.save(image);

                }
            }




            if (!productModel.getProductName().equals(product.getProductName())) {
                productModel.setProductName(product.getProductName());
            }
            if (productModel.getProductPrice() != Float.parseFloat(product.getProductPrice())) {
                productModel.setProductPrice(Float.parseFloat(product.getProductPrice()));
            }
            if (productModel.getProductQuantity() != Integer.parseInt(product.getProductQuantity())) {
                productModel.setProductQuantity(Integer.parseInt(product.getProductQuantity()));
            }
            if (!productModel.getDescription().equals(product.getDescription())) {
                productModel.setDescription(product.getDescription());
            }
            if (!productModel.getCategory().equals(product.getCategory())) {
                productModel.setCategory(product.getCategory());
            }





            productJPA.save(productModel);

            List<String> images=new ArrayList<>();
            for( FileMetadata img :productModel.getPictures()){

                images.add("http://localhost:5173/src/components/IMAGES/"+img.getFilename());


            }
            ManageproductDto listitem=ManageproductDto.builder()
                    .TotalLeft(productModel.getProductQuantity())
                    .TotalSold(productModel.getTotalSold())
                    .ProductName(productModel.getProductName())
                    .DetailedLink("product/"+productModel.Slug)
                    .Price(String.valueOf(productModel.getProductPrice()))
                    .Images(images)
                    .id(productModel.ID).build();

            return listitem;



        }






        return  null;
    }



    @PostMapping("/getphonedata")
    public  String getPhoneDataFromWeb(@RequestBody ScrapDTO Link) throws IOException {

        return   scrap.getdata( Link.getLINK());

    }

    @PostMapping("/saveproduct")
    public String  saveproduct(@RequestBody ProductSaveDTO productDTO){

        ProductModel productModel =new ProductModel(
                productDTO.ProductName,

                Float.parseFloat( productDTO.ProductPrice),
                0F,

                Integer.parseInt(productDTO.ProductQuantity),

                productDTO.Category,
                productDTO.Description

        );


        for (FileMetadata image : productDTO.images) {

            productModel.setPictures(image);
            imageJPA.save(image);

        }

        for (HashMap.Entry<String,String> entry : productDTO.Specs.entrySet()) {
            Productspecs  specs=new Productspecs(entry.getKey(),entry.getValue());
            productModel.setSpecs(specs);
            productSpecsJPA.save(specs);

        }




        productJPA.save(productModel)  ;










        return  "SUCCESS";
    }

    @PostMapping("/upload")
    public  List<FileMetadata> handleFileUpload(@RequestParam("files") List<MultipartFile> files) {

        List<FileMetadata> images=new ArrayList<>();
        for (MultipartFile file :files  ) {
            String filename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
            try {

                Path path = Paths.get(uploadDir + File.separator + filename);
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
            FileMetadata fileMetadata = new FileMetadata();
            fileMetadata.setFilename(filename);
            fileMetadata.setFileType(file.getContentType());
            fileMetadata.setSize(file.getSize());
            fileMetadata.setUploadDate(new Date().toString());
            fileMetadata.setFileLocation(uploadDir + File.separator + filename);

            images.add(fileMetadata);

        }
        return   images;
    }



    @GetMapping("/manageproducts/{page}")

    public    ManageProductWithCount  ManageProduct(@PathVariable  String page){

        int pagei=Integer.parseInt(page);

        int total=productJPA.totalcaunt();
        var pageRequest =PageRequestGenerator.PageRequestGenerator(productJPA.totalcaunt(), pagei,12);


        List<ProductModel> data=   productJPA.GETALLPRODUCTDATA(pageRequest).getContent();

        return    new ManageProductWithCount(DBData.ConverterToManageproductDto(data),total)   ;
    }
    @GetMapping("/manageproductsby/{category}/{page}")

    public    ManageProductWithCount  ManageProductBycategory( @PathVariable String category ,@PathVariable  String page){

        int pagei=Integer.parseInt(page);

        int total=productJPA.CategoryItemCount(category,"0","100000");
        var pageRequest =PageRequestGenerator.PageRequestGenerator(productJPA.totalcaunt(), pagei,12);


        List<ProductModel> data=   productJPA.SearchByCategoryASC(category,"0","100000",pageRequest).getContent();

        return    new ManageProductWithCount(DBData.ConverterToManageproductDto(data),total)   ;
    }


    @GetMapping("/manageproductsbyname/{productname}/{page}")

    public    ManageProductWithCount  ManageProductByName( @PathVariable String  productname ,@PathVariable String  page ){

        int pagei=Integer.parseInt(page);

        int total=productJPA.ByproductsnameItemCount(productname,"0","1000000");
        var pageRequest =PageRequestGenerator.PageRequestGenerator(productJPA.totalcaunt(), pagei,12);


        List<ProductModel> data=   productJPA.findproductsbynameASC(productname,"0","1000000",pageRequest).getContent();


        return    new ManageProductWithCount(DBData.ConverterToManageproductDto(data),total)   ;
    }



    @GetMapping("/getcurrentorders/{page}")
    public  OrderWithCountDTO  getcurrentorders(@PathVariable String page){
        int total=orderJPA.GetTotalCurrentOrdersCount();
         int pagee=Integer.parseInt(page);

         PageRequest pageRequest=PageRequestGenerator.PageRequestGenerator(total,pagee,12);
   List<Order> orders=orderJPA.getCurrentOrders( pageRequest).getContent();


        return  new OrderWithCountDTO(DBData.ConvertOrderToOrderDtolist(orders),total);

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




    @DeleteMapping("/deleteorder/{id}")
    public void DeleteOrderById(@PathVariable Long id ){

        Order order =orderJPA.FindByID(id);

        if(order!=null){
            orderJPA.delete(order);
        }




    }




    @PutMapping("/updatedeliver/{id}/{newstatus}")
    public ResponseEntity UpdateDeliverStatus(@PathVariable Long id, @PathVariable String newstatus ){

        Order order = orderJPA.FindByID(id);


if(order!=null){


            order.setStatus(newstatus);
            orderJPA.save(order);

    return  ResponseEntity.ok().build();
    }


    return  ResponseEntity.notFound().build();




    }






    @GetMapping("/getcanceledorders/{page}")
    public   OrderWithCountDTO  getCanceledOrders(@PathVariable String page){
        int pagee=Integer.parseInt(page);
        int total=orderJPA.GetTotalCanceledOrdersCount();
        PageRequest pageRequest=PageRequestGenerator.PageRequestGenerator(total,pagee,12);

        List<Order> dbdata=orderJPA.getCanceledOrders(pageRequest).getContent();
        if(!dbdata.isEmpty()){

         return   new OrderWithCountDTO (DBData.ConvertOrderToOrderDtolist(dbdata),total)  ;
        }
        return null;


    }



    @GetMapping("/getdeliveredorders/{page}")
    public    OrderWithCountDTO getDeliveredOrders(@PathVariable String page){

        int pagee=Integer.parseInt(page);
            int total=orderJPA.GetTotalDeliveredOrdersCount();

        PageRequest pageRequest=PageRequestGenerator.PageRequestGenerator(total,pagee,12);

        List<Order> dbdata=orderJPA.getDeliveredOrders(pageRequest).getContent();


        if(!dbdata.isEmpty()){

         return   new OrderWithCountDTO(DBData.ConvertOrderToOrderDtolist(dbdata),total);
        }
        return null;


    }




    @GetMapping("/getorder/{id}")
    public   OrderDto GetOrderByID(@PathVariable Long id ){
      Order order = orderJPA.FindByID(id);
        if(order!=null){



            LocalDateTime dateTime = order
                    .getCreationDateTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();


            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");


            String output = dateTime.format(outputFormatter);




            OrderDto orderDto=OrderDto.builder()
                    .id(order.getId())

                    .OrderDate( output)
                    .OrderStatus(order.getStatus())
                    .TotalPrice(order.getTotalPrice())
                    .UserFullname(order.getUser().getFullname())
                    .UserAddress(order.getUser().getAddress())
                    .UserPhone(order.getUser().getPhone())
                    .UserEmail(order.getUser().getEmail()).build();



        return orderDto;

        }


            return null;
    }







    @GetMapping("/getusers/{page}")
    public   userTableWithCount GetUsers(@PathVariable String page){


        int total=userjpa.CountUsers();
        PageRequest pageRequest=PageRequestGenerator.PageRequestGenerator(total,Integer.parseInt(page),12);
        List<UserModel> dbdata=userjpa.GetUsers(pageRequest).getContent();




        return new userTableWithCount(dbDataa.UserModelListToDTOConverter(dbdata),total);








    }






    @DeleteMapping("/user/{id}")
    public ResponseEntity DeleteUserById(@PathVariable  String id){

        userjpa.deleteById(Long.parseLong(id));
      try{
          userjpa.deleteById(Long.parseLong(id));
          return  ResponseEntity.ok().build();
      }catch (Exception e){
          return  ResponseEntity.notFound().build();
      }


      }



    @GetMapping("/getuser/{name}")
      public    UserTableDTO FindUserByName(@PathVariable  String name){

        UserModel user =userjpa.FindUserByName(name);


        if(user !=null){
            int totalUserOrders = orderJPA.CountUserOrders(String.valueOf(user.getID()));

            return UserTableDTO.builder()
                    .userid(user.getID())
                    .Emaill(user.getEmail())
                    .Phone(user.getPhone())
                    .FullName(user.getFullname())
                    .Address(user.getAddress())
                    .TotalOrders(totalUserOrders)
                    .build();
        }

            return null;


    }



    @GetMapping("/getuserallorders/{uid}")
        public   List<OrderDto> GetUserOrders(@PathVariable  Long uid){

            List<Order> userorders=orderJPA.GetUserAllOrders(uid);

           return   DBData.ConvertOrderToOrderDtolist(userorders);
    }




    @PostMapping("/uploadbanner")
    public  BannerDto UploadBannerImage(@RequestParam("files") MultipartFile file){




            String filename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
            try {

                Path path = Paths.get(uploadDir + File.separator + filename);
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);



                BannerModel  fileMetadata  =new BannerModel();
                fileMetadata.setFilename(filename);
                fileMetadata.setFileType(file.getContentType());
                fileMetadata.setSize(file.getSize());
                fileMetadata.setUploadDate(new Date().toString());
                fileMetadata.setFileLocation(uploadDir + File.separator + filename);




              var dtosave= bannerJPA.save(fileMetadata );


                 return  new BannerDto (dtosave.getId(), "http://localhost:5173/src/components/IMAGES/" +   dtosave.getFilename());




            } catch (IOException e) {
                e.printStackTrace();
                return  null;
            }




    }


    @DeleteMapping("/deletebanner/{id}")
    public ResponseEntity DeleteBannerById(@PathVariable String id ){


            try{
                bannerJPA.DeleteBannerById(id);


                return ResponseEntity.ok().build();
            }
            catch (Exception exception){
                return ResponseEntity.notFound().build();
            }

    }




    @GetMapping("/getbanners")
    public  List<BannerDto>  GetCRUDBanners(){

            List<BannerDto> bannerDtos=new ArrayList<>();

        bannerJPA.GetAllBanners().forEach(image->{



            bannerDtos.add(new BannerDto(image.getId(),"http://localhost:5173/src/components/IMAGES/" +  image.getFilename()));



        });

        return bannerDtos;


    }





    @GetMapping("/report")
    public List<Revmodel> report(){

        List<String> months = List.of(
        "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
        List<Revmodel> Finaldata = new ArrayList<>();

        ReportModelConverter re = new ReportModelConverter();



        orderJPA.reportrevenue().forEach(item -> {
            ReportModel d = re.convert(item);


            boolean found = false;
            for (Revmodel search : Finaldata) {
                if (Objects.equals(d.getMonth(), search.getMonth())) {
                    Revmodel model = Revmodel.builder()
                            .price(search.getPrice() + d.getPrice())
                            .month(d.getMonth())
                            .quantity( search.getQuantity()+Integer.parseInt( d.getQuantity()))
                            .build();
                    Finaldata.remove(search);
                    Finaldata.add(model);
                    found = true;
                    break;
                }
            }

            if (!found) {
                Finaldata.add(new  Revmodel(d.getMonth(), Integer.parseInt(d.getQuantity()), d.getPrice()));
            }
        });



        for (String month : months) {
            boolean isin = false;
            for (Revmodel finaldatum : Finaldata) {
                if (month.equals(finaldatum.getMonth())) {
                    isin = true;
                    break;
                }
            }
            if (!isin) {
                Finaldata.add(new Revmodel(month, 0, 0));
            }
        }


        Finaldata.forEach(System.out::println);









        return   Finaldata;


    }




















}
