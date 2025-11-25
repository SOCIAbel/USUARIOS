package com.example.msmariopago.feign;
import com.example.msmariopago.dtos.AuthUserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@FeignClient(name = "ms-auth-service", path = "/auth")
public interface UserFeignClient {
    @GetMapping("/{id}")
    AuthUserDto getUserById(@PathVariable Long id);

}
