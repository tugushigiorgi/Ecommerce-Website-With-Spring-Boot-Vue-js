package eshopping.com.eshopping.com.Controllers;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.param.checkout.SessionCreateParams;
import eshopping.com.eshopping.com.Converters.DBData;
import eshopping.com.eshopping.com.DATABASE.JPA.*;
import eshopping.com.eshopping.com.DATABASE.Models.ProductModel;
import eshopping.com.eshopping.com.DATABASE.ProductStuffs.DeliveryStatus;
import eshopping.com.eshopping.com.DATABASE.ProductStuffs.Order;
import eshopping.com.eshopping.com.DATABASE.Models.productCart;
import eshopping.com.eshopping.com.DATABASE.Models.UserModel;
import eshopping.com.eshopping.com.DTO.*;
import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController

public class OrderController {

    public ProductJPA productJPA;

    public ImageJPA imageJPA;

    public ProductSpecsJPA productSpecsJPA;


    public USERJPA userjpa;

    public OrderJPA orderJPA;

    public productCartJPA productcartjpa;


    public OrderController(ProductJPA productJPA, ImageJPA imageJPA, ProductSpecsJPA productSpecsJPA, USERJPA userjpa, OrderJPA orderJPA, productCartJPA productcartjpa) {
        this.productJPA = productJPA;
        this.imageJPA = imageJPA;
        this.productSpecsJPA = productSpecsJPA;
        this.userjpa = userjpa;
        this.orderJPA = orderJPA;
        this.productcartjpa = productcartjpa;
    }



















    @PostMapping("/addtocart/{id}")
    public String   AddToProductOrder(@PathVariable Long id, Authentication authentication){

        ProductModel productModel=productJPA.FindProductById(id);

//        UserModel user=userjpa.FindByID(1L);
        if(authentication.getName()!=null){
            UserModel user = userjpa.FindUserWithEmail(authentication.getName());


            if(productModel!=null){
            boolean ising=false;
            for (int i = 0; i < productcartjpa.getUserCart(user.getID()).size(); i++) {
                if (productcartjpa.getUserCart(user.getID()).get(i).getProduct().getID() == productModel.getID()) {
                   ising=true;
                    productCart productcartt =  productcartjpa.getUserCart(user.getID()).get(i);
                    var newvalue = productcartt.getQuantity() + 1;
                    productcartt.setQuantity(newvalue);
                    productcartt.setTotalPrice(newvalue * productModel.getProductPrice());

                    productcartjpa.save(productcartt);


                    break;

                }
            }









            if(!ising){



            productCart ProductOrder= productCart.builder()
                    .product(productModel)
                    .quantity(1)
                    .ListPrice(productModel.ProductPrice)
                    .user(user)
                    .totalPrice(productModel.ProductPrice*1)
                    .build();
            productcartjpa.save(ProductOrder);
        }
            return "Success";


        }   }


        return "FAIL";

    }


    @GetMapping("/getcart")
    @ResponseBody
    public     UsercartsWithBallance getUserCart(Authentication authentication){

//        UserModel user=userjpa.FindByID(1L);
        if(authentication.getName()!=null){
            UserModel user = userjpa.FindUserWithEmail(authentication.getName());

            List<productCart> user_carts= productcartjpa.getUserCart(user.getID());
        List<ProductCartDTO> cartDTOS=new ArrayList<>();

        if(!user_carts.isEmpty()){





        return new UsercartsWithBallance(DBData.ConvertCartToListDTO(user_carts),user.getBallance())  ;
    }else{
            return new UsercartsWithBallance(null,user.getBallance())  ;

        }
    }
            return null;
    }


    @PostMapping("/deletecartitem/{id}")
    public String DeleteCartItem(@PathVariable Long id){

        productcartjpa.deleteById(id);
        return "Success";

    }

@PostMapping("/synccart")
    public void synchronizecart(@RequestBody List<ProductCartDTO> dto ){

        if(!dto.isEmpty()){
            for(ProductCartDTO cartDTO : dto){
                productCart productCart= productcartjpa.findbyID(cartDTO.getId());
                if(productCart.equals(cartDTO)){
                    continue;
                }else{
                    productCart.setQuantity(cartDTO.getProductQuantity());
                    productCart.setTotalPrice(cartDTO.getTotalPrice());

                        productcartjpa.save(  productCart);

                }





            }



        }

    System.out.println("-------------------------------------");
    for (int i = 0; i <dto.size() ; i++) {
        System.out.println(dto.get(i).toString());
    }



    }


    @PostMapping("/addtodelivery")
    public String   AddToDelivery(@RequestBody List<ProductCartDTO> dto,Authentication authentication){


        if(authentication.getName()!=null){
            UserModel user = userjpa.FindUserWithEmail(authentication.getName());

            List<productCart> productCarts=new ArrayList<>();

        float totalprice=0;
        for(ProductCartDTO productCartDTO :dto){
            totalprice+=productCartDTO.getTotalPrice();



        }

        if(user.getBallance()>totalprice) {
            Order order = new Order();
            for (ProductCartDTO productCartDTO : dto) {


                productCart productCart = productcartjpa.findbyID(productCartDTO.getId());


                order.AddProduct(productCart);
                ProductModel productModel = productJPA.FindProductById(productCart.getProduct().getID());
                productModel.setProductQuantity(productModel.getProductQuantity() - productCartDTO.getProductQuantity());
                productModel.setTotalSold(productModel.getTotalSold() + productCartDTO.getProductQuantity());


                orderJPA.save(order);
                productJPA.save(productModel);


            }


            order.setUser(user);
            order.setCreationDateTime(new Date());
            order.setStatus(DeliveryStatus.PREPARING_TO_DELIVER);
            order.setTotalPrice(totalprice);


            user.setBallance(user.getBallance() - totalprice);


            userjpa.save(user);


            return "SUCCESS";

        }



        }



    return "FAIL";








    }






}
