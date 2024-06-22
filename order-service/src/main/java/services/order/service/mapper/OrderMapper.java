package services.order.service.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import services.order.service.dto.OrderDTO;
import services.order.service.entity.Order;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order toEntity(OrderDTO orderDTO);
    @InheritInverseConfiguration
    OrderDTO toDTO(Order order);
    List<Order> toEntity(List<OrderDTO> orderDTOs);
    List<OrderDTO> toDTO(List<Order> orders);

}
