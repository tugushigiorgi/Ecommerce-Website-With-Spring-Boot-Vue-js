<template>
<heading  v-slot="scope">


<div class="mainDiv">
    <div class="SearchConteiner">
    
<div><input v-model="SearchBoxTextInput" class="SearchInput" type="text"   placeholder="Search Something..">
    <button  @click="  searchbykeyword()" class="MainSearchInputBTN"><span class="material-symbols-outlined mainsearhbtn">
search
</span></button>
  
   
 

</div>
<div class="CategoryesBtns">

    <button class="categoryBtn" @click=pccategoryfilter()>Computers</button>
    <button class="categoryBtn" @click=phonefilter()>Mobile Phones</button>
    <button class="categoryBtn" @click=homefilter()>home appliances</button>
    <button class="categoryBtn" @click=tvfilter()>Tv & Audio</button>
     
</div>

 

</div>

 
<div class="FilterHeading"  v-if="isfull" >
    <div style="display:flex;align-items:center"> 
    <div class="pricedropwdown">
        <div class="pricedropdowntitle">Order by</div>
        <select  class="pricedropdownactual" v-model="pricefilterholder">
       <option class="optinep"> Price Increase</option>
       <option class="optinep"> Price Decrease</option>
    </select>

    </div>
 

    <div class="priceselector">

        <div class="priceselectorTxt">Min Price</div>
        <input type="number" class="priceselectorinput" v-model="this.minprice"> 
        <div class="priceselectorTxt">Max Price</div>
        <input type="number" class="priceselectorinput" v-model="this.maxprice"> 
    </div>

    <div class="salesselector">

        <div class="login_wrapper_remember_me_text"><input type="checkbox" class="remember_me_checkbox">Sales</div>

             
    </div>
    <button class="categoryBtn" @click="filter_list(this.currentPage)">Filter</button>
        
</div>

</div>
 


    <ul class="listItem" v-if="isfull">
            
        <li class="listItemLi"   v-for="item in getdata " :key="item.Price"   >
          
                <productitem :isauth="isauthenticated" :adminpage=false :imgarray=item.Images :title=item.ProductName :price=item.Price :detailedlink=item.DetailedLink :product_id=item.id
                
          
            @addedtocart="scope.updateData "     
                />
     


      
            </li>
          
          
          

    </ul>

     



    <center>
    <vue-awesome-paginate   v-if="isfull"
   :total-items= "totalItems"
   :items-per-page="12"
   :hide-prev-next-when-ends="true"
    
           
        v-model="currentPage"
    :on-click="onClickHandler"
  ></vue-awesome-paginate>
 

    </center>
 
</div>

 

</heading>



</template>
 
<script>
import "vue-awesome-paginate/dist/style.css";
 
import heading from '../components/header.vue'
import productitem from '../components/ProductItem.vue';
 
import axios from 'axios'; 
 

