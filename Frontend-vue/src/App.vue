<template>
  <v-app>
    <v-app-bar app color="primary" dark>
      <v-toolbar-title>User Management</v-toolbar-title>
      <v-spacer></v-spacer>
      <router-link to="/customers">
        <v-btn color="white">User</v-btn>
      </router-link>
      <router-link to="/tags">
        <v-btn color="white">Tag</v-btn>
      </router-link>
    </v-app-bar>
    <v-main>
      <router-view></router-view>
    </v-main>
  </v-app>

</template>

<script lang="ts">
import { defineComponent } from 'vue';

export default defineComponent({
  name: 'App',
});
</script>
