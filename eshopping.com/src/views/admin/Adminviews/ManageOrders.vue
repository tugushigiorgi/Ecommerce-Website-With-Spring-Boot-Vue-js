<template>



<adminheading  >
    <div class="parent"> 
    <center> 
   
    <div  v-if="    UserOrderedItemsModalVisibility && this.modeldata.length>0"    class="LogOut-modal">
      <div class="LogOut-modal-content">
            
     
          
            <div class="logoutmodaltitle">
                          
        <div  style="float:right">
    
    <button class="ORderModalExitbtn" @click="UserOrderedItemsModalVisibility=false">X</button>

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


    <div class="mainDiv">



        <div class="ManageOrdersTitlebox">

            <span class="material-symbols-outlined tlogo">
            shopping_bag
            </span>
                <div class="ManageOrdersTitle">Manage orders</div>
        </div>


        <div class="sectionConteiner">

            <div class="sectionBtns">
                <button class="sectionBtn"  :class="{sectionbtnactivated:  CurrentOrderContainer}"   @click="showCurrentOrder()">Current Orders</button>
                <button class="sectionBtn"  :class="{sectionbtnactivated:    DeliveredContainer}"  @click="showDeliveredContainer()">Delivered</button>
                <button class="sectionBtn"  :class="{sectionbtnactivated:                    CanceledContainer}"  @click="showcanceledContainer()"> Cancelled</button>
 
            </div>

            <div class="content">

                <div v-if="  CurrentOrderContainer" class="CurrentOrdersConteiner">
                    <div style="display:flex"> 
                  
                    <div class="pricedropwdown" >
        <div class="pricedropdowntitle">Order by</div>
        <select  class="pricedropdownactual" v-model="currentorderfilter">
       <option class="optinep">  Date Asc</option>
       <option class="optinep">Date Desc</option>
       
    </select>
    <div class="pricedropwdown" style="margin-left: 10px;">
        
        <select  class="pricedropdownactual" v-model="currentorderpricefilter">
       <option class="optinep">Price Increase</option>
       <option class="optinep">Price decrease</option>
       
    </select>

    </div>
            <button class="orderbtnfilter" @click="ordercurrentbtn">Sort</button>





    

            <div style="display: flex; margin-left: 20px;">
                <input type="text" class="ordersearchinput" placeholder="Search with order id" v-model="textinput">
                <button class="ordersearchinputbtn" @click="searchbykeywordbtn()" >Search</button>
            </div>

</div>
                    </div>

               

                  
                    <table    >
                        <tr class="OrderTableTH">
                            <th>Order Id</th>
                            <th>Order Date</th>
                            <th>Ordered Product(s)</th>
                            <th>total price</th>
                     
                            <th>User Full name </th>
                            <th>User Email </th>
                            <th>Phone Number </th>
                            <th>Address </th>
                            <th>Current Status</th>
                            
                        </tr>

                            <tr v-for="item in this.getdata" :key="item.id" v-if="getdata!=null || getdata!=[]">

                                <td>{{ item.id }}</td>
                                <td>{{ item.OrderDate }}</td>
                                <td><button @click="getordercarts(item.id)" class="ViewOrdersBTN">View All</button> </td>
                                <td>{{ item.TotalPrice }}$</td>
                                <td>{{ item.UserFullname }}</td>

                                <td>{{ item.UserEmail }}</td>

                                <td>{{ item.UserPhone }}</td>
                                <td>{{ item.UserAddress }}</td>

                                <td style="display: flex;">
                                    <select class="statusDropdown" v-model= "item.OrderStatus" > 
 
                                        <option >Preparing to Deliver</option>

                                        <option >Running Late </option>
                                         <option >  courier is coming </option>
                                        <option >  Delivered </option>
                                        <option >  Cancelled </option>
                                    </select>

                                        <button class="sevchangesbtn" @click="updatedeliverystatus(item.id,item.OrderStatus)">update </button>
                                </td>
                                <td>
                                    <button class="orderdeletebtn" @click="deleteorder(item.id)">Delete </button>
                                </td>
                            </tr>
                            <tr   v-if="search &&  this.searcheddata!=null">

