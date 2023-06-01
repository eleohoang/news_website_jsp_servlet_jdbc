<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var ="DetailUrl" value="/new-detail"/>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Trang chủ</title>
</head>

<body>
	<div class="row">

		<div class="col-lg-3">
			<h3 class="my-4">Chuyên mục</h3>
			<div class="list-group">
				<c:forEach var="category" items="${model.listResult}">
					<a href="#" class="list-group-item">${category.name}</a>
				</c:forEach>
			</div>

		</div>
		<!-- /.col-lg-3 -->

		<div class="col-lg-9">
			<div id="carouselExampleIndicators" class="carousel slide my-4" data-ride="carousel">
				<ol class="carousel-indicators">
					<li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
					<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
					<li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
				</ol>
				<div class="carousel-inner" role="listbox">
					<c:set var="count" value="${1}" />
					<c:forEach var="newsmodelcarousel" items="${carousel.listResult}">

						<c:if test="${count != 1}">
							<c:set var="active" value="" />
						</c:if>
						<c:if test="${count == 1}">
							<c:set var="active" value="active" />
							<c:set var="count" value="${0}" />
						</c:if>
						<div class="carousel-item ${active}">
							<img class="d-block img-fluid" src="http://placehold.it/900x350">
							<div class="carousel-caption">
								<h4>${newsmodelcarousel.title}</h4>
								<p>${newsmodelcarousel.shortDescription}</p>
							</div>
						</div>
					</c:forEach>
				</div>
				<a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
					<span class="carousel-control-prev-icon" aria-hidden="true"></span> <span
						class="sr-only">Previous</span>
				</a> <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
					<span class="carousel-control-next-icon" aria-hidden="true"></span> <span
						class="sr-only">Next</span>
				</a>
			</div>

			<div class="row">
				<c:forEach var="newsmodel" items="${newsmodel.listResult}">
					<div class="col-lg-4 col-md-6 mb-4">
					<div class="card h-100">
						<!-- <a href="#"><img class="card-img-top"
							src="http://placehold.it/700x400" alt=""></a> -->
						<div class="card-body">
								<c:url var="detailURL" value="/new-detail">
									<c:param name="type" value="get" />
									<c:param name="id" value="${newsmodel.id}" />
								</c:url>
								<h4 class="card-title">
								<a href="${detailURL}">${newsmodel.title}</a>
							</h4>
							<p class="new-detail-${newsmodel.id}">
								<a href="${detailURL}" style ="text-decoration: none; color: inherit">
									${newsmodel.shortDescription}
								</a>
							</p>
							</div>
						<div class="card-footer">
							<small class="text-muted">&#9733; &#9733; &#9733; &#9733;
								&#9734;</small>
						</div>
					</div>
				</div>
			</c:forEach>
			</div>
			<!-- /.row -->

		</div>
		<!-- /.col-lg-9 -->

	</div>
	<!-- /.row -->
</body>


</html>