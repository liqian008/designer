<%@ page language="java" contentType="text/html; charset=utf-8"%>

<form id="contact-form-widget" method="post" class="clearfix"
	action="/designer-front/uploadAvatar.art" enctype="MULTIPART/FORM-DATA">
	<div class="input-container">
		头 像: <input type="file" class="contact-form-name" name="avatarImage"
			value="头 像"/>
	</div>
	<input class="contact-submit button" type="submit" value="上 传">
</form>
