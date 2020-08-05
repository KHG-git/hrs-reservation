package HotelReservationSystem;

import HotelReservationSystem.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PolicyHandler{

    @Autowired
    ReservationRepository reservationRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverReservationConfirmedCanceled_Reservationstatuschange(@Payload ReservationConfirmedCanceled reservationConfirmedCanceled){

        if(reservationConfirmedCanceled.isMe()){
            System.out.println("##### listener Reservationstatuschange : " + reservationConfirmedCanceled.toJson());

            Optional<Reservation> reservationOptional = reservationRepository.findById(reservationConfirmedCanceled.getReservationId());

            if(reservationOptional.isPresent()){

                Reservation reservation = reservationOptional.get();

                reservation.setId(reservationConfirmedCanceled.getReservationId());
                reservation.setStatus("ReservationCanceled");

                reservationRepository.save(reservation);

            }

        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverReservationConfirmed_Reservationstatuschange(@Payload ReservationConfirmed reservationConfirmed){

        if(reservationConfirmed.isMe()){
            System.out.println("##### listener Reservationstatuschange : " + reservationConfirmed.toJson());

            Optional<Reservation> reservationOptional = reservationRepository.findById(reservationConfirmed.getReservationId());

            if(reservationOptional.isPresent()){

                Reservation reservation = reservationOptional.get();

                reservation.setId(reservationConfirmed.getReservationId());
                reservation.setStatus("ReservationConfirmed");

                reservationRepository.save(reservation);
            }

        }
    }

}
