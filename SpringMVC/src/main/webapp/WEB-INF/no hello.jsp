<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<!--[if IE 9]><html class="lt-ie10" lang="en" > <![endif]-->
<html class="no-js" lang="en">
<head>
<meta charset="utf-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Sample XXE Attack</title>

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/foundation/5.5.3/css/normalize.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/foundation/5.5.3/css/foundation.min.css">
<link
	href='http://cdnjs.cloudflare.com/ajax/libs/foundicons/3.0.0/foundation-icons.css'
	rel='stylesheet' type='text/css'>
<style>
.file-upload {
  position: relative;
  overflow: hidden;
  margin: 10px; }

.file-upload input.file-input {
  position: absolute;
  top: 0;
  right: 0;
  margin: 0;
  padding: 0;
  font-size: 20px;
  cursor: pointer;
  opacity: 0;
  filter: alpha(opacity=0); }
</style>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.3/modernizr.min.js"></script>
</head>
<body>
	<div class="row" align ="center">
		<div class="small-12 columns">
			<ul class="button-group">
				<li><a href="" class="button">Source Code</a></li>
				<li><a href="" class="button">Virtual Machine</a></li>
			</ul>
		</div>
	</div>


	<div class="row">
		<h2 align="center">Sample XXE Attack</h2>
		<form:form method="POST" enctype="multipart/form-data" action="upload">
			<table align="center">

				<tr>
					<td><h4>File to upload:</h4></td>
					<td><h4><input class="file-upload" type="file" name="file"></h4></td>
				</tr>
				<tr>
					<td><input class="radius button right" type="submit" value="Upload"></td>
					<td><h4>Press here to upload the file!</h4></td>
				</tr>

			</table>
		</form:form>
	</div>

	<footer class="row">
		<div class="large-12 columns">
			<hr />
			<div class="row">
				<div class="large-6 columns">
					<p>&copy; Done as part of ACNS Homework</p>
				</div>
			</div>
		</div>
	</footer>
	<script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/foundation/5.5.3/js/foundation.min.js"></script>
	<script>
		$(document).foundation();
	</script>
</body>