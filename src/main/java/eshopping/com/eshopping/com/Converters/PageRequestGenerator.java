package eshopping.com.eshopping.com.Converters;

import org.springframework.data.domain.PageRequest;

public class PageRequestGenerator {



        public static PageRequest PageRequestGenerator(int  totalitem, int page,int size){


            if(totalitem>0 && totalitem<=size){
               return  PageRequest.of( 0,  totalitem);

            }if(totalitem>size){
                return PageRequest.of(  page-1,  size);

            } else {if(totalitem==0) {
                return PageRequest.of(  0,  1);
            }}

return null;

        }










}
