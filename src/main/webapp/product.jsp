<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">

<jsp:include page="WEB-INF/head.jsp">
    <jsp:param name="pageHeader" value="Product"/>
</jsp:include>

<body>

<jsp:include page="WEB-INF/header.jsp"/>

<div class="container">
    <div class="row py-2">
        <div class="col-12">
            <c:url value="${requestScope.action}" var="productPostUrl"/>
            <form action="${productPostUrl}" method="post">
                <input type="hidden" value="${product.id}" id="id" name="id">
                <div class="form-group">
                    <label>Name</label>
                    <input type="text" value="${product.name}" class="form-control" id="name" name="name" placeholder="Enter name">
                </div>
                <div class="form-group">
                    <label>Description</label>
                    <input type="text" value="${product.description}" class="form-control" id="description" name="description" placeholder="Enter description">
                </div>
                <div class="form-group">
                    <label>Price</label>
                    <input type="number" value="${product.price}" class="form-control" id="price" name="price" placeholder="Enter price">
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>
    </div>
</div>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>

</body>