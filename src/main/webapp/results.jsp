<%@include file="taglib.jsp"%>
<c:set var="title" value="Search Results" />
<%@include file="head.jsp"%>


<script type="text/javascript" class="init">
    $(document).ready( function () {
        $('#bookTable').DataTable();
    } );
</script>


<style type="text/css">

    table.table td a.add {
        color: #27C46B;
    }
    table.table td a.edit {
        color: #FFC107;
    }
    table.table td a.delete {
        color: #E34724;
    }
    table.table td i {
        font-size: 19px;
    }

</style>

<html>
<body>


<div class="container-fluid">

    <div class="col-sm-8"><h2>Books</h2></div>
    <table id="bookTable" class="table table-bordered" cellspacing="0" width="100%">
        <thead>
        <th>Title</th>
        <th>Publisher</th>
        <th>Years Available</th>
        <th>Action</th>
        </thead>
        <tbody>
        <c:forEach var="book" items="${books}">
            <tr>
                <td>${book.title}</td>
                <td>${book.publisher}</td>
                <td>${book.age}</td>
                <td><a class='add' title="Add" data-toggle="tooltip" href="editBook.jsp"><i class="material-icons" >&#xe146;</i></a>
                    <a class="edit" title="Edit" data-toggle="tooltip" href="editBook?id=${book.id}"><i class="material-icons" >&#xE254;</i></a>
                    <a class="delete" title="Delete" data-toggle="tooltip" href="deleteBook?id=${book.id}"><i class="material-icons" >&#xE872;</i></a></td>
            </tr>

        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
