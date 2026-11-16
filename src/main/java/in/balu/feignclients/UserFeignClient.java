package in.balu.feignclients;

import in.balu.dto.UserDTO;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
@LoadBalancerClient
public interface UserFeignClient {

    @GetMapping("/api/users/{userId}")
    public UserDTO fetchUser(@PathVariable("userId") Integer userId);

}