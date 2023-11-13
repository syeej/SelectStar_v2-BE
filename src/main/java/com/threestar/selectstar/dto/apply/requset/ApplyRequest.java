package com.threestar.selectstar.dto.apply.requset;

import com.threestar.selectstar.domain.entity.Apply;
import com.threestar.selectstar.domain.entity.ApplyID;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplyRequest {
    private Integer userId;
    private Integer meetingId;
    private String emailAddress;
    private String snsAddress;
    private String reason;
    private Date applicationDate;

    public static Apply toEntity(ApplyRequest request, ApplyID applyID){
        return Apply.builder()
                .applyID(applyID)
                .emailAddress(request.emailAddress)
                .snsAddress(request.snsAddress)
                .reason(request.reason)
                .applicationDate(request.applicationDate)
                .build();
    }
}
