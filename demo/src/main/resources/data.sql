-- Insert rooms
INSERT INTO room (room_type, nights, desc) VALUES ('Single', 1, 'Single room description');
INSERT INTO room (room_type, nights, desc) VALUES ('Double', 2, 'Double room description');
INSERT INTO room (room_type, nights, desc) VALUES ('Suite', 3, 'Suite room description');


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