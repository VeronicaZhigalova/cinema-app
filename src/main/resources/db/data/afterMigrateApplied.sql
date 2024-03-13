INSERT INTO clients (client_name, phone_number, email_address) VALUES
('Alice Johnson', '+111222333', 'alice.j@example.com'),
('Bob Williams', '+444555666', 'bob.w@example.com'),
('Charlie Davis', '+777888999', 'charlie.d@example.com'),
('David Miller', '+123456789', 'david.m@example.com'),
('Eva Brown', '+987654321', 'eva.b@example.com'),
('Frank White', '+555444333', 'frank.w@example.com'),
('Grace Smith', '+999888777', 'grace.s@example.com'),
('Henry Wilson', '+333222111', 'henry.w@example.com'),
('Ivy Taylor', '+666777888', 'ivy.t@example.com'),
('Jack Harris', '+222333444', 'jack.h@example.com');



INSERT INTO blocklisted_clients (reason, client_id) VALUES
('Unauthorized Access', 6),
('Spamming', 9),
('Abuse of Services', 2),
('Security Breach', 5),
('Violation of Terms', 8);

INSERT INTO movies (title, genre, language, limit_age, author, date) VALUES
('The Dark Knight', 'ACTION', 'ENGLISH', 16, 'Nolan', '2008-07-18'),
('The Godfather', 'DRAMA', 'ENGLISH', 18, 'Coppola', '1972-03-24'),
('Pulp Fiction', 'THRILLER', 'ENGLISH', 17, 'Tarantino', '1994-05-21'),
('Forrest Gump', 'COMEDY', 'ENGLISH', 12, 'Zemeckis', '1994-07-06'),
('The Shawshank Redemption', 'DRAMA', 'ENGLISH', 18, 'Darabont', '1994-09-10'),
('Inception', 'SCIFI', 'ENGLISH', 13, 'Nolan', '2010-07-16'),
('The Matrix', 'SCIFI', 'ENGLISH', 16, 'Wachowski', '1999-03-31'),
('Titanic', 'ROMANCE', 'ENGLISH', 13, 'Cameron', '1997-12-19'),
('Jurassic Park', 'SCIFI', 'ENGLISH', 13, 'Spielberg', '1993-06-11'),
('The Hangover', 'COMEDY', 'ENGLISH', 18, 'Phillips', '2009-06-05');



INSERT INTO showtimes (movie_id, showtimeDateTime) VALUES
(1, '2023-03-10 15:00:00'),
(2, '2023-03-11 18:30:00'),
(3, '2023-03-12 21:45:00'),
(4, '2023-03-13 17:00:00'),
(5, '2023-03-14 19:15:00'),
(6, '2023-03-15 13:30:00'),
(7, '2023-03-16 11:45:00'),
(8, '2023-03-17 09:00:00'),
(9, '2023-03-18 14:15:00'),
(10, '2023-03-19 20:00:00');



INSERT INTO seats (row, seat_number, occupied)
VALUES
(1, 1, false),
(1, 2, false),
(2, 1, false),
(2, 2, false),
(3, 1, false),
(3, 2, false),
(4, 1, false),
(4, 2, false),
(5, 1, false),
(5, 2, false);



INSERT INTO tickets (showtime_id, client_id, seat_id, price, dateofpurchase, date_of_movie, ticket_type)
VALUES
(1, 1, 1, 15, '2023-02-01 14:30:00', '2023-02-10 16:30:00', 'ADULT'),
(2, 2, 3, 12, '2023-02-02 15:45:00', '2023-02-11 18:00:00', 'CHILD'),
(3, 3, 5, 8, '2023-02-03 18:00:00', '2023-02-12 20:15:00', 'ADULT'),
(4, 4, 7, 10, '2023-02-04 20:15:00', '2023-02-13 22:30:00', 'CHILD'),
(5, 5, 9, 18, '2023-02-05 22:30:00', '2023-02-14 14:45:00', 'ADULT'),
(6, 6, 2, 14, '2023-02-06 14:45:00', '2023-02-15 17:00:00', 'CHILD'),
(7, 7, 4, 22, '2023-02-07 17:00:00', '2023-02-16 19:15:00', 'ADULT'),
(8, 8, 6, 16, '2023-02-08 19:15:00', '2023-02-17 21:30:00', 'CHILD'),
(9, 9, 8, 20, '2023-02-09 21:30:00', '2023-02-18 23:45:00', 'ADULT'),
(10, 10, 10, 25, '2023-02-10 23:45:00', '2023-02-19 12:00:00', 'CHILD');


INSERT INTO reservations (showtime_id, client_id, seat_id, bookingstatus, reservation_date) VALUES
(1, 1, 2, 'CONFIRMED', '2023-02-01 16:10:00'),
(2, 2, 5, 'PENDING', '2023-02-02 19:30:00'),
(3, 3, 4, 'CONFIRMED', '2023-02-03 22:45:00'),
(4, 4, 6, 'PENDING', '2023-02-04 18:00:00'),
(5, 5, 8, 'CONFIRMED', '2023-02-05 20:15:00'),
(6, 6, 10, 'CONFIRMED', '2023-02-06 14:30:00'),
(7, 7, 3, 'PENDING', '2023-02-07 12:45:00'),
(8, 8, 7, 'CONFIRMED', '2023-02-08 10:00:00'),
(9, 9, 9, 'PENDING', '2023-02-09 08:15:00'),
(10, 10, 1, 'CONFIRMED', '2023-02-10 13:20:00');


INSERT INTO user_preferences (user_id, genre)
VALUES
(1, 'ACTION'),
(1, 'COMEDY'),
(2, 'THRILLER'),
(2, 'ROMANCE'),
(3, 'SCIFI'),
(3, 'DRAMA'),
(4, 'COMEDY'),
(4, 'THRILLER'),
(5, 'DRAMA'),
(5, 'SCIFI');

INSERT INTO user_view_history (user_id, movie_id, date_viewed)
VALUES
(1, 1, '2023-02-01 20:30:00'),
(2, 3, '2023-02-02 15:45:00'),
(3, 5, '2023-02-03 18:20:00'),
(4, 7, '2023-02-04 21:30:00'),
(5, 9, '2023-02-05 16:15:00'),
(6, 2, '2023-02-06 22:45:00'),
(7, 4, '2023-02-07 14:00:00'),
(8, 6, '2023-02-08 12:45:00'),
(9, 8, '2023-02-09 10:30:00'),
(10, 10, '2023-02-10 19:00:00');


