-- Insert rooms
INSERT INTO room (room_type, price, `desc`) VALUES ('Standard', 100.00, 'Standard room description');
INSERT INTO room (room_type, price, `desc`) VALUES ('Deluxe', 200.00, 'Deluxe room description');
INSERT INTO room (room_type, price, `desc`) VALUES ('Suite', 300.00, 'Suite room description');

-- Insert bookings
INSERT INTO booking (start, end_date, book_date, notes, status) VALUES ('2024-06-10', '2024-06-12', '2024-06-08', 'Booking notes 1', 'Confirmed');
INSERT INTO booking (start, end_date, book_date, notes, status) VALUES ('2024-07-15', '2024-07-18', '2024-07-10', 'Booking notes 2', 'Confirmed');
INSERT INTO booking (start, end_date, book_date, notes, status) VALUES ('2024-08-20', '2024-08-22', '2024-08-18', 'Booking notes 3', 'Pending');
INSERT INTO booking (start, end_date, book_date, notes, status) VALUES ('2024-09-25', '2024-09-28', '2024-09-23', 'Booking notes 4', 'Cancelled');

-- Insert customers
INSERT INTO customer (first_name, last_name, email, phone, booking_id) VALUES ('James', 'Dean', 'jd38@gmail.com', '0193899839', 1);
INSERT INTO customer (first_name, last_name, email, phone, booking_id) VALUES ('Ali', 'James', 'aj112@gmail.com', '0194999890', 2);
INSERT INTO customer (first_name, last_name, email, phone, booking_id) VALUES ('Samad', 'Bond', 'sb189@gmail.com', '0193783890', 3);
INSERT INTO customer (first_name, last_name, email, phone, booking_id) VALUES ('Sarip', 'John', 'sj134@gmail.com', '0191297890', 4);

-- Insert payments
INSERT INTO payment (cardholder_name, card_number, exp_date, cvc, total_price, payment_date, room_type, checkin_date, checkout_date, booking_id)
VALUES ('James Dean', '1234567812345678', '12/25', '123', 200.00, '2024-06-08', 'Standard', '2024-06-10', '2024-06-12', 1);

INSERT INTO payment (cardholder_name, card_number, exp_date, cvc, total_price, payment_date, room_type, checkin_date, checkout_date, booking_id)
VALUES ('Ali James', '2345678923456789', '01/26', '234', 400.00, '2024-07-10', 'Deluxe', '2024-07-15', '2024-07-18', 2);

INSERT INTO payment (cardholder_name, card_number, exp_date, cvc, total_price, payment_date, room_type, checkin_date, checkout_date, booking_id)
VALUES ('Samad Bond', '3456789034567890', '02/27', '345', 600.00, '2024-08-18', 'Suite', '2024-08-20', '2024-08-22', 3);

INSERT INTO payment (cardholder_name, card_number, exp_date, cvc, total_price, payment_date, room_type, checkin_date, checkout_date, booking_id)
VALUES ('Sarip John', '4567890145678901', '03/28', '456', 800.00, '2024-09-23', 'Suite', '2024-09-25', '2024-09-28', 4);