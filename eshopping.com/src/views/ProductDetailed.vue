<template>




<heading  v-slot="scope">

    <div class="main_content">

        <div class="DetailedHeading">
            <div class="content">  
            <div class="ImageSliderConteiner">

                <div  class="ImageSlider">
                    <button @click="prevpic()">
                        <span class="material-symbols-outlined arrkey">
                        arrow_left
                        </span>

                    </button>
                    <img  class="CarouselImg" :src="currentimg">

                        <button @click="nextpic()">
                            <span class="material-symbols-outlined arrkey">
                            arrow_right
                            </span>
                        </button>

                </div>
                <div class="imgicons">
                    <ul>
                        <li v-for="img in data" :key="img">
                        

                        <img @click="( currentposition=data.indexOf(img)) " :class="{iconactivated: currentimg== img}"   class="imageIconImg " :src="img"> 
                        </li>
                    </ul>
                </div>



            </div>
            <div class="TextAreaConteiner">

                <div class="ProductTitle">
                    {{ getmodel.ProductName }} </div>
               
                <div class="ratingbar">
                    <span class="material-symbols-outlined">
                        star
                        </span>
                        <span class="material-symbols-outlined">
                        star
                        </span>
                        <span class="material-symbols-outlined">
                        star
                        </span>
                        <span class="material-symbols-outlined">
                        star
                        </span>
                        <span class="material-symbols-outlined">
                        star
                        </span>
                </div>
                <div class="ProductPrice">
                    Price  {{ getmodel.Price }}$
                </div>
                
                    <button v-if="isauthenticated" class="addtocartbtn" @click="Addtocart(scope.updateData)">
                        Add to cart
                    </button>
                    <button v-if="!isauthenticated" class="addtocartbtn" @click="this.$router.push('/login')">
                        Log in to Buy
                    </button>


                    <div class="Aboutthisitem">
                        <div class="aboutthisitemtitle">
                            About this Product

                        </div>
                            <ul>
                                <li>
                                        {{ getmodel.Description }}

                                </li>
                               
                            </ul>

                    </div>

              
            </div>


        </div>  
      

       
        <div class="specificationConteiner">
            <div> 
                <div class="spectitle">
                    Specifications
                </div>

            <ul class="specificationUI">
                <li class="specificationli" v-for="spec in getmodel.specs" :key="spec.ID">
                    <div  class="lidiv">
                        <div class="spec_key">{{ spec.Specification_name }}</div>
                    <div class="spec_value">{{ spec.Specification_value.substring(0, 104) }} </div>
                    </div>
                   
                </li>
              
              
            </ul>      
            
        </div>

        </div>




    </div>

<!--                
                    <div class="reviewSection">
                 
                       <div class="reviewSectionTitle">Reviews ({{reviewdata.length}})</div>
                       <div class="reviewsConteiner">

                        <div class="WriteReviewForm">
                                <div class="writerinfo">

                                    <div class="userphoto">
                                        <img class="userPohot" src="https://flyclipart.com/thumb2/boss-circle-man-person-profile-staff-user-icon-133441.png" alt="">
                                    </div>
                              
                            </div>
                                    <input v-model="reviewtext" class="writereviewinput"> 
                                    
                                    <button @click="addnewreview()" class="reviewPostBTN">Post</button>
                               
                        </div>

                          

                                <div  v-for="item in  reviewdata "> 
                                    <reviewItem    :author=item.author  :authorImg=item.authorImg  :reviewtext=item.reviewtext  :isauthor=true  @deleterev="deletereview(item)"  />

                                </div>
 


                       </div>




                    </div>
                -->
             
    </div>



</heading>


</template>


<script>
import heading from '../components/header.vue'
import reviewItem from '../components/Review.vue'
import axios from 'axios';
export default{
    name: 'ProductDetailed',
    components:{
        heading ,reviewItem
    }, 
  
    data(){
        return{
            currentposition:0,
            isauthenticated:false,
            reviewtext:"",
            data:[
                   
          
          
                ],
                // reviewdata:[
                //     {
                //         'author':"Giorgi tughushi",
                //             'authorImg':"https://img.zoommer.ge/zoommer-images/thumbs/0174438_apple-iphone-14-pro-max-128gb-space-black_550.jpeg",
                //             'reviewtext':"wrak awbdobaw oaiwyubhbdhabw awuigdouihajwb oawuihdoiawu"
                //
                //     },
                //     {
                //         'author':"Giorgi tughushi",
                //             'authorImg':"https://img.zoommer.ge/zoommer-images/thumbs/0174438_apple-iphone-14-pro-max-128gb-space-black_550.jpeg",
                //             'reviewtext':"wrak awbdobaw oaiwyubhbdhabw awuigdouihajwb oawuihdoiawu"
                //
                //     }
                //
                // ] ,

                    getmodel:[]

        }
    },

    mounted(){
         
        axios.defaults.baseURL = 'http://localhost:8080';
axios.defaults.headers.common['access-control-allow-origin'] = 'http://localhost:5173';
axios.defaults.headers.common['Content-Type'] = 'application/json';

        var slug=  this.$route.params.slug
      


        if(localStorage.getItem('token')){ 
        var token=localStorage.getItem('token')
            axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
            this.isauthenticated=true
        }
axios.get('/product/product/'+slug)
 

.then(response => {
  console.log(response.data   );  
 this.getmodel= response.data
    this.data=this.getmodel.Images
})
.catch(error => {
  console.error(error);
}) 

}
,

        


  

    methods:{

        Addtocart(updateheader){
           
            axios.defaults.baseURL = 'http://localhost:8080';
axios.defaults.headers.common['access-control-allow-origin'] = 'http://localhost:5173';
axios.defaults.headers.common['Content-Type'] = 'application/json';
            if(localStorage.getItem('token')){
              
           
var token=localStorage.getItem('token')
            axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;

axios.post(`/addtocart/${this.getmodel.id}`)

    

.then(response => {
    updateheader()
    
 

 })
.catch(error => {
  console.error(error);
})



}

        },

        deletereview(item){
             
             this.reviewdata.splice(this.reviewdata.indexOf(item), 1);
        },

            nextpic(){
                this.currentposition+=1
            },
            prevpic(){

                this.currentposition-=1
            },
            addnewreview(){

                if(this.reviewtext!=""){
                    this.reviewdata.push({"author":"axali", "authorImg":"https://images.unsplash.com/photo-1481349518771-20055b2a7b24?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8M3x8cmFuZG9tfGVufDB8fDB8fA%3D%3D&w=1000&q=80",
                'reviewtext':this.reviewtext
                
            })
                }

             
            this.reviewtext=""
            }
            


    },
    computed:{
         currentimg :function(){


      return this.data[Math.abs(this.currentposition) % this.data.length];
     

            }

    }



}
</script>

