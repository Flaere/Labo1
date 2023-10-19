package staff;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.Set;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import hotel.BookingDetail;
import hotel.RemoteBooking;

public class BookingClient extends AbstractScriptedSimpleTest {

	private RemoteBooking bm = null;

	public static void main(String[] args) throws Exception {
		Registry registry = LocateRegistry.getRegistry("localhost", 0);
		RemoteBooking bm = (RemoteBooking) registry.lookup("manager");
		BookingClient client = new BookingClient(bm);
		client.run();
	}

	/***************
	 * CONSTRUCTOR *
	 ***************/
	public BookingClient(RemoteBooking rbm) {
		try {
			//Look up the registered remote instance
			bm = rbm;
		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}

	@Override
	public boolean isRoomAvailable(Integer roomNumber, LocalDate date) {
		try {
			return bm.isRoomAvailable(roomNumber, date);
		} catch (RemoteException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void addBooking(BookingDetail bookingDetail) {
		try {
			bm.addBooking(bookingDetail);
		} catch (RemoteException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Set<Integer> getAvailableRooms(LocalDate date) {
		try {
			return bm.getAvailableRooms(date);
		} catch (RemoteException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Set<Integer> getAllRooms() {
		try {
			return bm.getAllRooms();
		} catch (RemoteException e) {
			throw new RuntimeException(e);
		}
	}
}
