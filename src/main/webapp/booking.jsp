<%
    session = request.getSession();

    String urole = (String) session.getAttribute("LoggedInRole");

    if ("user".equals(urole) || "admin".equals(urole)) {

    } else {
        out.println("<script type=\"text/javascript\">");
        out.println("alert('Please, if you want to book a hotel, you must at least be logged in!');");
        out.println("location='./auth/login.jsp';");
        out.println("</script>");
    }

%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link href="https://cdn.jsdelivr.net/npm/remixicon@3.4.0/fonts/remixicon.css" rel="stylesheet" />
    <link rel="stylesheet" href="./css/booking_style.css" />
    <title>SDU Hotel</title>
</head>

<body>
    <nav>
        <ul class="nav__links">
            <li class="link"><a href="#">Home</a></li>
            <li class="link"><a href="#">Book</a></li>
            <li class="link"><a href="#">Contacts</a></li>
        </ul>
        <div class="nav__logo">SDU Hotel</div>
        <ul class="nav__links">
            <li class="link"><a href="#">Login</a></li>
            <li class="link"><a href="#">Registration</a></li>
        </ul>
    </nav>

    <header class="section__container header__container">
        <div class="header__image__container">
            <div class="header__content">
                <h1>Booking</h1>
            </div>
        </div>
    </header>

    <section class="section__container popular__container">
        <div class="booking__container">
            <form>
                <div class="form__group">
                    <div class="input__group">
                        <input type="text" />
                        <label>Location</label>
                    </div>
                    <p>Where are you going?</p>
                </div>
                <div class="form__group">
                    <div class="input__group">
                        <input type="text" />
                        <label>Check In</label>
                    </div>
                    <p>Add date</p>
                </div>
                <div class="form__group">
                    <div class="input__group">
                        <input type="text" />
                        <label>Check Out</label>
                    </div>
                    <p>Add date</p>
                </div>
                <div class="form__group">
                    <div class="input__group">
                        <input type="text" />
                        <label>Guests</label>
                    </div>
                    <p>Add guests</p>
                </div>
            </form>
            <button class="btn"><i class="ri-search-line"></i></button>
        </div>
        <div class="popular__grid">
            <div class="popular__card">
                <img src="assets/hotel-1.jpg" alt="popular hotel" />
                <div class="popular__content">
                    <div class="popular__card__header">
                        <h4>The Plaza Hotel</h4>
                        <h4>$499</h4>
                    </div>
                    <p>New York City, USA</p>
                </div>
            </div>
            <div class="popular__card">
                <img src="assets/hotel-2.jpg" alt="popular hotel" />
                <div class="popular__content">
                    <div class="popular__card__header">
                        <h4>Ritz Paris</h4>
                        <h4>$549</h4>
                    </div>
                    <p>Paris, France</p>
                </div>
            </div>
            <div class="popular__card">
                <img src="assets/hotel-3.jpg" alt="popular hotel" />
                <div class="popular__content">
                    <div class="popular__card__header">
                        <h4>The Peninsula</h4>
                        <h4>$599</h4>
                    </div>
                    <p>Hong Kong</p>
                </div>
            </div>
            <div class="popular__card">
                <img src="assets/hotel-4.jpg" alt="popular hotel" />
                <div class="popular__content">
                    <div class="popular__card__header">
                        <h4>Atlantis The Palm</h4>
                        <h4>$449</h4>
                    </div>
                    <p>Dubai, United Arab Emirates</p>
                </div>
            </div>
            <div class="popular__card">
                <img src="assets/hotel-5.jpg" alt="popular hotel" />
                <div class="popular__content">
                    <div class="popular__card__header">
                        <h4>The Ritz-Carlton</h4>
                        <h4>$649</h4>
                    </div>
                    <p>Tokyo, Japan</p>
                </div>
            </div>
            <div class="popular__card">
                <img src="assets/hotel-6.jpg" alt="popular hotel" />
                <div class="popular__content">
                    <div class="popular__card__header">
                        <h4>Marina Bay Sands</h4>
                        <h4>$549</h4>
                    </div>
                    <p>Singapore</p>
                </div>
            </div>
        </div>
    </section>

    <footer class="footer">
        <div class="section__container footer__container">
            <div class="footer__col">
                <h3>SDU Hotel</h3>
                <p>
                    SDU Hotel is a premier hotel booking website that offers a seamless and
                    convenient way to find and book accommodations worldwide.
                </p>
                <p>
                    With a user-friendly interface and a vast selection of hotels,
                    SDU Hotel aims to provide a stress-free experience for travelers
                    seeking the perfect stay.
                </p>
            </div>
            <div class="footer__col">
                <h4>Company</h4>
                <p>About Us</p>
                <p>Our Team</p>
                <p>Blog</p>
                <p>Book</p>
                <p>Contact Us</p>
            </div>
            <div class="footer__col">
                <h4>Legal</h4>
                <p>FAQs</p>
                <p>Terms & Conditions</p>
                <p>Privacy Policy</p>
            </div>
            <div class="footer__col">
                <h4>Resources</h4>
                <p>Social Media</p>
                <p>Help Center</p>
                <p>Partnerships</p>
            </div>
        </div>
    </footer>
</body>

</html>