<template>

    <adminheading>
        <div class="parent">
            <div class="mainDiv">

          
                <div class="ManageOrdersTitlebox">

<span class="material-symbols-outlined tlogo">
    percent
</span>
    <div class="ManageOrdersTitle">Manage Sales</div>
            </div>

                <div class="addNewSalesGroup">

                        <button class="CreateSalesBtn" @click="CreateSalesGroupModal=true"> + Create New Sales Group</button>
                </div>

                <div class="CurrentSales">
                    <div class="CurrentSalestitle">Current Sales group</div>



                        <div class="groupItem">
                                <div class="groupitemHEading">
                                    <div class="groupitemTitle">Black friday group</div>
                                    <button class="groupheadingbtns groupupdatebtn " @click="UpdateGroup=true">Update Date </button>
                                    <button class="groupheadingbtns groupdeletebtn" @click="DeleteGroupModal=true">Delete </button>
                                </div>
                         
                                <div class="groupitemitems">
                                    <ul class="listItem">
            
            
            <li class="listItemLi">
                <div class="controlling">
                    <button class=" controllingbtns DeleteBtn"  @click="DeleteProdcutModal=true" >  Delete From Group</button>
                 

                </div>
                <productitem :imgarray="data"  />
            </li>
      

          
          
          
          

    </ul>



                                </div>


                            
                        </div>





                </div>






                <center>

<!-- Modal for Delete   Item from group    -->
<div    v-if="DeleteProdcutModal"   class="LogOut-modal">
<div class="LogOut-modal-content">
<div class="LogOut-modal-container">

<div class="logoutmodaltitle">
Are you sure You want to Remove This Product From friday group ?
</div>

<div class="logoutbtnarr">

<button class="logoutmodalBtn" @click="DeleteProdcutModal=false">Cancel</button>
<button class="logoutmodalBtn deletebtnapprov"  click="RemoveItemfromGroup()">Remove</button>
</div>

</div>
</div>
</div> </center>

<center>

 
<div    v-if="DeleteGroupModal"   class="LogOut-modal">
<div class="LogOut-modal-content">
<div class="LogOut-modal-container">

<div class="logoutmodaltitle">
Are you sure You want to Remove     friday group ?
</div>

<div class="logoutbtnarr">

<button class="logoutmodalBtn" @click="DeleteGroupModal=false">Cancel</button>
<button class="logoutmodalBtn deletebtnapprov"  click="RemoveItemfromGroup()">Remove</button>
</div>

</div>
</div>
</div> </center>



 
<center> <div    v-if="    CreateSalesGroupModal"   class="LogOut-modal">
<div class="LogOut-modal-content1">
<div class="LogOut-modal-container">

<div class="logoutmodaltitle">
Create  new Group

</div>
<div class="inputConteiner">
                    <div>Enter Group name</div>
                    <input  v-model="productname" type="text" class="newpasswordinput" > 
                </div>

    <div class="Dates">
        
        <div class="DateConteiner">
                <div class="titlee">Sale start date</div>
            <input class="DateInput" type="date"> 
        </div>
        <div class="DateConteiner">
                <div class="titlee">End Date</div>
            <input  class="DateInput" type="date"> 
        </div>
       

    </div>               



<div class="logoutbtnarr">

<button class="logoutmodalBtn" @click="CreateSalesGroupModal=false">Cancel</button>
<button class="logoutmodalBtn deletebtnapprov"  click="RemoveItemfromGroup()">Create</button>
</div>

</div>
</div>
</div> </center>


<!-- Modal for  Updating group -->
<center> <div    v-if="UpdateGroup"   class="LogOut-modal">
<div class="LogOut-modal-content1">
<div class="LogOut-modal-container">

<div class="logoutmodaltitle">
Create  new Group

</div>
<div class="inputConteiner">
                    <div>Update  Group name</div>
                    <input  v-model="productname" type="text" class="newpasswordinput" > 
                </div>

    <div class="Dates">
        
        <div class="DateConteiner">
                <div class="titlee">Sale start date</div>
            <input class="DateInput" type="date"> 
        </div>
        <div class="DateConteiner">
                <div class="titlee">End Date</div>
            <input  class="DateInput" type="date"> 
        </div>
       

    </div>               



