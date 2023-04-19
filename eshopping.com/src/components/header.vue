<template >

    
<nav>
    <div class="HeaderLogo">Eshopping.com</div>
    <ul>        


        <li>
               
            <router-link    tag="li" :to="{name:'home'}">Home</router-link>
         
       
    </li>

    <li v-if="!isauthenticated" class="tooltip"><router-link       tag="li" :to="{name:'login'}">Log in </router-link></li>

    <li v-if="!isauthenticated" class="tooltip"><router-link       tag="li" :to="{name:'register'}">Register </router-link></li>

        <li v-if="isauthenticated" class="tooltip"><router-link       tag="li" :to="{name:'userpage'}">Profile </router-link></li>
        <li v-if="this.isadmin" class="tooltip"><a      href='/admin'>ADMIN </a></li>



     

        <li @click="OpenCart()"  v-if="isauthenticated">  
       
            <div class="Carttext">Cart </div>
            <span class="material-symbols-outlined">
            shopping_cart
            </span>
           {{ this.cartquantity}}  
       

        </li>
       


        <li v-if="isauthenticated" >  
       
       <div class="Carttext" @click="LogOutModalVisibilitiy=true">  Log out </div>
       <span class="material-symbols-outlined">
       logout
       </span>
     
  

   
   
    </li>
    </ul>


</nav>


<center>

 

<div  v-if="LogOutModalVisibilitiy"    class="LogOut-modal">
  <div class="LogOut-modal-content">
    <div class="LogOut-modal-container">
      
        <div class="logoutmodaltitle">
            Are you sure You want to exit ?
        </div>
        
        <div class="logoutbtnarr">

            <button class="logoutmodalBtn" @click=" LogOutModalVisibilitiy=false">Cancel</button>
            <button class="logoutmodalBtn" @click="logouthander()" >Log out</button>
        </div>

    </div>
  </div>
</div>

</center>


<div  v-if="cartmodalvisibility" id="myModal" class="modal"    >

 
  <div class="modal-content"  >
  <span class="close" @click="closecart()">&times;</span>

         <div class="ModalTitle">
            <div class="Mtitle">My cart</div>
            <span class="material-symbols-outlined mlogocart">
            shopping_cart
            </span>
                
         </div>
         <div class="AccountBallance"> Account Ballance  {{   this.userballance }}$</div>

         <div class="ModalCarttableDiv">
 
            <table class="ModalCarttable" v-if="getdata.length>0">
 


                        <tr>
                            <th style="width:340px">Item</th>
                            <th>List Price</th>
                            <th>Quantity</th>
                            <th>  Sum </th>
                        </tr>
                        <tr v-for="item in getdata ">

                            <td>
                                <div class="cartItemSection">
                                        <img  class="cartTableImage" :src=item.productImage alt="Image">
                                     <div>{{ item.productName }}</div>    


                                </div>

                            </td>
                            <td>{{ item.productPrice }}</td>
                            <td>
                           
   

 
                                        <div class="quantitydivwrapper">

                                        
                                <button class="quantitybtn" @click="DecreaseProductquantity(item)">-</button>
                                 
                                <div class="itemquantitydiv"> 
                                  
                                          {{item.productQuantity }}
                                      </div>
                                <button class="quantitybtn" @click="IncreaseProductquantity(item)">+</button>
                              
                            </div>  

</td>





                            <td>{{ item.totalPrice
 }}</td>
                            <td class="cartItemDelete">
                                    <button @click="DeleteCartItem(item)" class="cartItemDeleteBtn">
                                        Delete 
                                    </button>
                               </td>
                        </tr>
                       


            </table>
            <div v-if="succesmessagevisibility" class="classamptydiv"> 
            
                <div>you have succesfuly bought this items ! Go to Profile for more details  </div>
            
            </div>





            <div v-if="ballanceerror" style="font-size: 30px; margin-bottom: 30px; color: red;">You dont have enought Money in your Ballance</div>


            <div v-if="getdata.length==0 && !succesmessagevisibility" class="classamptydiv"> 
            
            <div> Cart is Empty</div>
        
        </div>



         </div>
         
         <div   v-if="getdata.length>0" class="CartTotalPrice">Total {{ this.totalprice}}$</div>

