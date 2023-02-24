<%@include file="taglib.jsp"%>
<c:set var="title" value="Add/Edit Book" />
<%@include file="head.jsp"%>


<html>

<body>

<p><p></p>
<div class="container-fluid">
   <h2>Add/Edit Book</h2><br/>
    <form id="editBookForm" role="form" data-toggle="validator"
          class="form-horizontal"
          action="editBook"
          method="POST">

        <input type="hidden" id="id"
               name="id"
               value = ${book.id}>

        <div class="form-group">
            <label class="control-label col-sm-2" for="title">Title</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="title"
                       name="title"
                       value = "${book.title}"
                       data-error="Please enter the book's title." required>
            </div>
            <div class="help-block with-errors"></div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="author">Author</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="author"
                       name="author"
                       value = "${book.author}"
                       data-error="Please enter the book's author."
                       required>
            </div>
            <div class="help-block with-errors"></div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="publisher">Publisher</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="publisher"
                       name="publisher"
                       value = "${book.publisher}"
                       data-error="Please enter the publisher."
                       required>
            </div>
            <div class="help-block with-errors"></div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="publishDate">Publish Date</label>
            <div class="col-sm-4">
                <div class="input-group date" data-provide="datepicker">
                    <input type="text" class="form-control" id="publishDate" name="publishDate"
                           value = "${book.publishDateForDisplay}" data-error="Please enter the book's publish date"
                           required>
                    <div class="input-group-addon">
                        <span class="glyphicon glyphicon-th"></span>
                    </div>
                </div>
                <div class="help-block with-errors"></div>
            </div>
        </div>



        <button type="submit" class="btn btn-default col-sm-offset-3"
                data-disable="true">Add
        </button>
        <button type="reset" class="btn btn-default">Clear</button>
        <a href ="searchBook?searchTerm=&submit=viewAll"><button type="button" class="btn btn-default">Cancel</button></a>
    </form>
</div>
</body>
</html>
