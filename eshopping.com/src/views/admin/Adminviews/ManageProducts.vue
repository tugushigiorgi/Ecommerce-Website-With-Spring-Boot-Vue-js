<template>

<adminheading>
        <div class="parent">
            
            <div class="mainDiv"> 
        <div class="ManageOrdersTitlebox">

<span class="material-symbols-outlined tlogo">
    manage_search
</span>
    <div class="ManageOrdersTitle">Manage Products</div>
</div>
<div class="SearchConteiner">

<div><input v-model="SearchBoxTextInput" class="SearchInput" type="text" name="" id="" placeholder="Search Something..">
    <button   @click="  searchbykeyword()" class="MainSearchInputBTN"><span class="material-symbols-outlined mainsearhbtn">
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
            


<ul class="listItem">
            
            
            <li class="listItemLi"   v-for="item in getdata " :key="item.Price" >
                <div class="controlling">
                    <button class=" controllingbtns DeleteBtn" @click="Deletemethod(item.id)">  Delete</button>
                    <button class="  controllingbtns Updatebtn" @click="Updatemethod(item.id)">Update</button>
                  </div>
                <productitem :adminpage=true :imgarray=item.Images :title=item.ProductName :price=item.Price :detailedlink=item.DetailedLink
                
                :pruductSold=item.TotalSold
                :productQuantity=item.TotalLeft
                
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
 
    <center>







      <!-- Modal for Delete       -->
<div    v-if="DeleteProductModal"   class="LogOut-modal">
<div class="LogOut-modal-content">
<div class="LogOut-modal-container">

<div class="logoutmodaltitle">
Are you sure You want to Delete This Product ?
</div>

<div class="logoutbtnarr">

<button class="logoutmodalBtn" @click="DeleteProductModal=false">Cancel</button>
<button class="logoutmodalBtn deletebtnapprov" @click="deleteproductnow()" >Delete</button>
</div>

</div>
</div>
</div> </center>

 
<center>      <!-- Modal for  Update       -->        
<div   v-if="UpdateProductModal"     class="LogOut-modal">
<div class="LogOut-modal-content1">
<div class="LogOut-modal-container">

<div class="logoutmodaltitle">
 
   
    
    <div style="margin-top:50px;font-size:40px">Update Product</div>
   
   <form class="addnewproductForm"  @submit.prevent="onSubmithandler"> 
             
               <div class="inputConteiner">
                   <div>Enter Product Name</div>
                   <input  v-model="productname" type="text" class="newpasswordinput" > 
               </div>
             
               <div class="inputConteiner">
                   <div>Enter Product  Price</div>
                   <input  v-model="productprice" type="number"  class="newpasswordinput"  > 
               </div>
              
               <div class="inputConteiner">
                   <div>Enter Product Quantity</div>
                   <input  v-model="productquantity" type="number"  class="newpasswordinput"  > 
               </div>
               
               <div class="  pricedropwdown">
       <div class="pricedropdowntitle">Choose Category</div>
       <select  class="pricedropdownactual" v-model=category>
      <option class="optinep">Phone</option>
      <option class="optinep">Tv & Audio</option>
      <option class="optinep">Computers</option>
      <option class="optinep">Home appliances</option>
   </select>
  </div>
 
               <div class="inputConteiner">
                   <div class="desctitle">Enter Product Description</div>
                   <textarea  v-model="productdescription" type="number"  class="newpasswordinput description"  ></textarea>
               </div>

               <div class="specificationConteiner">
                   <button @click.prevent  @click=" AddSpecificationConteiner=! AddSpecificationConteiner" class="AddSpecBTN">+ Add Specification</button>

                   <div v-if=" AddSpecificationConteiner" class="SpecificationInputContainer fade ">
                    
                       <div>
                           <div class="SpecificationTitle" >Specification Name</div>
                           <input v-model="specKey" type="text" class="SpecificationInput">
                       </div>
                       <div>
                           <div class="SpecificationTitle">Specification Value</div>
                           <input  v-model="specvalue"  type="text"  class="SpecificationInput">
                       </div>
                       

                           <button  @click.prevent @click="SaveSpec()" class="addspecmainbtn"  >Add</button>
                        

                   </div>  
                   
                   <div class="specificationList" v-for="  key,value    in  SpecificationData ">
                                     
                                     <div style="font-size: 15px"> {{  value }}  </div>
                                     <div style="display:flex ; align-items:center; font-size: 15px ; margin-left:210px;">{{ key }} <button @click.prevent  @click="removeSpecification( value)" class="removeSpecItemBTN">X</button> </div>
                                 </div>

               </div>

              <div class="imageuploadConteiner">
                   <div class="imageuploadTitle">Upload Images (max 6)</div>

                   <div style="float:left ; font-size:20px">Current images</div>
                   <br>
                    <div class="currentimages">
                     

                        <div class="currentimagecontainer" v-for="(item,index) in ImageArray">
                    <div>{{item.filename}}</div>
                      <button @click.prevent  @click=" removeimage(item,index)" class="removeSpecItemBTN">X</button>          
                      
                    </div>
                        
        

                    </div>


                      
                   <div class="inputarray"   v-for="(item, i) in length" :key="i">
    
                    
                    <input type="file" ref="fileInput" class="uploadseccont"  accept="image/gif,image/jpeg,image/png">
  
  </div>

              </div>       
              
   

               <center>
                   <button type="submit" class="ChangePasswordBtn">Update </button>

               </center>   
            </form>