<td>{{  this.searcheddata.id }}</td>
<td>{{ this.searcheddata.OrderDate }}</td>
<td><button @click="getordercarts( this.searcheddata.id)" class="ViewOrdersBTN">View All</button> </td>
<td>{{  this.searcheddata.TotalPrice }}$</td>
<td>{{  this.searcheddata.UserFullname }}</td>

<td>{{  this.searcheddata.UserEmail }}</td>

<td>{{  this.searcheddata.UserPhone }}</td>
<td>{{  this.searcheddata.UserAddress }}</td>

<td style="display: flex;"  >
    <select class="statusDropdown" v-model= "this.searcheddata.OrderStatus" > 
">
        <option >Preparing to Deliver</option>

        <option >Running Late </option>
         <option >  courier is coming </option>
        <option >  Delivered </option>
        <option >   Cancelled </option>
    </select>

        <button class="sevchangesbtn" @click="updatedeliverystatus(this.searcheddata.id,this.searcheddata.OrderStatus)">Save changes </button>
</td>
<td>
    <button class="orderdeletebtn" @click="deleteorder(this.searcheddata.id)">Delete </button>
</td>
</tr>

                    </table>
                
                       
                </div>
                
                <div v-if="DeliveredContainer" class="DeliveredContainer">

    
                    <div class="pricedropwdown" >
        <div class="pricedropdowntitle">Order by</div>
        <select  class="pricedropdownactual" v-model="currentorderfilter">
       <option class="optinep">  Date Asc</option>
       <option class="optinep">Date Desc</option>
       
    </select>
    <div class="pricedropwdown" style="margin-left: 10px;">
        
        <select  class="pricedropdownactual" v-model="currentorderpricefilter">
       <option class="optinep">Price Increase</option>
       <option class="optinep">Price decrease</option>
       
    </select>

    </div>
            <button class="orderbtnfilter" @click="subcategoryfilterbtn()">Sort</button>

    </div>


                    <table    >
                        <tr class="OrderTableTH">
                            <th>Order Id</th>
                            <th>Order Date</th>
                            <th>Ordered Product(s)</th>
                            <th>total price</th>
                     
                            <th>User Full name </th>
                            <th>User Email </th>
                            <th>Phone Number </th>
                            <th>Address </th>
                          
                          
                        </tr>


                            
                     




                            <tr v-for="item in  this.subcategorydata" :key="item.id">

                                <td>{{ item.id }}</td>
                                <td>{{ item.OrderDate }}</td>

                                <td><button @click="getordercarts(item.id)" class="ViewOrdersBTN">View All</button> </td>
                                
                                <td>{{ item.TotalPrice }}$</td>
                                <td>{{ item.UserFullname }}</td>

                                <td>{{ item.UserEmail }}</td>
                                <td>{{ item.UserPhone }}</td>
                                <td>{{ item.UserAddress }}</td>
                                <td>
                                    <button class="orderdeletebtn" @click="deleteorder(item.id)">Delete </button>
                                </td>
                            </tr>
  

                    </table>

                </div>

                <div  v-if="CanceledContainer" class="CanceledContianers">
                    <div style="display:flex">

                        <div class="pricedropwdown" >
        <div class="pricedropdowntitle">Order by</div>
        <select  class="pricedropdownactual" v-model="currentorderfilter">
       <option class="optinep">  Date Asc</option>
       <option class="optinep">Date Desc</option>
       
    </select>
    <div class="pricedropwdown" style="margin-left: 10px;">
        
        <select  class="pricedropdownactual" v-model="currentorderpricefilter">
       <option class="optinep">Price Increase</option>
       <option class="optinep">Price decrease</option>
       
    </select>

    </div>
            <button class="orderbtnfilter" @click="subcategoryfilterbtn()">Sort</button>

    </div>
 
 
 
</div>
                    <table    >
                        <tr class="OrderTableTH">
                            <th>Order Id</th>
                            <th>Order Date</th>
                            <th>Ordered Product(s)</th>
                            <th>total price</th>
                     
                            <th>User Full name </th>
                            <th>User Email </th>
                            <th>Phone Number </th>
                            <th>Address </th>
                          
                            
                            
                        </tr>
                        <tr v-for="item in  this.subcategorydata" :key="item.id">