<button v-if="getdata.length>0" class="CartBuyButton" @click="BUY()">Buy</button>

     
</div>  
    
</div>


        <div class="main_content">
          <slot    :updateData="updateData"  ></slot>
        </div>

</template>

<script>
import { isModelListener } from '@vue/shared';
import axios from 'axios';
    export default{
        mounted(){



        axios.defaults.baseURL = 'http://localhost:8080';
axios.defaults.headers.common['access-control-allow-origin'] = 'http://localhost:5173';
axios.defaults.headers.common['Content-Type'] = 'application/json';

if(localStorage.getItem('token')){

    this.isauthenticated=true
    var token=localStorage.getItem('token')
    axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
    axios.get('/product/checkuserrole',  {
    })
        .then(response => {

            if(response.data!="SUCCESS"){
                this.isadmin=false
            }else{
                this.isadmin=true
            }


        })
        .catch(error => {
            console.error(error);
        });










axios.get(`/getcart`)



.then(response => {

 if(response.data.maindata){


 this.getdata=response.data.maindata

    this.cartquantity= this.getdata.length
    this.userballance=response.data.UserBallance
    var sum = 0;
    var totalp=0
    this. getdata.forEach(function(item) {
  sum += item.productQuantity;
   totalp+= item.productPrice*item.productQuantity

});
this.totalprice=totalp
this.cartquantity=sum


 }else{

this.userballance=response.data.UserBallance

}


}
 )
.catch(error => {
  console.error(error);
})

        }else{
            this.isauthenticated=false
        }
    
    
    },



        data(){

            return{
                isadmin:false,
                ballanceerror:false,
                userballance:0,
                succesmessagevisibility:false,
                totalprice:0,
              cartquantity:0,
                getdata:[],
                data:"adwd",  
             LogOutModalVisibilitiy:false,   
             isauthenticated:false,
            cartmodalvisibility:false}

        },
        methods:{

            logouthander(){

 
                axios.defaults.baseURL = 'http://localhost:8080';
axios.defaults.headers.common['access-control-allow-origin'] = 'http://localhost:5173';
axios.defaults.headers.common['Content-Type'] = 'application/json';
if(localStorage.getItem('token')){

 
       

 


axios.post(`/auth/logout`, 
 
    




)

    

.then(response => {

    if(response.data=="SUCCESS"){

        localStorage.removeItem("token");

        this.$router.push('/login' )
    }else{

        console.log(response.data)

    }



 

 
 })
.catch(error => {
  console.error(error);
})


}








            },


            BUY(){
                

                


                axios.defaults.baseURL = 'http://localhost:8080';
axios.defaults.headers.common['access-control-allow-origin'] = 'http://localhost:5173';
axios.defaults.headers.common['Content-Type'] = 'application/json';
if(localStorage.getItem('token')){

var token=localStorage.getItem('token')
            axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;


        if(this.  userballance<  this. totalprice) {
                this.ballanceerror=true


        }    else{

        



 
axios.post(`/addtodelivery`, 
 
   this.getdata

)

    

.then(response => {

    if(response.data=="SUCCESS"){
        this.getdata=[]
        this.  userballance-=  this. totalprice
         this.succesmessagevisibility=true
        this. totalprice=0
        this. cartquantity=0
    }
 })
.catch(error => {
  console.error(error);
})



}
            }},



            closecart(){
                this.ballanceerror=false
                axios.defaults.baseURL = 'http://localhost:8080';
axios.defaults.headers.common['access-control-allow-origin'] = 'http://localhost:5173';
axios.defaults.headers.common['Content-Type'] = 'application/json';

if(localStorage.getItem('token')){ 
var token=localStorage.getItem('token')
            axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
console.log(this.getdata)
axios.post(`/synccart`, 

   this.getdata

)

    

.then(response => {


 })
.catch(error => {
  console.error(error);
})

this.succesmessagevisibility=false




                this.cartmodalvisibility=false
            }}
                ,



            IncreaseProductquantity(item){
                
                item.productQuantity+=1
                item.totalPrice=item.totalPrice+ item.productPrice
                var sum=0
                    var totalp=0
                    this. getdata.forEach(function(items) {
  sum += items.productQuantity;
   totalp= items.productPrice*item.productQuantity
//    item.totalPrice+= items.productPrice*item.productQuantity
item.totalPrice= items.productPrice*item.productQuantity
});
this.totalprice=totalp
this.cartquantity=sum
 
 



            },
            DecreaseProductquantity(item){
                this.ballanceerror=false
                if(item.productQuantity>1){
                    item.productQuantity-=1
                    item.totalPrice=item.totalPrice-item.productPrice
                    var sum=0
                    var totalp=0
                    this. getdata.forEach(function(items) {
  sum += items.productQuantity;
   totalp= items.productPrice*item.productQuantity
//    item.totalPrice-= items.productPrice*item.productQuantity
item.totalPrice= items.productPrice*item.productQuantity

});
this.totalprice=totalp
this.cartquantity=sum
                }
              
            },
            DeleteCartItem(item){


                this.ballanceerror=false
                axios.defaults.baseURL = 'http://localhost:8080';
axios.defaults.headers.common['access-control-allow-origin'] = 'http://localhost:5173';
axios.defaults.headers.common['Content-Type'] = 'application/json';
if(localStorage.getItem('token')){

var token=localStorage.getItem('token')
            axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;

 
axios.post(`/deletecartitem/`+item.id)

    

.then(response => {
    this.cartquantity-=item.productQuantity
    
    this.getdata= this.getdata.filter(items => items.id !== item.id);

    this.totalprice-=item.productPrice*item.productQuantity


 })
.catch(error => {
  console.error(error);
})





}  },
            updateData(  ) {
                this.cartquantity+=1
                    

    } ,
            OpenCart(){
                this.ballanceerror=false
                   
        axios.defaults.baseURL = 'http://localhost:8080';
axios.defaults.headers.common['access-control-allow-origin'] = 'http://localhost:5173';
axios.defaults.headers.common['Content-Type'] = 'application/json';
if(localStorage.getItem('token')){

var token=localStorage.getItem('token')
            axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;

 
axios.get(`/getcart`)

    

.then(response => {
 
   
 if(response.data.maindata){

 
this.getdata=response.data.maindata

   this.cartquantity= this.getdata.length
   this.userballance=response.data.UserBallance
   var sum = 0;
   var totalp=0
   this. getdata.forEach(function(item) {
 sum += item.productQuantity;
  totalp+= item.productPrice*item.productQuantity

});
this.totalprice=totalp
this.cartquantity=sum


}else{

    this.userballance=response.data.UserBallance

}


 
 })
.catch(error => {
  console.error(error);
})













                    this.cartmodalvisibility=!this.cartmodalvisibility
                }
            },
            
        }


    }


