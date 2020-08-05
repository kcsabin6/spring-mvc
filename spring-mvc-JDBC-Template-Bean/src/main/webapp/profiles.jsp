<%@ taglib prefix = "cc"  uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
 <title>Profles !!!!!!!!!!!</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
  
  <style>
.zoom {
  transition: transform .9s; /* Animation */
  margin: 0 auto;
}
.zoom:hover {
  transform: scale(2.0); /* (200% zoom - Note: if the zoom is too large, it will go outside of the viewport) */
}
</style>
</head>
<body>
   <header  style="height: 30px;background-color: #21c9ff;">
  
   </header>
    <div  class="container">
         <!-- sessionScope  is called implicit EL object -->
         <img src="img/student.png"  style="height: 139px">
                
                <button type="button" class="btn btn-primary">Manage Users</button>
   				  <button type="reset" class="btn btn-danger">Email</button>
   				            <a href="profiles">
   				  	        <button type="button" class="btn btn-success">Profiles</button>
   				  	        </a>
   				  	        <a href="LoggedUser">  <button type="button" class="btn btn-warning">Logged-in Users</button></a>
   				  	  	  <a href="logout"> 
   				  	        <button type="button" class="btn btn-warning">Logout</button>
   				  	 </a>
   				  	 <hr/>
   				  	 <form action="searchProfile">
   				  	 <input type="text" name="search" placeholder="Search..." class="form-control" style="width:40%; align:right;"/>
   				  	 <button type="submit" class="btn-primary">Go</button>
   				  	<a href="profiles"> <button type="button" class="btn-primary">Clear</button></a>
   				  	 </form>
   				  	 <hr/>
   			<form action="filterProfile">  	 
   	<select name="filterText" class="form-control" style="width:70%; display:inline;">
   	<option>Select</option>
   	<cc:forEach items="${listoptions}" var="toption">
   	<option>${toption}</option>
   	</cc:forEach>
   	</select>	
   	<button type="submit" class="btn btn-primary" style="margin-top: -4px;">Go</button>	
    	</form>		  	 
         <hr/>
        <table class="table table-bordered" >
    <thead>
      <tr>
        <th>Username</th>
        <th>Gender</th>
         <th>Name</th>
        <th>Email
        <a href="sortProfile?sort=asc"><img src="img/down.png" style="height:40px;"></a>
        <a href="sortProfile?sort=desc"><img src="img/up.png" style="height:40px;"></a>
        </th>
        <th>Qualification</th>
        <th>Mobile</th>
        <th>Photo</th>
        <th>Action</th>
      </tr>
    </thead>
    <tbody>
    
    <cc:forEach  items="${profileDTOs}"  var="profileDTO">
      <tr>
        <td>${profileDTO.username}</td>
        <td>${profileDTO.gender}</td>
        <td>${profileDTO.name}</td>
        <td>${profileDTO.email}</td>
        <td>${profileDTO.qualification}</td>
        <td>${profileDTO.mobile}</td>
        <td>
         <img src="${profileDTO.photo}"  style="height: 120px;"  class="zoom"/>
        </td>
        <td >
            <a href="editProfile?username=${profileDTO.username}">
            <button type="button" class="btn btn-primary">E</button>
            </a>
               <a href="deleteProfile?username=${profileDTO.username}">
           <button type="button" class="btn btn-danger">D</button>
            </a>
        </td>
      </tr>
      </cc:forEach>
    </tbody>
  </table>
    </div>
   
  
</body>
</html>