<template>
    <div class="page page--about">
        <p1>{{ title }}</p1>
    </div>
</template>
<script lang="ts">
import { Component, Vue } from 'vue-property-decorator';

@Component
export default class UserPage extends Vue {
    private title: string = 'This is info from Sprint 1 page';
}

</script>
<style scoped>

</style>