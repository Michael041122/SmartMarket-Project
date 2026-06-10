<template>
  <div class="stat-card" :style="{ borderTopColor: color, borderTopWidth: '3px', borderTopStyle: 'solid' }">
    <div class="label">{{ label }}</div>
    <div class="value" :style="{ color }">{{ value }}</div>
    <div class="sub">{{ sub }}</div>
  </div>
</template>

<script setup>
defineProps({
  label: String,
  value: [String, Number],
  sub:   String,
  color: { type: String, default: '#6c8aff' }
})
</script>
