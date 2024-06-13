import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import vuetify from 'vite-plugin-vuetify'
import path from 'path'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    vue(),
    vuetify({
      autoImport: true,
    }),
  ],
  define: {
    'process.env': {},
    '__VUE_OPTIONS_API__': true,
    '__VUE_PROD_DEVTOOLS__': false,
    '__VUE_PROD_HYDRATION_MISMATCH_DETAILS__': false,
  },
  resolve: {
    alias: {
      '@': path.resolve(__dirname, 'src'),
    },
  },
  // If you want to remove the need to specify .vue files
  // resolve: {
  //   extensions: [
  //     '.js',
  //     '.json',
  //     '.jsx',
  //     '.mjs',
  //     '.ts',
  //     '.tsx',
  //     '.vue',
  //   ]
  // },
})
