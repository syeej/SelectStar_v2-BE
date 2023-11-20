package com.threestar.selectstar.dto.apply.response;


import com.threestar.selectstar.domain.entity.Apply;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ApplyCheckResponse {
    int reject;
    String reason;

    public static ApplyCheckResponse fromEntity(Apply apply){
        return ApplyCheckResponse.builder()
                .reject(apply.getReject())
                .reason(apply.getReason())
                .build();
    }

}
