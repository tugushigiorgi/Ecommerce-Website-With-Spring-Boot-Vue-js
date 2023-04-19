<template>

    <adminheading>
     
        <div class="parent">


 










            <div class="ManageOrdersTitlebox">

<span class="material-symbols-outlined tlogo">
group
</span>
    <div class="ManageOrdersTitle">Manage Users</div>
            </div>

         

                <div class="Searchcontainer">
                
                <input placeholder="Search with UserName" v-model="Searchinputtext">
                <button @click="searchbyname()">Search</button>
                
                
                </div>
 

 


 



                <div class="tablecontent">

                        <table>
                            <tr>
                                <th>User Id</th>
                                <th>User Full name</th>
                                
                                    <th> Email </th>
                                    <th>  Phone number </th>
                                    <th>  Address </th>

                                    <th>    orders </th>
                                    <th>Total orders</th>
                                  
                            </tr>
                            <tr v-for="user in getdata" v-if="getdata.length>0">
                                <td>{{ user.userid }}</td>
                                <td> {{ user.fullName }}</td>
                                <td>{{ user.Emaill }}</td>
                                <td>{{ user.Phone }}</td>
                                <td>{{ user.Address }}</td>
                             
                                <td v-if="user.totalOrders>=1"><button class="viewAllorderBtn" @click="getorder( user.userid)" >View   </button></td>
                                <td v-if="user.totalOrders==0"><button class="notfound" >   </button></td>
                                
                                
                                <td> {{ user.totalOrders }}</td>
                                    <td><button class="orderdeletebtn" @click="Deleteuser(user.userid)">Delete</button></td>
                                    

                               
                   
                            </tr>

                            <tr   v-if="getdata.length==0 && this.searchdiv ">
                                <td>{{  getuserbyname.userid }}</td>
                                <td> {{  getuserbyname.fullName }}</td>
                                <td>{{  getuserbyname.Emaill }}</td>
                                <td>{{  getuserbyname.Phone }}</td>
                                <td>{{  getuserbyname.Address }}</td>
                             
                                <td v-if="getuserbyname.totalOrders>=1"><button class="viewAllorderBtn" @click="getorder(getuserbyname.userid)" >View   </button></td>
                               
                                 
                                 <td v-if="getuserbyname.totalOrders==0"><button class="notfound" >   </button></td>
                                
                                <td> {{  getuserbyname.totalOrders }}</td>
                                    <td><button class="orderdeletebtn" @click="Deleteuser( getuserbyname.userid)">Delete</button></td>
                                    

                               
                   
                            </tr>








                        </table>



                        <center> 
    <div style="margin-top: 50px;">

    
        <vue-awesome-paginate     v-if="isfull" 
   :total-items= "totalItems"
   :items-per-page="12"
   :hide-prev-next-when-ends="true"
    
           
        v-model="currentPage"
    :on-click="onClickHandler"
  ></vue-awesome-paginate>
</div>
</center>





                </div>




                <center> 
   
   <div  v-if="    UserOrderedItemsModalVisibility"    class="LogOut-modal">
     <div class="LogOut-modal-content">
           
    
         
           <div class="logoutmodaltitle">
                         
       <div  style="float:right">
   
   <button class="ORderModalExitbtn" @click="UserOrderedItemsModalVisibility=false">X</button>

</div>
               
               <div> All orders of   {{ ordermodeldata[0].UserFullname }}</div>
                        <div>Total orders {{ ordermodeldata.length }}</div>
             
           </div>
           <div class="modalmaincontent">
   
                   <table class="orderedTable">
                           <tr class="OrderModalTr" >

                               <th>Order Id</th>
                               <th>Order Date</th>
                               <th>Product(s)</th>
                               <th>Order Status</th>
                               <th>Total Price</th>
                              


                           </tr>
                           <tr  class="OrderModalTr" v-for="item in ordermodeldata">

                               <td>{{ item.id }}</td>
                               <td>{{ item.orderDate}}</td>
                               <td><button class=" modelbtnviewall" @click="getordercarts(item.id)">View </button></td>
                               <td>{{ item.orderStatus }}</td>
                                <td>{{ item.totalPrice }}$</td>



                           </tr>
                   
                   </table>



           </div>

        
   
     
     </div>
   </div>




   <div     class="LogOut-modal" v-if="usercartmodel">
      <div class="LogOut-modal-content" v-if="this.modeldata">
            
     
          
            <div class="logoutmodaltitle">
                          
        <div  style="float:right">
    
    <button class="ORderModalExitbtn" @click="this.usercartmodel=false">X</button>

