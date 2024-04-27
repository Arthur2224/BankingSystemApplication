<template>
  <div>
    <h1>Регистрация</h1>
    <form @submit.prevent="registerUser" class="registration-form">
      <label for="firstName">Имя:</label>
      <input type="text" id="firstName" v-model="firstName" required>
      
      <label for="lastName">Фамилия:</label>
      <input type="text" id="lastName" v-model="lastName" required>
      
      <label for="otherName">Отчество:</label>
      <input type="text" id="otherName" v-model="otherName">
      
      <label for="stateOfOrigin">Регион происхождения:</label>
      <input type="text" id="stateOfOrigin" v-model="stateOfOrigin">
      
      <label for="email">Email:</label>
      <input type="email" id="email" v-model="email" required>
      
      <label for="phoneNumber">Номер телефона:</label>
      <input type="tel" id="phoneNumber" v-model="phoneNumber" required>
     
      <label for="password">Пароль:</label>
      <input type="password" id="password" v-model="password" required>
      
      <button  id ="registerBtn" type="submit" @click="registerUser">Зарегистрироваться</button>
    </form>
  </div>
 



 


</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      firstName: '',
      lastName: '',
      otherName: '',
      stateOfOrigin: '',
      email: '',
      phoneNumber: '+375',
      password:'',
      alertMessage: '',
    
    };
  },
  methods: {
  registerUser() {
    if(!this.firstName.trim() || !this.lastName.trim() || !this.email.trim() || !this.password.trim()) {
      return;
    }

    const userData = {
      firstName: this.firstName,
      lastName: this.lastName,
      otherName: this.otherName,
      stateOfOrigin: this.stateOfOrigin,
      email: this.email,
      phoneNumber: this.phoneNumber,
      password: this.password
    };

   
    document.getElementById('registerBtn').disabled = true;

    axios.post('http://localhost:8080/api/user', userData)
      .then(response => {
        console.log(response.data); 
        this.$emit('registration', userData);
        
       
        setTimeout(() => {
          document.getElementById('registerBtn').disabled = false;
        }, 3000);
      })
      .catch(error => {
        console.error('Ошибка при отправке данных:', error);
        
        // Enable the button if there's an error
        document.getElementById('registerBtn').disabled = false;
      });
  }
}

};
</script>

<style scoped>
h1{
  text-align: center;
}
.registration-form {
  max-width: 400px;
  margin: 0 auto;
}

.registration-form label {
  display: block;
  margin-bottom: 5px;
}

.registration-form input[type="text"],
.registration-form input[type="email"],
.registration-form input[type="tel"],
.registration-form input[type="password"]
{
  width: 100%;
  padding: 8px;
  margin-bottom: 10px;
  border-radius: 4px;
  border: 1px solid #ccc;
}

.registration-form button {
  width: 100%;
  padding: 10px;
  background-color: purple;
  color: #fff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.registration-form button:hover {
  background-color: #0056b3;
}


.custom-alert-content {
  text-align: center;
}

.custom-alert-close {
  margin-top: 10px;
  padding: 5px 10px;
  background-color: #007bff;
  color: #ffffff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.custom-alert-close:hover {
  background-color: #0056b3;
}

</style>
