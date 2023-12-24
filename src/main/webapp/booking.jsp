<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="com.sdu.edu.kz.booking.Hotel" %>
<%@ page import="java.util.List" %>
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
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
    <style>
        .wf-force-outline-none[tabindex="-1"]:focus {
            outline: none;
        }
    </style>
    <meta charset="utf-8"/>
    <title>Contact</title>
    <meta content="Contact" property="og:title"/>
    <meta content="Contact" property="twitter:title"/>
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <link rel="stylesheet" href="./css/main.css" media="all"/>
    <link rel="stylesheet" href="./css/booking_style.css">

    <script type="text/javascript">
        WebFont.load({
            google: {
                families: [
                    "Montserrat:100,100italic,200,200italic,300,300italic,400,400italic,500,500italic,600,600italic,700,700italic,800,800italic,900,900italic",
                ],
            },
        });
    </script>
    <script type="text/javascript">
        !(function (o, c) {
            var n = c.documentElement,
                t = " w-mod-";
            (n.className += t + "js"),
            ("ontouchstart" in o ||
                (o.DocumentTouch && c instanceof DocumentTouch)) &&
            (n.className += t + "touch");
        })(window, document);
    </script>

    <link
            href="#"
            rel="shortcut icon"
            type="image/x-icon"
    />
    <link
            href="#"
            rel="apple-touch-icon"
    />

    <style>
        body {
            font-family: Arial, sans-serif;
        }

        .modal {
            display: none;
            position: fixed;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            z-index: 1000;
        }

        .modal img {
            width: 100%;
            height: auto;
            margin-bottom: 10px;
        }

        .checkbox-container {
            margin-bottom: 10px;
        }

        .hotel-options-container {
            display: flex;
            justify-content: space-between;
        }

        .hotel-option {
            flex: 0 0 30%; /* Adjust the width of each hotel option as needed */
        }
    </style>
</head>

<body>
<%
    String att = "check";
    if (request.getSession().getAttribute("LoggedInRole") != null) {
        att = (String) request.getSession().getAttribute("LoggedInRole");
%>
<%
    }
    if (att.equals("user")) {
%>
<jsp:include page="user-header.jsp"/>
<%
} else if (att.equals("admin")) {
%>
<jsp:include page="admin-header.jsp"/>
<%
} else {
%>
<jsp:include page="common-header.jsp"/>
<%
    }
%>

<%
    HttpSession session1 = (HttpSession) request.getSession();
    String location = (String) session1.getAttribute("location");
    String checkInDate = (String) session1.getAttribute("checkInDate");
    String checkOutDate = (String) session1.getAttribute("checkOutDate");
%>

<div class="section-6 wf-section"><h1 class="heading">Booking Hotel</h1></div>

