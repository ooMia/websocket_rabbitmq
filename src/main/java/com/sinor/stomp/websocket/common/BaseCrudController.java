package com.sinor.stomp.websocket.common;

import java.io.Serializable;
import org.springframework.http.ResponseEntity;

public interface BaseCrudController<ResponseDto, RequestDto, IdType extends Serializable> {

    ResponseEntity<ResponseDto> readObject(IdType id);

    ResponseEntity<ResponseDto> createObject(RequestDto requestDto);

    ResponseEntity<ResponseDto> updateObject(IdType id, RequestDto requestDto);

    ResponseEntity<ResponseDto> deleteObject(IdType id);
}
