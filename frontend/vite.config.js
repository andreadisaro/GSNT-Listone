import { defineConfig } from "vite";
import { readFileSync } from "fs";
import vue from "@vitejs/plugin-vue";
const localhost = false;

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
        secure: false,
        target: {
          protocol: localhost ? "http:" : "https:",
          host: localhost ? "localhost" : "listone.giochisulnostrotavolo.it",
          port: localhost ? 8089 : 443,
          ca: readFileSync("../certificate.pem"),
        },
        changeOrigin: true,
        rewrite: (path) => {
          if (localhost) {
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
