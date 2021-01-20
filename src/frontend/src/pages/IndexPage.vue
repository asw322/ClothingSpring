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
            <div>{{ this.responseUsername }}</div>
            <div>{{ this.responseHashedpassword }}</div>
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
    private formPassword: string = '435600oo';
    private formName: string = '';
    private formBirthdate: string = '';
    private formSex: string = '';
    private formID: number = 0;

    private formError: string = '';

    private responseID: number = 0;
    private responseUsername: string = '';
    private responseHashedpassword: string = '';

    public login(): void {
        console.log("Logging the user in");
        
        // Web version of a UserToken
        const newLoginRequest = {
            USERNAME: this.formUsername,
            PASSWORD: this.formPassword,
            NAME: this.formName,
            BIRTHDATE: this.formBirthdate,
            SEX: this.formSex,
            ID: this.formID 
        }

        console.log("password" + newLoginRequest.PASSWORD)

        // Logging user in based no USERNAME and PASSWORD
        // UsersDataService.login(newLoginRequest)
        //     .then(response => {
        //         // bring the user into their profile page
        //         console.log(response);
        //         this.responseID = response.data.ID;
        //         this.responseUsername = response.data.USERNAME;
        //         this.responseHashedpassword = response.data.HASHEDPASSWORD;
        //     })
        //     .catch(err => {
        //         this.formError = err.response.statusText;
        //     });

        UsersDataService.loginParameters(newLoginRequest.USERNAME, newLoginRequest.PASSWORD)
            .then(response => {
                // bring the user into their profile page
                console.log(response);
                this.responseID = response.data.ID;
                this.responseUsername = response.data.USERNAME;
                this.responseHashedpassword = response.data.HASHEDPASSWORD;
            })
            .catch(err => {
                this.formError = err.response.statusText;
            });
    }

    
}
</script>
<style scoped>

</style>