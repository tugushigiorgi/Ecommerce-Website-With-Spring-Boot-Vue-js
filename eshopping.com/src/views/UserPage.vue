<template>

 

    
<heading>
  
    <div style="display:flex ; padding: 50px;"> 
    <div class="UserInfoContainer">

        <div class="Userpinfo"> 
        <div class="UserProfilePhoto" >

  
                <img  class="profilephoto" src="https://flyclipart.com/thumb2/boss-circle-man-person-profile-staff-user-icon-133441.png"> 

        </div>
        <div class="Userinfo">
            <div class="UserName">{{ user_name }}</div>
             
            <div class="Userballance">Account ballance {{ user_ballance }}$</div>
            <div class="UserID"> ID  :{{ user_id }}</div>
             
   
        </div>
    </div>
        <div class="UserMainInformations">
            <Button class="myorders" @click="showMyorders()" :class="{mainbuttonsactivated:Myorders}"> 
              
                <span class="material-symbols-outlined">
shopping_bag
</span>  <div>My orders</div>
            </Button>
                        
                <Button class="mydata" @click=" showMyData()" :class="{mainbuttonsactivated:  MyData}"> 
                  
                <span class="material-symbols-outlined">
                        article
                        </span>
  <div>My Data</div>
            </Button>
            <Button class="mydata" @click=" showFavourites()" :class="{mainbuttonsactivated:  Favourites}"> 
                  
                  <span class="material-symbols-outlined">
                    local_atm
                          </span>
    <div>Update Ballance</div>
              </Button>





            <Button class="chpassword"  @click="showChangePassword()" :class="{mainbuttonsactivated:ChangePassword}"> 
              
                <span class="material-symbols-outlined">
lock
</span>
  <div>Change password</div>
            </Button>
        </div>




    </div >
   


    <div class="MainContent">


        <div v-if="Myorders" class="MyOrdersConteiner">
                <div class="myorderstitle">

                 
                    <span class="material-symbols-outlined lg">
shopping_bag
</span>
<div>My orders </div>
                </div>

                <div class="myordersfragmentBtns">
                     <button @click=" showCurrentOrdersConteiner()" :class="{activated:   CurrentOrdersConteiner}">Current</button>
                    <button @click="  showDeliveredOrdersConteiner() " :class="{activated:      DeliveredOrdersConteiner}">Delivered</button>
                    <button @click="showCanceledOrdersConteiner()" :class="{activated:  CanceledOrdersConteiner}">Cancelled</button>
                </div>
                <div   class="myorderssubconteiner">
       
                  

                    <div  v-if="CurrentOrdersConteiner" class="currentordersubcategory">
                    
                    <div class="wrapper"> 
                    
                        <table class="ModalCarttable">
                        <tr>
                            <th  style="width:34px">Order Id </th>
                            <th style="width:340px">Order Date </th>
                            <th>Ordered Product(s)</th>
                            <th>Total Price</th>
                            <th>  Address </th>
                            <th>Status</th>
                        </tr>
                        <tr v-for="item in getdata">
                            <th  style="width:34px">{{ item.id }}</th>
                            <th style="width:340px"> {{  item.OrderDate }}</th>
                            <th> <button class=" modelbtnviewall" @click="getordercarts(item.id)">View </button></th>
                            <th> {{ item.TotalPrice }}$</th>
                            <th>  {{ item.UserAddress }} </th>
                            <th>{{ item.orderStatus }}</th>
                        </tr>
                        


                        </table>
                    </div>
                    </div>
                    <div  v-if="DeliveredOrdersConteiner" class="Deliveredordersubcategory">

                        <div class="wrapper"> 
                    
                    <table class="ModalCarttable">
                    <tr>
                        <th  style="width:34px">Order Id </th>
                        <th style="width:340px">Order Date </th>
                        <th>Ordered Product(s)</th>
                        <th>Total Price</th>
                        <th>  Address </th>
                        <th>Status</th>
                    </tr>
                    <tr v-for="item in getdata">
                        <th  style="width:34px">{{ item.id }}</th>
                        <th style="width:340px"> {{  item.OrderDate }}</th>
                        <th> <button class=" modelbtnviewall" @click="getordercarts(item.id)">View </button></th>
                        <th> {{ item.TotalPrice }}$</th>
                        <th>  {{ item.UserAddress }} </th>
                        <th>{{ item.orderStatus }}</th>
                    </tr>
                    


                    </table>
                </div>
                    </div>
                    <div  v-if="CanceledOrdersConteiner" class="Cancalededordersubcategory">
                        <div class="wrapper"> 
                    
                    <table class="ModalCarttable">
                    <tr>
                        <th  style="width:34px">Order Id </th>
                        <th style="width:340px">Order Date </th>
                        <th>Ordered Product(s)</th>
                        <th>Total Price</th>
                        <th>  Address </th>
                        <th>Status</th>
                    </tr>
                    <tr v-for="item in getdata">
                        <th  style="width:34px">{{ item.id }}</th>
                        <th style="width:340px"> {{  item.OrderDate }}</th>
                        <th> <button class=" modelbtnviewall" @click="getordercarts(item.id)">View </button></th>
                        <th> {{ item.TotalPrice }}$</th>
                        <th>  {{ item.UserAddress }} </th>
                        <th>{{ item.orderStatus }}</th>
                    </tr>
                    


                    </table>
                </div>
                    </div>
                    
           


                    <center> 
    <div style="margin-top: 50px;">

    
        <vue-awesome-paginate     v-if="isfull" 
   :total-items= "totalItems"
   :items-per-page="5"
   :hide-prev-next-when-ends="true"
  
           
        v-model="currentPage"
    :on-click="onClickHandler"
  ></vue-awesome-paginate>
