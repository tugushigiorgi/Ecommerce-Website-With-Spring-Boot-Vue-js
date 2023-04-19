<template>
<heading> 

    <div class="main_conteiner"> 

    <div class="login_Wrapper">
   
   <div class="login_form_conainer">
           <div class="login_title_wrapper">
               <div class="login_title1">Hi there</div>
               <div class="login_title2">Sign in to your account</div>

           </div>
 

               <div class="input_wrapper">
                        <form @submit.prevent="handleSubmit">  
                       <div class="login_input_container">
                           <div class="error_mesage" v-if="email_error">{{email_error_message}}</div>
                           <span class="login_input_title">Email</span>
                           
                           <input type="email" class="input_container" maxlength="37" v-model="login_email_input">
                           <span class="material-symbols-outlined email_icon" >
                               mail
                               </span>
                                
                       </div>
                       <div class="login_input_container password_container_input">
                           <div class="error_mesage"   v-if="password_error">{{password_error_message}}</div>
                           <span class="login_input_title">Password</span>
                           <!-- input_error -->
                           <input type="password" class="input_container " maxlength="37"  ref="password_rel" v-model="login_password_input">
                           <button @click.prevent class="password_show_btn" @click="show_password"  >
                           <span class="material-symbols-outlined password_icon_visibility " >
                              visibility
                               </span>
                               </button>
                       </div>
                    
                           <div class="login_wrapper_remember_me_text"><input type="checkbox" class="remember_me_checkbox"> Remember me</div>
                        
                      
                       <div class="login_wrapper_footer">
   
                       
                       <button class="login_submit_btn"  type="submit">
                           Log in 
                       </button>
                   
                       <div>Don't have account? <a href="/register" class="signup_href">Sign up</a></div>
                   
                   </div> </form>
               </div>
   
   </div>
   
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

       mounted(){
   
   
   
     
 
       },
   data(){
       return{
           email_error_message:"",
           password_error_message:"",
           password_error:false,
           email_error:false,
           login_email_input:"",
           login_password_input:"",
           btn_disabled:false
       }
   },
       watch:{
           login_password_input:{
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
           login_email_input:{
               immediate: true,
               handler(new_value,old_value){
                   if(new_value!=""){
                   if(!new_value.match("@") || !new_value.match(".") ){
                               console.log("ar emtxveva")
                                   this.email_error_message="Please input valid Email"
                                   this.email_error=true
                          }else{
                           this. email_error=false
                          }
               }
               if(new_value==""){
                   this. email_error=false
               }
           
           }
           }
       },


       methods:{
           show_password(){
               if(this.$refs.password_rel.type=="text"){
                   this.$refs.password_rel.type="password"

               }else if( this.$refs.password_rel.type=="password"){
                   this.$refs.password_rel.type="text"
               }},

               handleSubmit(){
        
            if(  this.password_error==false &&   this. email_error==false){
                     

                 
            axios.defaults.baseURL = 'http://localhost:8080';
axios.defaults.headers.common['access-control-allow-origin'] = 'http://localhost:5173';
axios.defaults.headers.common['Content-Type'] = 'application/json';
                localStorage.clear()
axios.post(`/auth/login`,
{
       Email: this.login_email_input,
        Password: this. login_password_input
  
}

)

    

.then(response => {
    




          if(response.data!="Fail"){
              localStorage.setItem("token",response.data)
              this.$router.push('/')


    }else{

            this.email_error_message="Email or Password is incorrect"
              this. email_error=true
    }
 
     

 })
.catch(error => {
  console.error(error);
})
















            }
    }



       }

   }
   
   
   </script>
   
   <style scoped>
 
 
.login_Wrapper{
 
display: flex;
align-content: center;
justify-content: center;
margin-top: 150px;
}
 
.login_form_conainer{
width: 500px;
height: 500px;
border-radius: 18px;
background-color:  rgba(24, 23, 37, 1);
padding: 30px;

 
 
padding-top: 46px;
}
.login_title1{
font-weight: bold;
font-size: 38px;

color: white;
}
.login_title2{
    color: white;
margin-top: 5px;
font-size: 25px;
}

.input_container{
width: 97.2%;
border-width: 2px;
border-color: black;
padding-left: 10px;
height: 54px;
border-radius: 10px;
font-size: 20px;
}
.login_input_container{
margin-top: 5px;
}
.email_icon{

position: relative;
bottom: 42px;
left: 90.5%;
color: rgb(0, 0, 0);
}
.remember_me_checkbox{
accent-color: #000000;
transform: scale(1.5);
}
.password_show_btn{
position: relative;
bottom: 43px;
  left: 90%;
  border-color: transparent;
  background-color: transparent;
}
.password_icon_visibility:hover{

font-weight: bold;
}

.login_wrapper_remember_me_text{
position: relative;
bottom: 41px;
color: white;
}
.login_input_title{
position: relative;
top: 23px;
left: 10px;
cursor:none;
color: rgba(0, 0, 0, 0.58);
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
.password_container_input{
position: relative;
bottom: 30px;
}
.input_error{
border-color: red;
border-width: 1px;

}
    
    
   </style>