<template>
    <div class="account-cabinet">
      <h2>Lietotāja konta informācija</h2>
      <div>
        <p><strong>Vārds:</strong> {{ userData.accountName }}</p>
        <p if:userData.lastName><strong>Uzvārds:</strong> {{ userData.lastName }}</p>
        <p><strong>E-pasts:</strong> {{ userData.email }}</p>
       
      </div>
    </div>
  </template>
  
  <script>
  export default {
    name: 'AccountCabinet',
    props: {
      userData: {
        type: Object,
        required: true
      }
    }
  };
  </script>
  
  <style scoped>
  .account-cabinet {
    max-width: 600px;
    margin: 0 auto;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 5px;
    background-color: #050505;
  }
  .account-cabinet h2 {
    font-size: 24px;
    margin-bottom: 10px;
  }
  .account-cabinet div {
    background-color:#050505;
    padding: 20px;
    border-radius: 5px;
  }
  .account-cabinet p {
    margin-bottom: 10px;
  }
  </style>
  