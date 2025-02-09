package br.com.jproject.cotacao.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "quotation")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuotationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    @Column(name = "currency_price", precision = 10, scale = 4)
    private BigDecimal currencyPrice;
    @Column(name = "pct_change")
    private String pctChange;
    private String pair;
}