<td>{{ item.id }}</td>
<td>{{ item.OrderDate }}</td>

<td><button @click="getordercarts(item.id)" class="ViewOrdersBTN">View All</button> </td>

<td>{{ item.TotalPrice }}$</td>
<td>{{ item.UserFullname }}</td>

<td>{{ item.UserEmail }}</td>
<td>{{ item.UserPhone }}</td>
<td>{{ item.UserAddress }}</td>
<td>
                                    <button class="orderdeletebtn" @click="deleteorder(item.id)">Delete </button>
                                </td>
</tr>
  

                    </table>

                </div>


            </div>


        </div>
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


</div>

</adminheading>



</template>

 
<script>
import adminheading from '../admincomponenets/AdminHeader.vue'
import axios from 'axios';
import "vue-awesome-paginate/dist/style.css";
    export default{
        components:{
            adminheading
        },
        watch:{

           textinput:{

                    immediate:true,
                    handler(newd,old){
                        if(newd==""){
                            this.currentordersdata()
                            this.  search=false

                        }
                    }


            }


        },

        mounted(){

                    if(localStorage.getItem('token')){

                
                      
                                axios.defaults.headers.common['access-control-allow-origin'] = 'http://localhost:5173';
axios.defaults.headers.common['Content-Type'] = 'application/json';
axios.defaults.baseURL = 'http://localhost:8080';
            var token=localStorage.getItem('token')
            axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
 


















        
            }else{
                this.$router.push('/login')
            }







        },

        data(){
            return{

                totalPages:"",
   
   currentPage: 1,
   
         totalItems:"1",
     perpage:"12",

                    searchcategory:"Current",


                    isfull:false,


                   search:true,
                textinput:"",
                currentorderfilter:"Date Asc",
                    currentorderpricefilter:"Price Increase",
                    subcategorydata:[],

                    modeldata:[],

                orderstatus:"Preparing to Deliver",
                UserOrderedItemsModalVisibility:false,
                    searcheddata:[],
                    getdata:[],
                CurrentOrderContainer:true,
                 DeliveredContainer:false,
                 CanceledContainer:false 

            }
        },
        methods:{
            onClickHandler(val){
       
       this.currentPage=val
                if(this.searchcategory=="Current"){

                        this.currentordersdata()
                }
                if(this.searchcategory=="Cancelled"){

                    this.getorderswith("Cancelled")
                } 
                if(this.searchcategory=="Delivered"){
                    this.getorderswith("Delivered")
                }
                     
 


        
       
  
},
                currentordersdata(page){
                    this.getdata=[]     
            axios.defaults.baseURL = 'http://localhost:8080';
axios.defaults.headers.common['access-control-allow-origin'] = 'http://localhost:5173';
axios.defaults.headers.common['Content-Type'] = 'application/json';
 

if(localStorage.getItem('token')){

    var token=localStorage.getItem('token')
    axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;

axios.get('/admin/getcurrentorders/'+this.currentPage,  {
 

 
})
.then(response => {
    this.getdata=[]

   
 this.getdata=response.data. maindata
this. totalItems=response.data.total 
this.searchcategory="Current"
this.isfull=true
if(   response.data.Total<=12){
    this. perpage=this.totalItems 
    
}else{
    this. perpage=12
  
 
}
if(response.data.maindata==null){
    this.isfull=false
}
 
 
})
.catch(error => {
console.error(error);
});

}
                },



            searchbykeywordbtn(){

              this.  search=true
                this.currentPage=1
                this.isfull=false
              
            axios.defaults.baseURL = 'http://localhost:8080';
axios.defaults.headers.common['access-control-allow-origin'] = 'http://localhost:5173';
axios.defaults.headers.common['Content-Type'] = 'application/json';
var token=localStorage.getItem('token')
            axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
if(/^\d+$/.test(this.textinput)){

 

axios.get('/admin/getorder/'+this.textinput,  {
 

 
})
.then(response => {
 
    if(response.data!=null && response.data.id!=null){
        this.getdata=[]
        
 
 this.searcheddata=response.data
  
}else{
    this.getdata=[]
    this.searcheddata=null
    this.search=false
}

})
.catch(error => {
console.error(error);
});
}


            },









            subcategoryfilterbtn(){

                if(this.currentorderfilter=="Date Asc"){
                        if(this.currentorderpricefilter=="Price Increase"){

                            this.subcategorydata.sort((a, b) => a.TotalPrice - b.TotalPrice);
                            this.subcategorydata.sort((a, b) => new Date(a.OrderDate) - new Date(b.OrderDate));

                        }else{
                            this.subcategorydata.sort((a, b) => b.TotalPrice - a.TotalPrice);

                            this.subcategorydata.sort((a, b) => new Date(a.OrderDate) - new Date(b.OrderDate));

                        } 

                    }else{
                        this.subcategorydata.sort((a, b) => new Date(b.OrderDate) - new Date(a.OrderDate));

                            if(this.currentorderpricefilter=="Price Increase"){
                                this.subcategorydata.sort((a, b) => a.TotalPrice - b.TotalPrice);
                                this.subcategorydata.sort((a, b) => new Date(b.OrderDate) - new Date(a.OrderDate));

                            }else{

                                this.subcategorydata.sort((a, b) => b.TotalPrice - a.TotalPrice);
                                this.subcategorydata.sort((a, b) => new Date(b.OrderDate) - new Date(a.OrderDate));

                            }
                    }


            }
            ,






            ordercurrentbtn(){
                    if(this.currentorderfilter=="Date Asc"){
                        if(this.currentorderpricefilter=="Price Increase"){

                            this.getdata.sort((a, b) => a.TotalPrice - b.TotalPrice);
                            this.getdata.sort((a, b) => new Date(a.OrderDate) - new Date(b.OrderDate));

                        }else{
                            this.getdata.sort((a, b) => b.TotalPrice - a.TotalPrice);

                            this.getdata.sort((a, b) => new Date(a.OrderDate) - new Date(b.OrderDate));

                        } 

                    }else{
                            this.getdata.sort((a, b) => new Date(b.OrderDate) - new Date(a.OrderDate));

                            if(this.currentorderpricefilter=="Price Increase"){
                                this.getdata.sort((a, b) => a.TotalPrice - b.TotalPrice);
                                this.getdata.sort((a, b) => new Date(b.OrderDate) - new Date(a.OrderDate));

                            }else{

                                this.getdata.sort((a, b) => b.TotalPrice - a.TotalPrice);
                                this.getdata.sort((a, b) => new Date(b.OrderDate) - new Date(a.OrderDate));

                            }
                    }


            },

            updatedeliverystatus(id,status){

             

                axios.defaults.baseURL = 'http://localhost:8080';
axios.defaults.headers.common['access-control-allow-origin'] = 'http://localhost:5173';
axios.defaults.headers.common['Content-Type'] = 'application/json';

var token=localStorage.getItem('token')
            axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;

axios.put( `/admin/updatedeliver/${id}/${status}`,{
 

 
})
.then(response => {
     
 
    if(status=="Delivered" || status=="Cancelled" ){

        this.getdata=this.getdata.filter(item => item.id !== id);
 
   
    
    if(this.getdata==null || this.getdata==[]){
            this.isfull=false
            this.currentPage=1
    }
 
 }


 
})
.catch(error => {
console.error(error);
});

            },







            deleteorder(id){
                axios.defaults.baseURL = 'http://localhost:8080';
axios.defaults.headers.common['access-control-allow-origin'] = 'http://localhost:5173';
axios.defaults.headers.common['Content-Type'] = 'application/json';

var token=localStorage.getItem('token')
            axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;

axios.delete('/admin/deleteorder/'+id,  {
 

 
})
.then(response => {
    this.modeldata=[]
 this.getdata= this.getdata.filter(item => item.id !== id);

    this.subcategorydata=  this.subcategorydata.filter(item => item.id !== id);
 
     
    if(this.searcheddata!=null){
        this.searcheddata=    this.subcategorydata.filter(item => item.id !== id);
        this.textinput=""
            this.search=false
    }
    
 
 
 this.UserOrderedItemsModalVisibility=true

        if(this.getdata==[] || this.subcategorydata==[] ){
            this.currentPage=1,
         
        this.totalItems="0"
        }







 
})
.catch(error => {
console.error(error);
});






            },







            getorderswith(ordertype){




                var token=localStorage.getItem('token')
            axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
                axios.defaults.baseURL = 'http://localhost:8080';
axios.defaults.headers.common['access-control-allow-origin'] = 'http://localhost:5173';
axios.defaults.headers.common['Content-Type'] = 'application/json';

var link =""
if(ordertype==="Cancelled"){
    link="/admin/getcanceledorders/"+this.currentPage
    this.searchcategory="Cancelled"

}if(ordertype==="Delivered"){
    link="/admin/getdeliveredorders/"+this.currentPage
    this.searchcategory="Delivered"
}
 



axios.get( link ,  {
 

 
})
.then(response => {
 
  
 
this.subcategorydata=[]
this.subcategorydata=response.data. maindata
this. totalItems=response.data.total 
 
this.isfull=true
if(   response.data.Total<=12){
    this. perpage=this.totalItems 
    
}else{
    this. perpage=12
  
 
}
if(response.data.maindata==null){
    this.isfull=false
}
 

 
})
.catch(error => {
console.error(error);
});


            },    






            
            getordercarts(id){

                this.modeldata=[]
                axios.defaults.baseURL = 'http://localhost:8080';
axios.defaults.headers.common['access-control-allow-origin'] = 'http://localhost:5173';
axios.defaults.headers.common['Content-Type'] = 'application/json';
var token=localStorage.getItem('token')
            axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;


axios.get('/admin/getcartsbyid/'+id,  {
 

 
})
.then(response => {
 this.modeldata=response.data
 
  
 console.log(response.data)
 
 
 
 
 this.UserOrderedItemsModalVisibility=true



 
})
.catch(error => {
console.error(error);
});


                
                
                
                
               





            },
            showCurrentOrder(){

 
                    this. CurrentOrderContainer=true
                    this. DeliveredContainer=false,
                    this. CanceledContainer=false 

                    this.currentPage=1
                    this.currentordersdata()
                    if(this.getdata==null || this.getdata==[]){

                        this.isfull=false
                            console.log("AQ")

                    }

                    this.searchcategory="Current"
               











            },
            showDeliveredContainer(){
                this. CanceledContainer=false 
                this. CurrentOrderContainer=false
                this.currentPage=1
                    this.getorderswith("Delivered")
                   
                    this. DeliveredContainer=true


            },
            showcanceledContainer(){
               
                this. CurrentOrderContainer=false
                    this. DeliveredContainer=false 
                    this.currentPage=1
                    this.getorderswith("Cancelled")
                  
                    this. CanceledContainer=true 

            }



        }
         

    }

