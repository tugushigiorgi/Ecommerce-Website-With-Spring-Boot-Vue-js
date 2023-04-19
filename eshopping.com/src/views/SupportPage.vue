<template>
<heading>
    <!-- <div class="main_title_box">

                 
<span class="material-symbols-outlined tlogo">
support_agent
</span>
<div class="MainTitle">Support</div>
</div>
    <div class="main_content">

         

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


    </div> -->

</heading>

</template>

<script>

import heading from '../components/header.vue'
import sendermessage from '../components/SenderMessage.vue'
import supportmessage from '../components/operatorMessage.vue'
export default{
 components:{
    heading,sendermessage,supportmessage

 }

}
</script>

<style scoped>

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
    display: grid;
    place-items: center;
    padding: 20px;
}
.tlogo{

    font-size: 50px;

}
.MainTitle{

    font-size: 50px;

}
.main_title_box{
    display: flex;
        margin-left: 200px;
        margin-top: 20px;
        margin-bottom: 0px;
        align-items: center;
      
        
}
</style>
