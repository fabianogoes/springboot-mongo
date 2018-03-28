package com.example.springbootmongo;

import com.example.springbootmongo.config.MongoConfiguration;
import com.example.springbootmongo.config.SwaggerConfiguration;
import com.example.springbootmongo.model.Sale;
import com.example.springbootmongo.repository.SaleRepository;
import com.example.springbootmongo.template.SaleTemplate;
import com.example.springbootmongo.util.RandomUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		new SpringApplicationBuilder(
				Application.class,
				SwaggerConfiguration.class,
				MongoConfiguration.class
		).run(args);
	}

	@Bean
	CommandLineRunner run(SaleRepository repository){
		return (args -> {
			repository.deleteAll();
            Arrays.asList(
                    Sale.builder().description(SaleTemplate.SALE_001).total(RandomUtil.bigDecimalRandom()).build(),
                    Sale.builder().description(SaleTemplate.SALE_001).total(RandomUtil.bigDecimalRandom()).build(),
                    Sale.builder().description(SaleTemplate.SALE_001).total(RandomUtil.bigDecimalRandom()).build(),
                    Sale.builder().description(SaleTemplate.SALE_002).total(RandomUtil.bigDecimalRandom()).build(),
                    Sale.builder().description(SaleTemplate.SALE_002).total(RandomUtil.bigDecimalRandom()).build(),
                    Sale.builder().description(SaleTemplate.SALE_003).total(RandomUtil.bigDecimalRandom()).build(),
                    Sale.builder().description(SaleTemplate.SALE_003).total(RandomUtil.bigDecimalRandom()).build(),
                    Sale.builder().description(SaleTemplate.SALE_003).total(RandomUtil.bigDecimalRandom()).build(),
                    Sale.builder().description(SaleTemplate.SALE_003).total(RandomUtil.bigDecimalRandom()).build(),
                    Sale.builder().description(SaleTemplate.SALE_003).total(RandomUtil.bigDecimalRandom()).build()
            ).forEach(repository::save);
			repository.findAll().forEach(System.out::println);
		});
	}

}
