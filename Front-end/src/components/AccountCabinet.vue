<template>
  <div class="bodyMain" style="display: flex; justify-content: space-between; padding: 20px">
    <div class="leftSide" style="width: 50%">
      <div
        class="bodyCard"
        style="background-color: #f0f0f0; padding: 20px; border-radius: 5px; margin-bottom: 20px"
      >
        <div class="accountBalance_body" style="margin-top: 20px; color: black">
          <p>{{ holderName }}</p>
          <p>{{ holderNumber }}</p>
          <p id="accountBalance_text">{{ holderBalance }}</p>
        </div>
      </div>
      <div class="operations" style="display: flex; justify-content: space-between">
        <a @click="updateBalance" style="cursor: pointer">Update</a>
        <!-- Измененная кнопка "Send", добавлен обработчик события -->
        <a @click="showSendWindows" style="cursor: pointer">Send</a>
      </div>
    </div>
    <div class="rightSide" style="width: 50%">
      <div class="bodyHeader"></div>
    </div>
  </div>

  <!-- Добавлено внутреннее окно для отправки средств -->
  <div class="sendWindow" v-if="showSendWindow">
    <div class="sendWindowContent">
      <h2>Send Money</h2>
      <!-- Поля для аккаунта получателя и суммы -->
      <div>
        <label for="recipientAccount">Recipient Account:</label>
        <input type="text" id="recipientAccount" v-model="recipientAccount" />
      </div>
      <div>
        <label for="amount">Amount:</label>
        <input type="text" id="amount" v-model="amount" />
      </div>
      <!-- Кнопки для отправки и отмены -->
      <div>
        <button @click="sendMoney">Send</button>
        <button @click="cancelSend">Cancel</button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'AccountCabinet',
  data() {
    return {
      holderName: '',
      holderNumber: '',
      holderBalance: '',
      // Добавлены переменные для хранения данных о переводе
      showSendWindow: false,
      recipientAccount: '',
      amount: ''
    }
  },
  methods: {
    updateBalance() {
      axios
        .get('http://localhost:8080/api/user/balanceEnquiry/' + this.$route.params.id)
        .then((response) => {
          const { accountInfo } = response.data
          this.holderName = accountInfo.accountName
          this.holderBalance = accountInfo.accountBalance
          this.holderNumber = accountInfo.accountNumber
        })
        .catch((error) => {
          console.log('ERROR:', error)
        })
    },
    // Метод для отображения внутреннего окна отправки средств
    showSendWindows() {
      this.showSendWindow = true
    },
    // Метод для отправки средств
    sendMoney() {
      // Ваша логика отправки средств
      // Может быть реализована с помощью запроса axios, как для обновления баланса
      // и обработки ошибок
      console.log('Sending money to:', this.recipientAccount)
      console.log('Amount:', this.amount)
      // После отправки средств скрываем внутреннее окно
      this.showSendWindow = false
    },
    // Метод для отмены отправки средств
    cancelSend() {
      // Просто скрываем внутреннее окно
      this.showSendWindow = false
    }
  },
  mounted() {
    this.updateBalance()
  }
}
</script>

<style scoped>
@import url('/src/components/style/AccountCabinetStyle.css');
</style>
