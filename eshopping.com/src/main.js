import { createApp } from 'vue'
import App from './App.vue'
 import router from './router'
import './assets/main.css'
import VueAwesomePaginate from "vue-awesome-paginate";
import "vue-awesome-paginate/dist/style.css";
 
 
 
 
const app = createApp(App)

app.use(VueAwesomePaginate) 
app.use(router)
app.config.globalProperties.$currentpage="ManageOrderPage"
 
app.mount('#app')
 