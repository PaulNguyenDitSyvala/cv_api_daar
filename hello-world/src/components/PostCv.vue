<template>
  <div class="card text-center m-3">
    <h4 class="card-header">POST Request for sending CV</h4>
   
    <div v-if="!cv">
      <h5>Select a cv</h5>
      <input type="file" @change="onFileChange">
    </div>
    <div v-else>
      <div class="card-body"> cv : {{cv}}</div>
      <div>
        <button v-on:click="upload">Upload your CV</button>
        <button @click="removeCv">Remove selected Cv</button>
        
        <div v-if="filename!=null" class="card-body">result : {{filename}} has been added succesfully</div>
      </div>
    </div>
    <div v-if="errorMessage != 'No error'">
      <div class="card-body">Error message: {{errorMessage}}</div>
    </div>
  </div>
</template>

<script>
export default {
  name: "post-cv",
  data() {
    return {
      filename: null,
      errorMessage: "No error",
      cv: ''

    };
  },
  created() {
    
  },
  methods: {
    upload(){
      // POST request using fetch with error handling
      const cv = this.cv;
      const requestOptions = {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'},
        body: cv
      };
      fetch('http://localhost:8080/api/cv/', requestOptions)
        .then(async response => {
          const data = await response.json();
          
          // check for error response
          if (!response.ok) {
            // get error message from body or default to response status
            const error = (data && data.message) || response.status;
            return Promise.reject(error);
          }
          this.filename = data.filename;
        })
        .catch(error => {
          this.errorMessage = error;
          console.error('There was an error!', error);
        });
    },
    onFileChange(e) {
      var files = e.target.files || e.dataTransfer.files;
      if (!files.length)
        return;
      this.createCv(files[0]);
    },
    createCv(file) {

      var reader = new FileReader();
      var vm = this;

      reader.onload = (e) => {
        vm.cv = e.target.result;
      };
      reader.readAsText(file);
    },
    removeCv: function () {
      this.cv = '';
      this.filename = null;
    }
  }  
};
</script>

