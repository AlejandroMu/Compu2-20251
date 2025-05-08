import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vite.dev/config/
export default defineConfig({
  base: "/compu2/frontend/",
  server:{
    port: 3000,
    host: "0.0.0.0",
  },
  plugins: [react()],
})
