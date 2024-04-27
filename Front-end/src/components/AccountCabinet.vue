<template>
    <div class="bodyMain">
     <div class="leftSide">
         <div class="bodyCard">
            <p >Account number:</p>
            <p>{{ userData.accountNumber }}</p>
          <div class="accountBalance_body">
            <p>Account balance:</p>
            <p id="accountBalance_text">{{ userData.accountBalance }}</p>
          </div>
        </div>
        <div class="operations">
          <a @click="updateBalance">Update</a>
          <a>Send</a>
        </div>
      </div>
     <div class="rightSide">
       <div class="bodyHeader">
          <p>Hello,  {{userData.accountName }}</p>
        </div>
     </div>
  
     

     
     
    </div>
  </template>
  
  <script> 
  import axios from 'axios';
  export default {
    name: 'AccountCabinet',
    props: {
      userData: {
        type: Object,
        required: true
      }
    },
    methods:{
      updateBalance(){
        const enquiryRequest={
          account_number:this.userData.accountNumber
        };
        axios.get('http://localhost:8080/api/balanceEnquiry', enquiryRequest)
          .then(response =>{
            console.log(response.data)
          })
          .catch(error=>{
            console.log("ERROR:",error)
          })
      }
    }
  };
  </script>
  
  <style scoped>

  .bodyMain{
  margin: 10px;
    border: 5px solid #f8f8f8; 
    border-radius: 10px;
    display: flex;
    height: 100%;
    margin:40px;
    

  }
  .leftSide{
    background-color: #f8f8f8;
    min-width: fit-content;
    width: 50%;
    display: flex;
    border-radius: 5px;
  }
  .operations{
    display: flex;
    margin-left:20px;
    justify-content: center; 
    align-items: center; 
   
    
  }
  .operations a{
    margin-left:5px;
  }
  .rightSide{

  }
  .bodyHeader{
    
    margin-right: auto;
    
  }
  .bodyCard{
    width: 350px;
    min-width: auto;
    height: 200px;
   
   
    
    max-height: 400px;
   
    

    text-align: left; color:#000; font-size: 25px;
    
  }
  .accountBalance_body{
    display: flex;
  }
  #accountBalance_text{
    padding-left: 10px;
    font-size: 35px;
    margin-top: -10px;
  }

  </style>
  