export default{
    
watch:{


    getdata:{
          
            handler(newval,oldval){

          
     
    
                if(newval.length==0){
                    
         
      
          this.isfull = false;
   



                }else{

             
          this.isfull = true;
        

                   
                  
                }




            }


    },

    pricefilterholder:{

        immediate:true,
   handler(newval,oldval){
             if(newval=="Price Increase"){
                this.pricefilter="asc"
             }else{
                this.pricefilter="desc"
             }
        }

    }

},



components:{
    heading,productitem    },
data(){
    return{

SearchBoxTextInput:"",
        pricefilter:"asc",
        pricefilterholder:"Price Increase",
            minprice:0,
            maxprice:10000,
    
          current_link :" ",
          page_index:1,
         
            totalPages:"",
   
      currentPage: 1,
            isauthenticated:false,
            totalItems:"1",
        perpage:"12",
        pageshown:"",
        search_engine_type:"category",
       category_type:"Computers",
       isfull:false,
        getdata:[],
        page:1,
        data:[
                    "https://www.phoneworld.com.pk/price/wp-content/uploads/2021/08/7-1.jpg",
                    "https://media.pakprice.pk/89/YbIHVwc4pbFwbNHB95beaodzY4flCaHacnKvaxsK.jpeg",
                        "https://mobilebazaar.pk/wp-content/uploads/2022/05/iPhone-7-Price-in-Pakistan.png",
                    "https://img.zoommer.ge/zoommer-images/thumbs/0174438_apple-iphone-14-pro-max-128gb-space-black_550.jpeg"
            ],
    }
},

mounted(){

    axios.defaults.baseURL = 'http://localhost:8080';
axios.defaults.headers.common['access-control-allow-origin'] = 'http://localhost:5173';
axios.defaults.headers.common['Content-Type'] = 'application/json';

var route_param=""

if( localStorage.getItem('token')){
    this.isauthenticated=true



    
}



if (this.$route.params.category == undefined && this.$route.params.category == null) {
    
    this.category_filter_getdata("Computers")
    this.category_type="Computers"
}
else {
    route_param=this.$route.params.category.toString()

}
 
 


if(route_param=="Computers" || route_param=="Tv & Audio" || route_param=="Phone" || route_param=="Home appliances" ){

    this.category_type=  route_param
    this.category_filter_getdata( this.category_type  )
}
 
else{
    if(route_param){
    this.SearchBoxTextInput=route_param
    this.searchbykeyword()}
} 

 
 
 

 



   

},




methods:{
 
  
 
getlink(){return `${this.current_link}${this.page_index}`},



    pccategoryfilter(){
        this.currentPage=1
        this.category_type="Computers"
        this.category_filter_getdata("Computers")
    },
    
    
    phonefilter(){
        this.currentPage=1
        this.category_type= "Phone"
        this.category_filter_getdata("Phone")

    },
    homefilter(){
       
        this.currentPage=1
        this.category_type= "Home appliances"
        this.category_filter_getdata("Home appliances")

    },
    
    tvfilter()
    {   
        
        this.category_type= "Tv & Audio"
        this.currentPage=1
        this.category_filter_getdata( "Tv & Audio")


    },


    category_filter_getdata(categorytype){

        this.getdata=[]
        axios.defaults.baseURL = 'http://localhost:8080';
axios.defaults.headers.common['access-control-allow-origin'] = 'http://localhost:5173';
axios.defaults.headers.common['Content-Type'] = 'application/json';

axios.get(`/product/searchwith/${categorytype}?&orderby=${this.pricefilter}&minprice=${this.minprice}&maxprice=${this.maxprice}&sale=${true}&page=${1}`)

    

.then(response => {
    
 this.getdata=response.data. maindata
this. totalItems=response.data.Total 
this.search_engine_type="category"
if(   response.data.Total<=12){
    this. perpage=this.totalItems 
  
}else{
    this. perpage=12
 
}
const path =  categorytype
 

    var title=  categorytype
    window.history.pushState({ path }, title, path)
   

 this.current_link=`/product/searchwith/${categorytype}?orderby=${this.pricefilter}&minprice=${this.minprice}&maxprice=${this.maxprice}&sale=${true}&page=`
})
.catch(error => {
  console.error(error);
})


 



    },

















    searchbykeyword(){
        this.getdata=[]
        this.currentPage=1
        axios.defaults.baseURL = 'http://localhost:8080';
axios.defaults.headers.common['access-control-allow-origin'] = 'http://localhost:5173';
axios.defaults.headers.common['Content-Type'] = 'application/json';

let str =  this.SearchBoxTextInput;
str = str.charAt(0).toUpperCase() + str.slice(1);


axios.get("/product/searchby/"+str+`?orderby=${this.pricefilter}&minprice=${this.minprice}&maxprice=${this.maxprice}&sale=${true}&page=${1}`)
    

.then(response => {
    
    this.getdata=response.data. maindata
this. totalItems=response.data.Total 
this.search_engine_type="keyword"
 
if(   response.data.Total<=12){
    this. perpage=this.totalItems 
  
}else{
    this. perpage=12
 
}
const path =   this.SearchBoxTextInput
 

    var title=  this.SearchBoxTextInput
    window.history.pushState({ path }, title, path)

  this.current_link ="/product/searchby/"+this.SearchBoxTextInput+`?orderby=${this.pricefilter}&minprice=${this.minprice}&maxprice=${this.maxprice}&sale=${true}&page=`
})
.catch(error => {
  console.error(error);
})








    },






    filter_list(pagenum){
        this.getdata=[]
        this.currentPage=1
        axios.defaults.baseURL = 'http://localhost:8080';
axios.defaults.headers.common['access-control-allow-origin'] = 'http://localhost:5173';
axios.defaults.headers.common['Content-Type'] = 'application/json';
 
var LINK=""

if(this.search_engine_type==="category"){

    LINK=`/product/searchwith/${this.category_type}?&orderby=${this.pricefilter}&minprice=${this.minprice}&maxprice=${this.maxprice}&sale=${true}&page=`
}if(this.search_engine_type==="keyword"){
    LINK="/product/searchby/"+this.SearchBoxTextInput+`?orderby=${this.pricefilter}&minprice=${this.minprice}&maxprice=${this.maxprice}&sale=${true}&page=`


}
 
 

axios.get(LINK+1)
    

.then(response => {
   
    this.getdata=response.data. maindata
this. totalItems=response.data.Total 
 
if(   response.data.Total<=12){
    this. perpage=this.totalItems 
  
}else{
    this. perpage=12
 
}

this.current_link=LINK

})
.catch(error => {
  console.error(error);
})

    },


    onClickHandler(val){
     
            this.page_index=val
        this.search()
         
    },
    search(){
        this.getdata=[]
        axios.defaults.baseURL = 'http://localhost:8080';
axios.defaults.headers.common['access-control-allow-origin'] = 'http://localhost:5173';
axios.defaults.headers.common['Content-Type'] = 'application/json';
       
// axios.get(`/search?orderby=${this.pricefilter}&minprice=${this.minprice}&maxprice=${this.maxprice}&sale=${true}&page=${pagee}`)
    axios.get(this.getlink()  )


.then(response => {
    
 this.getdata=response.data. maindata
 
})
.catch(error => {
  console.error(error);
})





    }
}


}