</script>


<style scoped>





.ordersearchinput{

    height: 41px;
    width: 400px;
    font-size: 15px;
    padding-left: 10px;
    margin-right:10px;
    border-radius: 10px;
}
.ordersearchinputbtn{
    height: 43px;
    border-radius: 10px;
    width: 100px;

}




.sevchangesbtn{
    margin-left: 10px;
    font-size: 20px;
    width: 80px;
    background-color: rgb(49, 101, 244);
    color: white;
  
    border-color: transparent;
    cursor: pointer;
}
.orderbtnfilter{
  
    font-size: 20px;
    padding: 8px;
    width: 100px;
    
    border-radius: 10px;
    transition: 1000ms;
    cursor: pointer;
    border-color: transparent;
}
.orderbtnfilter:hover{
    color: white;
     background-color: rgba(255, 255, 255, 0.324);
}
.sevchangesbtn:hover{
    background-color: rgb(111, 0, 255);
}


.orderdeletebtn{
    cursor: pointer;
      color: white;
    font-size: 10px;
    width: 90px;
    padding: 9px;
    
    border-radius: 10px;
    background-color: rgb(244, 49, 49);
}
.orderdeletebtn:hover{
    background-color: rgb(255, 0, 0);

}


 .pricedropwdown{
    display: flex;
    align-items: center;
    margin-bottom: 20px;
   margin-right: 20px;
   margin-top: 20px;
    border-color: transparent;
 }
 .pricedropdowntitle{
font-weight: bold;
    margin-right: 10px;
    color: white;
    font-size: 20px;
 }
 .pricedropdownactual{
    border-color: transparent;
    font-size: 15px;
    background-color: rgb(255, 255, 255);
    color: rgb(0, 0, 0);
    padding: 10px;
    border-radius: 10px;
border-color: transparent;
}
.pricedropdownactual:hover{
    border-color: transparent;
    background-color: rgb(206, 195, 195);
    border-color: transparent;
}
 .parent{
    background-color:  rgba(24, 23, 37, 1);
    max-width: 100%;
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

 .modalmaincontent{
    display: grid;
    place-items: center;
  

 }

.content{
    background-color:  rgba(24, 23, 37, 1);
  
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

 

.statusDropdown{
   
    padding: 5px;
    font-size: 15px;
    border-color: transparent;
    border-radius: 5px;
}
 
.ViewOrdersBTN{
    padding: 5px;
   
    font-size: 15px;
    border-radius: 5px;
    transition: 500ms;
    border-color: transparent;
    cursor: pointer;
}
.ViewOrdersBTN:hover{
    background-color:  rgb(51, 51, 54);
    color: white;
}

.OrderTableTH{
    color: white;
    font-size: 25px;
    width:  100%;
 }

 .content table  th, td {
 
 
  padding-top: 10px;
  padding-bottom: 10px;
  padding-left: 10px;
  padding-right: 10px;



}
.content table th{
    font-size: 20px;
    
}
.content  table, th, td {
  border: 1px solid rgb(255, 255, 255);
   
  border-collapse:collapse;
  color: white;
   
     
}
.content table {
    min-width: 200vh;
     overflow: hidden;
        border-collapse: collapse;
        border-radius: 5px;
        border-style: hidden;  
        box-shadow: 0 0 0 1px rgb(255, 255, 255);  
      
 
   overflow-wrap: break-word;
 
    }
   
     .content  table  td {
     
        border: 1px solid rgb(255, 255, 255);
       
       
    }

    .content  table  tr{
    text-align: center;
        
  }



 
 
.mainDiv{

    padding-left: 70px;
    padding-top: 100px;
    background-color:  rgba(24, 23, 37, 1);
    height: 1100px;

    width: fit-content;
}
    
 
.sectionBtn{
    margin-right: 20px;
    font-size: 20px;
    padding: 10px;
     
    color: rgba(24, 23, 37, 1);
    border-color:transparent;
    transition: 500ms;
}

.sectionBtn:hover{
    background-color: rgb(79, 76, 104);
    color: white;
     

}

.sectionbtnactivated{

    background-color: rgba(24, 23, 37, 1);
    color: white;
    text-decoration: underline;

}
.sectionBtns{
    margin-bottom: 30px;
}

    
    .ManageOrdersTitle{
        color: white;
        font-size: 40px;
    }
    .ManageOrdersTitlebox{
        display: flex;
        align-items: center;
        margin-bottom: 30px;
    }
    .tlogo{
        color: white;
        font-size: 40px;

    }
    .OrderModalTr th  {
    color: black;
    font-size: 25px;
    padding-top: 10px;
  padding-bottom: 20px;
  padding-left: 30px;
  padding-right: 40px;
  border: 1px solid rgb(0, 0, 0);
  border-collapse:collapse;
  color: rgb(0, 0, 0);
    align-items: center;
}
.OrderModalTr td {
    color: black;
    border: 1px solid rgb(0, 0, 0);
        text-align: center; 
    vertical-align: middle;
    padding-top: 10px;
  padding-bottom: 20px;
  padding-left: 30px;
  padding-right: 40px;
  border: 1px solid rgb(0, 0, 0);
  border-collapse:collapse;
  color: rgb(0, 0, 0);
    align-items: center;
}

.orderedTable{
  
 
        border-collapse: collapse;
        border-radius: 5px;
        border-style: hidden;  
        box-shadow: 0 0 0 1px rgb(0, 0, 0);  
        
        border: 1px solid rgb(0, 0, 0);
  border-collapse:collapse;
  color: rgb(0, 0, 0);
    align-items: center;

}

 

  
</style>
