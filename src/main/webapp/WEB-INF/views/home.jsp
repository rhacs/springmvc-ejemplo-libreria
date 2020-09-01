<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
    <jsp:include page="./fragmentos/head.jsp" />

    <!-- Contenido -->
    <body>
        <jsp:include page="./fragmentos/navegacion.jsp" />

        <div class="container my-5">
            <div class="row">
                <!-- Menú Lateral -->
                <div class="col-md-4">
                    <h4 class="border-bottom pb-2 mb-4">Categorías</h4>

                    <!-- Listado de Categorías -->
                    <ul class="list-group list-group-flush">
                    <core:forEach items="${categorias}" var="cat">
                        <li class="list-group-item">${cat.nombre}</li>
                    </core:forEach>
                    </ul>
                </div>
                <!-- /Menú Lateral -->

                <!-- Contenido Principal -->
                <div class="col-md-8">

                </div>
                <!-- /Contenido Principal -->
            </div>
        </div>

        <jsp:include page="./fragmentos/javascripts.jsp" />
    </body>
    <!-- /Contenido -->
</html>
