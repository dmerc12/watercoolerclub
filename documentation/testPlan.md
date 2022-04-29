# Test Plan Document
## Technologies Used
- Languages: Java, ProstgreSQL, HTML, CSS, Javascript
- Libraries: Cucumber
- IDE: IntelliJ, Visual Studio Code
- Tertiary Programs: DBeaver, Postman, AWS RDS, AWS EC2, GitHub, Git, GitBash, Zoom

## What Is Being Tested
### Individuals - Login
- Database: check if username+password combination exists and return id of the account
- Positive Service: pass up the returned id to the API
- Negative Service: throw an error/pass failure message to API
- Positive API: pass up the returned id to the front end
- Negative API: pass up information from error to the front end
- Positive Front End: allow user to login
- Negative Front End: show error alert

### Individuals - Logout
- Positive Front End: removes cached data from the browser and redirect to login page

### Individuals - Create Accounts
- Database: check if insert account information into both the username/password table AND the applicant table and return a rowcount for both
- Positive Service: pass success message on correct rowcount to API
- Negative service: throw an error/pass failure message on incorrect rowcount to API
- Positive API: pass success message to the front end
- Negative API: pass up information of error to the front end
- Positive Front End: redirect to main page
- Negative Front End: show error alert

### Individuals - Apply To Jobs
- Database: check if the applicant id with the job id is put into the applied jobs table and return rowcount
- Positive Service: pass success message on correct rowcount to API
- Negative Service: throw an error/pass failure message on incorrect rowcount to API
- Positive API: pass success message to the front end
- Negative API: pass up information of error to the front end
- Positive Front End: show positive alert
- Negative Front End: show error alert

### Individuals - View Jobs Applied To
- Database: return ListArray of applied jobs
- Positive Service: pass list up to API
- Negative Service: pass the information that there is no data up to API
- Positive API: pass list up to front end
- Negative API: pass up information that there is no data up to front end
- Positive Front End: shows applied jobs data
- Negative Front End: shows no data

### Individuals - Search For Jobs
- Database: return ListArray of jobs of given search criteria
- Positive Service: pass list up to API
- Negative Service: pass the information that there is no data up to API
- Positive API: pass list up to front end
- Negative API: pass up information that there is no data up to front end
- Positive Front End: shows applied jobs data
- Negative Front End: shows no data

### Individuals - Do Skills Tests
- Database: return ListArray of available skill tests
- Database: return information for selected skill test
- Database: if passed, put applicant id and skill test id into the passed skill test table
- Positive Service: pass list up to API
- Positive Service: pass selected skill test data to API
- Positive Service: send applicant id and skill test id data to database
- Positive API: pass list up to front end
- Positive API: pass selected skill test up to front end
- Positive API: receives data on successful skill tests to pass down
- Positive Front End: shows skill test data (list)
- Positive Front End: shows individual skill test data
- Positive Front End: decides if the user passed the test or not and gives the appropriate alert

### Individuals - Edit Profile
- Database: returns data currently in the database for an individual
- Database: places given information into the database
- Positive Service: passes up data from the dao to the API
- Positive Service: sends validated data to the data access layer
- Negative Service: throws error if first name is too long
- Negative Service: throws error if last name is too long
- Negative Service: throws error if phone number is too long/big
- Negative Service: throws error if email is too long
- Negative Service: throws error if work history is too long
- Negative Service: throws error if references is too long
- Negative Service: throws error if education is too long
- Positive API: passes currently held data up to the front end
- Positive API: sends inputted data to the service layer
- Positive API: pass success message to the front end
- Negative API: pass up information of error to the front end
- Positive Front End: displays information that was already in the database
- Positive Front End: takes edited inputs and sends to the API
- Negative Front End: show error alert from API

### Companies - Login
- Database: check if username+password combination exists and return id of the account
- Positive Service: pass up the returned id to the API
- Negative Service: throw an error/pass failure message to API
- Positive API: pass up the returned id to the front end
- Negative API: pass up information from error to the front end
- Positive Front End: allow user to login
- Negative Front End: show error alert

