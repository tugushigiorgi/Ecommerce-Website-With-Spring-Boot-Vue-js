package eshopping.com.eshopping.com.Converters;

import eshopping.com.eshopping.com.DTO.ReportModel;
import jakarta.persistence.Tuple;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class ReportModelConverter implements Converter<Tuple, ReportModel> {

    @Override
    public  ReportModel convert(Tuple tuple) {
       String  month = (tuple.get(0,    String.class));
        Long quantity = tuple.get(1, Long.class);
       Double price = tuple.get(2,Double.class);

        return ReportModel.builder()
                .month(month)
                .quantity(String.valueOf(quantity))
                .price(price )
                .build();
    }
}