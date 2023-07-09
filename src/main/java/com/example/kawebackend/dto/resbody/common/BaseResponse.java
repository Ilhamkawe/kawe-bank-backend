package com.example.kawebackend.dto.resbody.common;

import lombok.*;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> implements Serializable {

    private MetaResponse meta;
    private T data;

}