</script>

<style scoped>

.notfoundtxt{

    font-size: 90px;

}
 .notfounddiv{
   
    align-items: center;
    display: grid;
    place-items: center;
    margin-top: 130px;
 }
 .notfoundimage{
    width: 200px;
    height: 200px;
 }
.salesselector{
    margin-left: 20px;
    display: flex;
    align-items: center;
}
.login_wrapper_remember_me_text{
    font-size: 25px;
    display: flex;
    align-items: center;
color: rgb(0, 0, 0);
}
.login_input_title{
   
cursor:none;
color: rgba(0, 0, 0, 0.58);
}
.remember_me_checkbox{
    margin-right: 10px;
    
accent-color: #000000;
transform: scale(1.5);
}   
.FilterHeading{
    
    margin-top: 30px;
    align-items: center;
    display: grid;
    place-items: center;

    
}
.priceselectorinput{
    width: 110px;
    font-size: 20px;
    border-radius: 10px;
    padding: 5px;
}
.priceselector{
    display: flex;
    height: 46px;
    align-items: center;
}
.priceselectorTxt{
    font-size: 25px;
    margin-right: 15px;
    margin-left: 15px;
}
 .pricedropwdown{
    display: flex;
    align-items: center;
    margin: 20px;
 }
.pricedropdownactual{
    font-size: 20px;
    background-color: rgba(24, 23, 37, 1);
    color: white;
    padding: 10px;
    border-radius: 10px;
border-color: transparent;
}
.pricedropdownactual:hover{
    background-color: rgb(56, 55, 69);
    border-color: transparent;
}
 
.pricedropdowntitle{
font-weight: bold;
    margin-right: 10px;
    font-size: 25px;
 }

 .listItem{
  
 
        
      
  /* display: inline-flex; */
  flex-wrap: wrap;
align-items: center;
 

  justify-content: center;
display: flex;
width: 80%;
margin: auto;

  padding: 0;

  

}
.listItemLi{
    float: left;
   
    list-style: none;
    display:inline ;
}

.categoryBtn{
    background-color: rgba(38, 41, 46, 1);
    color: white;
    font-size: 20px;
    margin: 10px;
padding: 10px;
border-color: transparent;
border-radius: 10px;
}
.categoryBtn:hover{
    background-color: rgb(125, 130, 140);
}

.SearchConteiner{
 display: grid;
 margin-top: 20px;
 place-items: center;

}
.SearchInput{

    width: 720px;
    height:     30px;
    background-color: rgba(38, 41, 46, 1);
    color: white;
    border-color: transparent;
    border:none;
    outline-width: 0;
    padding: 10px;
    border-radius: 10px;
    font-size: 15px;

}

.MainSearchInputBTN{
    background-color: transparent;
    border-color: transparent;
    color: white;
 position: relative;
 right: 40px;
 top: 5px;
   
}
.MainSearchInputBTN:hover{
    color: rgb(106, 43, 255);

}

</style>

