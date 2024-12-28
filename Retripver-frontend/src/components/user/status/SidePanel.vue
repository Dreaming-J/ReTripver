<script setup lang="ts">
import { onMounted, ref, watch } from "vue";

interface Props {
  value?: number;
  size?: number;
  thickness?: number;
  startAngle?: number;
  lineCap?: "butt" | "round";
  emptyFill?: string;
  fill?: {
    color?: string;
    gradient?: string[];
    gradientAngle?: number;
  };
  animation?: boolean;
  animationDuration?: number;
}

const props = withDefaults(defineProps<Props>(), {
  value: 0,
  size: 100,
  thickness: 10,
  startAngle: -Math.PI,
  lineCap: "butt",
  emptyFill: "#e6e6e6",
  fill: () => ({
    color: "#2196f3",
  }),
  animation: true,
  animationDuration: 1000,
});

const emit = defineEmits(["progress"]);
const canvas = ref<HTMLCanvasElement | null>(null);
const currentValue = ref(0);
const animationStartTime = ref(0);

const drawCircle = (ctx: CanvasRenderingContext2D, value: number) => {
  const radius = (props.size - props.thickness) / 2;
  const startAngle = props.startAngle;
  const endAngle = startAngle + 2 * Math.PI * value;

  // Clear canvas
  ctx.clearRect(0, 0, props.size, props.size);

  // Draw background circle
  ctx.beginPath();
  ctx.arc(props.size / 2, props.size / 2, radius, 0, 2 * Math.PI);
  ctx.strokeStyle = props.emptyFill;
  ctx.lineWidth = props.thickness;
  ctx.stroke();

  // Draw progress circle with gradient
  ctx.beginPath();
  ctx.arc(props.size / 2, props.size / 2, radius, startAngle, endAngle, false);

  const gradient = getFillStyle(ctx);
  ctx.strokeStyle = gradient;
  ctx.lineWidth = props.thickness;
  ctx.lineCap = props.lineCap;
  ctx.stroke();
};

const getFillStyle = (ctx: CanvasRenderingContext2D) => {
  if (props.fill.gradient) {
    const gradient = ctx.createLinearGradient(
      props.size / 2,
      0,
      props.size / 2,
      props.size
    );
    props.fill.gradient.forEach((color, index) => {
      gradient.addColorStop(index / (props.fill.gradient!.length - 1), color);
    });
    return gradient;
  }
  return props.fill.color!;
};

const animate = (timestamp: number) => {
  if (!animationStartTime.value) {
    animationStartTime.value = timestamp;
  }

  const progress = Math.min(
    (timestamp - animationStartTime.value) / props.animationDuration,
    1
  );
  currentValue.value = progress * props.value;

  const ctx = canvas.value?.getContext("2d");
  if (ctx) {
    drawCircle(ctx, currentValue.value);
    emit("progress", progress, currentValue.value);
  }

  if (progress < 1) {
    requestAnimationFrame(animate);
  }
};

watch(
  () => props.value,
  (newValue) => {
    if (props.animation) {
      animationStartTime.value = 0;
      requestAnimationFrame(animate);
    } else {
      currentValue.value = newValue;
      const ctx = canvas.value?.getContext("2d");
      if (ctx) {
        drawCircle(ctx, newValue);
      }
    }
  }
);

onMounted(() => {
  const ctx = canvas.value?.getContext("2d");
  if (ctx) {
    if (props.animation) {
      requestAnimationFrame(animate);
    } else {
      drawCircle(ctx, props.value);
    }
  }
});
</script>

<template>
  <div
    class="circle-progress"
    :style="{ width: size + 'px', height: size + 'px' }"
  >
    <canvas ref="canvas" :width="size" :height="size"></canvas>
    <strong :style="{ fontSize: size * 0.2 + 'px', lineHeight: size + 'px' }">
      <slot :progress="currentValue">
        {{ Math.round(currentValue * 100) }}<i>%</i>
      </slot>
    </strong>
  </div>
</template>

<style scoped>
.circle-progress {
  display: inline-block;
  position: relative;
  text-align: center;
  vertical-align: middle;
}

.circle-progress canvas {
  display: block;
}

.circle-progress strong {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  text-align: center;
  font-size: 20px;
  font-weight: bold;
}

.circle-progress strong i {
  font-style: normal;
  font-size: 0.6em;
  font-weight: normal;
}
</style>
