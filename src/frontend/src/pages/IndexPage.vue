<template>
    <div class="page page--index">


        <!-- List group -->
        <div class="list-group">
            <a class="list-group-item list-group-item-action active" aria-current="true">
                Welcome Clothing Software Header
            </a>
            <router-link class="list-group-item list-group-item-action" to="/sprint0">Sprint 0</router-link>
            <router-link class="list-group-item list-group-item-action" to="/sprint1">Sprint 1</router-link>
            <!-- <router-link class="list-group-item list-group-item-action" to="/sprint2">Sprint 2</router-link> -->
            <!-- <router-link class="list-group-item list-group-item-action" to="/sprint3">Sprint 3</router-link> -->
        </div>
    </div>
</template>
<script lang="ts">
import {Component, Vue} from 'vue-property-decorator';

@Component
export default class IndexPage extends Vue {

}
</script>
<style scoped>

</style>