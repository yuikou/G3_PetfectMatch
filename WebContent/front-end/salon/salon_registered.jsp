<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="en">

<head>
    <title>Groomer Login</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <link rel="stylesheet" type="text/css" href="css/groomerForm.css">
    <link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="css/animate.css">
    <link rel="stylesheet" type="text/css" href="css/animsition.min.css">
    <link rel="stylesheet" type="text/css" href="css/util.css">
    <link rel="Shortcut Icon" type="image/x-icon" href="photo/favicon.ico">
</head>

<body>
    <div class="limiter">
        <div class="container-login100 cover">
            <div class="wrap-login100 mybody">
                <form class="login100-form validate-form p-l-55 p-r-55 p-t-90">
                    <span class="login100-form-title">
                        Meow ♥
                    </span>
                    <div class="wrap-input100 validate-input m-b-16" data-validate="請輸入帳號">
                        <input class="input100" type="text" name="salAc" placeholder="帳號">
                        <span class="focus-input100"></span>
                    </div>
                    <!-- 密碼方便測試先用明碼  -->
                    <div class="wrap-input100 validate-input" data-validate="請輸入密碼">
                        <input class="input100" type="text" name="salPw" placeholder="密碼">
                        <span class="focus-input100"></span>
                    </div>
                    <div class="wrap-input100 validate-input" data-validate="請輸入一樣的密碼">
                        <input class="input100" type="text" name="salPw2" placeholder="密碼確認">
                        <span class="focus-input100"></span>
                    </div>
                    <!--  -->
                    <div class="wrap-input100 validate-input" data-validate="請輸入店名">
                        <input class="input100" type="text" name="salName" placeholder="美容店店名">
                        <span class="focus-input100"></span>
                    </div>
                    <div class="wrap-input100">
                        <input class="input100" type="text" name="salOwner" placeholder="負責人姓名">
                        <span class="focus-input100"></span>
                    </div>
                    <div class="wrap-input100 validate-input" data-validate="請輸入電話">
                        <input class="input100" type="tel" name="salPh" placeholder="連絡電話">
                        <span class="focus-input100"></span>
                    </div>
                    <div class="wrap-input100 validate-input" data-validate="請輸入信箱">
                        <input class="input100" type="email" name="salMail" placeholder="信箱">
                        <span class="focus-input100"></span>
                    </div>
                    <div class="wrap-input100 validate-input" data-validate="請輸入地址">
                        <input class="input100" type="text" name="salAdr" placeholder="地址">
                        <span class="focus-input100"></span>
                    </div>
                    <div class="wrap-input100 validate-input" data-validate="請輸入銀行代碼">
                        <input class="input100" type="text" name="bankCode" placeholder="銀行代碼">
                        <span class="focus-input100"></span>
                    </div>
                    <div class="wrap-input100 validate-input" data-validate="請輸入匯款帳號">
                        <input class="input100" type="text" name="salRemit" placeholder="匯款帳號">
                        <span class="focus-input100"></span>
                    </div>
                    <div class="wrap-input100 validate-input" data-validate="請輸入簡介">
                        <textarea class="input100 txt1" name="salinfo" id="salinfo" maxlength="200" placeholder="美容店簡介"></textarea>
                        <span class="focus-input100"></span>
                    </div>
                    <div class="wrap-input100 validate-input" data-validate="請上傳營業執照">
                        <label class="input100">
                            <p class="txt1 myP">請點擊此處上傳營業執照</p>
                            <input id="myFile" type="file" style="display:none" name="salCertif" multiple>
                        </label>
                    </div>
                    <div class="wrap-input100 validate-input input100 myP myBox" id="preview">
                        圖片檔預覽<button class="login100-form-btn" id="remove">刪除</button>
                    </div>
                    <div class="p-t-13 p-b-23" id="myTerm">
                        <span class="txt1">
                            註冊即同意PETfect Match的
                        </span>
                        <a href="#" class="txt2">
                            服務條款
                        </a>
                        <span class="txt1">
                            &
                        </span>
                        <a href="#" class="txt2">
                            隱私權條款
                        </a>
                    </div>
                    <div class="container-login100-form-btn p-b-40">
                        <button class="login100-form-btn" id="send">
                            Submit
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="js/animsition.min.js"></script>
    <script src="js/popper.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/main.js"></script>
    <script src="js/check.js"></script>
    <script src="js/picUp.js"></script>
    <script type="text/javascript">
        window.onload = init;
    </script>
</body>

</html>