<template>
    <router-link to="/profile">

    

    <div class="mainConteiner">
         
        <img class="imgItem" :src=img> 
        <div>
            <div class="title">{{ title}}  </div>
        <div class="price">   {{price}}</div>

        </div>
        
    </div>
</router-link>

</template>

<script>

export default{
    props:["title", "price", "img"]


}

</script>
<style scoped>
a{
  all: unset;
}
.title{
    font-size: 25px;
}
.price{
    font-size: 20px;
    margin-top: 5px;
}
.mainConteiner{
    background-color: white;
    display: flex;
    margin-bottom: 10px;
    border-radius: 10px;
    cursor: pointer;


}
.mainConteiner:hover{
    box-shadow: rgba(50, 50, 93, 0.25) 0px 13px 27px -5px, rgba(0, 0, 0, 0.3) 0px 8px 16px -8px;

 }
.imgItem{
    width: 100px;
    height: 100px;
}
</style>