/*
 * Copyright 2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.cloud.stream.binder.kafka.streams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.cloud.stream.binder.kafka.properties.KafkaBinderConfigurationProperties;
import org.springframework.cloud.stream.binder.kafka.provisioning.KafkaTopicProvisioner;
import org.springframework.cloud.stream.binder.kafka.streams.properties.KafkaStreamsBinderConfigurationProperties;
import org.springframework.cloud.stream.binder.kafka.streams.properties.KafkaStreamsExtendedBindingProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Soby Chacko
 */
@Configuration
public class KTableBinderConfiguration {

	@Autowired
	private KafkaProperties kafkaProperties;

	@Autowired
	private KafkaStreamsExtendedBindingProperties kafkaStreamsExtendedBindingProperties;

	@Bean
	public KafkaTopicProvisioner provisioningProvider(KafkaBinderConfigurationProperties binderConfigurationProperties) {
		return new KafkaTopicProvisioner(binderConfigurationProperties, kafkaProperties);
	}

	@Bean
	public KTableBinder kTableBinder(KafkaStreamsBinderConfigurationProperties binderConfigurationProperties,
									KafkaTopicProvisioner kafkaTopicProvisioner,
									KafkaStreamsBindingInformationCatalogue KafkaStreamsBindingInformationCatalogue) {
		KTableBinder kStreamBinder = new KTableBinder(binderConfigurationProperties, kafkaTopicProvisioner,
				KafkaStreamsBindingInformationCatalogue);
		return kStreamBinder;
	}
}
