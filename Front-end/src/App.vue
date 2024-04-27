<script>
export default {
  name: 'App',
  components: {},
  data() {
    return {
      showLabel: false,
      labelText: '',
      loggedIn: false,
      userData: {
        type: Object,
        required: true
      }
    }
  },
  methods: {
    handleUserSign(userData, actionType) {
      if (actionType === 'registration') {
        this.showLabel = true

        this.$router.push('/login')
      } else if (actionType === 'login') {
        console.log('Login data:', userData)
        if (userData.accountInfo != null) {
          this.userData = userData.accountInfo

          this.loggedIn = true
        } else if (userData.responseCode == '0021') {
          // check obly if message not success code
          console.log(userData.responseMessage)
        }
      }
    }
  }
}
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
      <div class="centerBlock">
        <div v-if="!loggedIn" class="tabs">
          <nav class="tabs_item">
            <router-link to="/signUp">SignUp</router-link>
            <router-link to="/login">Login</router-link>
          </nav>
        </div>
        <router-view @event="handleUserSign"></router-view>
      </div>

      <div class="rightBlock">
        <p>Right</p>
      </div>
    </div>
  </div>
</template>

<style scoped>
.blockBody {
  margin: 0;
  padding: 0 0;
  height: 100%;
}

.blocks {
  display: flex;
  min-height: 89vh;
}

.centerBlock {
  background-color: rgba(207, 79, 79, 0.267);
}
.header {
  color: whitesmoke;
  font-size: 40px;
  height: 100px;
}
.centerBlock {
  min-width: 540px;
  border-radius: 10px;
  width: 100%;

  color: rgb(248, 248, 248);
}
.leftBlock {
  margin-left: 0px;
  min-width: 15%;
}
.tabs {
  margin-left: 25%;
  margin-right: 25%;
}
.rightBlock {
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
.account__cabinet {
  text-align: center;
  flex: 1;
}
</style>
