<template>
    <div>
      <h1>Вход</h1>
      <form @submit.prevent="loginUser" class="login-form">
        <label for="email">Email:</label>
        <input type="email" id="email" v-model="email" required>
        
        <label for="password">Пароль:</label>
        <input type="password" id="password" v-model="password" required>
        
        <button type="submit">Войти</button>
      </form>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        email: '',
        password: ''
      };
    },
    methods: {
      async loginUser() {

        try {
          const response = await fetch('http://localhost:8080/api/user/login', {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json'
            },
            body: JSON.stringify({
              email: this.email,
              password: this.password
            })
          });
          const userData = await response.json();
          

          this.$emit('login', userData);
        } catch (error) {
          console.error('Ошибка при отправке данных:', error);
        }
      }
    }
  };
  </script>
  
  <style scoped>
  h1{
    text-align: center;
  }
  .login-form {
    
    max-width: 400px;
    margin: 0 auto;
  }
  
  .login-form label {
    display: block;
    margin-bottom: 5px;
  }
  
  .login-form input[type="email"],
  .login-form input[type="password"] {
    width: 100%;
    padding: 8px;
    margin-bottom: 10px;
    border-radius: 4px;
    border: 1px solid #ccc;
  }
  
  .login-form button {
    width: 100%;
    padding: 10px;
    background-color: purple;
    color: #fff;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    transition: background-color 0.3s;
  }
  
  .login-form button:hover {
    background-color: #0056b3;
  }
  </style>
  