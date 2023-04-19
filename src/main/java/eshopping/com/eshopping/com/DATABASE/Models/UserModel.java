package eshopping.com.eshopping.com.DATABASE.Models;

import eshopping.com.eshopping.com.DATABASE.Models.productCart;
import eshopping.com.eshopping.com.DATABASE.ProductStuffs.Order;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Table(name="User")
public class UserModel implements UserDetails {
    @GeneratedValue
    @Id
    private Long ID;

    private String Fullname;

    private  String Email;

    private  String Phone;


    private String Address;

    private double Ballance;
    private String Password;
    @OneToMany(mappedBy ="user",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Order>   orders=new ArrayList<>();


    @OneToMany(mappedBy ="user",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<productCart>   productordercart=new ArrayList<>();


    public  void setProductOrder(Order order){

            orders.add(order);


                order.setUser(this);





    }



    public String ROLE;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Return authorities based on the user's role
        return new ArrayList<>(List.of(new SimpleGrantedAuthority("ROLE_" + ROLE)));
    }

    @Override
    public String getUsername() {
        return Email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
