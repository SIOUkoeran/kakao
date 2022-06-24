package com.example.kakao.model;


import com.example.kakao.dto.CSVDto;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Bank {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bank_id")
    private Long id;

    @NotNull
    @Column(unique = true)
    private String instituteName;

    @NotNull
    @Column(unique = true)
    private Integer instituteCode;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "bank")
    private List<Amount> amounts = new ArrayList<>();


    public Bank(CSVDto csvDto){
        this.instituteName = InstituteCode.valueOfCode(csvDto.getBankCode());
        this.instituteCode = csvDto.getBankCode();
    }
    public Bank(String name, Integer code){
        this.instituteName = name;
        this.instituteCode = code;
    }
}
