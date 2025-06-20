package pl.mlcr.protector.warehouse.infracstructure.inbound.udp;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("warehouse.udp")
public class UdpProperties {
    private String host;
}
