<template>
 <div class="main_conteiner  ">


    
        <img  class="BannerImageItem" :src="currentImg"> 
    

          
         
   


 </div>

</template>


<script>
export default{
    props:["BannerImagesArray"],

    data(){
        return{
            currentposition:0

        }

    },

    mounted(){
        setInterval(()=>{this.currentposition+=1},3000)
    },
    computed:{
        currentImg: function() {
      return this.BannerImagesArray[Math.abs(this.currentposition) % this.BannerImagesArray.length];
    }
    }

}


</script>


<style scoped>
.main_conteiner{
    width: 100%;
    height: 500px;
    background-color: black;
    margin-bottom: 20px;
}
.BannerImageItem{

    width: 100%;
    height: 100%;
}   

 
</style>