<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">

<jsp:include page="WEB-INF/head.jsp">
    <jsp:param name="pageHeader" value="Product list"/>
</jsp:include>

<body>

<jsp:include page="WEB-INF/header.jsp"/>

<div class="container">
    <div class="row py-2">
        <div class="col-12">
            <c:url value="/create" var="productCreateUrl"/>
            <a class="btn btn-primary" href="${productCreateUrl}">Add Product</a>
        </div>

        <div class="col-12">
            <table class="table table-bordered my-2">
                <thead>
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">Name</th>
                    <th scope="col">Description</th>
                    <th scope="col">Price</th>
                    <th scope="col">Actions</th>
                </tr>
                </thead>
                <tbody>

                <%-- for (Product product : (List<Product>) request.getAttribute("products")) { --%>
                <c:forEach var="product" items="${requestScope.products}">
                <tr>
                    <th scope="row"><c:out value="${product.id}"/></th>
                    <td><c:out value="${product.name}"/></td>
                    <td><c:out value="${product.description}"/></td>
                    <td><c:out value="${product.price}"/></td>
                    <td>
                        <c:url value="/edit" var="productEditUrl">
                            <c:param name="id" value="${product.id}"/>
                        </c:url>
                        <a class="btn btn-success" href="${productEditUrl}"><i class="fas fa-edit"></i></a>
                        <c:url value="/delete" var="productDeleteUrl">
                            <c:param name="id" value="${product.id}"/>
                        </c:url>
                        <a class="btn btn-danger" href="${productDeleteUrl}"><i class="far fa-trash-alt"></i></a>
                    </td>
                </tr>
                </c:forEach>
                <%-- } --%>
                </tbody>
            </table>
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
</html>