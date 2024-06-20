function selectRoom(event, roomType) {
    event.preventDefault(); // Prevent the default action of the anchor tag

    // Update label text
    var ddtext = document.querySelector('.ddtext-room');
    ddtext.innerText = roomType;

    // Set hidden input value
    var selectedRoomInput = document.getElementById('selectedRoom');
    selectedRoomInput.value = roomType;

    // Add 'selected' class to ddtext if roomType is not empty
    if (roomType.trim() !== '') {
        ddtext.classList.add('selected');
    } else {
        ddtext.classList.remove('selected');
    }

    toggleDropdown(); // Close dropdown after selection (if needed)
}

function toggleDropdown() {
    var dropdown = document.getElementById('myDropdown');
    dropdown.classList.toggle('show');
}

// Close the dropdown if the user clicks outside of it
window.onclick = function(event) {
    if (!event.target.matches('.dd')) {
        var dropdowns = document.getElementsByClassName('dropdown-content-room');
        for (var i = 0; i < dropdowns.length; i++) {
            var openDropdown = dropdowns[i];
            if (openDropdown.classList.contains('show')) {
                openDropdown.classList.remove('show');
            }
        }
    }
}




/*for counter*/
document.addEventListener('DOMContentLoaded', function() {
    const ddtext = document.querySelector('.ddtext-guest');

    const adultCounterElement = document.getElementById('counter-adult');
    const incrementAdultButton = document.getElementById('increment-adult');
    const decrementAdultButton = document.getElementById('decrement-adult');
    const adultCounterValueInput = document.getElementById('counterValueAdult');
    let adultCounterValue = 0;

    const childCounterElement = document.getElementById('counter-child');
    const incrementChildButton = document.getElementById('increment-child');
    const decrementChildButton = document.getElementById('decrement-child');
    const childCounterValueInput = document.getElementById('counterValueChild');
    let childCounterValue = 0;

    function updateDDText() {
        let text = '';
        if (adultCounterValue > 0) {
            text += `${adultCounterValue} Adult${adultCounterValue > 1 ? 's' : ''}`;
        }
        if (childCounterValue > 0) {
            if (text) text += ', ';
            text += `${childCounterValue} Child${childCounterValue > 1 ? 'ren' : ''}`;
        }
        ddtext.textContent = text || 'Select guests';

        // Toggle 'selected' class based on whether guests are selected
        if (adultCounterValue > 0 || childCounterValue > 0) {
            ddtext.classList.add('selected');
        } else {
            ddtext.classList.remove('selected');
        }
    }

    function toggleChildButtons() {
        if (adultCounterValue > 0) {
            incrementChildButton.disabled = false;
            decrementChildButton.disabled = false;
        } else {
            incrementChildButton.disabled = true;
            decrementChildButton.disabled = true;
            childCounterValue = 0;
            childCounterElement.textContent = childCounterValue;
            childCounterValueInput.value = childCounterValue;
            updateDDText();
        }
    }

    incrementAdultButton.addEventListener('click', function(event) {
        event.preventDefault(); // Prevent the default form submission
        adultCounterValue++;
        adultCounterElement.textContent = adultCounterValue;
        adultCounterValueInput.value = adultCounterValue;
        updateDDText();
        toggleChildButtons();
    });

    decrementAdultButton.addEventListener('click', function(event) {
        event.preventDefault(); // Prevent the default form submission
        if (adultCounterValue > 0) {
            adultCounterValue--;
            adultCounterElement.textContent = adultCounterValue;
            adultCounterValueInput.value = adultCounterValue;
            updateDDText();
            toggleChildButtons();
        }
    });

    incrementChildButton.addEventListener('click', function(event) {
        event.preventDefault(); // Prevent the default form submission
        childCounterValue++;
        childCounterElement.textContent = childCounterValue;
        childCounterValueInput.value = childCounterValue;
        updateDDText();
    });

    decrementChildButton.addEventListener('click', function(event) {
        event.preventDefault(); // Prevent the default form submission
        if (childCounterValue > 0) {
            childCounterValue--;
            childCounterElement.textContent = childCounterValue;
            childCounterValueInput.value = childCounterValue;
            updateDDText();
        }
    });

    // Initially disable child buttons
    toggleChildButtons();
});


/*datepicker*/
document.addEventListener('DOMContentLoaded', function() {
    const checkinInput = document.getElementById('checkin');
    const checkoutInput = document.getElementById('checkout');

    const today = new Date();
    const todayStr = today.toISOString().split('T')[0];

    // Set the minimum date for checkin and checkout to today
    checkinInput.min = todayStr;
    checkoutInput.min = todayStr;

    checkinInput.addEventListener('change', function() {
        const checkinDate = new Date(this.value);
        checkinDate.setDate(checkinDate.getDate() + 1); // Set the minimum checkout date to 1 day after checkin

        checkoutInput.min = checkinDate.toISOString().split('T')[0];
        if (new Date(checkoutInput.value) <= new Date(this.value)) {
            checkoutInput.value = ''; // Reset checkout date if it's not valid
        }
    });

    checkoutInput.addEventListener('change', function() {
        if (new Date(this.value) <= new Date(checkinInput.value)) {
            alert('Checkout date must be after check-in date.');
            this.value = '';
        }
    });
});

/*to make the button functions to go to the next page*/
// JMain.js file

// Function to handle checkout button click
function handleCheckout(event) {
    event.preventDefault(); // Prevent default form submission

    // Get form data
    var selectedRoom = document.getElementById('selectedRoom').value;
    var counterValueAdult = document.getElementById('counterValueAdult').value;
    var counterValueChild = document.getElementById('counterValueChild').value;
    var checkinDate = document.getElementById('checkin').value;
    var checkoutDate = document.getElementById('checkout').value;

    // Redirect to checkout page with query parameters
    var queryString = `checkout.html?selectedRoom=${selectedRoom}&adults=${counterValueAdult}&children=${counterValueChild}&checkin=${checkinDate}&checkout=${checkoutDate}`;
    window.location.href = queryString;
}

// Attach event listener to the Checkout button
var checkoutButton = document.querySelector('.container_Rnavbar input[type="submit"]');
checkoutButton.addEventListener('click', handleCheckout);
