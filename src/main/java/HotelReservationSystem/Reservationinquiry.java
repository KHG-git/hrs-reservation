package HotelReservationSystem;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Reservationinquiry_table")
public class Reservationinquiry {

        @Id
       // @GeneratedValue(strategy=GenerationType.IDENTITY)
        private Long id;
        private String customerName;
        private Integer nights;
        private String checkinDate;
        private String status;
        private Float price;
        private Integer hotelId;
        private Integer pointAmount;


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
        public Float getPrice() {
            return price;
        }

        public void setPrice(Float price) {
            this.price = price;
        }
        public Integer getHotelId() {
            return hotelId;
        }

        public void setHotelId(Integer hotelId) {
            this.hotelId = hotelId;
        }
        public Integer getPointAmount() {
            return pointAmount;
        }

        public void setPointAmount(Integer pointAmount) {
            this.pointAmount = pointAmount;
        }

}
