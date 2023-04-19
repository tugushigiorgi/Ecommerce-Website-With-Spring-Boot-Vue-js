package eshopping.com.eshopping.com.Controllers;

import eshopping.com.eshopping.com.DATABASE.JPA.USERJPA;
import eshopping.com.eshopping.com.DATABASE.Models.UserModel;
import eshopping.com.eshopping.com.DTO.LoginDTO;
import eshopping.com.eshopping.com.DTO.RegisterDto;
import eshopping.com.eshopping.com.Security.JwtFactory;
import eshopping.com.eshopping.com.Security.Userdetailsservice;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.AuthenticationException;
@RestController

@RequestMapping("/auth")
public class AuthController {
    public USERJPA userjpa;

    PasswordEncoder passwordEncoder;
    JwtFactory jwtFactory;

    AuthenticationManager authenticationManager;

    public AuthController(USERJPA userjpa, PasswordEncoder passwordEncoder, JwtFactory jwtFactory, AuthenticationManager authenticationManager) {
        this.userjpa = userjpa;
        this.passwordEncoder = passwordEncoder;
        this.jwtFactory = jwtFactory;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public String Login(@RequestBody LoginDTO loginDTO ) throws InternalAuthenticationServiceException{

        try {
            authenticationManager.authenticate(

                    new
                            UsernamePasswordAuthenticationToken(loginDTO.getEmail(),loginDTO.getPassword()));


            UserModel userDetails= userjpa.FindUserWithEmail(  loginDTO.getEmail());

            if(userDetails!=null){


                jwtFactory=new JwtFactory();


                return    jwtFactory.generateToken(userDetails);}
        } catch (AuthenticationException e) {
              System.out.println("Authentication failed: " + e.getMessage());
            return "Fail";
        }





    return "Fail";



    }


    @PostMapping("/register")
    public ResponseEntity Register(@RequestBody RegisterDto registerDto){

         if(userjpa.FindUserWithEmail(registerDto.getEmail())==null){
             UserModel userModel = UserModel.builder()
                     .Email(registerDto.getEmail())
                     .Fullname(registerDto.getFullname())
                     .Password(passwordEncoder.encode(registerDto.getPassword()))
                     .Ballance(0)
                     .ROLE("USER")
                     .Address(registerDto.getAddress())
                     .Phone(registerDto.getPhone())
                     .build();



                    userjpa.save(userModel);

                        return  ResponseEntity.ok().build();

         }else{

             return ResponseEntity.noContent().build();


         }











    }




@PostMapping("/logout")
    public  String Logout( HttpServletRequest request){

        try{
    request.getSession().invalidate();
    SecurityContextHolder.clearContext();

    return "SUCCESS";
}catch(Exception e){
            System.out.println(e);
            return "FAIL";
        }



}








}
