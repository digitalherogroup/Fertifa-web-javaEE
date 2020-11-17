<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: IT-HOME
  Date: 5/5/2020
  Time: 12:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <c:set var="root" value="${pageContext.request.contextPath}"/>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />

    <link rel="apple-touch-icon" sizes="180x180" href="${root}/img/favicon/apple-touch-icon.png" />
    <link rel="icon" type="image/png" sizes="32x32" href="${root}/img/favicon/favicon-32x32.png" />
    <link rel="icon" type="image/png" sizes="16x16" href="${root}/img/favicon/favicon-16x16.png" />
    <link rel="mask-icon" href="${root}/img/favicon/safari-pinned-tab.svg" color="#5bbad5" />
    <meta name="msapplication-TileColor" content="#da532c" />
    <meta name="theme-color" content="#ffffff" />

    <link rel="stylesheet" href="${root}/css/index.bundle.css" />

    <title>
        Fertifa
    </title>

</head>
<body>

<div class="home-container">
    <div class="home-head">
        <div class="home-head__top">
            <a href="https://fertifa.com/for-individuals" target="_blank" class="home-head__logo"><img src="${root}/img/logo.svg" alt=""/></a>
            <form action="${root}/employee/affiliate/register" method="post">
                <input type="hidden" id="affiliateId" name="affiliateId" value="${requestScope.AffiliateObject.id}">
                <input type="hidden" id="affiliateEmail" name="affiliateEmail" value="${requestScope.AffiliateObject.email}">
                <input type="hidden" id="link" name="link" value="${requestScope.AffiliateObject.secretKey}">
                <button type="submit" class="home-head__register btn custom-rounded-btn">REGISTER</button>
            </form>
        </div>
        <div class="container m-auto">
            <div class="row">
                <div class="col-md-7">
                    <h1 class="home-head__title">
                        We are your Fertility Mentor
                    </h1>
                    <p class="home-head__desc">
                        We live in a world where 1 in 6 people experience fertility issues. At Fertifa, we believe in making fertility treatment accessible and affordable for everyone — and guide you every step of the way.
                    </p>
                </div>
                <div class="col-md-5">
                    <div class="home-head__play" style="flex-direction: column;">
                        <a
                                data-fancybox="bigbuckbunny"
                                data-width="640"
                                data-height="360"
                                href="${root}/img/Fertifa-promo-video.mp4"
                        >
                            <img src="${root}/img/video-play.svg" alt="" />
                        </a>
                        <a href="https://fertifa.com/for-individuals" target="_blank" style="color: #fff">About Fertifa </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="platform-content">
        <div class="container">
            <div class="row">
                <div class="col-md-7">
                    <div class="platform-content__content left">
                        <h2 class="platform-content__title">
                            Welcome to the Fertifa programme
                        </h2>
                        <p>
                            We are a One-Stop Fertility Companion for you throughout your fertility journey.
                            Fertifa are a digital clinic that partners with some of the best treatment centres,
                            fertility innovators and wellbeing professionals across the industry.
                        </p>
                        <p>
                            Patient care is at the heart of what we do, and we pride ourselves on offering a
                            bespoke service for all our patients.
                        </p>
                    </div>
                </div>
                <div class="col-md-5">
                    <div class="join-fertifa-platform">
                        <p class="join-title">
                            Join the Fertifa Platform now
                        </p>
                        <form action="${root}/employee/affiliate/register" method="post">
                            <input type="hidden" id="affiliateId" name="affiliateId" value="${requestScope.AffiliateObject.id}">
                            <input type="hidden" id="affiliateEmail" name="affiliateEmail" value="${requestScope.AffiliateObject.email}">
                            <input type="hidden" id="link" name="link" value="${requestScope.AffiliateObject.secretKey}">
                            <button type="submit" class="btn custom-rounded-btn purple w-100">REGISTER</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="platform-content mt-5 pt-5">
        <div class="container">
            <div class="row">
                <div class="col-md-7">
                    <div class="platform-content__image pr-5">
                        <img src="${root}/img/home-2.jpg" alt="" />
                    </div>
                </div>
                <div class="col-md-5">
                    <div class="platform-content__content pt-5">
                        <p class="mb-4">
                            By signing up, you’ll have access to:
                        </p>
                        <ul class="custom-list">
                            <li>
                                A free consultation with one of our fertility advisors
                            </li>
                            <li>
                                Discounted treatment with our partner clinics
                            </li>
                            <li>
                                Diagnostic home testing kits for men (sperm testing) and women (hormone and ovarian reserve testing)
                            </li>
                            <li>
                                Our messaging platform to get in touch with us 7 days a week for any fertility questions you may have
                            </li>
                            <li>
                                Education across an array of fertility subjects from our medical experts
                            </li>
                            <li>
                                Holistic services through our wellbeing partners — including nutrition, counselling and fertility coaching
                            </li>
                            <li>
                                Impartial advice around the fertility industry and advice on the best clinic for you
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<footer class="home-footer">
    <div class="home-footer__content">
        <div class="home-footer__top">
            <div class="home-footer__top-block">
                <a href=""><img src="${root}/img/logo-gray.svg" alt=""/></a>
            </div>
            <div class="home-footer__top-block">
                <p class="home-footer__top-phone">020 7459 4476</p>
            </div>
            <div class="home-footer__top-block">
                <p class="home-footer__top-mail"><a href="mailto:info@fertifa.com">info@fertifa.com</a></p>
            </div>
            <div class="home-footer__top-block">
                <p class="home-footer__top-location">
                    1 Harley Street, London W1G 6QD
                </p>
            </div>
        </div>
        <div class="home-footer__bottom">
            <div class="home-footer__bottom-block">
                <p>© 2020 All rights reserved</p>
            </div>
            <div class="home-footer__bottom-block">
                <a href="${root}/TermsCondition.jsp">Terms and Conditions</a>
            </div>
            <div class="home-footer__bottom-block">
                <a href="#">Cookies and Privacy Policy</a>
            </div>
            <div class="home-footer__bottom-block">
                <a href="#">Made by Billie A.</a>
            </div>
        </div>
    </div>
</footer>
<script>
    var selfId = 205;
    var roomIdFromFrontUser = 205;
    var sessionRoomFromFront = "jok2f4fud_9";
</script>
<script src="${root}/js/index.bundle.js"></script>
<script src= "https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script>
    setTimeout(submit_me, 5000);
    function submit_me() {
        var affiliateId = '${requestScope.AffiliateObject.id}';
        var affiliateEmail = '${requestScope.AffiliateObject.email}';
        console.log('inside submit_me me');
        jQuery.ajax({
            url: '/five-second-static',
            type: 'POST',
            data: {
                'affiliateId': affiliateId,
                'affiliateEmail': affiliateEmail
            },
            dataType: 'text',
            success: function (data) {
                console.log("success ", data)
            },
            error: function (data) {
                console.log("error", data)
            }
        });
    }
</script>
<script>
    setTimeout(onloadStatic,500);
    function onloadStatic() {
        var affiliateId = '${requestScope.AffiliateObject.id}';
        var affiliateEmail = '${requestScope.AffiliateObject.email}';
        console.log('inside onloadStatic');
        jQuery.ajax({
            url: '/onload-static',
            type: 'POST',
            data: {
                'affiliateId': affiliateId,
                'affiliateEmail': affiliateEmail
            },
            dataType: 'text',
            success: function (data) {
                console.log("success ", data)
            },
            error: function (data) {
                console.log("error", data)
            }
        });
    }
</script>
</body>

</html>
