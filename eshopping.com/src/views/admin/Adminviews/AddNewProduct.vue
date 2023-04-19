<template>

    <adminheading>
    <div class="parent">
        <div class="ManageOrdersTitlebox">

<span class="material-symbols-outlined tlogo">
 add_circle
</span>
    <div class="ManageOrdersTitle">Add new Product</div>
            </div>




    
   
    <form class="addnewproductForm"   @submit.prevent="handleSubmit"> 
                <div class="headingonform">Enter Menualy All Specs  or   Import  it   from Gsmarena  </div>
 
                
                <div class="inputConteiner">
                    <div>Enter Product Name</div>
                    <input  v-model="productname" type="text" class="newpasswordinput" > 
                </div>
              
                <div class="inputConteiner">
                    <div>Enter Product  Price</div>
                    <input  v-model="productprice"    class="newpasswordinput"  > 
                </div>
               
                <div class="inputConteiner">
                    <div>Enter Product Quantity</div>
                    <input  v-model="productquantity" type="number"  class="newpasswordinput"  > 
                </div>
                
                <div class="  pricedropwdown">
        <div class="pricedropdowntitle">Choose Category</div>
        <select  class="pricedropdownactual" v-model="category">
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
                <div class="inputConteiner">
                    <div>Enter Phone link From Gsmarena </div>
                    <input  v-model="gsmarenainput" type="text" class="newpasswordinput" placeholder="e.g https://www.gsmarena.com/apple_iphone_14_plus-11862.php" > 
                    <button @click=getdatafromweb() @click.prevent  class="getphonedatabtn">Import</button>
              
                </div>
                <div class="specificationConteiner">
                    <button  type="button" @click.prevent   @click="AddSpecificationConteiner=! AddSpecificationConteiner"    class="AddSpecBTN">+ Add Specification</button>

                    <div v-if=" AddSpecificationConteiner" class="SpecificationInputContainer fade ">
                     
                        <div>
                            <div class="SpecificationTitle" >Specification Name</div>
                            <input v-model="specKey" type="text" class="SpecificationInput">
                        </div>
                        <div>
                            <div class="SpecificationTitle">Specification Value</div>
                            <input  v-model="specvalue"  type="text"  class="SpecificationInput">
                        </div>
                        

                            <button  type="button" @click.prevent @click="SaveSpec()" class="addspecmainbtn"  >Add</button>
                         

                    </div>  
                    
                        <div class="specificationList" v-for="  key,value    in  SpecificationData ">
                                     
                            <div style="font-size: 20px"> {{  value }}  </div>
                            <div style="display:flex ; align-items:center; font-size: 20px ; margin-left:210px;">{{ key }} <button @click.prevent @click="removeSpecification( value)" class="removeSpecItemBTN">X</button> </div>
                        </div>

                </div>

               <div class="imageuploadConteiner">
                    <div class="imageuploadTitle">Upload Images </div>


                    <div class="inputarray">
    <input type="file" ref="fileInput" class="uploadseccont" accept="image/gif,image/jpeg,image/png"> 
    <input type="file" ref="fileInput" class="uploadseccont" accept="image/gif,image/jpeg,image/png"> 
    <input type="file" ref="fileInput" class="uploadseccont" accept="image/gif,image/jpeg,image/png"> 
    <input type="file" ref="fileInput" class="uploadseccont" accept="image/gif,image/jpeg,image/png"> 
    <input type="file" ref="fileInput" class="uploadseccont" accept="image/gif,image/jpeg,image/png"> 
    <input type="file" ref="fileInput" class="uploadseccont" accept="image/gif,image/jpeg,image/png"> 
</div>

 


 



               </div>     



                <center>
                    <button type="submit" class="ChangePasswordBtn"  >Upload </button>
 
                       

                </center>
             </form>


 

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
        data(){
   
return{
    gmsarenainput:"",
            AddSpecificationConteiner:false,
            specKey:"",
            specvalue:"",
            SpecificationData:{},
            gsmarenainput:"",
            category:"Phone",
            productname:"",
            productprice:"",
            productquantity:"",
            productdescription:"",



          

}

        },


        methods:{



     addalldata(images){


      


              
axios.defaults.baseURL = 'http://localhost:8080';
axios.defaults.headers.common['access-control-allow-origin'] = 'http://localhost:5173';
axios.defaults.headers.common['Content-Type'] = 'application/json';

var token=localStorage.getItem('token')
            axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;

axios.post('/admin/saveproduct',  {

ProductName: this.productname,
ProductPrice:   String(this.productprice) ,
ProductSalePrice:"",
ProductQuantity:String( this.productquantity) ,
Category:this.category,
Description: this.productdescription,
Specs:    this. SpecificationData,
images:images


})
.then(response => {

this.$router.push('/' );
})
.catch(error => {
console.error(error);
});

}
     ,



            getdatafromweb(){
                
                axios.defaults.baseURL = 'http://localhost:8080';
axios.defaults.headers.common['access-control-allow-origin'] = 'http://localhost:5173';
axios.defaults.headers.common['Content-Type'] = 'application/json';
var token=localStorage.getItem('token')
            axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
                axios.post('/admin/getphonedata',  {
  
                    LINK: this. gsmarenainput ,
 


})
.then(response => {
   
this.SpecificationData=response.data


})
.catch(error => {
  console.error(error);
});

            }
,


            SaveSpec(){
                   
                    this. SpecificationData[this.specKey]=this.specvalue
               
                    this.AddSpecificationConteiner=false

                    this.specKey="",
                    this.specvalue=""
            },
            removeSpecification(key){
                delete  this.SpecificationData[key]
             

            },
            handleSubmit(){
              


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

 if(c){


  
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

 
 
}else{
    alert("add at least 1 picture !")
}


}
}







}
    </script>


<style scoped>

.getphonedatabtn
{
    cursor: pointer;
        margin: 15px;
    border-radius: 10px;
    padding: 10px;
    font-size: 20px;
    border-color: transparent;

    transition: 500ms;
}

.getphonedatabtn:hover{
color: white;
background-color: rgb(84, 81, 81);

}

.headingonform{
    color: white;
    margin-bottom: 30px;
    font-size: 30px;
}
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
    color: white;
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
    color: white;
    display: flex;
    justify-content: space-between;
    font-size: 30px;
    margin-top: 10px;
    border-color: white;
    border-style: groove;
    border-radius: 10px;
    border-width: 1px;
    max-width: 860px;
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
    color: white;
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
}
.AddSpecBTN:hover{
    background-color: rgb(79, 71, 71);
    color: white;
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
    color: white;
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
color: white;
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
</style>