<div class="logoutbtnarr">

<button class="logoutmodalBtn" @click="UpdateGroup=false">Cancel</button>
<button class="logoutmodalBtn deletebtnapprov"  click="RemoveItemfromGroup()">Create</button>
</div>

</div>
</div>
</div> </center>




  </div>
</div>  

    </adminheading>
    </template>
    
    
    <script>
    import adminheading from '../admincomponenets/AdminHeader.vue'
    import productitem from  '../../../components/ProductItem.vue'
    
    export default{
        components:{
            adminheading 
        },
        data(){
            return{
                UpdateGroup:false,
                CreateSalesGroupModal:false,
                DeleteGroupModal:false,
                DeleteProdcutModal:false,
                data:[
                    "https://www.phoneworld.com.pk/price/wp-content/uploads/2021/08/7-1.jpg",
                    "https://media.pakprice.pk/89/YbIHVwc4pbFwbNHB95beaodzY4flCaHacnKvaxsK.jpeg",
                        "https://mobilebazaar.pk/wp-content/uploads/2022/05/iPhone-7-Price-in-Pakistan.png",
                    "https://img.zoommer.ge/zoommer-images/thumbs/0174438_apple-iphone-14-pro-max-128gb-space-black_550.jpeg"
            ], 
            }
        }
    
    
    }
    
    
    </script>


<style scoped>
.DateInput{
    width: 200px;
    height: 20px;
   border-color: rgba(24, 23, 37, 1);
    border-color: transparent;
    border-radius: 10px;
    color: rgb(0, 0, 0);
    font-size: 20px;
    margin-top: 10px;
    padding: 10px;
}
.titlee{

    font-size: 25px;
}
.DateConteiner{
   
    margin-top: 20px;
    margin-bottom: 20px;
    display: grid;
    place-items: center;
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
 .inputConteiner{

display: flex;
align-items: center;
align-content: center;
 
 
margin-bottom: 20px;
color: rgb(0, 0, 0);
font-size: 20px;
}
.logoutmodaltitle{
    font-size: 30px;
    margin-bottom: 60px;
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

.LogOut-modal-content1{
    background-color: #fefefe;

    display: flex;
    place-items: center;
 
 margin-top: 200px;
    display: grid;
    place-items: center;
    
    
 
  padding: 20px;
  border: 1px solid #888;
  border-radius: 10px;
  width: 30%;  
 
 
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
 .listItem{
  
 
        
      
  /* display: inline-flex; */
  flex-wrap: wrap;
 
display: flex;
width: 106%;
margin: auto;
border-style:groove;
border-width: 3px;
margin-top: 10px;
  padding: 10px;
 border-radius: 10px;

}
.listItemLi{
float: left;

list-style: none;
display:inline ;
}
.groupheadingbtns{
    font-size: 20px;
    padding: 10px;
    border-radius: 10px;
    margin-right: 10px;
    cursor: pointer;
}
.groupitemTitle{
    color: white;
    font-size: 30px;
    margin-right: 30px;

}
.groupItem{
    margin-top: 30px;
    margin-bottom: 30px;
}
.groupitemHEading{
    display: flex;
    align-items: center;

}
.groupupdatebtn {

    background-color: rgb(132, 132, 240);
    color: white;
    transition: 500ms;
}
.groupdeletebtn{
    background-color: rgb(251, 80, 80);
    color: white;
    transition: 500ms;

}
.groupdeletebtn:hover{
    background-color: rgb(255, 0, 0);
    color: white;
}
.groupupdatebtn:hover{
    background-color: rgb(0, 0, 244);

}


.CurrentSales{
    margin-top: 20px;

}
.CurrentSalestitle{
    font-size: 35px;
    color: white;
}
.CreateSalesBtn{
    padding: 10px;
    font-size: 30px;
    border-radius: 10px;
    cursor: pointer;

}
.CreateSalesBtn:hover{
    background-color: rgb(181, 181, 182);
}
.ManageOrdersTitle{
        color: white;
        font-size: 60px;
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
    .deletebtnapprov{

background-color: rgb(240, 47, 47);


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

 .mainDiv{

padding: 150px;


}
.parent{
    background-color:  rgba(24, 23, 37, 1);
    max-width: 100%;
   height: 200vh;
 }







</style>