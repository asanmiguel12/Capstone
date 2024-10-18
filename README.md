# Capstone Project 

## Accounting Ledger Application
##### Creates and organizes a functional ledger that can be updated in real time
##### Can filter transaction searches by type, date, and by vendor
##### All main methods incorporate a try-catch in order to process or loop user back to Home Screen in case of invalid inputs

### Home Screen
![HomeScreen](Images%20/HomeScreen.png)
##### Switch-Case method used to retrieve user input 
##### .toUpperCase ignores invalid inputs in terms of case as long as character is correct
##### Loop triggers in case of errors or out of bounds inputs and refreshes ui so user can try again

### User Login (Favorite Feature)
![UserLogin](Images%20/UserLogin.png)
##### Function to get user's name
##### Logs time and date of login
##### Provides a smoother timing for user navigation
##### Can be defined further to access specified ledgers according to name

### Deposit & Payment
![DepositInput](Images%20/DepositInput.png) ![Deposit](Images%20/DepositOutput.png)
##### Returns user input via scanner method
##### Outputs as formatted new transaction in user ledger
##### Similar functions utilized for both deposits and payments tailored to their respective type of transaction

### Ledger Reports Menu
![ReportsMenu](Images%20/ReportsMenu.png)
##### Switch-Case method utilized to receive user-input and output the method of corresponding command
##### Try and catch method for user inputs that do not correspond
##### Return-Home method implemented to loop user back to home or exit application in case of invalid input

### Month To Date Ledger Report
![MTDReportsCode](Images%20/Screenshot%202024-10-16%20at%209.19.49%E2%80%AFAM.png)
##### Method reads provided ledger or transaction file
##### Sorts deposits and payments by current month 
##### Utilizes Thread.sleep function to control UI pacing and make user experience more comprehensive
##### Return-Home method implemented to loop user back to home or exit application in case of invalid input

### Year To Date Ledger Report
![YTDReportsCode](Images%20/Screenshot%202024-10-16%20at%209.21.07%E2%80%AFAM.png)
##### Similar method to mtd report 
##### Sorts a filters transactions according to current year
##### Return-Home method implemented to loop user back to home or exit application in case of invalid input

### Custom Search 
![CustomSearch](Images%20/CustomSearch.png) ![CustomSearchOutput](Images%20/CustomSearchOutput.png)
##### IF method used to filter user input 
##### Determines type of search according to input 
##### Finds transactions that best matches input by utilizing array and split functions nested in while loop 

### Notes, Afterthoughts, & Reflection 
#### Creating more classes to split methods into different files instead of compiling all in one 
#### Make more methods for repetitive code i.e FileReader and BufferedReader 
#### Instantiating a new scanner for all methods that include it and closing it in each, for runner memory efficiency
#### Implementing more types of methods and classes such as ArrayLists, Hashmaps, For Loops 



