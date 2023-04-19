<template>

    <adminheading>
    

        <div class="parent">
            <div class="mainDiv">

                <div class="ManageOrdersTitlebox">

<span class="material-symbols-outlined tlogo">
    monitoring
</span>
    <div class="ManageOrdersTitle">Revanue Report</div>
            </div>


                <div style="display: flex;"> 
            <div class="chartdiv">
                    
                                <div>
                <canvas id="planet-chart"></canvas>
                </div>

            </div>

                <table>

                       <tr style="color: white; font-size: 30px;">

                            <td>Month</td>
                            <td>Total Orders</td>
                            <td>Revanue</td>
                       </tr>
                   
                       <tr v-for="item in sorted" style="color: white; font-size: 25px">
                    
                        <td>{{ item.month }}</td>
                        <td>{{ item.quantity }}</td>
                        <td>{{ item.price }} $</td>
                    </tr>

                </table>



        </div>





        </div>
    
    
    </div>


  
        
    </adminheading>
    </template>
    
    
    <script>
  
    import adminheading from '../admincomponenets/AdminHeader.vue';
    // import PlanetChart from '../admincomponenets/PlanetChart.vue'
    import Chart from 'chart.js'
  import axios from 'axios'
  
    
    export default{
       
        components:{
            adminheading 
        },
        data() {
    return {
        sorted:[],
      filldata:[],
      revanue:[],
      monthsMap : {
  January: 1,
  February: 2,
  March: 3,
  April: 4,
  May: 5,
  June: 6,
  July: 7,
  August: 8,
  September: 9,
  October: 10,
  November: 11,
  December: 12
}
    }
  },

methods:{

 














  createchart( ){

    const ctx = document.getElementById('planet-chart');
    new Chart(ctx,    {
    type: "line",
    data: {
      labels: [" January", "February", "March", "April", "May", "June", "July", "August",
        "September",
        "October",
        "November",
        "December"
    
    
    ],
      
     
      
      datasets: [
        {
          label: "Number of  Orders",
        
          data: this.filldata,
          backgroundColor: "rgba(71, 183,132,.5)",
          borderColor: "#47b784",
          borderWidth: 3
        },
          {
          label: "Total Revanue",
          data: this.revanue,
          backgroundColor: "rgba(54,73,93,.5)",
    
          borderColor: "#36495d",
          borderWidth: 5
        }
        
      
      ]
    },
    options: {
      responsive: true,
      lineTension: 1,
      scales: {
        yAxes: [
          {
            ticks: {
              beginAtZero: true,
              padding: 25
            }
          }
        ]
      }
    }
    });
  }




},

    
mounted() {

      axios.defaults.baseURL = 'http://localhost:8080';
axios.defaults.headers.common['access-control-allow-origin'] = 'http://localhost:5173';
axios.defaults.headers.common['Content-Type'] = 'application/json';
var token=localStorage.getItem('token')
            axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;


axios.get('/admin/report' ,  {
 

 
})
.then(response => {
    

 
var data = response.data
 

const sortedArray =  data.sort((a, b) => {
  const monthA = this.monthsMap[a.month];
  const monthB = this.monthsMap[b.month];
  return monthA - monthB;
});
 
    this.sorted=sortedArray

for (let i = 0; i < sortedArray.length; i++) {
  
  this.filldata.push(   sortedArray[i].quantity );
  this.revanue.push(sortedArray[i].price)
}


  this.createchart( )
 
})
.catch(error => {
console.error(error);
}) 




}}

 


  
    </script>


<style scoped>

.chartdiv{
    width: 900px;
    max-height: 100px;
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
.mainDiv{

padding: 110px;


}
.parent{
    background-color:  rgba(24, 23, 37, 1);
    max-width: 100%;
   height: 200vh;
 }

 
table  td {
   
    border:  #47b784;
   font-size: 20px;
    padding-top: 5px;
  padding-bottom: 5px;
  padding-left: 5px;
  padding-right: 5px;
  border:  #47b784;
  border-collapse:collapse;
 
    align-items: center;
}

 


</style>