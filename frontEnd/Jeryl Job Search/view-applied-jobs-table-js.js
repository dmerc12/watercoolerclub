// BASED ON JOB SEARCH WEBPAGE
// ALSO, USING PARAMETERS AND PATH IN INTELLIJ AND POSTMAN:

//      "/jobSearch/{jobType}/{jobLocation}" "/jobInsertJob/{jobId}/{applicantId}"

//      http://localhost:8080/jobSearch
//      http://localhost:8080/jobInsertJob/{jobId}/{applicantId}
//      http://localhost:8080/viewAppliedJobs



const viewJobTable = document.getElementById("view-applied-jobs-table");
const jobId = document.getElementById("job-id-input");
const applicantId = document.getElementById("applicant-id-input");


async function viewAppliedJobs() {
    let requestURL = `http://localhost:8080/viewAppliedJobs/${applicantId.value}`
    let response = await fetch(requestURL, {method:"GET"});
    if (response.status == 200) {
        const data = await response.json();
        viewAppliedJobsTable(data);
    }

}                                                                               

function viewAppliedJobsTable(data) {
    viewJobTable.innerHTML = ""                                       
    for (let key in data) {
        const row = document.createElement("tr");
        const jobType = data[key].type;
        const jobLocation = data[key].location;
        const jobTitle = data[key].title;
        const jobDescription = data[key].description;
        const companyName = data[key].companyName;

        viewJobTable.appendChild(row);

        dataList = [jobType, jobLocation, jobTitle, jobDescription, companyName]
                                                                                   
    for (let element of dataList) {
        const square = document.createElement("td");
        square.textContent = element;
        row.appendChild(square);
    }

}
}

