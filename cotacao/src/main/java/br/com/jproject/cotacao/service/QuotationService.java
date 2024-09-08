package br.com.jproject.cotacao.service;

import br.com.jproject.cotacao.client.CurrencyPriceClient;
import br.com.jproject.cotacao.dto.CurrencyPriceDTO;
import br.com.jproject.cotacao.dto.QuotationDTO;
import br.com.jproject.cotacao.entity.QuotationEntity;
import br.com.jproject.cotacao.message.KafkaEvents;
import br.com.jproject.cotacao.repository.QuotationRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public class QuotationService {

    @Inject
    @RestClient
    CurrencyPriceClient currencyPriceClient;

    @Inject
    QuotationRepository quotationRepository;

    @Inject
    KafkaEvents kafkaEvents;


    public void getCurrentPrice() {

        var quotation = currencyPriceClient.getCurrencyByPair("USD-BRL");
        if(updateCurrentInfoPrice(quotation)){
           kafkaEvents.sendNewKafkaEvent(QuotationDTO
                   .builder()
                   .currencyPrice(new BigDecimal(quotation.getUSDBRL().getBid()))
                   .date(new Date())
                   .build());
        }
    }

    private boolean updateCurrentInfoPrice(CurrencyPriceDTO quotation) {
        BigDecimal currentPrice = new BigDecimal(quotation.getUSDBRL().getBid());
        boolean updatePrice = false;

        List<QuotationEntity> quotationList = quotationRepository.findAll().list();
        if(quotationList.isEmpty()){

            saveQuotation(quotation);
            updatePrice = true;

        } else {
            QuotationEntity lastDollarPrice = quotationList
                    .get(quotationList.size() -1);

            if(currentPrice.floatValue() > lastDollarPrice.getCurrencyPrice().floatValue()){
                updatePrice = true;
                saveQuotation(quotation);
            }
        }

        return updatePrice;

    }

    private void saveQuotation(CurrencyPriceDTO quotation) {
        QuotationEntity quotationEntity = new QuotationEntity();
        quotationEntity.setCurrencyPrice(new BigDecimal(quotation.getUSDBRL().getBid()));
        quotationEntity.setDate(new Date());
        quotationEntity.setPair("USD-BRL");
        quotationEntity.setPctChange(quotation.getUSDBRL().getPtcChange());
        quotationRepository.persist(quotationEntity);
    }

}
