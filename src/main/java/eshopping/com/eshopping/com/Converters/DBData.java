package eshopping.com.eshopping.com.Converters;

import eshopping.com.eshopping.com.DATABASE.JPA.OrderJPA;
import eshopping.com.eshopping.com.DATABASE.Models.FileMetadata;
import eshopping.com.eshopping.com.DATABASE.Models.ProductModel;
import eshopping.com.eshopping.com.DATABASE.Models.UserModel;
import eshopping.com.eshopping.com.DATABASE.ProductStuffs.Order;
import eshopping.com.eshopping.com.DATABASE.Models.productCart;
import eshopping.com.eshopping.com.DTO.*;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
@Repository
public class DBData {


    public OrderJPA orderJPA;

    public DBData(OrderJPA orderJPA) {
        this.orderJPA = orderJPA;
    }


    public static List<ProductItemDTO> ConverterToListDTO(List<ProductModel> data) {
        List<ProductItemDTO> productItemDTOS = new ArrayList<>();
        for (ProductModel datum : data) {
            List<String> images = new ArrayList<>();
            for (FileMetadata img : datum.Pictures) {

                images.add("http://localhost:5173/src/components/IMAGES/" + img.getFilename());


            }
            ProductItemDTO productItemDTO = ProductItemDTO.builder()

                    .ProductName(datum.ProductName)
                    .id(datum.getID())
                    .Images(images)
                    .Price(String.valueOf(datum.getProductPrice()))

                    .DetailedLink("product/" + datum.Slug)
                    .build();


            productItemDTOS.add(productItemDTO);
        }

        return productItemDTOS;


    }




    public static List<ManageproductDto> ConverterToManageproductDto(List<ProductModel> data) {
        List<ManageproductDto> productItemDTOS = new ArrayList<>();
        for (ProductModel datum : data) {
            List<String> images = new ArrayList<>();
            for (FileMetadata img : datum.Pictures) {

                images.add("http://localhost:5173/src/components/IMAGES/" + img.getFilename());


            }
            ManageproductDto ManageproductItemDTO =  ManageproductDto.builder()

                    .ProductName(datum.ProductName)
                    .id(datum.getID())
                    .Images(images)
                    .Price(String.valueOf(datum.getProductPrice()))

                    .DetailedLink("product/" + datum.Slug)
                    .TotalSold(datum.getTotalSold())
                    .TotalLeft(datum.getProductQuantity())

                    .build();


            productItemDTOS.add(ManageproductItemDTO );
        }

        return productItemDTOS;


    }








    public static List<ProductCartDTO> ConvertCartToListDTO(List<productCart> data) {
        List<ProductCartDTO> cartDTOS = new ArrayList<>();
        String src = "http://localhost:5173/src/components/IMAGES/";


        for (productCart item : data) {

            cartDTOS.add(new ProductCartDTO(
                    item.getID(),
                    item.getProduct().getProductName(),
                    src + item.getProduct().getPictures().get(0).getFilename(),


                    item.getListPrice(),
                    item.getQuantity(), item.getTotalPrice()));
        }

        return cartDTOS;
    }





    public static  List<OrderDto> ConvertOrderToOrderDtolist(List<Order> data){

        List<OrderDto> returndata=new ArrayList<>();

        if(!data.isEmpty()){


        data.forEach(orderitem->{



            LocalDateTime dateTime = orderitem
                    .getCreationDateTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();


            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");


            String output = dateTime.format(outputFormatter);




            OrderDto orderDto=OrderDto.builder()
                    .id(orderitem.getId())

                    .OrderDate( output)
                    .OrderStatus(orderitem.getStatus())
                    .TotalPrice(orderitem.getTotalPrice())
                    .UserFullname(orderitem.getUser().getFullname())
                    .UserAddress(orderitem.getUser().getAddress())
                    .UserPhone(orderitem.getUser().getPhone())
                    .UserEmail(orderitem.getUser().getEmail()).build();


            returndata.add(orderDto);


        });
        return  returndata;

        }

    return null;

    }





    public   List<UserTableDTO> UserModelListToDTOConverter(List<UserModel> dbdata) {

        List<UserTableDTO> userTableDTOS = new ArrayList<>();
        dbdata.forEach(user -> {
            int totalUserOrders = orderJPA.CountUserOrders(String.valueOf(user.getID()));
            UserTableDTO userdto = UserTableDTO.builder()
                    .userid(user.getID())
                    .Emaill(user.getEmail())
                    .Phone(user.getPhone())
                    .FullName(user.getFullname())
                    .Address(user.getAddress())
                    .TotalOrders(totalUserOrders)
                    .build();

            userTableDTOS.add(userdto);


        });
        return userTableDTOS;


    }


}