<section class="section__container popular__container" style="margin-top: 20px;">
    <div class="form-block-2 w-form">
        <form
                id="wf-form-Booking-Form"
                name="wf-form-Booking-Form"
                data-name="Booking Form"
                method="post"
                class="form-2"
                aria-label="Booking Form"
                action="./addbooking"
        >
            <label for="loction" class="field-label-6">Location</label>
            <input
                    type="text"
                    class="w-input"
                    maxlength="256"
                    name="loction"
                    placeholder=""
                    id="loction"
                    required=""
                    value="Almaty..."
            />
            <label for="adults" class="field-label-10">Adults</label>
            <input
                    type="number"
                    class="w-input"
                    maxlength="16"
                    name="adults"
                    placeholder=""
                    id="adults"
                    required=""
                    value="0"
            />
            <label for="children" class="field-label-9">children</label>
            <input
                    type="number"
                    class="w-input"
                    maxlength="16"
                    name="children"
                    placeholder=""
                    id="children"
                    value="0"
            />
            <label for="cidate" class="field-label-8">Check-in date</label>
            <input
                    type="date"
                    class="w-input"
                    maxlength="256"
                    name="cidate"
                    placeholder=""
                    id="cidate"
                    required=""
            />
            <label for="codate" class="field-label-7">check-out date</label>
            <input
                    type="date"
                    class="w-input"
                    maxlength="256"
                    name="codate"
                    placeholder=""
                    id="codate"
                    required=""
            /><input
                type="submit"
                value="Submit"
                data-wait="Please wait..."
                class="submit-button-2 w-button"
        />
        </form>
        <div
                class="w-form-done"
                tabindex="-1"
                role="region"
                aria-label="Booking Form success"
        >
            <div>Thank you! Your submission has been received!</div>
        </div>
        <div
                class="w-form-fail"
                tabindex="-1"
                role="region"
                aria-label="Booking Form failure"
        >
            <div>Oops! Something went wrong while submitting the form.</div>
        </div>
    </div>

    <h2>Available Hotels</h2>
    <%
        List<Hotel> hotels = (List<Hotel>) request.getAttribute("hotelList");
        if (hotels == null || hotels.isEmpty()) {
            out.println("<p>No hotels available for the selected criteria.</p>");
        } else {
    %>
    <table border="1" style="text-align: center">
        <tr>
            <th>Hotel ID</th>
            <th>Name</th>
            <th>Address</th>
            <th>Phone Number</th>
            <th>Description</th>
            <th>Book</th>
        </tr>
        <%
            for (Hotel hotel : hotels) {
        %>
        <tr>
            <td><%= hotel.getId() %>
            </td>
            <td><%= hotel.getName() %>
            </td>
            <td><%= hotel.getAddress() %>
            </td>
            <td><%= hotel.getPhoneNumber() %>
            </td>
            <td><%= hotel.getDescription() %>
            </td>
            <td>
                <button onclick="openModal()">Open Modal</button>
            </td>
        </tr>
        <%
            }
        %>
    </table>

    <div id="myModal" class="modal">
        <div>
            <h2>Room Options</h2>

            <!-- Hotel Images and Checkboxes -->
            <div class="hotel-options-container">
                <div class="hotel-option">
                    <img src="assets/hotel-1.jpg" alt="Hotel 1">
                    <div class="checkbox-container">
                        <input type="checkbox" id="hotel1Checkbox">
                        <label for="hotel1Checkbox">Standard Room - $100</label>
                    </div>
                </div>

                <div class="hotel-option">
                    <img src="assets/hotel-2.jpg" alt="Hotel 2">
                    <div class="checkbox-container">
                        <input type="checkbox" id="hotel2Checkbox">
                        <label for="hotel2Checkbox">Family Room - $120</label>
                    </div>
                </div>

                <div class="hotel-option">
                    <img src="assets/hotel-3.jpg" alt="Hotel 3">
                    <div class="checkbox-container">
                        <input type="checkbox" id="hotel3Checkbox">
                        <label for="hotel3Checkbox">Luxury Room - $150</label>
                    </div>
                </div>
            </div>

            <!-- Breakfast Options -->
            <h2>Hotel Options</h2>

            <div class="checkbox-container">
                <input type="checkbox" id="breakfastOption1">
                <label for="breakfastOption1">Breakfast - $10</label>
            </div>

            <div class="checkbox-container">
                <input type="checkbox" id="breakfastOption2">
                <label for="breakfastOption3">Excursion - $12</label>
            </div>

            <div class="checkbox-container">
                <input type="checkbox" id="breakfastOption3">
                <label for="breakfastOption2">Add room - $15</label>
            </div>

            <!-- Payment Options -->
            <h2>Payment Options</h2>

            <div class="checkbox-container">
                <input type="checkbox" id="kaspiOption">
                <label for="kaspiOption">Kaspi - Pay Later</label>
            </div>

            <div class="checkbox-container">
                <input type="checkbox" id="cashOption">
                <label for="cashOption">Cash on Arrival</label>
            </div>

            <h2>Total Price: <span id="totalPrice">$0</span></h2>


            <button onclick="closeModal()">Close Modal</button>
            <%
                for (Hotel hotel : hotels) {
            %>
            <form action="pages/creditCardPayment.jsp" method="post">
                <input type="hidden" name="hotelId" value="<%= hotel.getId() %>">
                <input type="submit" value="Book">
            </form>
            <%
                }
            %>
        </div>
    </div>
    <%
        }
    %>
    <%--    <div class="popular__grid">--%>
    <%--        <div class="popular__card">--%>
    <%--            <img src="assets/hotel-1.jpg" alt="popular hotel"/>--%>
    <%--            <div class="popular__content">--%>
    <%--                <div class="popular__card__header">--%>
    <%--                    <h4>The Plaza Hotel</h4>--%>
    <%--                    <h4>$499</h4>--%>
    <%--                </div>--%>
    <%--                <p>New York City, USA</p>--%>
    <%--            </div>--%>
    <%--        </div>--%>
    <%--        <div class="popular__card">--%>
    <%--            <img src="assets/hotel-2.jpg" alt="popular hotel"/>--%>
    <%--            <div class="popular__content">--%>
    <%--                <div class="popular__card__header">--%>
    <%--                    <h4>Ritz Paris</h4>--%>
    <%--                    <h4>$549</h4>--%>
    <%--                </div>--%>
    <%--                <p>Paris, France</p>--%>
    <%--            </div>--%>
    <%--        </div>--%>
    <%--        <div class="popular__card">--%>
    <%--            <img src="assets/hotel-3.jpg" alt="popular hotel"/>--%>
    <%--            <div class="popular__content">--%>
    <%--                <div class="popular__card__header">--%>
    <%--                    <h4>The Peninsula</h4>--%>
    <%--                    <h4>$599</h4>--%>
    <%--                </div>--%>
    <%--                <p>Hong Kong</p>--%>
    <%--            </div>--%>
    <%--        </div>--%>
    <%--        <div class="popular__card">--%>
    <%--            <img src="assets/hotel-4.jpg" alt="popular hotel"/>--%>
    <%--            <div class="popular__content">--%>
    <%--                <div class="popular__card__header">--%>
    <%--                    <h4>Atlantis The Palm</h4>--%>
    <%--                    <h4>$449</h4>--%>
    <%--                </div>--%>
    <%--                <p>Dubai, United Arab Emirates</p>--%>
    <%--            </div>--%>
    <%--        </div>--%>
    <%--        <div class="popular__card">--%>
    <%--            <img src="assets/hotel-5.jpg" alt="popular hotel"/>--%>
    <%--            <div class="popular__content">--%>
    <%--                <div class="popular__card__header">--%>
    <%--                    <h4>The Ritz-Carlton</h4>--%>
    <%--                    <h4>$649</h4>--%>
    <%--                </div>--%>
    <%--                <p>Tokyo, Japan</p>--%>
    <%--            </div>--%>
    <%--        </div>--%>
    <%--        <div class="popular__card">--%>
    <%--            <img src="assets/hotel-6.jpg" alt="popular hotel"/>--%>
    <%--            <div class="popular__content">--%>
    <%--                <div class="popular__card__header">--%>
    <%--                    <h4>Marina Bay Sands</h4>--%>
    <%--                    <h4>$549</h4>--%>
    <%--                </div>--%>
    <%--                <p>Singapore</p>--%>
    <%--            </div>--%>
    <%--        </div>--%>
    <%--    </div>--%>
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

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script>
    $(document).ready(function () {
        $('#location').on('input', function () {
            var query = $(this).val();
            $.ajax({
                url: 'autocomplete',
                data: {query: query},
                success: function (response) {
                    var cityList = $('#cityList');
                    cityList.empty();
                    response.forEach(function (city) {
                        cityList.append($('<option>').val(city));
                    });
                }
            });
        });
    });

    function openModal() {
        document.getElementById('myModal').style.display = 'block';
    }

    function closeModal() {
        document.getElementById('myModal').style.display = 'none';
    }

    // Close modal if the user clicks outside the modal content
    window.onclick = function(event) {
        var modal = document.getElementById('myModal');
        var modalContent = modal.querySelector('div');

        if (event.target === modal && !modalContent.contains(event.target)) {
            closeModal();
        }
    };

        function updateTotal() {
        var total = 0;

        // Room Prices
        if (document.getElementById('hotel1Checkbox').checked) total += 100;
        if (document.getElementById('hotel2Checkbox').checked) total += 120;
        if (document.getElementById('hotel3Checkbox').checked) total += 150;

        // Additional Options
        if (document.getElementById('breakfastOption1').checked) total += 10;
        if (document.getElementById('breakfastOption2').checked) total += 12;
        if (document.getElementById('breakfastOption3').checked) total += 15;

        // Update the total display
        document.getElementById('totalPrice').innerText = '$' + total;
    }

    document.querySelectorAll('.modal input[type=checkbox]').forEach(function(checkbox) {
        checkbox.addEventListener('change', updateTotal);
    });

</script>

</body>
</html>