### Companies - Logout
- Positive Front End: removes cached data from the browser and redirect to login page

### Companies - Create Account
- Database: check if insert account information into both the username/password table AND the applicant table and return a rowcount for both
- Positive Service: pass success message on correct rowcount to API
- Negative service: throw an error/pass failure message on incorrect rowcount to API
- Positive API: pass success message to the front end
- Negative API: pass up information of error to the front end
- Positive Front End: redirect to main page
- Negative Front End: show error alert

### Companies - Edit Profile
- Database: returns data currently in the database for an individual
- Database: places given information into the database
- Positive Service: passes up data from the dao to the API
- Positive Service: sends validated data to the data access layer
- Negative Service: throws error if company name is too long
- Negative Service: throws error if phone number is too long/big
- Negative Service: throws error if email is too long
- Negative Service: throws error if the about us is too long
- Positive API: passes currently held data up to the front end
- Positive API: sends inputted data to the service layer
- Positive API: pass success message to the front end
- Negative API: pass up information of error to the front end
- Positive Front End: displays information that was already in the database
- Positive Front End: takes edited inputs and sends to the API
- Positive Front End: shows positive alert from API
- Negative Front End: show error alert from API

### Companies - Post Jobs
- Database: places given information into the database
- Positive Service: passes validated data down to the database
- Negative Service: throws error if job title is too long
- Negative Service: throws error if job description is too long
- Negative Service: throws error if relevant skills is too long
- Positive API: pass inputted data down to the service layer
- Positive API: pass success message to the front end
- Negative API: pass up information of error to the front end
- Positive Front End: takes inputted data and sends it to the api
- Positive Front End: shows success alert from api
- Negative Front End: show error alert from api

### Companies - View Applicants (shares tests with Delete Job Postings)
- Database: returns ListArray of currently posted jobs
- Database: returns ListArray of current applicants for selected job
- Positive Service Layer: sends data up to the API layer (all jobs)
- Positive Service Layer: sends data up to the API layer (selected job)
- Negative Service: pass the information that there is no data up to API (all jobs)
- Negative Service: pass the information that there is no data up to API (selected job)
- Positive API: sends data to the front end (all jobs)
- Positive API: sends data to the front end (selected jobs)
- Negative API: pass up information that there is no data up to front end (all jobs)
- Negative API: pass up information that there is no data up to front end (selected jobs)
- Positive Front End: shows current job postings
- Positive Front End: shows applicants for selected job
- Negative Front End: shows no data

### Companies - Delete Job Postings (shares tests with View Applicants)
- Database: returns ListArray of currently posted jobs
- Database: deletes selected job from the database and returns a rowcount
- Positive Service Layer: sends data up to the API layer (all jobs)
- Positive Service Layer: checks the rowcount is correct and sends a success message to api
- Negative Service: pass the information that there is no data up to API (all jobs)
- Negative Service: checks the rowcount is incorrect and sends an error message to api
- Positive API: sends data to the front end (all jobs)
- Positive API: sends success message up to front end
- Negative API: sends error message up to api
- Negative API: pass up information that there is no data up to front end (all jobs)
- Negative API: sends error message up to front end
- Positive Front End: shows current job postings
- Positive Front End: shows applicants for selected job
- Negative Front End: shows no data

### Companies - Create Skill Tests
- Database: places given information into the database and returns a rowcount
- Positive Service Layer: sends validated data down to the database
- Positive Service Layer: checks rowcount and returns success message up to API
- Negative Service Layer: throws error if first question is too long
- Negative Service Layer: throws error if first answer is too long
- Negative Service Layer: throws error if second question is too long
- Negative Service Layer: throws error if second answer is too long
- Negative Service Layer: throws error if third question is too long
- Negative Service Layer: throws error if third answer is too long
- Positive API: sends inputted data to the service layer
- Positive API: sends success message up to the front end
- Negative API: sends error message up to the front end
- Positive Front End: sends inputted data down to the API 
- Positive Front End: shows positive alert message then redirects
- Negative Front End: shows negative alert message
