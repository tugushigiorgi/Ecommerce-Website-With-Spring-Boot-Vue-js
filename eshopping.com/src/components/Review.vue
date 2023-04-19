<template>

<div class="writtenreviewItem">

<div class="ReviewAuthorInfo">

    <div  >

        <img class="userPohot" :src="authorImg" alt="">
        </div>
        <div> 

             
                     
               
                <div  class="cont">
                    <div class="authortitle">{{author}} </div>

                       <button @click="deleteReview()" v-if="isauthor" class="DeleteReviewbtn">Delete</button>
                      
                </div>
                
              <div class="actualreview">   {{  reviewtext}} </div>

            
          
              
    
            
           
            
        
</div>

            

</div>


</div>
</template>
<script>
export default{

    props:['author','authorImg','reviewtext','isauthor'],
    data(){


        return {



        } },
        methods:{
            deleteReview(){
                this.$emit("deleterev")
            }

        }
   





}

</script>




<style scoped>
 
 .cont {
  
    display: flex;
    align-items: center;
    align-content: center;
}


.DeleteReviewbtn{
 
    width:100px;
   


    font-size: 15px;
   margin-left: 10px;
    width:90px;
    border-color: transparent;
    border-color: transparent;
    background-color: rgb(215, 73, 73);
    color: white;
    border-radius: 10px;
    cursor: pointer;
    margin-bottom: 10px;
   
}
.DeleteReviewbtn:hover{
    background-color: rgb(232, 8, 8);

}

.actualreview{
    font-size: 20px;
}
.authortitle{
    font-size: 25px;
    margin-bottom: 10px;
   
   
}
.userPohot{
    width: 100px;
    height: 100px;
    padding: 10px;
    margin-right: 10px;
}
.writtenreviewItem{
    width: 100%;
    border-radius: 10px;
    box-shadow: rgba(0, 0, 0, 0.07) 0px 1px 1px, rgba(0, 0, 0, 0.07) 0px 2px 2px, rgba(0, 0, 0, 0.07) 0px 4px 4px, rgba(0, 0, 0, 0.07) 0px 8px 8px, rgba(0, 0, 0, 0.07) 0px 16px 16px;
    margin-top: 20px;
    margin-bottom: 20px;
    height: auto;
    cursor: pointer;
}
.ReviewAuthorInfo{
    display: flex;
}

</style>