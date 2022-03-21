package com.example.graduate_sever.common;

import lombok.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResVO {
    List<?> list;
    long pageTotal;
}
