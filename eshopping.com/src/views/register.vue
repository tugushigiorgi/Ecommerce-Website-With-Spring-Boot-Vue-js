
<template>


 
<heading> 
    <div class="register_wrapper">
                <div class="register_form_contianer">
                    <form  @submit.prevent="onSubmit"> 
                        <div class="register_title_wrapper">
                            <div class="register_title">Sign up now  </div>
                        </div>
    
                            <div class="register_input_wrapper">
    

                                <div class="register_input_container">
                                 
    
                                 <div class="error_mesage"  > </div>
 
                                 <span class="register_input_title">Full name </span>
                                 
                                 <input  required type="text" class="register_input" maxlength="35" v-model="fullnameinput" >
                                 <span class="material-symbols-outlined email_icon">
                                     account_box
                                     </span>
                                      
                             </div>














                                <div class="register_input_container">
                                 
    
                                    <div class="error_mesage" v-if="email_error">{{email_error_message}}</div>
    
                                    <span class="register_input_title">Email</span>
                                    
                                    <input  required type="email" class="register_input" maxlength="35" v-model="email_email_input">
                                    <span class="material-symbols-outlined email_icon">
                                        mail
                                        </span>
                                         
                                </div>







                                <div class="register_input_container">
                                 
                                    <div class="error_mesage"      v-if="number_error"> {{number_error_message}}  </div>
    
                                    <span class="register_input_title">Phone number</span>
                                    
                                    <input type="number"  required    class="register_input" v-model.number="email_number_input">
                                    <span class="material-symbols-outlined email_icon" >
                                        smartphone
                                        </span>
                                         
                                </div>


                                <div class="register_input_container">
                                 
  
                                 <span class="register_input_title">Address</span>
                                 
                                 <input type="text"  required    class="register_input" v-model ="address_input">
                                 <span class="material-symbols-outlined email_icon" >
                                     home_pin
                                     </span>
                                      
                             </div>






                                <div class="register_input_container">
                                    <div class="error_mesage"   v-if="password_error">{{password_error_message}}</div>
    
                                    <span class="register_input_title">Password</span>
                                    
                                    <input   required type="password" class="register_input" v-model="email_password_input">
                                    <span class="material-symbols-outlined email_icon" >
                                       lock
                                        </span>
                                         
                                </div>
                                <div class="register_input_container">
                                    <div class="error_mesage"   v-if="repassword_error">{{repeat_password_error_message}}</div>
    
                                     <span class="register_input_title">Repeat password</span>
                                    
                                    <input   required type="password" class="register_input" v-model="emaiL_repassword_input">
                                    <span class="material-symbols-outlined email_icon" >
                                      lock
                                        </span>
                                         
                                </div>
                                 
     
                                <div class="login_wrapper_footer register_footer">
    
                                    
                                    <button   type="submit" class="login_submit_btn"   ref="main_btn_reg">
                                        Sign up 
                                    </button>
                                    <div>Already have account? <a href="/login" class="signup_href">Sign in</a></div>
                                    
                                </div>
                            </div>
                              
    
                        </form>
    
                            </div>
    
                </div>
           
    
    
    
         </heading>   
    </template>
    
    
    <script>
 import heading from '../components/header.vue'
    import axios from 'axios'
    export default{
    components:{
        heading

    },
        data(){
            return{

                address_input:"",
                fullnameinput:"",
                email_email_input:"",
             email_password_input:"",
             emaiL_repassword_input:"",
                email_number_input:"",

                   
                   email_error_message:"",
                   password_error_message:"",
                   repeat_password_error_message:"",
                   number_error_message:"",
    
    
                   email_error:false,
                    password_error:false,
                    repassword_error:false,
                     number_error:false,
                     
            }
        },
        
            methods:{
                onSubmit(){
                    let email_exists=true
                    console.log(this.email_error )
                    if(!this.email_error  && !this.password_errorr   && ! this.repassword_errorr    && !this.number_error && this.address_input){
 
           

                        axios.post('/auth/register'  ,{
 
 Fullname:this.  fullnameinput,
   Email:this.  email_email_input,
   Address:this.address_input ,
    Phone:this. email_number_input,
    Password:this.  emaiL_repassword_input

})
.then(response => {

    if(response.status==200){

        this.$router.push('/login' )
    }else{

 this.email_email_input="User Is register or there is another problem"

    }

 


 



})
.catch(error => {
console.error(error);
});











                    }
    
    
                    
                   
                }
            },
        watch:{
            email_email_input:{
                immediate:true,
                handler(new_value,old_value){
                
                    if(new_value!=""){
                    if(!new_value.match("@") || !new_value.match(".") ){
                                    this.email_error_message="Please enter valid email address"
                                    this.email_error=true;
                                   
                                   
                                  
                           }else{
                            this.email_error=false
                       
                            
                           }
                        }if(new_value==""){
                            this.email_error=false
                        }
                    
                    
                    }
                         
            },
    
            email_password_input:{
                immediate:true,
                handler(new_value,old_value){
                
                if(  !new_value==""){
    
                    if(new_value.length<8){
                            this.password_error_message="Password should be more than 8 character"
                           this.password_error=true
                         
    
                    }else{
                        this.password_error=false
                    }
                      
    
                }  if(new_value==""){
                    this.password_error=false
        }
    
        }
    
    
            },
            email_number_input:{
                    immediate:true,
                    handler(new_value,old_value){
                        
                      if(new_value.length!=""){
                         
                       if(new_value.toString().length<9 ){
                      
                         this.number_error_message="Please Enter Valid number"
                        this.number_error=true
                            
                          }else{
                            this.number_error=false
                          }
                          
                        
                        }if(new_value.length==0){
                              this.number_error=false
                             
                          }
                      
                      
                     }
        }
    ,
            emaiL_repassword_input:{
                immediate:true,
                handler(new_value,old_value){
                
                if(  !new_value==""){
    
                    if(new_value.length<8){
                        this.repeat_password_error_message="Password should be more than 8 character"
                           this.repassword_error=true
                         
    
                    }else if(new_value!=this.email_password_input){
                        this.repeat_password_error_message="Passwords does not match"
                           this.repassword_error=true      
                    }
                    
                    
                    else{
                        this.repassword_error=false
                    }
                      
    
                }  if(new_value==""){
                    this.repassword_error=false
        }
    
        }
    
            }
    
    
    
    
    
    
    
    
    
    
    
    
        }
    
    
    
    
        
    
    
    
    
    
    }
    
    </script>
    
    <style scoped>
 
 
  
 
 .email_icon{
 
 position: relative;
 bottom: 42px;
 left: 92%;
 color: white;
 }
 
 .material-symbols-outlined{
    color: black;
 }
 
 
 
 .login_submit_btn{
 background-color: rgb(254, 254, 255);
 color: rgba(24, 23, 37, 1);
 padding: 10px;
 width: 220px;
 font-weight: bold;
 font-size: 23px;
 height: 54px;
 border-radius: 10px;
 border-color: transparent;
 }
 .login_submit_btn:hover{
 background-color:  rgb(36, 35, 43);
 color: rgb(255, 255, 255);
 }
    .login_wrapper_footer{
    position: absolute;
    left: 50%;
    transform: translate(-50%, 0);
    color: white;
    margin-top: 30px;
    
    }
 .login_submit_btn{
 margin-bottom: 20px;
 }
 a {
 all: unset;
 }
 
 .signup_href{
 font-weight: bold;
 cursor: pointer;
 }
 .signup_href:hover{
  color: rgb(72, 70, 100);
 }
 
 .error_mesage{
 position: relative; 
 top: 10px;
 color: red;
 }
 
 
.register_wrapper{
    display: flex;
    align-content: center;
    justify-content: center;
    margin-top: 30px;

    
    
}

.register_form_contianer{
    width: 500px;
    height: 750px;
    border-radius: 18px;
    background-color:  rgba(24, 23, 37, 1);
    padding: 30px;
    padding-top: 46px;
}

.register_title{
    font-weight: bold;
font-size: 30px;
color: white;
}


.register_input_title{
    position: relative;
    top: 23px;
    left: 10px;
    cursor:none;
   color: rgba(0, 0, 0, 0.58);
}
.register_input_container{
max-height: 90px;
}
.register_input{
    width: 97.2%;
    border-width: 2px;
    border-color: black;
    padding-left: 10px;
    height: 54px;
    border-radius: 10px;
    font-size: 20px;
   
}
.register_footer{
    margin-top: 60px;
}

    </style>