</div>
</center>





                </div>

        </div>

        <div  v-if="ChangePassword" class="ChangePasswordConteiner">
            <div class="myorderstitle">

                 
                <span class="material-symbols-outlined lg">
                lock
                </span>
                <div>Change Password</div>
                </div>
                <div class="succesmessage">{{  this.succesupdate }}</div>
                <form class="changepasswordform"  @submit.prevent="onSubmitResset"> 
                    <div class="errorMessage"> {{oldpassworderror}}</div>
                <div class="inputConteiner">
                    <div>Enter Current Password</div>
                    <input  v-model="useroldpasswordform" type="password" class="newpasswordinput" required> 
                </div>
                <div class="errorMessage">  {{  newpassworderror}}</div>
                <div class="inputConteiner">
                    <div>Enter New Password</div>
                    <input  v-model="usernewpasswordform" type="password"  class="newpasswordinput" required> 
                </div>
                <div class="errorMessage"> {{ repeatpassworderror}}  </div>
                <div class="inputConteiner">
                    <div>Repeat New Password</div>
                    <input  v-model="userrepeatpasswordform" type="password" class="newpasswordinput" required> 
                </div>
                <center>
                    <button type="submit" class="ChangePasswordBtn">Update Password</button>

                </center>
             </form>
        
        </div>

        <div v-if="MyData" class="Mydataconteiner">
            
             
            <div class="myorderstitle">

                 
<span class="material-symbols-outlined lg">
    article
</span>
<div>My Data</div>
            </div>
            <div class="Mydataconteinerdiv"> 
               
            <div class="mydataSection">
                <form  @submit.prevent="updateuserForm">  
                    <div class="succesmessage">{{  this.succesupdate }}</div>
                    <div class="errorMessage"> {{userformNameError}} </div>
                <div class="inputConteiner">
                       
                    <div>Fullname</div>
                 
                      
                        <input  placeholder="Enter your Fullname" v-model="userformName"  type="text"   class="newpasswordinput" required> 

                   
                 </div>
              
             
                <div class="inputConteiner">
                    <div>Email</div>
                    <input     v-model=" userformEmail" type="text"   class="newpasswordinput disabledemail" required> 
                </div>
                <div class="errorMessage">{{  userPhoneError}} </div>
                <div class="inputConteiner">
                    <div>Phone Number</div>
                    <input   v-model.number="userFormPhone" type="number" placeholder="Enter Phone number"   class="newpasswordinput" > 
                </div>
                <div class="errorMessage">{{userformEdressError}} </div>
                <div class="inputConteiner">
                    <div>Address</div>
                    <input   placeholder="Enter your Address" v-model=" userFormAddress" type="text"   class="newpasswordinput"  > 
                </div>
                <center>
                <button type="submit"    class="ChangePasswordBtn  ">Update Changes</button>
