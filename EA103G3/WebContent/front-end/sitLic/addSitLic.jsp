<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.sitLic.model.*" %>
<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- �s���������ۮe�� -->
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

<TITLE>�s�W�Ү�</TITLE>

<!-- �פJ�~��CSS -->
<c:set var="path" value="/EA103G3/front-end/sitLic" />

<link rel="stylesheet" type="text/css" href="${path}/css/bootstrap.min.css">   
<link rel="stylesheet" type="text/css" href="${path}/css/Main.css">
<link rel="stylesheet" type="text/css" href="${path}/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="${path}/css/animate.css">
<link rel="stylesheet" type="text/css" href="${path}/css/animsition.min.css">
<link rel="stylesheet" type="text/css" href="${path}/css/util.css">
<link rel="stylesheet" type="text/css" href="${path}/css/Petfect.css">
<link rel="stylesheet" type="text/css" href="${path}/css/pitSitterForm.css">
<link rel="stylesheet" type="text/css" href="${path}/css/sitLicAll.css">
<link rel="Shortcut Icon" type="image/x-icon" href="https://dzmg8959fhe1k.cloudfront.net/all/favicon.ico">

</head>

<BODY>
<!-- �H�U������navigation bar -->
    <nav class="navbar fixed-top navbar-expand-lg navbar-light bg-light myNav">
        <div class="container-fluid">
            <!-- logo (click to home page)-->
            <a class="navbar-brand" href="#" style="padding-top: 0px;padding-bottom: 0px"><img class="myLogo"
                    src="https://dzmg8959fhe1k.cloudfront.net/all/logo.jpg"><img class="myLogoWord"
                    src="https://dzmg8959fhe1k.cloudfront.net/all/logoWord.jpg"></a>
            <!-- logo -->
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText"
                aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation"
                style="margin-right: 15px">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="myNavDiv collapse navbar-collapse" id="navbarText">
                <!-- �W�s���s��U�A�ȷj�M���� -->
                <ul class="navbar-nav ml-auto">
	                <div class="myUndeLine">
	                    <li class="nav-item">
                            <a class="nav-link accordion myFirst" href="#"><span class="ch-word">�d���A��</span><span
                                    class="en-word">Services</span></a>
                            <a class="nav-link morelink" href="#">���i</a>
                            <a class="nav-link morelink" href="#">���e</a>
                            <a class="nav-link morelink" href="#">��i</a>
                    	</li>
        	        </div>
                   	<div class="myUndeLine">
                    	<li class="nav-item">
                            <a class="nav-link accordion myFirst" href="#"><span class="ch-word">�d�ͤ���</span><span
                                    class="en-word">Interaction</span></a>
                            <a class="nav-link morelink" href="#">�Q�װ�</a>
                            <a class="nav-link morelink" href="#">����</a>
                    	</li>
                    </div>
                    <div class="myUndeLine">
                    	<li class="nav-item">
                            <a class="nav-link accordion" href="#"><span class="ch-word">���e�~�̱M��</span><span
                                    class="en-word">Salon store</span></a>
                    	</li>
                    </div>
                </ul>
                <!-- �H�W�W�s���s��U�A�ȷj�M���� -->
                <!-- �n�J���s -->
                <input type="button" class="myBtn" value="sign in"></input>
                <!-- �H�W�n�Jbtn -->
            </div>
        </div>
    </nav>
    <!-- ����navigation bar end-->
    
	
	<!-- ����body -->
    <div class="container">
		
		<!-- ���~�C�� -->
	   	<div class="errorList"> 
			<c:if test="${not empty errorMsgs}" >
				<font style="color:red;">�o�ͥH�U���~�G</font>
				<ul>
				<c:forEach var="msg" items="${errorMsgs}">
					<li style="color:red;">${msg}</li>
				</c:forEach>
				</ul>
			</c:if>
		</div>

	<!-- ���ե���wsitNo=S005 -->
	<% session.setAttribute("sitNo","S001");%>
		<!-- ���ե� -->
		<div class="test">
			<a href="listOneSitAllLic.jsp">�|���d�ݩҦ��Ү�</a>
			<a href="listUnverifiedLic.jsp">���u�d�ݫݼf���Ү�</a>
		</div>
		<!-- ���ե� -->
	