<style scoped>
.actualreview{
    font-size: 20px;
}
.authortitle{
    font-size: 25px;
}
.writtenreviewItem{
    width: 100%;
    border-radius: 10px;
    box-shadow: rgba(0, 0, 0, 0.07) 0px 1px 1px, rgba(0, 0, 0, 0.07) 0px 2px 2px, rgba(0, 0, 0, 0.07) 0px 4px 4px, rgba(0, 0, 0, 0.07) 0px 8px 8px, rgba(0, 0, 0, 0.07) 0px 16px 16px;
    margin-top: 20px;
    margin-bottom: 20px;
    height: auto;
}
.ReviewAuthorInfo{
    display: flex;
}
.reviewPostBTN{
    font-size: 20px;
   width: 200px;
    height: 50px;
    border-radius: 10px;
    border-color: transparent;
    color: white;
    background-color: #26292E;
}
.reviewPostBTN:hover{
    background-color: #5e6167;
}
.writereviewinput{
    height: 30px;
    width: 100%;
    font-size: 30px;
    padding: 10px;
    border-radius: 10px;
   
    margin-right: 10px;
}
.WriteReviewForm{
    display: flex;
    align-items: center;
}
.writerinfo{
    display: flex;
    
}
.userPohot{
    width: 100px;
    height: 100px;
    margin-right: 10px;
}
 
.reviewSection{
    
     margin-left: 130px;
    margin-right: 130px;
    
    height: 100vh;

}
.reviewSectionTitle{
    font-size: 35px;
    margin-bottom: 50px;

}
.lidiv{
    display: flex;
    justify-content: space-between  ;
    align-items: center;

    width: 100%;
}
.specificationUI{
   display: block;
  
    
    margin-top: 20px;
    height: auto;
}
.spec_key{
    font-size: 20px;
    margin-top: 5px;


}
.spec_value{
    font-size: 20px;
     margin-top: 5px;
}
.specificationli{
   
    list-style: none;
    display: flex;
    align-items: center;
     
    justify-content:space-between;
}
.spectitle{
    font-size: 35px;
    text-align: center;
    margin-bottom: 60px;
}
.specificationConteiner{
    width: 90%;
    
}
.Aboutthisitem{
    margin-top: 30px;
}
.aboutthisitemtitle{
    font-size: 25px;
    text-align: left;
    margin-bottom: 10px;
}
.addtocartbtn{
    font-size: 30px;
    margin-top: 40px;
    padding: 10px;
    border-color: transparent;
    background-color:  #26292E;
    color: white;
    border-radius: 10px;
    cursor: pointer;
}

.addtocartbtn:hover{
    background-color:  #494d53;
}
.ProductPrice{
    margin-top: 20px;
    font-size: 35px;
}



.imgicons ul{
    display: flex;
    display:inline-table;
        width: 75%;
}
.imgicons ul li {
    list-style: none;
    display:inline ;
}
.CarouselImg{

    width: 400px;
    height: 400px;
 
}
.imageIconImg{
    width: 90px;
    height: 90px;
    opacity: 0.6
}
.ImageSlider{

    display: flex;
    align-items: center;


}
.ImageSlider button{
    border-color: transparent;
    
}
.ratingbar{
    margin-top: 10px;
}
.ratingbar span{
    color: rgb(167, 167, 14);
    cursor: pointer;
}
.arrkey{

    font-size: 60px;
    cursor: pointer;
}
.arrkey:hover{
    color: rgb(153, 142, 142);


}
.DetailedHeading{
    padding: 50px;
    
    display: grid;
    place-items: center;
    
}
.content{
    display: flex;
}
.ProductTitle{
    font-size: 40px;
    font-weight: bold;
}

.iconactivated{
    opacity: 1;

}



</style>