<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<body>
	<div class="container">
		<form method="post">

			<font color="red">${errorMessage}</font> Name: <input type="text"
				name="name" /> Password: <input type="password" name="password" />

			<input type="submit" />

		</form>
	</div>

	<%@ include file="common/footer.jspf"%>