<%@include file="head.jsp"%>
<html>
<body>
<div class="container-fluid">
<h2>Book Display</h2>
<form action="searchBook" class="form-inline">
    <div class="form-group">
        <label for="searchTerm">Search</label>
        <input type="text" class="form-control" id="searchTerm" name="searchTerm" aria-describedby="searchTermHelp" placeholder="Enter author">
    </div>
    <button type="submit" name="submit" value="search" class="btn btn-primary">Search</button>
    <button type="submit" name="submit" value="viewAll" class="btn btn-primary">View all books</button>
</form>
</div>
</body>
</html>