</center>
</form>
            </div>

        </div>
        </div>
        
      
        <div v-if="Favourites" class="Favouritesconteiner">
            
             
            <div class="myorderstitle">

                 
<span class="material-symbols-outlined lg">
   local_atm
</span>
<div>Update Ballance</div>
            </div>
           <div class="favouritessubcategory">
        <div style="font-size: 30px;margin-bottom: 50px">{{ballancemessage}}</div>

                    <div style="display: grid; place-items: center;">


                        <div class="inputConteiner" style="display: flex; width: 600px;" >


                    <div>Enter Emount </div>
                    <input   v-model ="MONEY" type="text"     class="newpasswordinput"   > 
                </div>


                <button  @click="fillballance()"    class="ChangePasswordBtn  ">Checkout ></button>


                    </div>
       

           </div>     






        </div>
     

        <center>
            <div     class="LogOut-modal" v-if="usercartmodel">
      <div class="LogOut-modal-content" v-if="this.modeldata">
            
     
          
            <div class="logoutmodaltitle">
                          
        <div  style="float:right">
    
    <button class="ORderModalExitbtn" @click="this.usercartmodel=false">X</button>

</div>
                            <div style="display: flex;">  
                <div style="margin-left: 10px;" >  Order ID  {{ this.modeldata[0].orderid }}</div>


                      <div style="margin-left: 10px;" >   Total Price :{{ this.modeldata[0]. total_ORDER_PRICE }}$</div>


                </div>
            </div>
            <div class="modalmaincontent">
    
                    <table class="orderedTable">
                            <tr class="OrderModalTr">

                                <th>Product Id</th>
                            
                                <th>Product Name </th>
                                <th>Quantity</th>
                                <th>List Price</th>
                              


                            </tr>
                            <tr  class="OrderModalTr" v-for="cart in this.modeldata" >
 
                                <td>{{ cart.protuctID }}</td>

                                <td>{{ cart.productName }}</td>
                                <td>{{ cart.productQuantity }}</td>
                                <td>{{ cart.productListPrice }}</td>
                             
                            
                                
                            </tr>
                    
                    </table>


 
            </div>

         
    
      
      </div>
    </div>
        </center>

       
    </div>
   
</div>

</heading>


</template>


<script>
 import axios from 'axios'
    import heading from '../components/header.vue'
    import productitem from '../components/ProductItem.vue'
    import "vue-awesome-paginate/dist/style.css"