</div>

<div class="logoutbtnarr">

<button class="logoutmodalBtn" @click="UpdateProductModal=false">Cancel</button>
 </div>
 
</div>
</div>
</div> 
</center>

<center> 
<div    v-if="SalesModal"   class="LogOut-modal">
<div class="LogOut-modal-content">
<div class="LogOut-modal-containerrv2">

    <div class="pricedropwdown">
        <div class="pricedropdowntitle"> Select Sales Group</div>
        <select  class="pricedropdownactual">
       <option class="optinep">  Date Asc</option>
       <option class="optinep">Date Desc</option>
       
    </select>

    </div>
    <div class="current">Current Price 400$</div>


    <div class="inputConteiner">
                    <div>Enter Sale Price</div>
                    <input  v-model="productname" type="text" class="newpasswordinput" > 
                </div>


 

<div class="logoutbtnarrv2">

<button class="logoutmodalBtn" @click="SalesModal=false">Cancel</button>
<button class="logoutmodalBtn  savesalesmodal" @click="deleteBannernow()" >Save</button>
</div>

</div>
</div>
</div> 
</center>





        </div></div>
   
</adminheading>
</template>


<script>
 
  import axios from 'axios';
import adminheading from '../admincomponenets/AdminHeader.vue'
import productitem from  '../../../components/ProductItem.vue'
import "vue-awesome-paginate/dist/style.css";
 
