import "@/assets/css/main.css";

import { createApp } from "vue";
import { createPinia } from "pinia";

import App from "./App.vue";
import router from "./router";
import { instance } from "@/api/axios"

// fontawesome - 아이콘 관련 설정
import { library } from "@fortawesome/fontawesome-svg-core";
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";
import { fas } from "@fortawesome/free-solid-svg-icons";
import { far } from "@fortawesome/free-regular-svg-icons";
import { fab } from "@fortawesome/free-brands-svg-icons";

// primevue -  관련 설정
import PrimeVue from "primevue/config";
import Aura from "@primevue/themes/aura";
// import ConfirmationService from "primevue/confirmationservice";
// import ToastService from "primevue/toastservice";
import "primeicons/primeicons.css";
import "primeflex/primeflex.css";

// jQuery 추가
import $ from "jquery";

// jQuery 전역으로 등록
window.$ = window.jQuery = $;

library.add(fas, far, fab);

const app = createApp(App);

app.provide('axios', instance)
app.use(createPinia());
app.use(router);

app.use(PrimeVue, {
  theme: {
    preset: Aura,
    options: {
      darkModeSelector: false,
    },
  },
});
// app.use(ConfirmationService);
// app.use(ToastService);

app.component("font-awesome-icon", FontAwesomeIcon);

app.mount("#app");
