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
        <div class="check">
          <font-awesome-icon
            :icon="localValue ? ['fas', 'car'] : ['fas', 'person-walking']"
            class="toggle-icon"
          />
        </div>
      </label>
    </div>
  </div>
</template>

<script setup>
import { ref, watch } from "vue";

const props = defineProps({
  modelValue: {
    type: Boolean,
    default: false,
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
  width: 60px;
  justify-content: center;
}

input[type="checkbox"] {
  height: 100%;
  width: 100%;
  left: 0;
  opacity: 0;
  position: absolute;
  top: 0;
  cursor: pointer;
}

.toggle-item {
  width: 100%;
  background: #e3e4e1;
  height: 30px;
  display: flex;
  border-radius: 15px;
  position: relative;
  transition: all 0.3s ease;
  cursor: pointer;
}

.checkcross .check {
  position: absolute;
  width: 27px;
  height: 27px;
  background: #ddd44e;
  border-radius: 50%;
  left: 1.5px;
  top: 1.3px;
  transition: 0.4s ease;
  display: flex;
  align-items: center;
  justify-content: center;
}

.toggle-icon {
  color: white;
  font-size: 14px;
  transition: 0.4s ease;
}

.checkcross input:checked + .toggle-item .check {
  transform: rotate(360deg);
  background: #4577d4;
  left: calc(100% - 28.5px);
}

.checkcross input:checked + .toggle-item .check:before {
  width: 19.5px;
  transform: rotate(-45deg) translate(-6px, 13.5px);
}

.checkcross input:checked + .toggle-item .check:after {
  width: 19.5px;
  transform: rotate(45deg) translate(13.5px, 6px);
}
</style>
