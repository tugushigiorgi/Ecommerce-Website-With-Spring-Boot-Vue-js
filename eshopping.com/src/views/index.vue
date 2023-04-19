<template >

<heading  v-slot="scope">

    <slot>

                <div class="SearchConteiner">

                    <div><input v-model="SearchBoxTextInput" class="SearchInput" type="text"   placeholder="Search Something..">
                        <button  @click="searchbtn()"  class="MainSearchInputBTN"><span class="material-symbols-outlined mainsearhbtn">
search
</span></button>

                  
 
                          
                  

                    </div>
                    <div class="CategoryesBtns">

                        <button class="categoryBtn" @click="Computerbtn()">Computers</button>
                        <button class="categoryBtn"   @click="mobilebtn()" >Mobile Phones</button>
                        <button class="categoryBtn" @click="homebtn()" >home appliances</button>
                        <button class="categoryBtn" @click="tvbtn()">Tv & Audio</button>
                         
                    </div>

                   
                </div>   
                <div> 
                <div class="sliderConteiner  "> 

                <bannerSlider :BannerImagesArray=" bannerdata"/>
                  </div>



 
         <div class="cont"> 
 

  
        <div class="titlebox">


<div class="titlee">Popular Products</div>
<span class="material-symbols-outlined  lt">
chart_data
</span> 
                    </div>

                 
 
                  
            <ul class="listItem">
            
                <li class="listItemLi" v-for="item in getdata " :key="item.Price" >
              
                    <productitem  :show=true :imgarray=item.Images :title=item.ProductName :price=item.Price :detailedlink=item.DetailedLink
                    
                    :isauth=this.isauthenticated
                    
                    :product_id=item.id
                    
                    @addedtocart="scope.updateData "    
                    />
                </li>
              
          
             
              
              
              

        </ul>
     
 

     
  
 
                    
 
     
    </div>
 
</div>


    </slot>

</heading>

</template>

<script>
   import axios from 'axios';
    import heading from '../components/header.vue'
    import bannerSlider from '../components/BannerSlider.vue'
    import productitem from '../components/ProductItem.vue'
    import QuickSearchItem from '../components/QuicksearchItem.vue'
    export default{

        components:{ heading,productitem,bannerSlider,QuickSearchItem},
        data(){
            return{
               
                isauthenticated:true,
                searched:[],
                SearchBoxTextInput:"",


                bannerdata:[
      ],


               
                getdata:[],

 
            }
        },
        mounted(){


         
       
                if(  localStorage.getItem('token')){
                        this.isauthenticated=true
                        var token=localStorage.getItem('token')
            axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;

                }else{
                    this.isauthenticated=false
                }



            axios.defaults.baseURL = 'http://localhost:8080';
axios.defaults.headers.common['access-control-allow-origin'] = 'http://localhost:5173';
axios.defaults.headers.common['Content-Type'] = 'application/json';
 
axios.get('/product/getpopularproducts', {
 
})
.then(response => {
  
 
this.getdata=(response.data)
})
.catch(error => {
  console.error(error);
})
 
 
          

axios.get('/product/banners', {
 
})
.then(response => {
 
    this. bannerdata=response.data
    
})
.catch(error => {
  console.error(error);
})

 


 









},
        
        methods:{
            updateData(){

            },
            searchbtn(){

                this.$router.push(`search/${this.SearchBoxTextInput}` )


                
            },
        
            Computerbtn(){

                this.$router.push('search/Computers' )
            },
                    mobilebtn( ){

                        this.$router.push('search/Phone' )
},
 homebtn(){
    this.$router.push('search/Home appliances' )
          },          
                   
                        
                    
                   
                    tvbtn(){
                        this.$router.push('search/Tv & Audio' )
                    },
        }
        }
  
    
</script>
<style scoped> 






.quicksearchBtn{
    text-align: right;
    cursor: pointer;
}
.quicksearch{
    padding: 10px;
     width: 720px;
     border-radius: 10px;
    height: auto;
    background-color: rgba(251, 251, 251, 0.769);
    position: absolute;
    z-index: 1;
  
}
.cont{
    display: grid;
    place-items: center;
    

}
.tlogo{
    font-size: 45px;
    color:  rgb(252, 175, 42);
}
.lt{
    font-size: 45px;
    color:  rgb(252, 42, 42);
}
 
.titlebox{
    margin-top: 10px;
display: flex;
  
    

}
 
 

 .titlee{
  font-size: 40px;
    text-align: center;

 }
.sliderConteiner{
    width: 100%;
    display: grid;
    place-items: center;

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
</style>
