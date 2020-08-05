package HotelReservationSystem;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationinquiryRepository extends CrudRepository<Reservationinquiry, Long> {

    //List<Reservationinquiry> findById(String customerName);
    List<Reservationinquiry> findByCustomerName(String customerName);

}