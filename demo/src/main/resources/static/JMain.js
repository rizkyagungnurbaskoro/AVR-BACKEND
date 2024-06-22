document.addEventListener('DOMContentLoaded', function() {
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

    incrementAdultButton.addEventListener('click', function() {
        adultCounterValue++;
        adultCounterElement.textContent = adultCounterValue;
        adultCounterValueInput.value = adultCounterValue;
        validateForm();
    });

    decrementAdultButton.addEventListener('click', function() {
        if (adultCounterValue > 0) {
            adultCounterValue--;
            adultCounterElement.textContent = adultCounterValue;
            adultCounterValueInput.value = adultCounterValue;
            validateForm();
        }
    });

    incrementChildButton.addEventListener('click', function() {
        childCounterValue++;
        childCounterElement.textContent = childCounterValue;
        childCounterValueInput.value = childCounterValue;
        validateForm();
    });

    decrementChildButton.addEventListener('click', function() {
        if (childCounterValue > 0) {
            childCounterValue--;
            childCounterElement.textContent = childCounterValue;
            childCounterValueInput.value = childCounterValue;
            validateForm();
        }
    });

    const form = document.getElementById('bookingForm');
    form.addEventListener('input', validateForm);

    function validateForm() {
        const selectedRoom = document.getElementById('selectedRoom').value;
        const checkin = document.getElementById('checkin').value;
        const checkout = document.getElementById('checkout').value;
        const adults = document.getElementById('counterValueAdult').value;
        const children = document.getElementById('counterValueChild').value;

        const checkoutButton = document.getElementById('checkoutButton');

        if (selectedRoom && checkin && checkout && (adults > 0 || children > 0)) {
            checkoutButton.disabled = false;
        } else {
            checkoutButton.disabled = true;
        }
    }
});

function toggleDropdown(dropdownId) {
    document.getElementById(dropdownId).classList.toggle("show");
}

function selectRoom(event, roomType) {
    event.preventDefault();
    const selectedRoomLabel = document.getElementById('selectedRoomLabel');
    selectedRoomLabel.textContent = roomType;
    selectedRoomLabel.classList.add('selected');
    document.getElementById('selectedRoom').value = roomType;
    document.getElementById('myDropdown').classList.remove('show');
    validateForm();
}

function validateForm() {
    const selectedRoom = document.getElementById('selectedRoom').value;
    const checkin = document.getElementById('checkin').value;
    const checkout = document.getElementById('checkout').value;
    const adults = document.getElementById('counterValueAdult').value;
    const children = document.getElementById('counterValueChild').value;

    const checkoutButton = document.getElementById('checkoutButton');

    if (selectedRoom && checkin && checkout && (adults > 0 || children > 0)) {
        checkoutButton.disabled = false;
    } else {
        checkoutButton.disabled = true;
    }
}