<!-- �s�W�Ү� -->
        <div class="limiter">
	        <div class="container-login100 cover">
	            <div class="wrap-login100 mybody">
	            
				
					<FORM class="login100-form validate-form p-l-55 p-r-55 p-t-90" 
							action="sitLic.do" method="post"
							enctype="multipart/form-data">
						<span class="login100-form-title">
	                        	�s�W�O�i�ҷ�
	                    </span>
	                    
						<!-- ���ե���wsitNo=S005 -->
						<input type="hidden" name="sitNo" value="S005">
						
						<!-- �H�U���o�s�W�ҮѪ��Ѽ� -->
						<div class="licBox">
				           <div class="wrap-input100 validate-input" data-validate="�п�J�ҷӦW��">
				           		<input class="input100" type="text" name="licName" placeholder="�ҷӦW��">
				           		<span class="focus-input100"></span>
				           </div>
				           <div class="info txt1 myP">
                                	�ҷӥ��Ĥ� <span>*</span>
                                <div class="add-note">
                                    <span>�Y�ҷӦ����Ĥ���A�Щ�U�C�������J</span>
                                </div>
                           </div>
				           <div class="wrap-input100">
				           		<input class="input100" type="date" name="licEXP" placeholder="�ҷӨ����" style="margin-top:0">
				           		<span class="focus-input100"></span>
				           </div>
				           <div class="wrap-input100 hideDiv">
				           		<input class="input100" type="text" name="licStatus" placeholder="�ҷӪ��A">
				           		<span>�s�W�ɹw�]���A0-�ݼf��</span>
				           		<span class="focus-input100"></span>
				           </div>
					       <div class="wrap-input100 validate-input"  data-validate="�ФW�ǰ��ӹϤ��B�ɮ�">
					           	<label class="myLabel">
					           		<span class="txt1 myP uploadTXT info">�W���d�������ҷ�</span>
					           		<img src="${path}/img/upload.svg" class="myUpload">
					           		<input class="myFile" type="file" name="licPic" style="display:none">
					           	</label>
					       </div>
					       <div class="wrap-input100 validate-input input100 myP preview" style="margin-top:0;">
					                                �Ϥ��w��<button class="login100-form-btn remove">�R��</button>
					       </div>
					       <div class="container-login100-form-btn p-b-40">
						   		<input type="hidden" name="action" value="add">
						   		<input class="login100-form-btn input100" id="send" type="submit" value="�s�W">
                        	</div>
						</div>
					</FORM>
				</div>
            </div>
        </div>

		<script src="${path}/js/jquery-3.2.1.min.js"></script>
		<script src="${path}/js/animsition.min.js"></script>
		<script src="${path}/js/popper.js"></script>
		<script src="${path}/js/bootstrap.min.js"></script>
		<script src="${path}/js/main.js"></script>
		<script src="${path}/js/checkSitPitter.js"></script>
		<!-- �W�ǹϤ��w�� -->
		<script type="text/javascript">
            function init() {
                var myFile = document.getElementsByClassName('myFile');
                var preview = document.getElementsByClassName('preview');
                var file;
                
                /*���U�ɮפW�Ǩƥ�*/
                for (let i = 0; i < myFile.length; i++) {
                    let index = i;
                    myFile[index].addEventListener('change', function (e) {
                        //���o�ɮת���
                        var file = e.target.files;
                        console.log(e.target.value);
                        console.log(myFile[index].value);
                        
                        //�P�_�ɮ׬O�_�s�b
                        if (file !== null) {
                            for (var i = 0; i < file.length; i++) {
                                //�P�_file.type�����O�O�_�]�t'image', ���O���ܭn�u�Xĵ�i
                                if (file[i].type.indexOf('image') > -1) {
                                    //file����s�b:�bFileReader����W���Uload�ƥ� - ���J�ɮת��N��
                                    var fr = new FileReader();
                                    fr.addEventListener('load', function (e) {
                                        //���o���G
                                        console.log(e.target.result);
    
                                        //�s�Wimg����
                                        var img = document.createElement('img');
                                        //�ᤩ�ݩ�
                                        img.setAttribute('src', e.target.result);
                                        img.classList.add("reviewImg");
    
                                        //�s�W�@��div�]��img
                                        var box = document.createElement('div');
                                        box.setAttribute('class', 'myReview');
                                        box.append(img);
                                        preview[index].append(box);
                                    });
                                    // �ϥ�FileReader����W�� readAsDataURL(file) ����k�A�ǤJ�nŪ�����ɮסA�ö}�l�i��Ū��
                                    fr.readAsDataURL(file[i]);
                                } else {
                                    alert('�ФW�ǹϤ���!');
                                }
                            }
                        }
                        //e.target.value = ''
                        /*
                        	�ѨMinput type=file �P�@���ɮפG���W�ǵL�Ī����D!
                        	�ϥ�input[type=file]���ɮפW�ǥ\��A�O�q�Lonchange�ƥ�Ĳ�ojs�{���X, �p�G�⦸�ɮ׬O���ƪ��A�ҥH�o�Ӯɭ�onchange�ƥ�O�S��Ĳ�o�쪺�C�ѨM��k :  ��input��value�]�w����
                        	!!! �o��Part�N������F
                        */
                    });
                }
                
             	// ���U�R���ƥ�
                $('.remove').on('click', function(e){
                    e.preventDefault();
                    $(this).next(".myReview").remove();
                    myFile[0].value='';
                });

            }
            window.onload = init;
        </script>
    </div>
    <!-- ����end -->
    <!-- �ȪA�p���s -->
    <div class="qaicon">
        <a href="#"><img src="https://dzmg8959fhe1k.cloudfront.net/all/unicorn.svg" /></a>
    </div>
    <!-- footer -->
    <div class="container-fluid main-footer text-center">
        <div class="row myFooterContainer">
            <div class="col-12 col-sm">
                <ul>About us
                    <li><a href="#" class="myFooterLink">�A�ȱ���</a></li>
                    <li><a href="#" class="myFooterLink">���p�v�F��</a></li>
                </ul>
            </div>
            <div class="col-12 col-sm">
                <ul>FAQ
                    <li><a href="#" class="myFooterLink">�`�����D</a></li>
                    <li><a href="#" class="myFooterLink">�N���^�X</a></li>
                </ul>
            </div>
            <div class="col-12 col-sm">
                <ul>Contact us
                    <li><a href="#" class="myFooterLink">�o�e�q�l�l��</a></li>
                    <li><a href="#" class="myFooterLink">�l�ܧڭ�</a></li>
                </ul>
            </div>
        </div>
        <p class="copyright">&copy; copyright by <i>NiHaiZaiMa</i>
        <p>
    </div>
</body>
</html>