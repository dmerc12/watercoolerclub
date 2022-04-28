// "/jobSearch/{jobType}/{jobLocation}" "/jobInsertJob/{jobId}/{applicantId}"
// from Postman: 
// http://localhost:8080/jobSearch
// "http://localhost:8080/jobInsertJob/{jobId}/{applicantId}"
// http://localhost:8080/viewAppliedJobs


// 1. variable to grab the table element
// 2. variable to grab the input for job type
// 3. variable to grab the input for job location
// 4. async function findJobs()
// 5. function populatePage()
// 6. async function applyJob()
// 7. in findJobs, do your API call for the entire job list by location and type
// 8. if response is good, pass returned information into populatePage
// 9. for populatePage, take code logic from view-skill-tests-js.js populatePage function to populate table
// 10. for applyJob(), create API call for jog application + whatever visual response on a good/bad resp

// ------------------------------------------------------------------------------------------------------------


// BASED ON EXAMPLE FROM ZACH'S VIEW SKILL TEST; ***USING POSTMAN PATHS***

const viewJobTable = document.getElementById("view-jobs-table");
const jobType = document.getElementById("job-type-select");
const jobLocation = document.getElementById("job-location-select");
const applicantId = document.getElementById("applicant-id-input");


async function jobSearch() {
    let requestURL = `http://localhost:8080/jobSearch/${jobType.value}/${jobLocation.value}`
    let response = await fetch(requestURL, {method:"GET"});
    if (response.status == 200) {
        const data = await response.json();
        jobInsertJob(data);
    }

}

function jobInsertJob(data) {
    viewJobTable.innerHTML = ""
    for (let key in data) {
        const row = document.createElement("tr");
        viewJobTable.appendChild(row);

        const jType = data[key].type;
        const jLocation = data[key].location;
        const jTitle = data[key].title;
        const jDescription = data[key].description;
        const companyName = data[key].companyName;
        const button = `<button id="applyJob${data[key].jobId}" onclick="applyToJob(${data[key].jobId})">Apply To This Job</button>`

        dataList = [jType, jLocation, jTitle, jDescription, companyName, button]

        for (let element of dataList) {
            const square = document.createElement("td");
            
            if (element != button) {
                square.textContent = element;
            } else {
                square.innerHTML = element;
            }

            row.appendChild(square);
        }

        // const square1 = document.createElement("td");
        // square1.textContent = data[key];
        // row.appendChild(square1);

        // const square2 = document.createElement("td");
        // square2.innerHTML = `<button id="applyJob"${key}" onclick="applyJob"(${key})">Apply for this job</button>`
        // row.appendChild(square2);
    }

}

// "http://localhost:8080/jobInsertJob/{jobId}/{applicantId}"

async function applyToJob(jobId) {
    let requestURL = `http://localhost:8080/jobInsertJob/${jobId}/${applicantId.value}`
    let config = {
        method: "POST",
        headers: {'Content-Type': "application/json"}
    }

    let response = await fetch(requestURL, config);
    if (response.status == 201) {
        alert("Application successful!");
    } else {
        alert("Something went wrong, please contact administration.");
    }

}

// async function viewAppliedJobs() {
//     let requestURL = "http://localhost:8080/viewAppliedJobs"
//     let response = await fetch(requestURL, {method:"GET"});
//     if (response.status == 200) {
//         const data = await response.json();
//         populatePage(data);
//     }

// }

// -----------------------------------------------------------------------------------------------------------------------------

// EXAMPLE FROM ZACH'S VIEW SKILL TEST 

const returnButton = document.getElementById("return-button");
const testTable = document.getElementById("view-tests-table");

async function requestInformation() {
    let requestURL = "http://localhost:8080/skilltest"
    let response = await fetch(requestURL, {method:"GET"});
    if (response.status == 200) {
        const data = await response.json();
        populatePage(data);
    }

}

function populatePage(data) {
    testTable.innerHTML = ""
    for (let key in data) {
        const row = document.createElement("tr");
        testTable.appendChild(row);

        const square1 = document.createElement("td");
        square1.textContent = data[key];
        row.appendChild(square1);

        const square2 = document.createElement("td");
        square2.innerHTML = `<button id="skillTest${key}" onclick="takeSkillTest(${key})">Take This Test</button>`
        row.appendChild(square2);
    }

}

function takeSkillTest(testID) {
    window.sessionStorage.setItem("current-test-id", testID);
    window.location.href = "take-skill-test.html";

}

function toLandingPage() {
    window.location.href = "zach-test-landing-page.html";
}








