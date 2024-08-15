package br.com.jproject.cotacao.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Data
@Builder
@AllArgsConstructor
public class CurrencyPriceDTO {

    private USDBRLDTO usdbrldto;

}
