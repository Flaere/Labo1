package hotel;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class BookingManager implements RemoteBooking{

	private Room[] rooms;

	public BookingManager() {
		this.rooms = initializeRooms();
	}

	public Set<Integer> getAllRooms() {
		Set<Integer> allRooms = new HashSet<>();
		Room[] roomIterator = rooms;
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
		int roomNumber = bookingDetail.getRoomNumber();
		Optional<Room> room = Arrays.stream(rooms).filter(r -> r.getRoomNumber().equals(roomNumber)).findFirst();
		if (room.isPresent()){
			List<BookingDetail> booking = room.get().getBookings();
			booking.add(bookingDetail);
			room.get().setBookings(booking);
		}
	}

	public Set<Integer> getAvailableRooms(LocalDate date) {
		return Arrays.stream(rooms).filter(room -> !room.hasBooked(date)).map(Room :: getRoomNumber).collect(Collectors.toSet());
	}

	public static void main(String[] args){
		try {
			BookingManager bm = new BookingManager();
			RemoteBooking stub = (RemoteBooking) UnicastRemoteObject.exportObject(bm, 0);
			Registry registry = LocateRegistry.getRegistry();
			registry.bind("manager", stub);
			System.out.println("Server Ready");
		}catch (Exception e){
			System.err.println("Server Exception:" + e);
			e.printStackTrace();
		}
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
