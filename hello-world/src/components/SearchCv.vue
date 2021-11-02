<template>
  <div class="card text-center m-3">
    <h4 class="card-header">GET Request for searching in the CV Database</h4>
   
    <h5>Select a input</h5>
    <input type="text" @change="onInputChange">
    <div class="card-body"> input : {{input}}</div>
    <button v-on:click="search">search matching CVs in ElasticSearch</button>
    <div v-if="result !=''">
      <div class="card-body">Number of matches : {{nbMatches}}</div>
        <div v-for="(cv, index) in result" :key=index >
          <div class="card-body">{{index + 1}} - {{cv.filename}} : {{cv.content}}</div> 
        </div>
    </div>
    <div v-if="errorMessage != 'No error'">
      <div class="card-body">Error message: {{errorMessage}}</div>
    </div>
  </div>
</template>

<script>
export default {
  name: "search-cv",
  data() {
    return {
      errorMessage: "No error",
      input: '',
      result: '',
      nbMatches: 0
    };
  },
  created() {
    
  },
  methods: {
    search(){
      // GET request using fetch with error handling
      const query = "?tags=" + this.input;
      const requestOptions = {
        method: 'GET',
        headers: {'Accept': 'application/json', 'Access-Control-Request-Method' : '*'}
      };
      fetch('http://localhost:8080/api/search/'+query, requestOptions)
        .then(async response => {
          const data = await response.json();
          
          // check for error response
          if (!response.ok) {
            // get error message from body or default to response status
            const error = (data && data.message) || response.status;
            return Promise.reject(error);
          }
          this.result = data;
          this.nbMatches = Object.keys(data).length;
        })
        .catch(error => {
          this.errorMessage = error;
          console.error('There was an error!', error);
        });
    },
    onInputChange(e) {
      this.input = e.target.value;
    }
  }  
};
</script>

