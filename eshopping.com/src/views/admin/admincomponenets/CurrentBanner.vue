<template>

    <div class="maincontent"  >
        <button class="deletebtn" @click="Deletebanner()">Delete</button>
        <img  :src="imagelinks" class="mainpic"  alt="image not found"   > 

            
    </div>

</template> 


<script>
export default{

    props: ['imagelinks']  ,
           
            data(){

                
                return{
                
                    
                }
            
                
            },
            methods:{

                Deletebanner(){

                    this.$emit("deletethisimg")


                }


            }



}
</script>

<style scoped>

 

.maincontent{
   
    width: 1300px;
    height: 700px;
    padding: 10px;
border-radius: 10px;
 
 

}
.mainpic{

    width: 1300px;
    height: 600px; 
    
    
}


    

.deletebtn{

    
    float: right;
    font-size: 25px;
       
    padding: 5px;
    border-radius: 10px;
    border-color: transparent;
    background-color: rgb(243, 74, 99);
    margin-bottom: 10px;
    cursor: pointer;
    color: white;
}
.deletebtn:hover{

    background-color: rgb(255, 0, 38);


}


</style>