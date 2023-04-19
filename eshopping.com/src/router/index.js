import { createRouter, createWebHistory } from 'vue-router'
import index from '../views/index.vue'
import userpage from '../views/userpage.vue'
import  ProductDetailed from '../views/ProductDetailed.vue'
import login from '../views/login.vue'
import register from '../views/register.vue'
import searchPage from '../views/searchView.vue'
import Support from '../views/SupportPage.vue'
import adminIndex from '../views/admin/admincomponenets/AdminHeader.vue'

import AdminManageOrderPage from '../views/admin/Adminviews/ManageOrders.vue'
import ManageSales from  '../views/admin/Adminviews/ManageSales.vue'
import ManageProducts from '../views/admin/Adminviews/ManageProducts.vue'
import addnewproduct from '../views/admin/Adminviews/AddNewProduct.vue'
import  manageusers from '../views/admin/Adminviews/ManageUsers.vue'
import  revanuereport from '../views/admin/Adminviews/RevanueReport.vue'

import bannerpage from '../views/admin/Adminviews/BannerPage.vue'
import supportpage from '../views/admin/Adminviews/SupportPageAdmin.vue'


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component:  index
    } ,
    {
      path: '/product/:slug',
      name: 'Productdetailed',
      component:  ProductDetailed,
      props: true
    } ,
    {
      path: '/admin/product/:slug',
      name: 'adminProductdetailed',
      component:  ProductDetailed,
      props: true
    } ,
    {
      path: '/search/product/:slug',
      name: 'searchfrom',
      component:  ProductDetailed,
      props: true
    } ,
    {
      path: '/profile',
      name: 'userpage',
      component:   userpage
    } ,
    {
      path: '/login',
      name: 'login',
      component:   login
    } ,
    {
      path: '/register',
      name: 'register',
      component:    register
    } ,
    {
      path: '/search',
      name: 'search',
      component:   searchPage 
    } ,
    {
      path: '/search/:category',
      name: 'search_view',
      component:   searchPage 
    } ,
    {
      path: '/support',
      name: 'support',
      component:    Support
    } ,

    //Admin PAGES

    {
      path: '/admin/',
      name: 'admin',
      component:  AdminManageOrderPage
    } ,
    {

      path:'/admin/manageproducts',
      name: "manageorders",
      component:  ManageProducts

    },
    
    {

      path:'/admin/ManageSales',
      name: "ManageSales",
      component:   ManageSales

    },
    {

      path:'/admin/addnewproduct',
      name: "addnewproduct",
      component:   addnewproduct

    },
    {

      path:'/admin/manageusers',
      name: "manageusers",
      component:   manageusers

    },
  

    {

      path:'/admin/revanue',
      name: "revanuereport",
      component: revanuereport

    },
    {

      path:'/admin/banners',
      name: "bannerpage",
      component:  bannerpage

    },
    {

      path:'/admin/support',
      name: "supportpage",
      component:   supportpage

    },






  ]
})

export default router
