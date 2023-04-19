<template>

    <adminheading>
        <div class="parent">

            <center>

            
            <div    v-if=" approvedeletemodal"   class="LogOut-modal">
  <div class="LogOut-modal-content">
    <div class="LogOut-modal-container">
      
        <div class="logoutmodaltitle">
            Are you sure You want to Delete This banner ?
        </div>
        
        <div class="logoutbtnarr">

            <button class="logoutmodalBtn" @click="approvedeletemodal=false">Cancel</button>
            <button class="logoutmodalBtn deletebtnapprov" @click="deleteBannernow()" >Delete</button>
        </div>

    </div>
  </div>
</div> </center>

            <div class="ManageOrdersTitlebox">

 
    <div class="ManageOrdersTitle">Banners</div>
            </div>
            <div   class="addnewbannercontianer">
        
             
                <div class="inputarray" style="align-items: center;"> 
                <input type="file" ref="fileInput" class="uploadseccont" accept="image/gif,image/jpeg,image/png"> 
            
                <button class="uploadbtn" @click="uploadbanner()">Upload</button>

</div>

            </div>

            <div class="curentbannerContienr">
                <div class="currentbannertitle">
                    Current Banners 
                </div>

                <ul class="ulofbanner" v-if="   bannerdata.length>0">

                    <li class="bannerli" v-for="img in bannerdata" :key="img" >
                       <bannerImage   :imagelinks=img.image  @deletethisimg=deleteBanner(img.imageid) />
                    </li>
                  
            
                     </ul>
                    



            </div>



        </div>



    </adminheading>
    </template>
    
    
    <script>
    import adminheading from '../admincomponenets/AdminHeader.vue'
    import axios from 'axios'
        import bannerImage from '../admincomponenets/CurrentBanner.vue'
        export default{
        components:{
            adminheading,bannerImage
        },
        data(){
            return{
                
                currnetImageTodelete:"",
                approvedeletemodal:false  , 
                bannerdata:[],
                     

                
            }
        },

        mounted(){
            

            axios.defaults.baseURL = 'http://localhost:8080';
axios.defaults.headers.common['access-control-allow-origin'] = 'http://localhost:5173';
axios.defaults.headers.common['Content-Type'] = 'application/json';
if(localStorage.getItem('token')){

var token=localStorage.getItem('token')
            axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
this.ordermodeldata=[]

axios.get( `/admin/getbanners`,  {
  
})
.then(response => {
    this.  bannerdata=response.data
    
})
.catch(error => {
console.error(error);
});

}



        },




        methods:{

            deleteBanner(id)
            {

                this.  currnetImageTodelete=id
               this. approvedeletemodal=true

                



            },
              
            uploadbanner(){
    

const formData = new FormData();

 
const fileInputs = document.querySelectorAll('.uploadseccont');
var c=false;
 
fileInputs.forEach(fileInput => {
  const files = fileInput.files;
  for (let i = 0; i < files.length; i++) {
    formData.append('files', files[i]);
    c=true;
  }
  fileInput.value = '';
});


var token=localStorage.getItem('token')
            axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;

axios.defaults.baseURL = 'http://localhost:8080';
axios.defaults.headers.common['access-control-allow-origin'] = 'http://localhost:5173';

 if(c){ 
  
axios.post('/admin/uploadbanner', formData, {
  headers: {
    'Content-Type': 'multipart/form-data'
  }
})
  .then(response => {
    this.bannerdata.unshift(response.data)
   
     
  })
  .catch(error => {
    console.error(error);
  });

 
}else{
    alert("select Image First ! ")
}







            }
            ,








            deleteBannernow(){
 
    axios.defaults.baseURL = 'http://localhost:8080';
axios.defaults.headers.common['access-control-allow-origin'] = 'http://localhost:5173';
axios.defaults.headers.common['Content-Type'] = 'application/json';
var token=localStorage.getItem('token')
            axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
this.ordermodeldata=[]

axios.delete( `/admin/deletebanner/${this.currnetImageTodelete}`,  {
  
})
.then(response => {
    
    this.bannerdata= this.bannerdata.filter(item => item.imageid !==  this.currnetImageTodelete)
    this.approvedeletemodal=false   
})
.catch(error => {
console.error(error);
});









            },
           
         




        }
     
    
    
    }
    
    
    </script>

    <style scoped>


.uploadbtn{

    width: 100px;
    height: 30px;
   

}
    .uploadseccont{
       color:  rgb(255, 255, 255);
       width: 250px;
    }
    .addnewbannercontianer{
        display: flex;
        color: white;
        align-items: center;
        font-size: 30px;
        margin-bottom: 20px;
    }
    .nodatafound{

        font-size: 30px;
      color: rgb(247, 43, 43);
        text-align: center;

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
.logoutmodalBtn:hover{

background-color: rgb(62, 61, 78);
}
.deletebtnapprov:hover{

    background-color: rgb(255, 0, 0);

}
 
.currentbannertitle{

    color: white;
    font-size: 35px;
    margin-bottom: 20px;
}
.ulofbanner{
    background-color:  rgba(24, 23, 37, 1);
    height: 100%;
  
    display: flex;
    flex-wrap: wrap;
      align-items: center;
       
      
        justify-content: center;

}
.bannerli{
    list-style: none;
    float: left;
   
    list-style: none;
    display:inline ;
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