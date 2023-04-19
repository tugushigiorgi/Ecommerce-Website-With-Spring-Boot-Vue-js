 

<template>
 
 <!-- <index/> -->
 <!-- <userpage/> -->
<!-- <productdetailed/> -->

<router-view/>
</template>
<script>
 
   import index from './views/index.vue'
   import userpage from './views/UserPage.vue'
   import productdetailed from './views/ProductDetailed.vue'
   

  export default{
   components:{index, userpage,productdetailed},
   
 
  }
 

</script>

<style scoped>
 
</style>
