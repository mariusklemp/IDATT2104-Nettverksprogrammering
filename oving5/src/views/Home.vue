<template>
  <div class="home">
    <CompilerComponent msg="Welcome to your compiler" />
  </div>
</template>

<script>
// @ is an alias to /src
import CompilerComponent from "@/components/CompilerComponent.vue";

export default {
  name: "Home",
  components: {
    CompilerComponent,
  },
};
</script>
