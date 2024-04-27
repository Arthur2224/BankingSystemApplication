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
        // Add notification of successful registration 
      }

    },
    handleLogin(loginData) {
     
      console.log("Login data:", loginData);
      if(loginData.accountInfo!=null){
         this.userData=loginData.accountInfo;
        // add notification of successful login
        this.loggedIn=true;
        
        
        
      }
      else if(loginData.responseCode=="0021")
      {
        console.log(loginData.responseMessage)
      }
     
     
    }
  }};

</script>
<template>
  <div class="blockBody">
    <div class="header">
      <p>BankingSystemApplication</p>
    </div>
    <div class="blocks">
      <div class="leftBlock">
        <p>left</p>
      </div>
      <div class="centreBlock">
        <div v-if="!loggedIn" class="tabs">
          <nav class="tabs_item">
            <RouterLink to="/signUp">SignUp</RouterLink>
           
            <RouterLink to="/login">Login</RouterLink>
          </nav>
        </div>
        <div v-if="!loggedIn" class="tabs__body">
          <div id="RegistrationTab" class="tabs__block">
            <RegistrationForm @registration="handleRegistration" />
          </div>
          <div id="LoginTab" class="tabs__block">
            <LoginF @login="handleLogin" />
          </div>
        </div>
        <div v-if="loggedIn" class="account__cabinet">
          <AccountCabinet :userData="userData" />
        </div>
      </div>
      <div class="rightBlock">
        <p>Right</p>
      </div>
    </div>
  </div>
  <RouterView/>
</template>




<style scoped>
.blockBody{
  margin:0;
  padding: 0 0;
  height: 100%;
 

}

.blocks {
  display:flex;
  min-height: 89vh ;
 
}

.centreBlock{
  background-color: rgba(207, 79, 79, 0.267);
}
.header{

  color:whitesmoke;
  font-size: 40px;
  height: 100px;
}
.centreBlock{
  
 
  min-width: 540px;
  border-radius: 10px;
  width: 100%;
  
  color:rgb(248, 248, 248);
}
.leftBlock{
  margin-left:0px;
  min-width: 15%;
  
}
.tabs{
  margin-left: 25%;
  margin-right: 25%;
}
.rightBlock{
  min-width: 15%;

   
 
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
.account__cabinet{

  text-align: center;
  flex:1;


}

</style>

