<template>
  <header class="container">
    <div class="greetings">
      <h1>Welcome!</h1>
      <h3>
        You’ve successfully logged in!<br>
        Please click on one of the links below to get started!
      </h3>
    </div>

    <nav class="nav-links">
      <router-link class="link" to="/customers">User</router-link>
      <router-link class="link" to="/tags">Tag</router-link>
    </nav>
  </header>
</template>

<script setup lang="ts">
</script>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  min-height: 80vh;
  text-align: center;
}

.greetings {
  margin-bottom: 20px;
}

h1 {
  font-weight: 500;
  font-size: 2.6rem;
}

h3 {
  font-size: 1.2rem;
  margin: 10px 0;
}

.nav-links {
  display: flex;
  gap: 20px;
}

.link {
  font-size: 1.2rem;
  color: #007bff; /* Bootstrap primary color */
  text-decoration: none;
  transition: color 0.3s;
}

.link:hover {
  color: #0056b3; /* Darker shade of the Bootstrap primary color */
}

@media (min-width: 1024px) {
  .container {
    text-align: left;
  }
}
</style>
