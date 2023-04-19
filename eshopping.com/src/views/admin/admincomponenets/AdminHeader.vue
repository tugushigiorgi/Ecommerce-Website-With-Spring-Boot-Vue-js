<template  >

   
    <nav class="headerNav">
        <div class="HeaderLogo">Eshopping.com Administrator   </div>
        <ul>        
    
    
            <li>
                   
                <router-link    tag="li" :to="{name:'home'}">Home</router-link>
             
           
        </li>
        <li  class="tooltip">  
           
        
    
       </li>


 
         
    
           
    
    
            <li >  
           
           <div class="Carttext" @click="LogOutModalVisibilitiy=true">  Log out </div>
           <span class="material-symbols-outlined">
           logout
           </span>
         
      
    
       
       
        </li>
        </ul>
    
    
    </nav>
    
    <nav class='sider'>
    <ul class="sider_items_ul">
           <li class="item" >
           
                <router-link :to="{name:'admin'}"  tag="a" class="sider_item_a"  >
                    <div class="sider_item_a_div"> 
                    <span class="material-symbols-outlined sider_image "    > shopping_bag</span>
 
                     <div class='sider_text'>Manage Orders</div>
                    </div>
                </router-link>
               
           </li> 
           <li class="item">                                            
                <router-link  :to="{name:'manageorders'}"  tag="a" class="sider_item_a"   >
                    <div class="sider_item_a_div"> 
                    <span class="material-symbols-outlined sider_image"     > manage_search </span>
                

                     <div class='sider_text'>Manage Products</div>
                    </div>
                </router-link>
               
           </li> 

           <!-- <li class="item">
                <router-link  tag="a" :to="{name:'ManageSales'}" class="sider_item_a"   >
                    <div class="sider_item_a_div"> 
                    <span class="material-symbols-outlined sider_image"   > percent  </span>
                

                     <div class='sider_text'> Manage Sales</div>
                    </div>
                </router-link>
               
           </li>  -->
       
           <li class="item">
                <router-link  :to="{name:'addnewproduct'}" class="sider_item_a"   >
                    <div class="sider_item_a_div"> 
                    <span class="material-symbols-outlined sider_image"   >add_circle </span>
                

                     <div class='sider_text'>Add new Product</div>
                    </div>
                </router-link>
               
           </li> 

  
           <li class="item">
                <router-link  tag="a" :to="{name:'manageusers'}" class="sider_item_a"  >
                    <div class="sider_item_a_div"> 
                    <span class="material-symbols-outlined sider_image"    > group </span>
                
 
                     <div class='sider_text'>Manage Users </div>
                    </div>
                </router-link>
               
           </li> 

           <li class="item">
                <router-link  tag="a" :to="{name:'revanuereport'}" class="sider_item_a"   >
                    <div class="sider_item_a_div"> 
                    <span class="material-symbols-outlined sider_image"     > monitoring </span>
                
 
                     <div class='sider_text'>Revanue Report</div>
                    </div>
                </router-link>
               
           </li> 






           <li class="item">
                <router-link  tag="a" :to="{name:'bannerpage'}" class="sider_item_a"   >
                    <div class="sider_item_a_div"> 
                    <span class="material-symbols-outlined sider_image"     > panorama </span>
                
 
                     <div class='sider_text'>Banner Images </div>
                    </div>
                </router-link>
               
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
                <button class="logoutmodalBtn" @click="$router.push('/login')" >Log out</button>
            </div>
    
        </div>
      </div>
    </div>
    
    </center>
     <div  >

                <slot></slot>
            </div>
              
    
   
    
    
           
            
    
    </template>
    
    <script>
 


        import axios from 'axios'


        export default{
            props:[
                'actived'
            ],
    
            data(){
                return{     
                 LogOutModalVisibilitiy:false,  
                  ManageOrderPage:false,  
                 ManageProducts:false,
                 ManageSales:false,
                 AddnewProduct:false,
                 ManageUsers:false,
                 RevanueReport:false,
                 BannerImage:false



    
               }
    
            },
            methods:{
               
                
            },
            mounted(){
                if(localStorage.getItem('token')){
                var token=localStorage.getItem('token')
            axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
            axios.defaults.headers.common['access-control-allow-origin'] = 'http://localhost:5173';
            axios.defaults.headers.common['Content-Type'] = 'application/json';
axios.defaults.baseURL = 'http://localhost:8080';
axios.get('/product/checkuserrole',  {
 })
.then(response => {

    if(response.data!="SUCCESS"){
        this.$router.push("/")
    }


})
.catch(error => {
console.error(error);
});


                }

} 
 
            }
    
     

   
    
    </script>
    
    <style scoped>
    input[type='number']::-webkit-inner-spin-button, 
    input[type='number']::-webkit-outer-spin-button { 
        opacity: 1;
    }
    
     
    .main_content{
       
        background-color:  rgba(24, 23, 37, 1);
     
        min-width: 100%;
        min-height: 100%;
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
     
    
    
    
     
    
    
    .HeaderLogo{
        margin-left: 30px;
        font-size: 30px;
    }
    .Carttext{
    
        margin-right: 3px;
    }
    
   
    .perent{
       
        height: 100%;
        
        background-color: blue;
    }
   
     .headerNav{
        
        width: 100%;
        height: 80px;
        background-color:  rgba(17, 228, 151, 1);
        display: flex;
        justify-content: space-between;
        align-items: center;
        color: rgb(0, 0, 0);
        z-index: 4;
        position: fixed;
      
     }
    
     .headerNav  li{
        list-style: none;
        display: flex;
        align-items: center;
        font-size: 25px;
        margin-right: 20px;
     
    transition: 500ms;
    padding: 10px;
     }
     .headerNav li:hover{
        background-color: white;
        color: black;
       
        cursor: pointer;
        
    
    }
    
     
    a{
      all: unset;
    }
    .router-link-active{
       
        
   
     padding: 5px;
      cursor: pointer;
    }

    .router-link-exact-active{
        background-color: rgb(255, 255, 255);
      color: rgb(0, 0, 0);


    }
    .headerNav    ul{
        display: flex;
        align-items: center;
         
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
    .sider_image{

font-size: 36px;

color:  rgb(0, 0, 0);
margin-left: 12px;

 

}

   


.sider_items_ul{
   display: flex;
   margin: 0;
   padding: 0;
   list-style: none;
   flex-direction: column;
 
  

}
.actived{

    background-color: rgba(24, 23, 37, 1);
    
    
}
 
.sider_item_a{
   
   text-decoration: none;
   height: 70px;
 display: flex;
 align-items: center;
 
   color: rgb(0, 0, 0);
    
   
}
.sider_item_a:hover  {
      transition: 200ms;
     background-color:   rgb(255, 255, 255);
     
}

.sider_item_a_div{
      
      display: flex ;
    align-items: center;
    
    align-content: center;  
 }
 .sider_text{
 display: none;
 margin-left: 20px;
 font-size: 20px;
  
 align-items: center;
 min-width: 200px;
 
 }
 .sider:hover  {
    width: 256px;
      
 }        
     
      
 .sider:hover .sider_text  {
  display: inline;
      
 }      
    
    .sider{
   z-index: 1;

    
   position: fixed;
margin-top: 80px;
  height: 100%;
  width: 60px;
  background-color: rgba(17, 228, 151, 1);

  padding-top: 30px;
transition: 200ms;

}

.sider_items_ul li{
    cursor: pointer;
    
}
    
    </style>