package com.dto.demo.example;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CardDto extends CommonDto {
    String cardId;
    String siteCd;
    String siteName;
    String deptCd;
    String deptName;
    String empSeq;
    String guestSeq;
    String empName;
    String empNo;
    String statusCd;
    String certiCombCd;
    String passwd;
    String useYnCard;
    String useYnMobile;
    String useYnFace;
    String useYnBio;
    String cardSerial;
    String guestYn;
    String cardTypeCd;
    String autoSecurityCd;
    String mobileTypeCd;
    String masterYn;
    String adminYn;
    String contractNo;

    String validDtSt, validDtEd;

    String console560, console910, mainDevice;
    String lastAuthDt;
    String regDttm;

    String slotIdx;
    String unitSlotIdx;
    String contractSlotIdx;

    List<EmpManageGroupDto> manageGroupAuthList;
    List<EmpManageGroupDto> manageGroupGuardList;
    List<EmpManageGroupDto> manageGroupTimeList;
    List<EmpInoutExceptDto> inoutExceptList;

    List<DeviceDto> cardList;

    String isCreatedCard;
    String timeGroup;

    String[] slotArr;

    String deviceId;

    /* [DK][22.05.02][장치로 계약판단 위한 파라미터] */
    List<String> groupAuthDeviceIdList;
    List<String> inoutExceptDeviceIdList;
    List<String> manageGroupGuardDeviceIdList;
    /* [DK][22.05.02][장치로 계약판단 위한 파라미터] */
    /* [DK][22.05.09][장치로 데이터 전달하는 방법 front 에서 backend 로 변경하기] */
    String andor;
    String expireSdate;
    String expireEdate;
    String expireUnlimit;
    String empGbn;
    List<Map> inoutPolicyGp;
    List<Map> inoutTimeGp;
    List<Map> inoutExceptGp;
    List<Map> partitionPolicyGp;
    String tmpkey;
    /* [방문자] */
    String guestNm;

    List<GuestInoutExceptDto> selectedDevices;
    List<GuestManageGroupDto> selectedGroups;
    /* [방문자] */
    /* [DK][22.05.09][장치로 데이터 전달하는 방법 front 에서 backend 로 변경하기] */
    /* [DK][22.05.10][권한 리스트 기준, 장치 ID 가지고 판단 하는 로직] */
    String deviceType;
    String syncMode;
    int limitCnt;
    String no; // 파티션 또는 도어 번호
    String chkAuthType; // 주장치 연동 모드 시, 파티션 번호 랑 도어 번호 중 어떤거 사용할지 지정하는 변수
    String addUserDeviceId; // 장치조회 시, 조회한 DeviceId
    String contSlotIdx; // 장치조회 시, 추가할 슬롯 번호
    String userVirtualCardId; // 따로 조회하는 가상카드번호
    /* [DK][22.05.10][권한 리스트 기준, 장치 ID 가지고 판단 하는 로직] */

    /* [DK][22.05.13][카드발급 인증수단 저장] */
    String dataGbnCd;
    byte[] profileData;
    byte[] cardData;
    List<CardDto> cardList2;
    /* [DK][22.05.13][카드발급 인증수단 저장] */

    /* [DK][22.06.02][카드삭제용오리진값추가] */
    String orginUseYnBio;
    String orginUseYnCard;
    String orginUseYnFace;
    String orginUseYnMobile;
    /* [DK][22.06.02][카드삭제용오리진값추가] */

    public void setCardData(String cardData) {
        System.out.println("＃＃＃＃＃＃＃＃＃＃＃ [LOG] cardData : " + cardData + "＃＃＃＃＃＃＃＃＃＃＃");
        if (cardData != null)
            this.cardData = cardData.getBytes();
    }

    public void setProfileData(String profileData) {
        if (profileData != null)
            this.profileData = profileData.getBytes();
    }

    public void setCardData(byte[] cardData) {
        if (cardData != null)
            this.cardData = cardData;
    }

    public void setProfileData(byte[] profileData) {
        if (profileData != null)
            this.profileData = profileData;
    }

    public void setCardId(String cardId) {
        if (cardId != null && cardId != null && !cardId.equals(null) && !cardId.equals("") && !cardId.equals(" ")) {
            if (cardId.substring(0, 2).equals("ac")
                    || cardId.substring(0, 2).equals("AC")
                    || cardId.substring(0, 2).equals("aC")
                    || cardId.substring(0, 2).equals("Ac")) {
                this.cardId = cardId.toUpperCase();
            } else {
                this.cardId = cardId;
            }
        }
    }

    public void setUserVirtualCardId(String userVirtualCardId) {
        if (userVirtualCardId != null && !userVirtualCardId.equals(null) && !userVirtualCardId.equals("")
                && !userVirtualCardId.equals(" ")) {
            if (userVirtualCardId.substring(0, 2).equals("ac")
                    || userVirtualCardId.substring(0, 2).equals("AC")
                    || userVirtualCardId.substring(0, 2).equals("aC")
                    || userVirtualCardId.substring(0, 2).equals("Ac")) {
                this.userVirtualCardId = userVirtualCardId.toUpperCase();
            } else {
                this.userVirtualCardId = userVirtualCardId;
            }
        }
    }
}