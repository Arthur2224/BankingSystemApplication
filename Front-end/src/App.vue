
<template>
  <div class="blockBody">
    <div class="header"><p>BankingSystemApplication</p></div>
   <div class="blocks">
      <div class="leftBlock">
        <p>left</p>
      </div>
      <div class="centreBlock"> 
        <div class="tabs">
          <nav class="tabs_item">
            <a href="#RegistrationTab" class="tabs__item">Registration</a>
            <a href="#LoginTab" class="tabs__item">Login</a>
        </nav>
      <div class="tabs__body">
        <div id="RegistrationTab" class="tabs__block">
          <RegistrationForm @registration="handleRegistration" />
        </div>
        <div id="LoginTab" class="tabs__block">
          <LoginF @login="handleLogin" />
        </div>
      </div>
    
  
  </div>
  <div v-if="loggedIn">
  <AccountCabinet :userData="userData" />
</div>
      </div>
      <div class="rightBlock">
        <p>right</p>
      </div>
   </div>
  </div>
</template>

<script>
import RegistrationForm from './components/RegistrationForm.vue';
import LoginForm from './components/Login.vue';
import CabinetForm from './components/AccountCabinet.vue';

export default {
  name: 'App',
  components: { 
    AccountCabinet:CabinetForm,
    RegistrationForm,
    LoginF: LoginForm,

    
  },
  data() {
    return {
      showLabel: false,
      labelText: '',
      loggedIn:false,
      userData: {
        type: Object,
        required: true
      }
    };
  },
  methods: {
    handleRegistration(userData) {
     
      if(userData.accountInfo!=null){
              this.showLabel = true;
      this.loggedIn=true;
      }

    },
    handleLogin(loginData) {
      
      console.log("Login data:", loginData);
      if(loginData.accountInfo!=null){
         this.userData=loginData.accountInfo;
      console.log( loginData.accountInfo.accountBalance);
      this.loggedIn=true;
      }
      else if(loginData.responseCode=="0021")
      {
        console.log(loginData.responseMessage)
      }
     
     
    }
  }};

</script>

<style scoped>
.blockBody{
  margin:0;
  padding: 0 0;
  height: 100%;

}

.blocks {
  display:flex;
  min-height: 500px ;
 
}

.centreBlock{
  background-color: rgba(138, 26, 12, 0.8);
}
.header{

  background-color: rgba(33, 7, 80, 0.832);

  height: 100px;
}
.centreBlock{
  display: flex;
 content: center;
  min-width: 540px;
  max-width: 40%;
  width: 100%;
  
  color:black;
}
.leftBlock{
  margin-left:0px;
  width: 35%;
 
}
.tabs{
  margin-left: 25%;
  margin-right: 25%;
}
.rightBlock{
  min-width: 25%;
  width: 35%;
 
}
.header{
  color:black;
  font-size: 30px;
}











.tabs {
  width: 50%; /* Ширина контейнера с вкладками */

}

.tabs_item {
  display: flex;
}

.tabs__item {
  flex: 1;
  padding: 10px;
  text-align: center;
  font-size: 18px;
  cursor: pointer;
}

.tabs__item:hover {
  background-color: #f0f0f0;
}

.tabs__body {
  padding: 20px;
}

.tabs__block {
  display: none;
}

.tabs__block:target {
  display: block;
}

</style>

