package com.dto.demo.example;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommonDto {
    String userId;
    String sysMemberJoinSeq;
    String siteCd;
    String corpCd;
    String empSeq;
    String crud;
    String authMode;
    String langCd;
    String searchGbn;
    String searchText;
    String searchType;
    PageInfo pageInfo;
    SessionInfo session;

    String chgSiteCd;
}
