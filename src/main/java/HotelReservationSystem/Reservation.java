package HotelReservationSystem;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="Reservation_table")
public class Reservation {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String customerName;
    private Integer nights;
    private String checkinDate;
    private String status;
    private Integer hotelId;
    private Float price;
    private String updateGubun;

    @PostPersist
    public void onPostPersist(){
        Reserved reserved = new Reserved();
        BeanUtils.copyProperties(this, reserved);
        reserved.publishAfterCommit();

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        HotelReservationSystem.external.Payment payment = new HotelReservationSystem.external.Payment();
        // mappings goes here

        payment.setPrice(reserved.getPrice());
        payment.setReservationId(reserved.getId());
        payment.setCustomerName(reserved.getCustomerName());
        payment.setStatus("PaymentRequested");

        ReservationApplication.applicationContext.getBean(HotelReservationSystem.external.PaymentService.class)
            .payment(payment);


    }

    @PostUpdate
    public void onPostUpdate(){

        if(!"ReservationCanceled".equals(getStatus()) && "delete".equals(getUpdateGubun())){
            ReservationCanceled reservationCanceled = new ReservationCanceled();
            BeanUtils.copyProperties(this, reservationCanceled);
            reservationCanceled.publishAfterCommit();
        }

//        ReservationCanceled reservationCanceled = new ReservationCanceled();
//        BeanUtils.copyProperties(this, reservationCanceled);
//        reservationCanceled.publishAfterCommit();


    }

    @PreUpdate
    public void onPreUpdate(){
        if(!"ReservationCanceled".equals(getStatus()) && "delete".equals(getUpdateGubun())){
//            ReservationCanceled reservationCanceled = new ReservationCanceled();
//            BeanUtils.copyProperties(this, reservationCanceled);
//            reservationCanceled.publishAfterCommit();

            System.out.println("#### Reservation onPreUpdate ###");
            System.out.println(getId());
            System.out.println(getHotelId());
            System.out.println("#### CI/CD TEST ###");



//            Optional<Reservation> reservationOptional = reservationRepository.findById(this.getId());

//            if(reservationOptional.isPresent()){
//                Reservation reservation = reservationOptional.get();
//                BeanUtils.copyProperties(reservation, this);
//                this.setStatus("Reservation Cancel");

//                ReservationCanceled reservationCanceled = new ReservationCanceled();
//                BeanUtils.copyProperties(this, reservationCanceled);
//                reservationCanceled.publishAfterCommit();
//            }
        }
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public Integer getNights() {
        return nights;
    }

    public void setNights(Integer nights) {
        this.nights = nights;
    }
    public String getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(String checkinDate) {
        this.checkinDate = checkinDate;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }
    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getUpdateGubun() {
        return updateGubun;
    }

    public void setUpdateGubun(String updateGubun) {
        this.updateGubun = updateGubun;
    }





}
