package eshopping.com.eshopping.com.Controllers;

import eshopping.com.eshopping.com.Converters.DBData;
import eshopping.com.eshopping.com.Converters.PageRequestGenerator;
import eshopping.com.eshopping.com.DATABASE.JPA.*;
import eshopping.com.eshopping.com.DATABASE.Models.UserModel;
import eshopping.com.eshopping.com.DATABASE.ProductStuffs.Order;
import eshopping.com.eshopping.com.DTO.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController

public class ProfileController {

    public ProductJPA productJPA;

    public ImageJPA imageJPA;

    public ProductSpecsJPA productSpecsJPA;


    public USERJPA userjpa;

    public OrderJPA orderJPA;

    public productCartJPA productCartJPA;

   public  DBData convert;
   PasswordEncoder passwordEncoder;

    public ProfileController(ProductJPA productJPA, ImageJPA imageJPA, ProductSpecsJPA productSpecsJPA, USERJPA userjpa, OrderJPA orderJPA, eshopping.com.eshopping.com.DATABASE.JPA.productCartJPA productCartJPA, DBData convert, PasswordEncoder passwordEncoder) {
        this.productJPA = productJPA;
        this.imageJPA = imageJPA;
        this.productSpecsJPA = productSpecsJPA;
        this.userjpa = userjpa;
        this.orderJPA = orderJPA;
        this.productCartJPA = productCartJPA;
        this.convert = convert;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/profile/getcurrentusrorders/{page}")
    public   OrderWithCountDTO  GetUserCurrentOrders(Authentication authentication, @PathVariable String page){


        if(authentication.getName()!=null){
           UserModel user = userjpa.FindUserWithEmail(authentication.getName());


    int total =orderJPA.coutuserCurrentorders(user.getID());
        PageRequest pageRequest= PageRequestGenerator.PageRequestGenerator(total,Integer.parseInt(page),5);

        List<Order>  dbdata= orderJPA.GetuserCurrentOrders(user.getID(),pageRequest).getContent();




       return new OrderWithCountDTO(  DBData.ConvertOrderToOrderDtolist(dbdata) ,total);
    }
        return null;
    }




    @GetMapping("/profile/getdeliveredusrorders/{page}")
    public   OrderWithCountDTO  GetUserDeliveredOrders(Authentication authentication,      @PathVariable String page){

        if(authentication.getName()!=null) {
            UserModel user = userjpa.FindUserWithEmail(authentication.getName());


            int total = orderJPA.countuserdeliveredorders(user.getID());
            PageRequest pageRequest = PageRequestGenerator.PageRequestGenerator(total, Integer.parseInt(page), 5);

            List<Order> dbdata = orderJPA.GetuserDeliveredrders(user.getID(), pageRequest).getContent();

            return new OrderWithCountDTO(DBData.ConvertOrderToOrderDtolist(dbdata), total);

        }
        return null;
    }


    @GetMapping("/profile/getcanceledusrorders/{page}")
    public   OrderWithCountDTO  GetUserCanceledOrders(Authentication authentication, @PathVariable String page){


        if(authentication.getName()!=null) {
            UserModel user = userjpa.FindUserWithEmail(authentication.getName());
            int total = orderJPA.countusercanceledorders(user.getID());
            PageRequest pageRequest = PageRequestGenerator.PageRequestGenerator(total, Integer.parseInt(page), 5);

            List<Order> dbdata = orderJPA.GetuserCanceleddrders(user.getID(), pageRequest).getContent();

            return new OrderWithCountDTO(DBData.ConvertOrderToOrderDtolist(dbdata), total);
        }
        return null;

    }




@GetMapping("/profile/userbasicdata")
    public  UserBasicDTO GetUserBasicInfo(Authentication authentication){

    if(authentication.getName()!=null){

        System.out.println(authentication.getName());
        UserModel usr = userjpa.FindUserWithEmail(authentication.getName());


        if(usr!=null){

            return new UserBasicDTO(usr.getID(),usr.getFullname(),usr.getBallance());

        }   }

            return null;



    }



    @GetMapping("/profile/userdata")
    public UserProfileDataDto GetuserDataToFill(Authentication authentication) {
        if(authentication.getName()!=null){
            UserModel usr = userjpa.FindUserWithEmail(authentication.getName());

        if (usr != null) {

            return new UserProfileDataDto(usr.getFullname(), usr.getEmail(), usr.getPhone(), usr.getAddress());

        }}

        return null;



        }


    @PutMapping("/profile/userdata")
    public void UpdateUserData(@RequestBody UserProfileDataDto dto,Authentication authentication){





        if(authentication.getName()!=null) {
            UserModel usr = userjpa.FindUserWithEmail(authentication.getName());
            if (!Objects.equals(dto.getFullname(), usr.getFullname())) {

                usr.setFullname(dto.getFullname());
            }

            if (!Objects.equals(dto.getAddress(), usr.getAddress())) {

                usr.setAddress(dto.getAddress());

            }
            if (!Objects.equals(dto.getPhone(), usr.getPhone())) {

                usr.setPhone(dto.getPhone());
            }

            userjpa.save(usr);

        }



    }



@PutMapping("/profile/updatepassword")

    public String  UpdateUserPassword(@RequestBody UserPasswordUpdateDto dto,Authentication authentication){
            UserModel userModel =userjpa.FindUserWithEmail(authentication.getName());

            try{

//                if(passwordEncoder.matches(dto.oldpassword,userModel.getPassword()))


                    userModel.setPassword( passwordEncoder.encode(dto.newpassword));


                    userjpa.save(  userModel);  return "Success";
}catch(Exception e ){
                return "FAIL";

    }









    }



    @PostMapping("/fillballance")
    public String Fillballance(@RequestBody PaymentRequest paymentRequest,Authentication authentication){


            try{

        UserModel userModel=userjpa.FindUserWithEmail(authentication.getName());

            userModel.setBallance( Double.parseDouble( paymentRequest.getAmount()));

            userjpa.save( userModel);
            return "Success";
            }
         catch (Exception e ){
                return  null;
         }








    }







}










