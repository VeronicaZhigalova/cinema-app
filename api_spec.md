# APIs

## Client Operations

### Create Client
- Method: POST
- Endpoint: /clients/register
- Request:
    - Request body with fields:
    - client_name
    - phone_number
    - email_address
- Response:
    - HTTP status code

### Delete Client
- Method: DELETE
- Endpoint: /clients/{client_id}
- Response:
    - HTTP status code

### Update Client
- Method: PUT
- Endpoint: /clients/{client_id}
- Request:
    - Request body with fields:
    - client_name
    - phone_number
    - email_address
- Response:
    - HTTP status code

## Movie Operations

### Create Movie
- Method: POST
- Endpoint: /movies/create
- Request:
    - Request body with fields:
    - movie_name
    - genre
    - limit_age
    - author
    - movie_start_time
    - language
    - fk_client_id
- Response:
    - HTTP status code

### Delete Movie
- Method: DELETE
- Endpoint: /movies/{movie_id}
- Response:
    - HTTP status code

### Ticket Operations
- Available (Find free) Tickets
- Method: GET
- Endpoint: /tickets/available
- Request:
    - Query parameter:
    - date
- Response:
    - List of available tickets for the specified date
    - HTTP status code

### Create Ticket
- Method: POST
- Endpoint: /tickets/create
- Request:
    - Request body with fields:
    - seat
    - price
    - fk_movie_id
- Response:
    - HTTP status code

### Delete Ticket
- Method: DELETE
- Endpoint: /tickets/{ticket_id}
- Response:
    - HTTP status code

### Update Ticket
- Method: PUT
- Endpoint: /tickets/{ticket_id}
- Request:
    - Request body with fields:
    - seat
    - price
- Response:
    - HTTP status code

## Reservation Operations

### Find Reservation
- Method: GET
- Endpoint: /reservations/find
- Response:
    - List of reservations based on provided criteria

### Create Reservation
- Method: POST
- Endpoint: /reservations/create
- Request:
    - Request body of
        - reservation_status
        - fk_movie_id
        - fk_client_id
        - fk_ticket_id
- Response:
    - HTTP status code

### Delete Reservation
- Method: DELETE
- Endpoint: /reservations/{reservation_id}
- Response:
    - HTTP status code

### Update Reservation
- Method: PUT
- Endpoint: /reservations/{reservation_id}
- Request:
    - Request body of
        - reservation_status (String)
- Response:
    - HTTP status code