</div>
                            <div style="display: flex;">  
                <div style="margin-left: 10px;" >  Order ID  {{ this.modeldata[0].orderid }}</div>


                <div style="margin-left: 10px;">User ID  : {{ this.modeldata[0].USERID }}</div>
                <div style="margin-left: 10px;">  User name :{{ this.modeldata[0].userName }} </div>
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


    </adminheading>
    </template>
    
    
    <script>
    import adminheading from '../admincomponenets/AdminHeader.vue'
    import axios from 'axios';
    export default{
        components:{
            adminheading
        },
        watch:{

            Searchinputtext:{

         immediate:true,
         handler(newd,old){
             if(newd==""){
                 this.filldata()


             }
         }


 } 


},
        data(){
            return{
               
                UserOrderedItemsModalVisibility:true,
                Searchinputtext:"",
                totalPages:"",
   
   currentPage: 1,
   usercartmodel:false,
         totalItems:"1",
     perpage:"12",
                    searchdiv:false,
                    searchcategory:"Current",

                        ordermodeldata:[],
                    isfull:false,
                  modeldata:[],
                        getuserbyname:[],
                UserOrderedItemsModalVisibility:false, 
                  getdata:[]
          
        }}, 
        methods:{
       containsOnlyText(str) {
  return /^[a-zA-Z\s]+$/.test(str);
},

getorder(uid){

     
    var token=localStorage.getItem('token')
            axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
 


    axios.defaults.baseURL = 'http://localhost:8080';
axios.defaults.headers.common['access-control-allow-origin'] = 'http://localhost:5173';
axios.defaults.headers.common['Content-Type'] = 'application/json';

this.ordermodeldata=[]

axios.get( `/admin/getuserallorders/${uid}`,  {
 

 
})
.then(response => {
    
    if(response.data.length>=1){


        this.ordermodeldata=response.data



        this.UserOrderedItemsModalVisibility=true
    }
  
   
 
})
.catch(error => {
console.error(error);
});


},
getordercarts(id){

 
axios.defaults.baseURL = 'http://localhost:8080';
axios.defaults.headers.common['access-control-allow-origin'] = 'http://localhost:5173';
axios.defaults.headers.common['Content-Type'] = 'application/json';

var token=localStorage.getItem('token')
            axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;

axios.get('/admin/getcartsbyid/'+id,  {



})
.then(response => {
this.modeldata=response.data

  
this.usercartmodel=true




})
.catch(error => {
console.error(error);
});
















},





            searchbyname(){

                this.currentPage=1
                var token=localStorage.getItem('token')
            axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
                        axios.defaults.baseURL = 'http://localhost:8080';
axios.defaults.headers.common['access-control-allow-origin'] = 'http://localhost:5173';
axios.defaults.headers.common['Content-Type'] = 'application/json';



axios.get( `/admin/getuser/${this.Searchinputtext}`,  {
 

 
})
.then(response => {
     
    if(response.data!=null && response.data.userid!=null){
        this.getuserbyname=response.data
    this.isfull=false
    this.getdata=[]
    this.searchdiv=true
    }else{
          
        this.searchdiv=false
    this.isfull=false
    this.getdata=[]
    }




 
})
.catch(error => {
console.error(error);
});



                        
                



            },



            Deleteuser(id){

                var token=localStorage.getItem('token')
            axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
           
                axios.defaults.baseURL = 'http://localhost:8080';
axios.defaults.headers.common['access-control-allow-origin'] = 'http://localhost:5173';
axios.defaults.headers.common['Content-Type'] = 'application/json';



axios.delete('/admin/user/'+id,  {})
 .then(response => {
 
 this.getdata= this.getdata.filter(item => item.userid !== id);
 this.getuserbyname=[]
 this.Searchinputtext=""
})
.catch(error => {
console.error(error);
});






            },



            onClickHandler(val){
       
             this.currentPage=val
            this.filldata()
  
            }
        ,
       filldata(){
        this.getuserbyname=[]
        this.getdata=[]
        axios.defaults.baseURL = 'http://localhost:8080';
axios.defaults.headers.common['access-control-allow-origin'] = 'http://localhost:5173';
axios.defaults.headers.common['Content-Type'] = 'application/json';
 
var token=localStorage.getItem('token')
            axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;

axios.get('/admin/getusers/'+this.currentPage,  {
 

 
})
.then(response => {
    this.getdata=[]

   
 this.getdata=response.data. maindata
this. totalItems=response.data.total 
 
this.isfull=true
if(   response.data.Total<=12){
    this. perpage=this.totalItems 
    
}else{
    this. perpage=12
  
 
}
if(response.data.maindata.length==0){
    this.isfull=false
}
 
 
})
.catch(error => {
console.error(error);
});





       }
  
},

    

        mounted(){

      
        this.filldata()





        }
     
    
    
    }
 
    
    </script>
















