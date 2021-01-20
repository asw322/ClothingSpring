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

        <!-- Login area -->
        <div>
            <textarea class="form-control" v-model="formUsername" @keyup="formError= ''"></textarea>
            <button type="button" class="btn btn-primary mt-3" @click="login">Log In</button>
            <div class="post-form-error" v-if="formError">{{formError}}, please fill in the username and password</div>
            <div>{{ this.responseID }}</div>
        </div>
    </div>
</template>
<script lang="ts">
import {Component, Vue} from 'vue-property-decorator';
import UsersDataService from '../services/UsersDataService';

@Component
export default class IndexPage extends Vue {
    // Life cycle hook: created() fetch data from cookie if exists and automatically log user in

    private formUsername: string = '';
    private formPassword: string = '';
    private formError: string = '';

    private responseID: number = 0;

    public login(): void {
        console.log("Logging the user in");
        
        // Web version of a UserToken
        const newLoginRequest = {
            username: this.formUsername,
            password: this.formPassword
        }

        console.log(newLoginRequest);

        UsersDataService.login(newLoginRequest)
            .then(response => {
                // bring the user into their profile page
                console.log(response);
                this.responseID = response.data.ID;

            })
            .catch(err => {
                this.formError = err.response.statusText;
            })
    }

    
}
</script>
<style scoped>

</style>