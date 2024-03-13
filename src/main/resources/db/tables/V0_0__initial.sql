CREATE TABLE clients (
    id SERIAL PRIMARY KEY,
    client_name VARCHAR(255) NOT NULL,
    phone_number VARCHAR(255) NOT NULL,
    email_address VARCHAR(255) NOT NULL
);

CREATE TABLE blocklisted_clients (
    id SERIAL PRIMARY KEY,
    reason VARCHAR(255) NOT NULL,
    client_id INTEGER NOT NULL,
    CONSTRAINT fk_blocklisted_clients_clients FOREIGN KEY (client_id) REFERENCES clients(id) ON DELETE CASCADE
);

CREATE TABLE movies (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    genre VARCHAR(255) NOT NULL,
    language VARCHAR(255) NOT NULL,
    limit_age INTEGER NOT NULL,
    author VARCHAR(255) NOT NULL,
    date DATE NOT NULL
);

CREATE TABLE showtimes (
    id SERIAL PRIMARY KEY,
    movie_id INTEGER NOT NULL,
    showtimeDateTime TIMESTAMP NOT NULL,
    CONSTRAINT fk_showtimes_movies FOREIGN KEY (movie_id) REFERENCES movies(id) ON DELETE CASCADE
);

CREATE TABLE seats (
    id SERIAL PRIMARY KEY,
    row INTEGER NOT NULL,
    seat_number INTEGER NOT NULL,
    occupied BOOLEAN NOT NULL
);


CREATE TABLE tickets (
    id SERIAL PRIMARY KEY,
    showtime_id INTEGER NOT NULL,
    client_id INTEGER NOT NULL,
    seat_id INTEGER NOT NULL,
    price INTEGER NOT NULL,
    dateofpurchase TIMESTAMP NOT NULL,
    date_of_movie TIMESTAMP NOT NULL,
    ticket_type VARCHAR(20) NOT NULL,
    CONSTRAINT fk_tickets_showtimes FOREIGN KEY (showtime_id) REFERENCES showtimes(id) ON DELETE CASCADE,
    CONSTRAINT fk_tickets_clients FOREIGN KEY (client_id) REFERENCES clients(id) ON DELETE CASCADE,
    CONSTRAINT fk_tickets_seats FOREIGN KEY (seat_id) REFERENCES seats(id) ON DELETE CASCADE
);


CREATE TABLE reservations (
    id SERIAL PRIMARY KEY,
    showtime_id INTEGER NOT NULL,
    client_id INTEGER NOT NULL,
    seat_id INTEGER NOT NULL,
    bookingstatus VARCHAR(255) NOT NULL,
    reservation_date TIMESTAMP NOT NULL,
    CONSTRAINT fk_reservations_showtimes FOREIGN KEY (showtime_id) REFERENCES showtimes(id) ON DELETE CASCADE,
    CONSTRAINT fk_reservations_clients FOREIGN KEY (client_id) REFERENCES clients(id) ON DELETE CASCADE,
    CONSTRAINT fk_reservations_seats FOREIGN KEY (seat_id) REFERENCES seats(id) ON DELETE CASCADE
);

CREATE TABLE user_preferences (
    user_id INTEGER NOT NULL,
    genre VARCHAR(255) NOT NULL,
    PRIMARY KEY (user_id, genre),
    FOREIGN KEY (user_id) REFERENCES clients(id) ON DELETE CASCADE
);

CREATE TABLE user_view_history (
    id SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL,
    movie_id INTEGER NOT NULL,
    date_viewed TIMESTAMP NOT NULL,
    CONSTRAINT fk_user_view_history_clients FOREIGN KEY (user_id) REFERENCES clients(id) ON DELETE CASCADE,
    CONSTRAINT fk_user_view_history_movies FOREIGN KEY (movie_id) REFERENCES movies(id) ON DELETE CASCADE
);
