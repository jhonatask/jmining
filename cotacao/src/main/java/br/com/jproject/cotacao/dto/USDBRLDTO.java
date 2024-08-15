package br.com.jproject.cotacao.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Data
@Builder
@AllArgsConstructor
public class USDBRLDTO {

    public String code;
    public String codein;
    public String name;
    public String high;
    public String low;
    public String varBid;
    public String ptcChange;
    public String bid;
    public String ask;
    public String timestamp;
    public String create_date;

}
