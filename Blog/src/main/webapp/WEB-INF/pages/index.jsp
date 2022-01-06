<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Locale" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <title>Blog Home - Start Bootstrap Template</title>
    <!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/resources/assets/favicon.ico"/>
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="${pageContext.request.contextPath}/resources/css/styles.css" rel="stylesheet"/>
</head>
<body>
<!-- Responsive navbar-->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="/">Start Bootstrap</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span
                class="navbar-toggler-icon"></span></button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                <c:if test="${admin!=null}">
                    <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/add">Add</a></li>
                    <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/admin">Admin</a>
                    </li>
                </c:if>
                <c:if test="${admin==null}">
                    <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/admin">SignIn</a>
                    </li>
                </c:if>
                <li class="nav-item"><a class="nav-link" href="/">Home</a></li>
                <li class="nav-item"><a class="nav-link" href="#!">About</a></li>
                <li class="nav-item"><a class="nav-link" href="#!">Contact</a></li>
                <li class="nav-item"><a class="nav-link active" aria-current="page" href="/post">Blog</a></li>
            </ul>
        </div>
    </div>
</nav>
<!-- Page header with logo and tagline-->
<header class="py-5 bg-light border-bottom mb-4">
    <div class="container">
        <div class="text-center my-5">
            <h1 class="fw-bolder">Welcome to Blog Home!</h1>
            <p class="lead mb-0">A Bootstrap 5 starter layout for your next blog homepage</p>
        </div>
    </div>
</header>
<!-- Page content-->
<div class="container">
    <div class="row">
        <!-- Blog entries-->
        <div class="col-lg-8">
            <!-- Featured blog post-->
            <%--@elvariable id="posts" type="ArrayList<org.itstep.domain.Post>"--%>
            <c:if test="${!posts.isEmpty()}">
                <div class="card mb-4">
                    <a href="#!"><img class="card-img-top" src="${posts.get(0).fileUrl}" alt="..."/></a>
                    <div class="card-body">
                        <div class="small text-muted">${posts.get(0).getStringDate()}</div>
                        <h2 class="card-title">${posts.get(0).name}</h2>
                        <p class="card-text">${posts.get(0).text.substring(0,150)}</p>
                        <a class="btn btn-primary" href="/post?id=${posts.get(0).id}">Read more →</a>
                    </div>
                </div>
            </c:if>
            <!-- Nested row for non-featured blog posts-->

            <div class="row">
                <div class="col-lg-6">
                    <!-- Blog post-->
                    <c:if test="${posts!=null}">
                        <% int i = 0;%>
                        <c:forEach var="post" items="${posts}">
                            <% i++; %>
                            <% if (i % 2 == 0 && i!=1) { %>
                            <div class="card mb-4">
                                <a href="#!"><img class="card-img-top"
                                                  src="${pageContext.request.contextPath}${post.fileUrl}"
                                                  alt="${post.name}"/></a>
                                <div class="card-body">
                                    <div class="small text-muted">${post.getStringDate()}</div>
                                    <h2 class="card-title h4">${post.name}</h2>
                                    <c:if test="${post.text.length()>150}">
                                        <p class="card-text">${post.text.substring(0,150)}...</p>
                                    </c:if>
                                    <c:if test="${post.text.length()<=150}">
                                        <p class="card-text">${post.text}</p>
                                    </c:if>
                                    <a class="btn btn-primary" href="/post?id=${post.id}">Read more →</a>
                                </div>
                            </div>

                            <%}%>
                            <!-- Blog post-->
                        </c:forEach>
                    </c:if>
                </div>
                <div class="col-lg-6">
                    <!-- Blog post-->
                    <c:if test="${posts!=null}">
                        <% int i = 0;%>
                        <c:forEach var="post" items="${posts}">
                            <% i++; %>
                            <% if (i % 2 != 0 && i!=1) { %>
                            <div class="card mb-4">
                                <a href="#!"><img class="card-img-top"
                                                  src="${pageContext.request.contextPath}${post.fileUrl}"
                                                  alt="${post.name}"/></a>
                                <div class="card-body">
                                    <div class="small text-muted">${post.getStringDate()}</div>
                                    <h2 class="card-title h4">${post.name}</h2>
                                    <c:if test="${post.text.length()>150}">
                                        <p class="card-text">${post.text.substring(0,150)}...</p>
                                    </c:if>
                                    <c:if test="${post.text.length()<=150}">
                                        <p class="card-text">${post.text}</p>
                                    </c:if>
                                    <a class="btn btn-primary" href="/post?id=${post.id}">Read more →</a>
                                </div>
                            </div>

                            <%}%>
                            <!-- Blog post-->
                        </c:forEach>
                    </c:if>
                </div>
            </div>

            <!-- Pagination-->
            <nav aria-label="Pagination">
                <hr class="my-0"/>
                <ul class="pagination justify-content-center my-4">
                    <li class="page-item disabled"><a class="page-link" href="#" tabindex="-1" aria-disabled="true">Newer</a>
                    </li>
                    <li class="page-item active" aria-current="page"><a class="page-link" href="#!">1</a></li>
                    <li class="page-item"><a class="page-link" href="#!">2</a></li>
                    <li class="page-item"><a class="page-link" href="#!">3</a></li>
                    <li class="page-item disabled"><a class="page-link" href="#!">...</a></li>
                    <li class="page-item"><a class="page-link" href="#!">15</a></li>
                    <li class="page-item"><a class="page-link" href="#!">Older</a></li>
                </ul>
            </nav>
        </div>
        <!-- Side widgets-->
        <div class="col-lg-4">
            <!-- Search widget-->
            <div class="card mb-4">
                <div class="card-header">Search</div>
                <div class="card-body">
                    <div class="input-group">
                        <input class="form-control" type="text" placeholder="Enter search term..."
                               aria-label="Enter search term..." aria-describedby="button-search"/>
                        <button class="btn btn-primary" id="button-search" type="button">Go!</button>
                    </div>
                </div>
            </div>
            <!-- Categories widget-->
            <div class="card mb-4">
                <div class="card-header">Categories</div>
                <div class="card-body">
                    <div class="row">
                        <div class="col-sm-6">
                            <ul class="list-unstyled mb-0">
                                <li><a href="#!">Web Design</a></li>
                                <li><a href="#!">HTML</a></li>
                                <li><a href="#!">Freebies</a></li>
                            </ul>
                        </div>
                        <div class="col-sm-6">
                            <ul class="list-unstyled mb-0">
                                <li><a href="#!">JavaScript</a></li>
                                <li><a href="#!">CSS</a></li>
                                <li><a href="#!">Tutorials</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Side widget-->
            <div class="card mb-4">
                <div class="card-header">Side Widget</div>
                <div class="card-body">You can put anything you want inside of these side widgets. They are easy to use,
                    and feature the Bootstrap 5 card component!
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Footer-->
<footer class="py-5 bg-dark">
    <div class="container"><p class="m-0 text-center text-white">Copyright &copy; Your Website 2021</p></div>
</footer>
<!-- Bootstrap core JS-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- Core theme JS-->
<script src="${pageContext.request.contextPath}/resources/js/scripts.js"></script>

</body>
</html>
