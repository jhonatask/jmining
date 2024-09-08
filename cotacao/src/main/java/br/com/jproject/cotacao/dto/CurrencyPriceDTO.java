package br.com.jproject.cotacao.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CurrencyPriceDTO {

    private USDBRLDTO USDBRL;

    @JsonCreator
    public CurrencyPriceDTO(@JsonProperty("USDBRL") USDBRLDTO USDBRL) {
        this.USDBRL = USDBRL;
    }

}
