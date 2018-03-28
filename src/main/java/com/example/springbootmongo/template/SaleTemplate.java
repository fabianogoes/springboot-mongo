package com.example.springbootmongo.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.example.springbootmongo.model.Sale;
import com.example.springbootmongo.util.RandomUtil;

import java.time.LocalDateTime;

public class SaleTemplate implements TemplateLoader {

    public static final String SALE_001 = "SALE_001";
    public static final String SALE_002 = "SALE_002";
    public static final String SALE_003 = "SALE_003";

    class SaleFields {
        public static final String ID = "id";
        public static final String CREATEDATE = "createDate";
        public static final String DESCRIPTION = "description";
        public static final String TOTAL = "total";
    }

    @Override
    public void load() {
        Fixture.of(Sale.class).addTemplate(SALE_001, new Rule(){
            {
                add(SaleFields.ID, SALE_001);
                add(SaleFields.DESCRIPTION, "Sale Test "+SALE_001);
                add(SaleFields.CREATEDATE, LocalDateTime.now());
                add(SaleFields.TOTAL, RandomUtil.bigDecimalRandom());
            }
        }).addTemplate(SALE_002, new Rule(){
            {
                add(SaleFields.ID, SALE_002);
                add(SaleFields.DESCRIPTION, "Sale Test "+SALE_002);
                add(SaleFields.CREATEDATE, LocalDateTime.now());
                add(SaleFields.TOTAL, RandomUtil.bigDecimalRandom());
            }
        }).addTemplate(SALE_003, new Rule(){
            {
                add(SaleFields.ID, SALE_003);
                add(SaleFields.DESCRIPTION, "Sale Test "+SALE_003);
                add(SaleFields.CREATEDATE, LocalDateTime.now());
                add(SaleFields.TOTAL, RandomUtil.bigDecimalRandom());
            }
        });
    }

}
