<template>
   <adminheading> 


    <div class="parent">  
            <div class="mainDiv">


        <div class="main_title_box">
    
                     
    <span class="material-symbols-outlined tlogo">
    support_agent
    </span>
    <div class="MainTitle">Support</div>
    </div>


        <div class="main_content">
    
             
            <div class="MessagedUserssider">
                <div class="MessagedUserssidertitle">Users</div>

                <ul class="sideruserul">
                    <li class="sideruserli"> <usersideritem /></li>
                </ul>
                    
                 

 </div>

           
            <div class="MassagingConteiner">
    
                    <div class="messagingcent">
    
    
    
                            <div class="sender">
                                <sendermessage></sendermessage>
                                 
                            </div>
                                
                            <div class="reciver">
                                <supportmessage></supportmessage>
                            
                            </div>
                            <div class="sender">
                                <sendermessage></sendermessage>
                                 
                            </div>
                                
                            <div class="reciver">
                                <supportmessage></supportmessage>
                            
                            </div>
                    </div>
                    <div class="messagingfooter">
    
                        <input type="text" class="writeMessageInput"> 
                        <button class="SendMessageBtn">Send</button>
    
                    </div>
    
    
            </div>
            
    
        </div>
    </div></div>
   
    </adminheading>
    </template>
    
    <script>
    
    import adminheading from '../admincomponenets/AdminHeader.vue'


    import sendermessage from '../../../components/SenderMessage.vue'
    import supportmessage from '../../../components/operatorMessage.vue'
    import usersideritem from '../admincomponenets/UserListSider.vue'
    export default{
     components:{
        adminheading,sendermessage,supportmessage,usersideritem 
    
     }
    
    }
    </script>
    
    <style scoped>
    .MessagedUserssidertitle{
        color: white;
        font-size: 30px;
    }
    .sideruserli{
        list-style: none;
    }
    .sideruserul{
        border-style: groove;
       height: 88%;
       padding: 10px;
       border-radius: 10px;
    }
     .mainDiv{

padding: 150px;


}
.parent{
    background-color:  rgba(24, 23, 37, 1);
    max-width: 100%;
   height: 200vh;
 }

    .sender{
        float: right;
        margin-bottom: 10px;
       
    }
    .reciver{
        float: left;
        margin-bottom: 10px;
        
    }
    .breaker{
        width: 100%;
        height: 90px;
    }
    .MassagingConteiner{
        width: 80%;
        height: 750px;
        background-color: rgba(24, 23, 37, 1);
        border-radius: 10px;
        padding: 10px;
    }
    .SendMessageBtn{
        width: 150px;
        background-color: rgb(255, 255, 255);
        color: rgb(0, 0, 0);
        border-radius: 10px;
        font-size: 20px;
        border-color: transparent;
        margin-left: 5px;
        transition: 500ms;
    }
    .SendMessageBtn:hover{
        background-color:  rgba(24, 23, 37, 1);
        color: rgb(255, 255, 255);
    }
    .messagingfooter{
        display: flex;
    }
    .messagingcent{
        width: 100%;
        height: 620px;
        padding-bottom: 20px;
    }
    .writeMessageInput{
        padding: 5px;
        width: 100%;
        height: 50px;
        font-size: 20px;
       border-radius: 10px;
    
    }
    .main_content{
        display: flex;
       
        padding: 20px;
    }
    .tlogo{
        color: white;
        font-size: 50px;
    
    }
    .MainTitle{
        color: white;
        font-size: 50px;

    
    }
    .main_title_box{
        display: flex;
            margin-left: 300px;
            margin-top: 20px;
            margin-bottom: 0px;
            align-items: center;
          
            
    }
    </style>
    