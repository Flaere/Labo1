package hotel;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class BookingManager {

	private Room[] rooms;
	private Set<BookingDetail> bookings = new HashSet<>();

	public BookingManager() {
		this.rooms = initializeRooms();
	}

	public Set<Integer> getAllRooms() {
		Set<Integer> allRooms = new HashSet<Integer>();
		Iterable<Room> roomIterator = Arrays.asList(rooms);
		for (Room room : roomIterator) {
			allRooms.add(room.getRoomNumber());
		}
		return allRooms;
	}

	public boolean isRoomAvailable(Integer roomNumber, LocalDate date) {
		boolean available = false;
		Optional<Room> room = Arrays.stream(rooms).filter(r -> r.getRoomNumber().equals(roomNumber)).findFirst();
		if (room.isPresent()){
			List<BookingDetail> booking = room.get().getBookings();
			available = booking.stream().anyMatch(b -> b.getDate().equals(date));
		}
		return available;
	}

	public void addBooking(BookingDetail bookingDetail) {
		this.bookings.add(bookingDetail) ;
	}

	public Set<Integer> getAvailableRooms(LocalDate date) {
		Set<Integer> allRooms = getAllRooms();
		Set<BookingDetail> bookingsDate = bookings.stream().filter(booking -> !booking.getDate().equals(date)).collect(Collectors.toSet());
		return bookingsDate.stream().map(BookingDetail :: getRoomNumber).collect(Collectors.toSet());
	}

	private static Room[] initializeRooms() {
		Room[] rooms = new Room[4];
		rooms[0] = new Room(101);
		rooms[1] = new Room(102);
		rooms[2] = new Room(201);
		rooms[3] = new Room(203);
		return rooms;
	}
}
