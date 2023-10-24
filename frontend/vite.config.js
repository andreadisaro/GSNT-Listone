import { defineConfig } from "vite";
import vue from "@vitejs/plugin-vue";
let host = "http://localhost:8089";
//host = "https://listone.giochisulnostrotavolo.it";

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],
  base: "/",
  build: {
    outDir: "target/dist",
    assetsDir: "static",
  },
  server: {
    proxy: {
      // string shorthand: http://localhost:5173/foo -> http://localhost:4567/foo
      //"/public": "http://localhost:8089",
      // with options: http://localhost:5173/api/bar-> http://jsonplaceholder.typicode.com/bar
      "/be": {
        target: host,
        changeOrigin: true,
        rewrite: (path) => {
          if (host.indexOf("localhost") != -1) {
            return path.replace(/^\/be/, "");
          } else {
            return path;
          }
        },
      },
      // with RegEx: http://localhost:5173/fallback/ -> http://jsonplaceholder.typicode.com/
      /*
      "^/fallback/.*": {
        target: "http://jsonplaceholder.typicode.com",
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/fallback/, ""),
      },
      // Using the proxy instance
      "/api": {
        target: "http://jsonplaceholder.typicode.com",
        changeOrigin: true,
        configure: (proxy, options) => {
          // proxy will be an instance of 'http-proxy'
        },
      },
      // Proxying websockets or socket.io: ws://localhost:5173/socket.io -> ws://localhost:5174/socket.io
      "/socket.io": {
        target: "ws://localhost:5174",
        ws: true,
      },
      */
    },
  },
});
