<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</head>
<body>
    <div class="container">
        <h2>Admin Login</h2>
        <div>${email}</div>
        <c:if test="${errors != null}">
            <c:forEach items="${errors}" var="item">
                <li>${item.getField()} - ${item.getDefaultMessage()}</li>
            </c:forEach>
        </c:if>
        <form method="post" action="/login" class="col-sm-4">
            <div class="mb-3">
                <input required type="email" name="email" placeholder="E-Mail" class="form-control" />
            </div>
            <div class="mb-3">
                <input required type="password" name="password" placeholder="Password" class="form-control" />
            </div>
            <button type="submit" class="btn btn-danger">Send</button>
        </form>
    </div>
</body>
</html>