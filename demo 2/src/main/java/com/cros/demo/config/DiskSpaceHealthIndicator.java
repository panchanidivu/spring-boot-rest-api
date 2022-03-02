package com.cros.demo.config;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.system.DiskSpaceHealthIndicatorProperties;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Health.Builder;
import org.springframework.context.annotation.Configuration;
@Configuration
public class DiskSpaceHealthIndicator extends AbstractHealthIndicator {
    

	private final DiskSpaceHealthIndicatorProperties properties;

	@Autowired
	public DiskSpaceHealthIndicator(DiskSpaceHealthIndicatorProperties properties) {
		this.properties = properties;
	}

	@Override
	protected void doHealthCheck(Health.Builder builder) throws Exception {
		File path = this.properties.getPath();
		long diskFreeInBytes = path.getFreeSpace();
		// if(diskFreeInBytes < this.properties.getThreshold()) {
        //     builder.down().withDetail("freeSpace", diskFreeInBytes);
        // }
		// else {
        //     builder.up().withDetail("freeSpace", diskFreeInBytes);
        // }
		// }
		builder.withDetail("total", path.getTotalSpace())
				.withDetail("free", diskFreeInBytes)
				.withDetail("threshold", this.properties.getThreshold());
	}

    
    
}
