package br.com.jproject.cotacao.message;

import br.com.jproject.cotacao.dto.QuotationDTO;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class KafkaEvents {

    private final Logger LOGGER = LoggerFactory.getLogger(KafkaEvents.class);

    @Channel("quotation-chanel")
    Emitter<QuotationDTO> quotationRequestEmitter;

    public void sendNewKafkaEvent(QuotationDTO quotationDTO) {
        LOGGER.info("Sending message to Kafka:");
        quotationRequestEmitter.send(quotationDTO).toCompletableFuture().join();
    }
}
