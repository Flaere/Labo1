# Labo1
The goal of this assignment is to convert this local hotel booking application into a distributed application
using Java RMI, allowing hotel staff (the main users of this application) to book the hotel rooms remotely.
More specifically, you have to provide the following functionality in your implementation:

1. A booking system (which uses the server application) offers a remote BookingManager object, which
hotel staff can use to make bookings. Allow the hotel staff client application to locate this remote
instance of the booking manager object in a naming registry.

2. ( Check ) Allow the hotel staff (who use the client application) to check if a specific room is available for a
given date, using isRoomAvailable(Integer roomNumber, LocalDate date).

3. ( Semi-Check ) The hotel staff should be able to create a new booking by collecting the necessary information
(guest, room no, date) into a BookingDetail object. It supplies these details to the booking manager,
which will try to add a new booking for the given guest in the given room on the given date
through addBooking(BookingDetail bookingDetail).
( TBA ) If the room is not available, throw a suitable Exception.

5. ( Check ) Good news! The hotel staff loves your system. However, they find it rather tedious to check the
availability of each room separately. Therefore, implement a method to request the availability of
all rooms of the hotel on a given date, using getAvailableRooms(LocalDate date).

( TBA ) The hotel booking system may be used by a number of hotel staff at once so your implementation must be
thread-safe. ( Check? ) In addition, efficiency is important for the hotel booking system, as such two bookings can be
made concurrently as long as they are for different rooms.
