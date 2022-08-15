# Employee_Leave_Mangement

#SighnUp: POST
http://localhost:8080/user/signup

#Login
http://localhost:8080/user/login

#Change Password
http://localhost:8080/user/changePassword

#Admin <br>
create leave type: POST
http://localhost:8080/leaveType/add

Allocate Yearly Leave For Each Type: POST
http://localhost:8080/yearlyLeave/allocateYearlyLeave

#Manager <br>
Show list of approval pending leave: GET
http://localhost:8080/leaveApplication/get

Approve/reject leave application: POST
http://localhost:8080/leaveApplication/update

Have option to put remark: POST
http://localhost:8080/leaveApplication/create

#User <br>
create leave application: POST
http://localhost:8080/leaveApplication/create

send leave application for approval: POST
http://localhost:8080/leaveApplication/update

show all leave with status: GET
http://localhost:8080/leaveApplication/get
 