export default {
    components:{
        heading,productitem
    },

    mounted(){
        var token=localStorage.getItem('token')
            axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
        this.   ordertype=  "Current"
    
        this.getorderwith( )
    
        this.getuserbasicdata()
    
       
    
    
    
    
    
    
    
    },





    data(){
        return{
            totalPages:"",
            modeldata:[],
   currentPage: 1,
            ballancemessage:"",
        user_name:"",
        user_id:"",
        user_ballance:"",         


            MONEY:"",







         totalItems:"",
     perpage:"5",

                  
                ordertype:"Current",

                    isfull:true,


            Myorders:true,
            Favourites:false,
            MyData:false,
            ChangePassword:false,
            usercartmodel:false,
            succesupdate:"",
            userformName:"user",
            userformSurname:"surname",
            userformID: "" ,
            userformEmail:"",
            userFormPhone:"",
            userFormAddress:"",


            userformNameError:"",
            userformUserNameError:"",

            userformIdError:"",

          
            userformEdressError:"",
            userPhoneError:"",
 

            allOrdersConteiner:true,
            CurrentOrdersConteiner:true,
            DeliveredOrdersConteiner:false,
            CanceledOrdersConteiner:false,


            getdata:[],

            //password change
            oldpassworderror:"",
            newpassworderror:"",
            repeatpassworderror:"",

            useroldpasswordform:"",
            usernewpasswordform:"",
            userrepeatpasswordform:""





        }
    },
    watch:{
        userformName:{
            immediate:true,
            handler(newv,oldv){
                if(newv==""){
                    this.userformNameError="User name should not be empty"
                }else{
                    this.userformNameError=""
                }

            }
        },
  
     
      
        userFormAddress:{
            immediate:true,
            handler(newv,oldv){
                    if(newv!=""){
                if(newv.length<5){
                    this.userformEdressError="Please input Valid Address"
                }
                   }
                else{
                    this.userformEdressError=""
                }

        }


    },
    userFormPhone:{
            immediate:true,
            handler(newv,oldv){

                if(newv!=""){

                    if(newv.toString().length<9){
                        this.userPhoneError="Please Enter Valid Number"
                    } 
                    else if(newv.toString().length>=9){
                    this.userPhoneError=""

                   }
                }  else{
                    this.userPhoneError=""
                }
               

              }  

            
               
               
 
                
             

    },



    usernewpasswordform:{
        immediate:true,
        handler(newv,oldv){
            if(newv!=""){
                if(newv.toString().length<8){
                    this.newpassworderror="Passwod must contain 8 or more than 8 character"
                }
                else if(newv==this.useroldpasswordform){
                    this.newpassworderror="New password matches new password"
                } else{
                    this.newpassworderror=""
                }



            }else{
                    this. newpassworderror=""
                }


        }

    },
    userrepeatpasswordform:{
        immediate:true,
        handler(newv,oldv){
            
            if(newv!=""){
                 
                if(newv.toString().length<8){
                    
                    this.repeatpassworderror="Passwod must contain 8 or more than 8 character"
                }
                if(newv!=this.usernewpasswordform){
                    this. repeatpassworderror="  passwords does not match"
               
                }else{
                    this. repeatpassworderror=""
                }
            
            } else{
                this. repeatpassworderror=""
            }



            


        }

    },

    useroldpasswordform:{

    immediate:true,
        handler(newv,oldv){
            if(newv!=""){ 
            if(newv.toString().length<8){
               this.oldpassworderror="Enter valid password"
            }
             if (newv==this.usernewpasswordform){
                this.newpassworderror="New password matches new password"
            }
             if(newv.toString().length>8 && newv!=this.usernewpasswordform){
                this.oldpassworderror=""
             }  
             

        }else{
            this.oldpassworderror=""
        }
    }

 }


},
    
 
    methods: {


        getuserbasicdata() {


            axios.defaults.baseURL = 'http://localhost:8080';
            axios.defaults.headers.common['access-control-allow-origin'] = 'http://localhost:5173';
            axios.defaults.headers.common['Content-Type'] = 'application/json';
            var token = localStorage.getItem('token')
            axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;


            axios.get("/profile/userbasicdata", {})
                .then(response => {


                    this.user_name = response.data.fullname
                    this.user_id = response.data.id
                    this.user_ballance = response.data.Ballance


                })
                .catch(error => {
                    console.error(error);
                });


        },


        onClickHandler(val) {

            this.currentPage = val

            this.getorderwith()

        },

        getordercarts(id) {

            axios.defaults.baseURL = 'http://localhost:8080';
            axios.defaults.headers.common['access-control-allow-origin'] = 'http://localhost:5173';
            axios.defaults.headers.common['Content-Type'] = 'application/json';

            if (localStorage.getItem('token')) {
                var token = localStorage.getItem('token')


                axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
            }

            axios.get('/product/getcartsbyid/' + id, {})
                .then(response => {
                    this.modeldata = response.data


                    this.usercartmodel = true


                })
                .catch(error => {
                    console.error(error);
                });


        },
        onSubmitResset() {


            axios.defaults.baseURL = 'http://localhost:8080';
            axios.defaults.headers.common['access-control-allow-origin'] = 'http://localhost:5173';
            axios.defaults.headers.common['Content-Type'] = 'application/json';
            var token = localStorage.getItem('token')
            axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;


            axios.put("/profile/updatepassword", {

                newpassword: this.usernewpasswordform,
                oldpassword: this.useroldpasswordform


            })
                .then(response => {

                    console.log(response.data)
                    if (response.data =="Success") {
                        this.useroldpasswordform=""
                        this.usernewpasswordform=""
                        this.repeatpassworderror=""
                        this.succesupdate = "Profile Updated Succesfully"



                    } else {
                        this.succesupdate = "Error Updating password"

                    }


                })
                .catch(error => {
                    console.error(error);
                });







          








        },
       updateuserForm() {
      
            if(this.userformNameError=="" &&
         

        

          
            this.  userformEdressError==""&&
            this. userPhoneError==""){

                 this.  updateuserdata()
               
            } 


    },


        fillballance(){


            axios.defaults.baseURL = 'http://localhost:8080';
            axios.defaults.headers.common['access-control-allow-origin'] = 'http://localhost:5173';
            axios.defaults.headers.common['Content-Type'] = 'application/json';
            var token = localStorage.getItem('token')
            axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;


            axios.post("/fillballance", {

             amount:this.MONEY


            })
                .then(response => {


                    if (response.data =="Success") {

                        this.ballancemessage = "Ballance Updated Succesfully"
            this.MONEY=""


                    } else {
                        this.ballancemessage = "Error Updating Ballance"

                    }


                })
                .catch(error => {
                    console.error(error);
                });







        },



            showFavourites(){
                this.Favourites=true,
                this.Myorders=false,
                this.Mycards=false,
                this.MyData=false,
                this.ChangePassword=false

            },

            showMyorders(){
                this.succesupdate=""
                this.currentPage=1
                this.Myorders=true,
                this.Mycards=false,
                this.MyData=false,
                this.ChangePassword=false
                this.Favourites=false 

            },
          
            showMyData(){
                this.succesupdate=""
                this.fulluserdata()
                this.MyData=true,
                this.Mycards=false,
                this.Myorders=false,
                this.Favourites=false,
                this.ChangePassword=false

            },
            showChangePassword(){
                this.succesupdate=""
                this.ChangePassword=true,
                this.MyData=false,
                this.Mycards=false,
                this.Favourites=false,
                this.Myorders=false
                
                 

            }
,
                getorderwith(){
                    
                    var link=""
                    if( this.ordertype=="Current"){

                        link=`/profile/getcurrentusrorders/${this.currentPage}`
                     
                    }if(  this.   ordertype=="Canceled"){
                        link= `/profile/getcanceledusrorders/${this.currentPage}`
                        
                    }if( this.   ordertype=="Delivered"){
                        link =`/profile/getdeliveredusrorders/${this.currentPage}`
                      
                    }
                    var token=localStorage.getItem('token')
            axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
                    axios.defaults.baseURL = 'http://localhost:8080';
axios.defaults.headers.common['access-control-allow-origin'] = 'http://localhost:5173';
axios.defaults.headers.common['Content-Type'] = 'application/json';
 
 
this.getdata=[]

axios.get(link,   {



})
.then(response => {
 
 
    if(response.data.maindata==null){
    this.isfull=false
    this.getdata=[]
}else{ 
   
   
 this.getdata=response.data. maindata
this. totalItems=response.data.total 
 console.log(this.getdata)
this.isfull=true
if(   response.data.Total<=5){
    this. perpage=this.totalItems 
    
}else{
    this. perpage=5
  
 
}
 
}
 
 




})
.catch(error => {
console.error(error);
});














                },





                    fulluserdata(){

                        axios.defaults.baseURL = 'http://localhost:8080';
axios.defaults.headers.common['access-control-allow-origin'] = 'http://localhost:5173';
axios.defaults.headers.common['Content-Type'] = 'application/json';
 
 

axios.get("/profile/userdata",   {



})
.then(response => {
 

    

                this.userformName =response.data.fullname
      
        
                this.  userformEmail  =response.data.email
                this. userFormPhone  =response.data. phone
            this. userFormAddress  =response.data. address



})
.catch(error => {
console.error(error);
});









                    },



                        updateuserdata(){



                           

                            axios.defaults.baseURL = 'http://localhost:8080';
axios.defaults.headers.common['access-control-allow-origin'] = 'http://localhost:5173';
axios.defaults.headers.common['Content-Type'] = 'application/json';
 
 

axios.put("/profile/userdata",   {

     Fullname :  this.userformName ,
     Email : this.  userformEmail   ,

      Phone :      this. userFormPhone ,
     Address :  this. userFormAddress  

 

})
.then(response => {
 

    this.succesupdate="Profile Updated Succesfully"

               



})
.catch(error => {
console.error(error);
});







                        },










       
        showCurrentOrdersConteiner(){
  
            this.currentPage=1
            this.   ordertype= "Current"
            this.getorderwith( )


     
           
            this.DeliveredOrdersConteiner=false,
            this.CanceledOrdersConteiner=false
            this.CurrentOrdersConteiner=true
        }
,
        showDeliveredOrdersConteiner(){
         
            this.CurrentOrdersConteiner=false,
           
          this.CanceledOrdersConteiner=false
            
          this.currentPage=1
          this.   ordertype="Delivered"
          this.getorderwith( )
          this.DeliveredOrdersConteiner=true

        },
        showCanceledOrdersConteiner(){
            this.DeliveredOrdersConteiner=false,
            this.CurrentOrdersConteiner=false,
           
            this.currentPage=1
            this.   ordertype= "Canceled"
          this.getorderwith( )

          this.CanceledOrdersConteiner=true


        }





    

    }} 
   
