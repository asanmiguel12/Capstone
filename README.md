# Capstone Project 

## Accounting Ledger Application
Creates and organizes a functional ledger that can be updated in real time
Can filter transaction searches by type, date, and by vendor

### Month To Date Ledger Report
![MTDReportsCode](Images%20/Screenshot%202024-10-16%20at%209.19.49%E2%80%AFAM.png)
Method reads provided ledger or transaction file
Sorts deposits and payments by current month 
Utilizes Thread.sleep function to control UI pacing and make user experience more comprehensive
Return-Home method implemented to loop user back to home or exit application in case of invalid input

### Year To Date Ledger Report
![YTDReportsCode](Images%20/Screenshot%202024-10-16%20at%209.21.07%E2%80%AFAM.png)
Similar method to mtd report 
Sorts a filters transactions according to current year
Return-Home method implemented to loop user back to home or exit application in case of invalid input

### Ledger Reports Menu
![ReportsMenu](Images%20/Screenshot%202024-10-17%20at%206.15.23%E2%80%AFPM.png)
Switch-Case method utilized to receive user-input and output the method of corresponding command
Try and catch method for user inputs that do not correspond
Return-Home method implemented to loop user back to home or exit application in case of invalid input

### User Login
![UserLogin](Images%20/UserLogin.png) 
Function to get user's name 
Logs time and date of login
Provides a smoother timing for user navigation