</script>

<style scoped>
input[type='number']::-webkit-inner-spin-button, 
input[type='number']::-webkit-outer-spin-button { 
    opacity: 1;
}
.quantitydivwrapper{
    display: flex;
 
    align-items: center;
}


.logoutmodalBtn{
    font-size: 20px;
    padding: 10px;
    border-radius: 10px;
 border-color: transparent;
 background-color: rgba(24, 23, 37, 1);
 color: white;
    margin-left: 10px;
    margin-right: 10px;
    cursor: pointer;
}
.logoutmodalBtn:hover{

    background-color: rgb(62, 61, 78);
}

 .logoutmodaltitle{
    font-size: 30px;
    margin-bottom: 60px;
 }
.LogOut-modal{

     
   position: fixed; /* Stay in place */
   z-index: 1; /* Sit on top */
   left: 0;
   top: 0;
   width: 100%; /* Full width */
   height: 100%; /* Full height */
   overflow: auto; /* Enable scroll if needed */
   background-color: rgb(0,0,0); /* Fallback color */
   background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
 
    
 

}
 .classamptydiv{
    display: grid;
    place-items: center;
    height: 300px;

 }
 .itemquantitydiv{
    display: grid;
    place-items: center;
    justify-content: center;
    align-items: center;
    width: 10px;
    margin-left: 15px;
 }
 .quantitybtn{
    width: 30px;
    height: 30px;
    border-radius: 5px;
    margin-left: 20px;
    font-size: 20px;

 }
 .classamptydiv div{

    font-size: 30px;

 }

