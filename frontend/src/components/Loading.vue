<script setup>
import { useLoadingStore } from "../store/useLoadingStore";
const loadingStore = useLoadingStore();
</script>

<template>
  <div className="flex fixed inset-0 items-center justify-center z-50 background" v-if="loadingStore.count > 0">
    <div className="flex items-center justify-center">
      <div :aria-label="$t('loading') + '...'" role="status" class="flex items-center space-x-2">
        <svg width="200" height="200" viewBox="0 0 200 200" color="#fff" fill="none" xmlns="http://www.w3.org/2000/svg"
          class="h-9 w-9">
          <defs>
            <linearGradient id="spinner-secondHalf">
              <stop offset="0%" stop-opacity="0" stop-color="currentColor" />
              <stop offset="100%" stop-opacity="0.5" stop-color="currentColor" />
            </linearGradient>
            <linearGradient id="spinner-firstHalf">
              <stop offset="0%" stop-opacity="1" stop-color="currentColor" />
              <stop offset="100%" stop-opacity="0.5" stop-color="currentColor" />
            </linearGradient>
          </defs>

          <g stroke-width="8">
            <path stroke="url(#spinner-secondHalf)" d="M 4 100 A 96 96 0 0 1 196 100" />
            <path stroke="url(#spinner-firstHalf)" d="M 196 100 A 96 96 0 0 1 4 100" />

            <!-- 1deg extra path to have the round end cap -->
            <path stroke="currentColor" stroke-linecap="round" d="M 4 100 A 96 96 0 0 1 4 98" />
          </g>

          <animateTransform from="0 0 0" to="360 0 0" attributeName="transform" type="rotate" repeatCount="indefinite"
            dur="1300ms" />
        </svg><span class="text-lg font-medium text-white">{{ $t("loading") }}...</span>
      </div>
    </div>
  </div>
</template>

<style scoped>
.background {
  background: rgba(85, 85, 85, 0.65);
}
</style>