export default{ 
    components:{
        adminheading,productitem  
    },
 data(){
    return{

        totalPages:"",
   
   currentPage: 1,
   
         totalItems:"1",
     perpage:"12",

     search_engine_type:"normal",



        AddSpecificationConteiner:false,
            specKey:"",
            specvalue:"",
            SpecificationData:{},
            ImageArray:[],
            length:0,
            productname:"",
            productprice:"",
            productquantity:"",
            productdescription:"",
            category:"",

            currentproductid:0,
         trashimagesid:[] ,   
 
        SalesModal:false,
        UpdateProductModal:false,
        DeleteProductModal:false,
        currentitemidtodelete:0,
        dataforupdate:[],
        getdata:[],
        isfull:false,
        category_type:"Computers",
        SearchBoxTextInput:""
    }
 },
 
 mounted(){
    this.category_filter_getdata(this.category_type)

},
 
 methods:{

    searchbykeyword(){
        
        this.getdata=[]
        axios.defaults.baseURL = 'http://localhost:8080';
axios.defaults.headers.common['access-control-allow-origin'] = 'http://localhost:5173';
axios.defaults.headers.common['Content-Type'] = 'application/json';

var token=localStorage.getItem('token')
            axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
this.SearchBoxTextInput =  this.SearchBoxTextInput.charAt(0).toUpperCase() +   this.SearchBoxTextInput.slice(1);

axios.get(`/admin/manageproductsbyname/${this.SearchBoxTextInput}/${this.currentPage}` , {
 
})
.then(response => {
    this.search_engine_type="keyword"
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
})


 







    },



    onClickHandler(val){
       
       this.currentPage=val
        
        
         
        if(this.search_engine_type==="keyword"){
           
            this.searchbykeyword()
        }
        else{
            this.category_filter_getdata(this.category_type)
        }




  
}, pccategoryfilter(){
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


    category_filter_getdata(categorytype,pagee){

        this.getdata=[]
        axios.defaults.baseURL = 'http://localhost:8080';
axios.defaults.headers.common['access-control-allow-origin'] = 'http://localhost:5173';
axios.defaults.headers.common['Content-Type'] = 'application/json';
var token=localStorage.getItem('token')
            axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
axios.get(`/admin/manageproductsby/${categorytype}/${ this.currentPage}`)

    

.then(response => {
    this.getdata=[]
 this.getdata=response.data. maindata
this. totalItems=response.data.total 
this.search_engine_type="category"
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
})

    },

       





        removeimage(item,index){
        
            this.  ImageArray.splice(index, 1);
            this.length=6-  this.    ImageArray.length
            this.trashimagesid.push(item.id.toString())
         

        },
    removeSpecification(key){
                delete  this.SpecificationData[key]
                
 

            },
    SaveSpec(){
                   
                   this. SpecificationData[this.specKey]=this.specvalue
              
                   this.AddSpecificationConteiner=false

                   this.specKey="",
                   this.specvalue=""
           },
           //Deleting PRODUCT
    Deletemethod(id){
        this.DeleteProductModal=true
        this.currentitemidtodelete=id
      
    },


//FillING Existing Product data To update next
    Updatemethod(id){
       

        var token=localStorage.getItem('token')
            axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
        axios.defaults.baseURL = 'http://localhost:8080';
axios.defaults.headers.common['access-control-allow-origin'] = 'http://localhost:5173';
axios.defaults.headers.common['Content-Type'] = 'application/json';
this.currentproductid=id
axios.post('/admin/getdatatoupdate/'+id, {
 
})
.then(response => {
   
  var dataforupdate=response.data 
  this.productname=dataforupdate.ProductName

          this.  productprice =dataforupdate.ProductPrice
          this.  productquantity =dataforupdate.ProductQuantity
          this.  productdescription =dataforupdate.Description
          this.  category =dataforupdate.Category
           this.SpecificationData=dataforupdate.Specs
         


       
       this.    ImageArray=dataforupdate.images 
       if(this.    ImageArray.length>6){

        this.length=0
        this.    ImageArray.length=6
            console.log( this.    ImageArray)
       }else{
        this.length=6
        console.log("-------------------------------------------")
       }
        
      
})
.catch(error => {
  console.error(error);
})


 

this.trashimagesid=[]
this.ImageArray=[]
        this.UpdateProductModal=true







    },
    MakeSaleMethod(){
        
        this.SalesModal=true
    },
    deleteproductnow(){
        axios.defaults.baseURL = 'http://localhost:8080';
axios.defaults.headers.common['access-control-allow-origin'] = 'http://localhost:5173';
axios.defaults.headers.common['Content-Type'] = 'application/json';
var token=localStorage.getItem('token')
            axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
axios.delete('/admin/product/'+this.currentitemidtodelete, {
 
})
.then(response => {
    
 
    this.DeleteProductModal=false
      
    this.getdata= this.getdata.filter(item => item.id !== this.currentitemidtodelete);

})
.catch(error => {
  console.error(error);
})

    },



    
    addalldata(images){
        var token=localStorage.getItem('token')
            axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
        axios.defaults.baseURL = 'http://localhost:8080';
axios.defaults.headers.common['access-control-allow-origin'] = 'http://localhost:5173';
axios.defaults.headers.common['Content-Type'] = 'application/json';

 if(images!=null){

 

axios.put('/admin/product/'+this.currentproductid,  {

ProductName: this.productname,
ProductPrice:   String(this.productprice) ,
ProductSalePrice:"",
ProductQuantity:String( this.productquantity) ,
Category:this.category,
Description: this.productdescription,
Specs:    this. SpecificationData,
images:images,
IDOfImagesToBeDeleted :this.trashimagesid


})
.then(response => {

    this.UpdateProductModal=false
    this.trashimagesid=[]

    const index = this.getdata.findIndex(obj => obj.id ===this.currentproductid);


if (index !== -1) {
    this.getdata[index] = response.data;
}

 

 

 


})
.catch(error => {
console.error(error);
});


}else{



    axios.put('/admin/product/'+this.currentproductid,  {

ProductName: this.productname,
ProductPrice:   String(this.productprice) ,
ProductSalePrice:"",
ProductQuantity:String( this.productquantity) ,
Category:this.category,
Description: this.productdescription,
Specs:    this. SpecificationData,
images:[],
IDOfImagesToBeDeleted :this.trashimagesid


})
.then(response => {

    this.UpdateProductModal=false
    this.trashimagesid=[]

    const index = this.getdata.findIndex(obj => obj.id ===this.currentproductid);

if (index !== -1) {
    this.getdata[index] = response.data;
}

 

 


 



})
.catch(error => {
console.error(error);
});






}



    },

    onSubmithandler(){
       

        const formData = new FormData();
const fileInputs = document.querySelectorAll('.uploadseccont');

 
 var c=false;
fileInputs.forEach(fileInput => {
  const files = fileInput.files;
  for (let i = 0; i < files.length; i++) {
    formData.append('files', files[i]);
    c=true;
  }
});
 
var token=localStorage.getItem('token')
            axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
 
axios.defaults.baseURL = 'http://localhost:8080';
axios.defaults.headers.common['access-control-allow-origin'] = 'http://localhost:5173';

 
if (!c) {
  

    this.addalldata(null)

} else {
  
 
 
 
axios.post('/admin/upload', formData, {
  headers: {
    'Content-Type': 'multipart/form-data'
  }
})
  .then(response => {
    this.addalldata(response.data)
    
     
  })
  .catch(error => {
    console.error(error);
  });


 
    }

 }}

}


</script>


<style scoped>
 .currentimagecontainer{
    display: flex;
    background-color:  rgba(24, 23, 37, 1);
    border-radius: 10px;
    color: white;
    padding: 10px;
    
    margin-bottom: 5px;
    align-items: center;
    font-size: 20px;
    justify-content: space-between;
 }
.current{
    margin-top: 20px;
    font-size: 20px;
    margin-bottom: 20px;
    text-align: left;
}
.pricedropdowntitle{
    font-size: 20px;
}
 
.LogOut-modal-containerrv2{

    display: grid;
    place-items: center;
    width: 100%;
}
.pricedropdowntitle{
    margin-right: 20px;
}
.inputConteiner{

display: flex;
align-items: center;
align-content: center;
 
 
margin-bottom: 20px;
color: rgb(0, 0, 0);
font-size: 20px;
}

.newpasswordinput{
    width: 200px;
    height: 20px;
    background-color: rgba(24, 23, 37, 1);
    border-color: transparent;
    border-radius: 10px;
    color: white;
    font-size: 20px;
    padding: 10px;
    margin-left: 20px;

}
.pricedropwdown{
    display: flex;
    align-items: center;
     
   margin-right: 20px;
   margin-top: 20px;
    border-color: transparent;
 }
 
 .pricedropdownactual{
    border-color: transparent;
    font-size: 20px;
    background-color: rgb(255, 255, 255);
    color: rgb(0, 0, 0);
    border-color: transparent;
    padding: 10px;
    border-radius: 10px;
border-color: transparent;
}
.pricedropdownactual:hover{
    border-color: transparent;
    background-color: rgb(206, 195, 195);
    border-color: transparent;
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
.SearchConteiner{
 display: grid;
 margin-top: 20px;
 place-items: center;
 margin-bottom: 50px;

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

.LogOut-modal-content1{

    background-color: #fefefe;

display: flex;
place-items: center;

margin-top: 100px;
display: grid;
place-items: center;

min-width: 60%;

padding: 20px;
border: 1px solid #888;
border-radius: 10px;
width: 60%;  

 
z-index: 1;
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
 
} .logoutmodaltitle{
    font-size: 30px;
    margin-bottom: 60px;
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

.deletebtnapprov{

    background-color: rgb(240, 47, 47);


}
 .controlling{
    display: flex;
         padding-left: 10px;
 }

.controllingbtns{
    font-size: 20px;
    margin-right: 10px;
    padding: 5px;
    border-radius:5px;
    cursor: pointer;
    border-color: transparent;
}

.DeleteBtn{

    background-color: rgb(239, 96, 96);
    color: white;
}
.DeleteBtn:hover{
    background-color: rgb(242, 0, 0);
    color: white;
}

.Updatebtn{

    background-color: rgb(132, 96, 239);
    color: white;

}

.Updatebtn:hover {

background-color: rgb(70, 8, 255);
color: white;

}
 .mainDiv{

padding: 150px;
  

}

.parent{
    background-color:  rgba(24, 23, 37, 1);
    max-width: 100%;
    min-height: 100vh;
   height: fit-content;
 }
 .ManageOrdersTitle{
        color: white;
        font-size: 40px;
    }
    .ManageOrdersTitlebox{
        display: flex;
        align-items: center;
        margin-bottom: 50px;
    }
    .tlogo{
        color: white;
        font-size: 60px;

    }

    .listItem{
  
 
        
      
  /* display: inline-flex; */
  flex-wrap: wrap;
align-items: center;
 

  justify-content: center;
display: flex;
width: 100%;
margin: auto;
min-height: fit-content;
  padding: 0;
 

}
.listItemLi{
float: left;

list-style: none;
display:inline ;
}
.savesalesmodal{
    background-color: rgb(106, 106, 239);
}
.savesalesmodal:hover{
    background-color: rgb(53, 53, 240);


}


.makesalebtn{

    background-color: rgb(245, 240, 134);

}
.makesalebtn:hover{
    background-color: rgb(255, 242, 0);
}


/* modal */

.uploadseccont{
    margin: 10px;
    font-size: 25px;
}
.inputarray{
    display: inline-block;
    width: 900px;
    
    margin-top: 10px;
}
.imageuploadTitle{
    font-size: 25px;
}
.imageuploadConteiner{
   
    margin-top: 10px;
    margin-bottom: 10px;
    color: rgb(0, 0, 0);
}
.removeSpecItemBTN{

    border-color: transparent;
    font-size: 25px;
    background-color: transparent;
    color: rgb(243, 72, 72);
    margin-left: 5px;
    cursor: pointer;
}
.removeSpecItemBTN:hover{
    color: rgb(255, 0, 0);

}
.specificationList{
    color: rgb(0, 0, 0);
    display: flex;
    justify-content: space-between;
    font-size: 30px;
    margin-top: 10px;
    border-color: white;
    border-style: groove;
    border-radius: 10px;
    border-width: 1px;
    padding: 10px;
    margin-bottom: 10px;

}

 
.addspecmainbtn{
    margin-right: 30px;
    margin-top: 30px;
         font-size: 25px;
         padding: 10px;
         width: 100px;
         border-radius: 10px;
         border-color: transparent;
         background-color: rgb(255, 255, 255);
         color: rgb(0, 0, 0);
         transition: 500ms;
         cursor: pointer;
}
.addspecmainbtn:hover{
    background-color: rgb(177, 159, 159);
   
}
.SpecificationTitle{
    color: rgb(0, 0, 0);
    font-size: 20px;
}
.SpecificationInput{
    width: 300px;
    height: 20px;
    background-color: rgba(24, 23, 37, 1);
    border-color: white;
    border-radius: 10px;
    color: white;
    font-size: 20px;
    padding: 10px;
    margin-right: 30px;
    margin-top: 10px;
}
.SpecificationInputContainer{
   
    display: flex;
    align-items: center;
    transition: 500ms;
    margin-top: 20px;
    margin-bottom: 20px;
}
.fade {
  animation-name: fade;
  animation-duration: 1.5s;
}

@keyframes fade {
    from {opacity: .6}
  to {opacity: 1}  
  
}
.AddSpecBTN{
    font-size: 25px;
    padding: 5px;
    border-radius: 10px;
     border-color: transparent;
     border-color: transparent;
     transition: 500ms;
     color: rgb(0, 0, 0);
}
.AddSpecBTN:hover{
    background-color: rgb(0, 0, 0);
    color: rgb(255, 255, 255);
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
.pricedropdownactual:hover{
    border-color: transparent;
    background-color: rgb(206, 195, 195);
    border-color: transparent;
}
.pricedropwdown{
    display: flex;
    align-items: center;
    margin-bottom: 20px;
    
    border-color: transparent;
 }

 .pricedropdowntitle{
font-weight: bold;
    margin-right: 92px;
    color: rgb(0, 0, 0);
    font-size: 25px;

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
.description{
    width: 500px;
    height: 220px;
    min-width: 500px;
    min-height: 220px;
    max-width: 500px;
    max-height:  220px;
}
.desctitle{
    text-align: top  ;
    z-index: 3;
    position: relative;
    bottom: 96px;
}
.addnewproductForm{
    display: grid;
    place-content: center;
    margin-top: 90px;
}

.errorMessage{
    color: red;
    text-align: left;
    position: relative;
    left: 280px;
   bottom: 10px;
}


.inputConteiner{

display: flex;
align-items: center;
align-content: center;
justify-content: space-between;
width: 800px;
margin-bottom: 20px;
color: rgb(0, 0, 0);
font-size: 25px;
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
    background-color: rgba(17, 228, 151, 1);
    
    transition: 500ms;
}
.ChangePasswordBtn:hover{

 
color: white;
}
 
 
 


</style>