.cartItemDeleteBtn{
    font-size: 10px;
    padding: 10px;
    border-color: transparent;
    background-color: rgb(215, 73, 73);
    color: white;
    border-radius: 10px;
    cursor: pointer;
}

.cartItemDeleteBtn:hover{

    background-color: rgb(243, 14, 14);
}

.CartBuyButton{
   
  
    font-size: 30px;
    padding: 10px;
    width: 200px;
    position: relative;
 
  left: 46%;

  margin: -50px 0 0 -50px;
    border-radius: 10px;
    color: white;
    background-color: rgba(38, 41, 46, 1);
border-color: transparent;
cursor: pointer;
}
.CartBuyButton:hover{
    background-color: rgb(89, 96, 107);

}
.AccountBallance{
    margin-top: 10px;
    
    font-size: 20px;
}
.cartTableImage{
    width: 80px;
    height: 30px;

}
.CartTotalPrice{
    text-align: right;
    margin: 20px ;
    font-size: 30px;
    color: red;
}
.cartItemSection{
    display: flex;
    align-items: center;
}
th, td {
  padding-top: 10px;
  padding-bottom: 20px;
  padding-left: 30px;
  padding-right: 40px;
}
th{
    font-size: 20px;
}
table, th, td {
  border: 1px solid black;
  border-collapse:collapse;
    
}
table {
        border-collapse: collapse;
        border-radius: 5px;
        border-style: hidden; /* hide standard table (collapsed) border */
        box-shadow: 0 0 0 1px #666; /* this draws the table border  */ 
    }

    td {
        border: 1   px solid rgb(0, 0, 0);
    }
.cartQuantityinput{
    width: 70px;
    text-align:center;
    
}
tr{
    text-align: center;
}
.ModalCarttable{
    width: 100%;
    max-height: 300px;
   
}
.ModalCarttableDiv{
    max-height: 400px;
    min-height: 400px;
    margin-top: 10px;
 
    overflow-x: scroll;
}
.HeaderLogo{
    margin-left: 30px;
    font-size: 30px;
}
.Carttext{

    margin-right: 3px;
}

.ModalTitle{
    display: flex;
    align-items: center;
    font-size: 20px;
    margin-top: 20px;
 
}
.Mtitle{
    margin-right: 4px;
    font-size: 30px;
}
.mlogocart{
    font-weight: bold;
    margin-top: 2px;
    font-size: 30px;
}
 nav{
    
    width: 100%;
    height: 80px;
    background-color: rgba(24, 23, 37, 1);
    display: flex;
    justify-content: space-between;
    align-items: center;
    color: white;
 
 
  
 }

 li{
    list-style: none;
    display: flex;
    align-items: center;
    font-size: 25px;
    margin-right: 20px;
 
transition: 500ms;
padding: 10px;
 }
li:hover{
    background-color: white;
    color: black;
   
    cursor: pointer;
    

}

 
a{
  all: unset;
}
.router-link-active{
   
  
  background-color: white;
  color: black;
 padding: 5px;
  cursor: pointer;
}
 ul{
    display: flex;
    align-items: center;
     
 }




 .modal {
   
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  left: 0;
  top: 0;
  width: 100%; /* Full width */
  height: 100%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
  background-color: rgb(0,0,0); /* Fallback color */
  background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
}
.LogOut-modal-content{
    background-color: #fefefe;

    display: flex;
    place-items: center;
 
 margin-top: 290px;
    display: grid;
    place-items: center;
    
    
 
  padding: 20px;
  border: 1px solid #888;
  border-radius: 10px;
  width: 30%;  
 
 
    z-index: 1;
 
}
/* Modal Content/Box */
.modal-content {
  background-color: #fefefe;
  
  position: fixed;
  right: 1px;
 margin-top: 80px;
 
 
    
    border-top-left-radius: 10px;
    border-bottom-left-radius: 10px;
  padding: 20px;
  border: 1px solid #888;
  width: 50%;  
  min-width: 1000px;
height: 100vh;
 
 
    z-index: 1;
 


}
 
/* The Close Button */
.close {
  color: #aaa;
  float: right;
  font-size: 28px;
  font-weight: bold;
}

.close:hover,
.close:focus {
  color: black;
  text-decoration: none;
  cursor: pointer;
}
 
</style>