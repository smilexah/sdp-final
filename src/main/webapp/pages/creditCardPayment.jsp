<%@ page import="com.sdu.edu.kz.adapter.CreditCardPayment" %>
<html>
<head>
    <title>Credit Card Payment</title>
    <script>
        function updateTotal() {
            var total = 0;
            // ... your total calculation logic ...

            document.getElementById('totalPrice').innerText = '$' + total;

            // Set the calculated total in the payment form
            document.getElementById('amount').value = total;
        }

        // Add event listeners to update total whenever an option changes
        window.onload = function() {
            document.querySelectorAll('.modal input[type=checkbox]').forEach(function(checkbox) {
                checkbox.addEventListener('change', updateTotal);
            });
        };
    </script>
</head>
<body>

<!-- Modal with Booking Options -->
<div id="myModal" class="modal">
    <!-- ... your modal content ... -->

    <!-- Total Price Display -->
    <h2>Total Price: <span id="totalPrice">$0</span></h2>

    <!-- Button to Open Payment Form -->
    <button onclick="document.getElementById('paymentForm').style.display='block'">Proceed to Payment</button>
</div>

<!-- Credit Card Payment Form -->
<div id="paymentForm" style="display:none;">
    <h2>Credit Card Payment Form</h2>
    <form action="../succes.jsp" method="post">
        <label for="amount">Enter Amount in KZT:</label>
        <input type="text" id="amount" name="amount" readonly><br><br>
        <input type="submit" value="Pay">
    </form>
</div>

</body>
</html>