<style scoped>



.Searchcontainer{
    display: flex;
    margin-bottom: 30px;
}
.Searchcontainer input {

width: 400px;
height: 30px;
margin-right: 5px;
padding: 6px;
border-radius: 5px;
font-size: 20px;
}
.Searchcontainer button{

    width: 100px;
    border-radius: 5px;
    font-size: 15px;
    transition: 500ms;
    cursor: pointer;
    border-color: transparent;
}
.Searchcontainer button:hover{
 background-color: transparent;
 color: white;
 border-color:white;
}

.orderdeletebtn{
    cursor: pointer;
      color: white;
    font-size: 15px;
    width: 90px;
    padding: 9px;
    
    border-radius: 10px;
    background-color: rgb(244, 49, 49);
}
.orderdeletebtn:hover{
    background-color: rgb(255, 0, 0);

}

 

 .pricedropdowntitle{
font-weight: bold;
    margin-right: 10px;
    color: white;
    font-size: 25px;
 }
.saveChangesBtn{
   
   padding: 10px;
   background-color: rgb(244, 49, 49);
   color: white;
   border-color: transparent;
   font-size: 20px;
   border-radius: 10px;
   margin-top: 20px;
   transition: 500ms;
   margin-bottom: 20px;
   cursor: pointer;
   margin-right: 20px;
}
.saveChangesBtn:hover{
   background-color: rgb(255, 0, 0);
}
 .modalmaincontent{
    display: grid;
    place-items: center;

 }
 .ORderModalExitbtn{
    font-size: 25px;
    border-color: transparent;
    cursor: pointer;
    transition: 500ms;
 }
 .ORderModalExitbtn:hover{
    color: rgba(17, 228, 151, 1);

 }
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





 



.viewAllorderBtn{

    padding: 10px;
    font-size: 20px;
    border-radius: 10px;
    cursor:  pointer;
    background-color: white;
    transition: 500ms;
    border-color: transparent;
}
.pricedropdownactual{
    border-color: transparent;
    font-size: 20px;
    background-color: rgb(255, 255, 255);
    color: rgb(0, 0, 0);
    padding: 10px;
    border-radius: 10px;
border-color: transparent;
}

.viewAllorderBtn:hover{
    background-color: rgb(172, 169, 169);


}
.notfound{

background-color: transparent;
    color: transparent;
    border-color: transparent;
}


.parent{

background-color:  rgba(24, 23, 37, 1);
 
padding: 150px;
height: auto;
 
min-height: 100vh;

}
.ManageOrdersTitlebox{
        display: flex;
        align-items: center;
        margin-bottom: 50px;
    }
    .ManageOrdersTitle{
        color: white;
        font-size: 60px;
    }
    .tlogo{
        color: white;
        font-size: 59px;

    }

    .pricedropwdown{
    display: flex;
    align-items: center;
        
   
    border-color: transparent;
 }

    .v2{
        margin-left: 20px;
        color: white;
    }
tr{
    color: white;
    font-size: 20px;
    width:  100%;
 }

  th, td {
  padding-top: 10px;
  padding-bottom: 10px;
  padding-left: 10px;
  padding-right: 10px;
 
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
    width: 101%;
     overflow: hidden;
        border-collapse: collapse;
        border-radius: 5px;
        border-style: hidden;  
        box-shadow: 0 0 0 1px rgb(255, 255, 255);  
      
 
   overflow-wrap: break-word;
 
    }

      table  td {
     
        border: 1px solid rgb(255, 255, 255);
       
       
    }

     table  tr{
    text-align: center;
        
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

.modelbtnviewall{
    width: 100px;
   background-color:  rgba(17, 228, 151, 1);
    color: rgb(255, 255, 255);
    padding: 10px;
    font-size: 20px;
    border-radius: 10px;
    cursor:  pointer;
   
    transition: 500ms;
    border-color: transparent;
}

.modelbtnviewall:hover{
    background-color:  rgb(3, 104, 67);


}


</style>