</script>
<style  scoped>
.logoutmodaltitle{
     font-size: 25px;
        height: auto;
            margin-bottom: 20px;
}


.LogOut-modal-content{
        background-color: #fefefe;
     
     margin-top: 100px;
    
      padding: 20px;
      border: 1px solid #888;
      border-radius: 10px;
      width: 70%;  
      height: 500px;
    max-height: 500px;
        overflow-x: auto;
        z-index: 1;
     
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



 .modelbtnviewall{
    width: 100px;
   background-color:  rgb(255, 255, 255);
    color: rgb(0, 0, 0);
    padding: 10px;
    font-size: 20px;
    border-radius: 10px;
    cursor:  pointer;
   
    transition: 500ms;
    border-color: transparent;
}

.modelbtnviewall:hover{
    background-color:  rgb(168, 180, 175);


}
  
.errorMessage{
    color: red;
    text-align: left;
    position: relative;
    left: 280px;
   bottom: 10px;
}
.MainContent{
    width: 100%;
    height: auto;
    min-height: 100vh;
    border-radius: 10px;
    margin-left: 30px;
    background-color: rgba(24, 23, 37, 1) !important;;
    

    
}

.listItem{
  
 
  display:inline-table;
  width: 100%;
    
 

}
.listItemLi{
list-style: none;
display:inline ;
 color: white;
 
}



.Mydataconteinerdiv{
     
    display: grid;
    place-content: center;
 
}
 
.Favouritesconteiner{
    padding: 20px;
  color: white;
  
}


.Mydataconteiner{
    padding: 20px;
}
.ChangePasswordBtn{
    display: flex;
    font-size: 26px;
    align-items: center;
     
    border-radius: 10px;
    border-color: transparent;
    padding: 10px;
    width: 300px;
    margin-top: 50px;
    text-align: center;
    justify-content: center;

    transition: 500ms;
}
.ChangePasswordBtn:hover{

    background-color: rgb(42, 41, 41);
    color: white;
}
.newpasswordinput{
    width: 500px;
    height: 20px;
    background-color: rgba(24, 23, 37, 1);
    border-color: white;
    border-radius: 10px;
    color: white;
    font-size: 20px;
    padding: 10px;

}
.ChangePasswordConteiner{
    padding: 20px;
  

}
.changepasswordform{
    display: grid;
    place-content: center;
    margin-top: 90px;
}
.inputConteiner{

    display: flex;
    align-items: center;
    align-content: center;
    justify-content: space-between;
    width: 800px;
    margin-bottom: 20px;
    color: white;
    font-size: 25px;
}
.cartTableImage{
    width: 100px;
    height: 70px;
    margin-right: 9px;
    border-radius: 10px;
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
  border: 1px solid rgb(255, 255, 255);
  border-collapse:collapse;
  color: white;
    
}
table {
        border-collapse: collapse;
        border-radius: 5px;
        border-style: hidden; /* hide standard table (collapsed) border */
        box-shadow: 0 0 0 1px rgb(255, 255, 255); /* this draws the table border  */ 
    }

    td {
        border: 1px solid rgb(255, 255, 255);
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
    margin-top: 40px;
}
.myorderstitle{
    display: flex;
    font-size: 50px;
    align-items: center;
    margin-bottom: 40px;
   color: white;
}
.myordersfragmentBtns button{

    border-color: transparent;
    margin-right: 10px;
    font-size: 25px;
    padding: 5px;
    transition: 500ms;
   
}
.myordersfragmentBtns button:hover{

    background-color: transparent;
    text-decoration-line:underline;
    color:rgb(255, 255, 255)
}

.activated{
    background-color: transparent;
    text-decoration-line:underline;
    color:rgb(255, 255, 255)
}


.MyOrdersConteiner{
    padding: 20px;
}
.lg{
    font-size: 50px;
    font-weight: bold;

}
 


.disablesubmitbtn{
    pointer-events:none; 

}



.Userpinfo{
    display: grid;
 
 place-items: center;
}
.Userpinfo div{
    margin-top: 5px;
}

.UserID{
    text-align: center;
    font-size: 20px;
    margin-bottom: 20px;
}
.Userballance{
    text-align: center;
    font-size: 20px;

}
.UserName{
    font-size: 25px;
        text-align: center;
}
 
.UserInfoContainer{
padding: 20px;
    width: 400px;
    height: 460px; 
    background-color: rgba(24, 23, 37, 1);
   border-color: transparent;
    border-width: 2px;
    border-style: groove;
    border-radius: 10px;
     color: white;

 
 
}
.profilephoto{
    width: 100px;
    height: 100px;
    border-radius: 10px;
}
 .UserMainInformations button{
  display: flex;
    font-size: 26px;
    align-items: center;
    margin-top: 5px;  
    border-radius: 10px;
    border-color: transparent;
    padding: 10px;
    width: 300px;
    text-align: center;
    justify-content: center;
    transition: 500ms;

 }
 .UserMainInformations button:hover{
    
    background-color:  rgb(23, 23, 32);
    color: white;
 }

 .mainbuttonsactivated{

    background-color:  rgb(23, 23, 32);
    color: white;
 }


     
.UserMainInformations{
        display: block;
    }
    .UserMainInformations {
        
        display: grid;
 
 place-items: center;

    }
    .disabledemail{

pointer-events:none;   
 color:rgb(0, 0, 0);
 background-color: rgb(251, 255, 254);
 font-weight: bold;

}




.orderedTable{
    width: 100%;
 
        border-collapse: collapse;
        border-radius: 5px;
        border-style: hidden;  
        box-shadow: 0 0 0 1px rgb(0, 0, 0);  
        
        border: 1px solid rgb(0, 0, 0);
  border-collapse:collapse;
  color: rgb(0, 0, 0);
    align-items: center;

}
 

.orderedTable  tr th {
    color: rgb(0, 0, 0);
    border: 1px solid rgb(0, 0, 0);

}
.orderedTable  tr td{
    color: rgb(0, 0, 0);
    border: 1px solid rgb(0, 0, 0);
}
.tablecontent  {
     display: grid;
    
}






.wrapper{
    height: 59vh;
    min-height: 59vh;
}

.succesmessage{
    color:white;
    font-size: 30px;
    text-align: center;
    margin-bottom: 30px;
}


</style>