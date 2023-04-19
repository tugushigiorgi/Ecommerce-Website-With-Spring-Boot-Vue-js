<template>
    <a :href="detailedlink">
      <div class="ProductItem">


        
        <div class="productPrice">{{ this.  pricem() }}$</div>
        <div class="productnewprice">1100₾</div>
        <div class="ProductImagesContentinerArraydiv">
          <Button @click.prevent="prevbtn()">
            <span class="material-symbols-outlined arrows">arrow_left</span>
          </Button>
  
          <img class="ProductItemImage" :src=currentImg alt="Image not found">
  
          <Button @click.prevent="nextbnt()">
            <span class="material-symbols-outlined arrows">arrow_right</span>
          </Button>
        </div>
  
        <div class="ProductItemTitle">{{ this.title }}</div>
  
        <button   v-if="this.isauth==true"   class="ProductItemaddTocartBtn"  @click.prevent="addtoCart()"  >
          <div class="ProductItemaddTocartBtnTITLE">add to cart</div>
          <span class="material-symbols-outlined">shopping_cart</span>
        </button>
     
        <button   v-if="!this.isauth"   class="ProductItemaddTocartBtn"  @click.prevent="this.$router.push('/login')"  >
          <div class="ProductItemaddTocartBtnTITLE">add to cart</div>
          <span class="material-symbols-outlined">shopping_cart</span>
        </button>
     


        <div v-if="this.adminpage">
          <div class="adminitemdiv">
            <span class="material-symbols-outlined">attach_money</span>
              <div  class="adminitemdivtxt" >Total Sold  {{ this.pruductSold }}</div>


          </div>
          <div class="adminitemdiv">
            <span class="material-symbols-outlined">folder_copy</span>
              <div class="adminitemdivtxt">Left {{ this.productQuantity }}</div>


          </div>
        </div>



      </div>
    </a>
  </template>
<script>
    import heading from './header.vue'
    import axios from 'axios';
    export default{
        props:["imgarray","price","detailedlink","title","adminpage","product_id",
          "pruductSold",
          "productQuantity","isauth"
      
      ],
        

        data(){
            return{
                

             
                saved:false,
           
          
                currentposition:0
            }
        },

 
        components:{ heading},

        


        methods:{

            pricem(){

              if(this.price.endsWith(".0")){
               return  this.price.slice(0, -2);
              } 
              return this.price


            },


               
          addtoCart(){
            
               this.$emit('addedtocart', 'Hello from child!');
            

            
        axios.defaults.baseURL = 'http://localhost:8080';
axios.defaults.headers.common['access-control-allow-origin'] = 'http://localhost:5173';
axios.defaults.headers.common['Content-Type'] = 'application/json';
var token=localStorage.getItem('token')
            axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
axios.post(`/addtocart/${this.product_id}`)

    

.then(response => {
 
  
 console.log(response)

 })
.catch(error => {
  console.error(error);
})






          },

            Removefromfavourites(){
                this.saved=false
                
            },
            Savetofavourites(){
                this.saved=true
                console.log(this.detailedlink)
            },
            prevbtn(){
                this.currentposition-=1
              

            },
            nextbnt(){
                this.   currentposition+=1
                    
                
            }
        }, 
        computed: {
    currentImg: function() {
      return   this.imgarray[Math.abs(this.currentposition) % this.imgarray.length];
    }
  }

    }
</script>
<style scoped>
button{border-color: transparent;}
.adminitemdivtxt{
  margin-left: 5px;
  font-size: 15px;
}
.adminitemdiv{
  align-items: center;
  display: flex;
}
.productPrice{
  
   text-align: right;
 
   font-size: 25px;
   font-weight: bold;
   color: rgb(0, 0, 0) ;
    
     
}
.productdiscunt{
    text-decoration:line-through

}

.productnewprice{
    display: none;
    text-align: right;
 
 font-size: 25px;
 font-weight: bold;
 color: rgb(53, 14, 225) ;
  

}
.arrows{
    font-size: 45px;
    background-color: white;
    border-color: transparent;
    cursor: pointer;
}
.arrows:hover{
    color: brown;
}
.ProductImagesContentinerArraydiv{
    display: flex;
    align-items: center;
    width: 100%;
    position: relative;
    right: 10px;
}
.ProductItemaddTocartBtnTITLE{
    font-size: 16px;
    margin-right: 5px;
}

.ProductItemaddTocartBtn{
    display: flex;
    position: relative;
    float: right;
   top: 10px;
 background-color: rgba(38, 41, 46, 1);
 color: white;
 border-radius: 10px;
 padding: 10px;
 
}
.ProductItemaddTocartBtn:hover{
    background-color: rgb(84, 88, 96);

}


.saved{
  
  font-variation-settings:
  'FILL' 1,
  'wght' 400,
  'GRAD' 0,
  'opsz' 48
 
 
}
.ProductSaveBtn{
    border-color: transparent;
    position: relative;
    float: right;
}
.ProductItem{
    width: 320px;
    height: 390px;
    display: inline-block ;
    align-items: center;
    justify-content: center;
     padding: 15px;
    margin: 10px;
    border-radius: 10px;
    box-shadow: rgba(0, 0, 0, 0.4) 0px 2px 4px, rgba(0, 0, 0, 0.3) 0px 7px 13px -3px, rgba(0, 0, 0, 0.2) 0px -3px 0px inset;
    background-color: white;
   color: black;
   
}
.ProductItem:hover{

    box-shadow: rgba(0, 0, 0, 0.3) 0px 19px 38px, rgba(0, 0, 0, 0.22) 0px 15px 12px;
}
.ProductItemImage{
    width: 240px;
    height: 250px;
    min-width: 240px;
    min-height: 250px;

}
.ProductItemTitle{
    text-align: center;
    font-size: 20px;
     height: 50px;
     overflow: hidden;
}


</style>

