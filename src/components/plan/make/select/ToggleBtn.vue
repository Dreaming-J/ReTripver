<template>
  <div class="toggle-container">
    <div class="toggle checkcross">
      <input
        type="checkbox"
        :id="id"
        v-model="localValue"
        @change="handleChange"
      />
      <label class="toggle-item" :for="id">
        <div class="check"></div>
      </label>
    </div>
    <div v-if="label" class="name">{{ label }}</div>
  </div>
</template>

<script setup>
import { ref, watch } from "vue";

const props = defineProps({
  modelValue: {
    type: Boolean,
    default: false,
  },
  label: {
    type: String,
    default: "Yes & No",
  },
  id: {
    type: String,
    default: "toggle-" + Math.random().toString(36).substr(2, 9),
  },
});

const emit = defineEmits(["update:modelValue", "change"]);

const localValue = ref(props.modelValue);

const handleChange = () => {
  emit("update:modelValue", localValue.value);
  emit("change", localValue.value);
};

watch(
  () => props.modelValue,
  (newValue) => {
    localValue.value = newValue;
  }
);
</script>

<style scoped>
.toggle-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
  gap: 8px;
}

.toggle {
  position: relative;
  display: flex;
  width: 100%;
  justify-content: center;
}

input[type="checkbox"] {
  height: 40px;
  left: 0;
  opacity: 0;
  position: absolute;
  top: 0;
  width: 40px;
  cursor: pointer;
}

.toggle-item {
  width: 100%;
  background: #2e394d;
  height: 3em;
  display: flex;
  border-radius: 50px;
  margin: 8px;
  position: relative;
  transition: all 0.3s ease;
  transform-origin: 20% center;
  cursor: pointer;
  min-width: 7em; /* 최소 너비 설정 */
}

.name {
  font: 500 14px "Rubik", sans-serif;
  letter-spacing: 0.5px;
  text-transform: uppercase;
  text-align: center;
}

/* Yes/No Toggle Styles */
.checkcross .check {
  position: absolute;
  width: 2.5em;
  height: 2.5em;
  background: #8bc34a;
  border-radius: 50%;
  top: 4.5px;
  left: 4.5px;
  transition: 0.4s ease;
}

.checkcross .check:before,
.checkcross .check:after {
  content: "";
  position: absolute;
  height: 4px;
  border-radius: 10px;
  background: #fff;
  transition: 0.4s ease;
}

.checkcross .check:before {
  width: 25px;
  transform: rotate(-45deg) translate(-6px, 20px);
}

.checkcross .check:after {
  width: 10px;
  transform: rotate(45deg) translate(20px, 11px);
}

.checkcross input:checked + .toggle-item .check {
  transform: rotate(360deg);
  background: #c34a4a;
  left: calc(100% - 2.9em); /* 컨테이너 크기에 따라 동적으로 이동 */
}

.checkcross input:checked + .toggle-item .check:before {
  width: 27px;
  transform: rotate(-45deg) translate(-8px, 18px);
}

.checkcross input:checked + .toggle-item .check:after {
  width: 27px;
  transform: rotate(45deg) translate(18px, 8px);
}
</style>
